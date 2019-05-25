package com.exam.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.domain.ClassInfo;
import com.exam.domain.Exam;
import com.exam.domain.ExportStudentInfo;
import com.exam.domain.Student;
import com.exam.exception.ExcelException;
import com.exam.service.ClassInfoService;
import com.exam.service.ExamService;
import com.exam.service.TeacherService;
import com.exam.service.UserService;
import com.exam.utlis.ExcelUtil;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.ResultModel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;
    @Resource
    private ExamService examService;
    @Resource
    private UserService userService;
    @Resource
    private ClassInfoService classInfoService;

    @RequestMapping("/mainPage.do")
	public String show() {
		return "teacher/t_main";
	}


    @RequestMapping("/studentmanager.do")
    public String studentmanager() {
        return "teacher/studentmanager";
    }
    @RequestMapping("/examming.do")
    public String examing(@RequestParam(value = "examID")Integer examID,HttpServletRequest request) {

        HttpSession session=request.getSession();
        //System.out.println("访问考试进行页"+examID);
        Exam exam=teacherService.getExamByID(examID);
        session.setAttribute("examInfo",exam);
        return "teacher/exam_info";
    }
    
    @RequestMapping("/exammanager.do")
    public String getExamList(HttpServletRequest request) {

        HttpSession session=request.getSession();
        String teaNumber=request.getParameter("teaNumber");
        //判空
        if (teaNumber != null){

            List<Exam> exams = teacherService.queryExamByTeacher(teaNumber);
            session.setAttribute("examList",exams);
            return "teacher/exammanager";
        }
        else {
            return "error/error";
        }
    }


    @RequestMapping(value="/addExam.do", method= RequestMethod.POST)
    @ResponseBody
    public ResultModel addExam(Exam exam) {

        //System.out.println(exam.toString());
        try {
           ResultModel res=teacherService.addExam(exam);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultModel.build(500, "创建考试失败！");
    }

    @RequestMapping(value="/updateExam.do", method=RequestMethod.POST)
    @ResponseBody
    public ResultModel updateExam(Exam exam) {
        System.out.println(exam.toString());
        try {
            ResultModel res=teacherService.modifyExam(exam);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "更新考试失败，请联系管理员！");
        }

    }


    @RequestMapping("/forceBegin.do")
    @ResponseBody
    public ResultModel forceBegin(@Param(value = "examID")Integer examID){

        //System.out.println("强制考试"+examID);

        Exam exam=teacherService.getExamByID(examID);

        examService.BeginExam(exam);

        return ResultModel.ok();
    }

    @RequestMapping("/deleteExam.do/{examID}")
    @ResponseBody
    public ResultModel removeExam(@PathVariable Integer examID) {
        try {

            Exam exam=teacherService.getExamByID(examID);
            ResultModel res=null;
            if(exam.getState().equals("before")){
                res= teacherService.deleteExam(examID);
            }else if(exam.getPack()){
                examService.CleanExam(exam);
                res=ResultModel.build(200,"考试已经清理完毕");

            }else {
                res=ResultModel.build(500,"考试已经开始或者考试信息未下载不可清理！");
            }

            //返回数据
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "删除考试失败，请联系管理员！");
        }
    }


    @RequestMapping(value = "/unBindIp.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel unBindIp(@Param(value = "stuNumber")String stuNumber,@Param(value = "examID")Integer examID){

        //System.out.println("将要重置的学号"+stuNumber);

        //判断学生是否未本次考试成员

        //修改学生用户状态
        ResultModel rs=teacherService.unBindIp(stuNumber,examID);

        return null;
    }

    //文件操作

    @RequestMapping(value = "/upPaper.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel upPaper(@RequestParam(value = "examID")Integer examID, @RequestParam(value = "upFile", required = false) MultipartFile file){

        if (file != null){
            ResultModel res=teacherService.upPaper(file,examID);
            return res;
        }
        return ResultModel.build(500,"试卷上传失败");
    }


    @RequestMapping(value = "/downloadZip.do")
    public void downZip(@RequestParam(value = "examName") String examName, @RequestParam(value = "examID")Integer examID, HttpServletResponse response){

        //System.out.println(examID+examName);

        String dirPath="/public/"+examID+"/";

        Exam exam=new Exam();
        exam.setExamID(examID);
        exam.setPack(true);
        teacherService.modifyExam(exam);

        List<Map<String,String>> mapList=teacherService.getStuNumberFile(examID);

        FtpUtil.zipDownloadFile(response,examName,dirPath,mapList);

    }

    @RequestMapping(value = "/downloadUpInfo.do",method=RequestMethod.POST)
    public void downloadUpInfo(@RequestParam(value = "examName") String examName, @RequestParam(value = "examID")Integer examID, HttpServletResponse response){
        //导出提交信息
        List<ExportStudentInfo> list=examService.ExportExamInfo(examID);
        for(ExportStudentInfo e:list)
            System.out.println(e.toString());
        if(list!=null){
            try {
                ExcelUtil.writeExcel(response,list,examName+"学生提交情况","sheet1",ExcelTypeEnum.XLSX,ExportStudentInfo.class);

            } catch (Exception e) {
                System.out.println("excel导出错误");
                e.printStackTrace();
            }
        }
    }


    @RequestMapping(value = "/importAllStudent.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel importAllStudent( @RequestParam("file") MultipartFile multipartFile){

        if (multipartFile != null){
            //System.out.println("file成功传入");
            ResultModel res=teacherService.importAllStudent(multipartFile);
            return res;
        }
        return ResultModel.build(500,"文件导入失败");

    }

    @RequestMapping(value = "/importStudent.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel importStudent(Student student){
        System.out.println(student);
        if (student != null){

            ResultModel res=teacherService.importStudent(student);
            return  ResultModel.ok();
        }
        return ResultModel.build(500,"学生信息为空");

    }


    @RequestMapping(value = "/getStudents.do",method = { RequestMethod.GET })
    @ResponseBody
    public JSONObject getstudents(@RequestParam(value = "limit",required = false)Integer limit,
                        @RequestParam(value = "offset",required = false)Integer offset,
                        @RequestParam(value = "search",required = false)String search,
                        HttpServletResponse response){

        //System.out.println("数据请求服务");
        //System.out.println("limit:"+limit+" offset:"+offset+" search:"+search);

        JSONObject object = new JSONObject();

        List<Student> list=new ArrayList<>();

        list=teacherService.getAllStudents();

        object.put("total",list.size());
        object.put("totalNotFiltered",list.size());
        object.put("rows",list);


       // System.out.println(object);
        //System.out.println("json处理完毕");

        return object;
       /* try {
            response.getWriter().write(String.valueOf(object));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @RequestMapping(value = "/updateStudent.do",method = { RequestMethod.POST })
    @ResponseBody
    public ResultModel updateStudent(Student student){

        if(student!=null){
            ResultModel res=teacherService.updateStudent(student);
            return res;
        }
        return ResultModel.build(500,"修改学生信息失败");
    }

    @RequestMapping(value = "/deleteStudent.do",method = { RequestMethod.GET })
    @ResponseBody
    public ResultModel deleteStudent(@Param(value = "stuNumber")String stuNumber){

        if(stuNumber!=null && stuNumber.length()>0){
            ResultModel res=teacherService.deleteStudent(stuNumber);
            return res;
        }
        return ResultModel.build(500,"传入学号不合法");
    }

    @RequestMapping(value = "/deleteStudents.do",method = { RequestMethod.POST })
    @ResponseBody
    public ResultModel deleteStudents(@Param("stuNumbers")String stuNumbers){
        //System.out.println("获取的数据为"+stuNumbers);

        //stuNumbers=stuNumbers.substring(1,stuNumbers.length()-1);

        if(stuNumbers.length()>0){

            JSONArray stuArr=JSON.parseArray(stuNumbers);
            List<String> stuList= new ArrayList<>();

            for(int i=0;i<stuArr.size();i++){
                stuList.add((String) stuArr.get(i));
            }

            ResultModel res=teacherService.deleteStudents(stuList);
            return res;
        }
        return ResultModel.build(500,"传入学号不合法");
    }


    @RequestMapping(value = "/getClassSelects.do",method = { RequestMethod.GET})
    @ResponseBody
    public ResultModel getClassSelects(){

       ResultModel res=classInfoService.getAllClassInfo();
       if(res!=null)
           return res;

        return ResultModel.build(500,"获取班级信息失败");
    }


    @RequestMapping(value = "/importAdditionStudent.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel importAdditionStudent(@Param(value = "examID")Integer examID,
                                             @Param(value = "stuNumber")String stuNumber,
                                             @Param(value = "stuName")String stuName){
        System.out.println(examID+stuNumber+stuName);

            ResultModel res=examService.addAdditionStudent(examID,stuNumber,stuName);
            return  res;

    }
}
