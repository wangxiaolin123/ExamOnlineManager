package com.exam.controller;

import com.exam.dao.ExamStudentDao;
import com.exam.domain.Exam;
import com.exam.service.StudentService;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.ResultModel;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {


    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/examList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel getExamList(HttpServletRequest request) {
        String stuNumber = request.getParameter("stuNumber");
        String state = request.getParameter("state");
        //判空
        if (stuNumber == null)
            return null;
        try {
            ResultModel res = studentService.getExamsByNumberState(stuNumber, state);
           /* List<Exam> list=(List<Exam>)res.getData();
            System.out.println("______________________________________");
            for(Exam e:list){
                System.out.println(e.toString());
            }
            System.out.println("______________________________________");*/
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/downloadPaper.do")
    public void downloadPaper(@RequestParam(value = "examName") String examName, @RequestParam(value = "examPath") String examPath, HttpServletResponse response) {

        String suffix = examPath.substring(examPath.lastIndexOf("."));
        String ftpFileName = examPath.substring(examPath.lastIndexOf("/") + 1);
        String ftpPath = examPath.substring(0, examPath.lastIndexOf("/") + 1);
        String downFileName = examName + suffix;

        //System.out.println("学生下载 文件名"+downFileName+"后缀名"+suffix);

        System.out.println(downFileName);
        response.setCharacterEncoding("UTF-8");//设置字符集
        response.setContentType("multipart/form-data;charset=UTF-8");//设置下载内容类型

        String headerValue = "attachment;";
        headerValue += " filename=\"" + encodeURIComponent(downFileName) + "\";";
        headerValue += " filename*=utf-8''" + encodeURIComponent(downFileName);
        response.setHeader("Content-Disposition", headerValue);
        //response.setHeader("Content-Disposition", "attachment;fileName="+downFileName);//设置下载方式
        try {
            OutputStream output = response.getOutputStream();//获取文件输出流
            FtpUtil.downloadFile(ftpPath, ftpFileName, output);//调用ftp方法
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/upAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel upAnswer(@RequestParam(value = "examID") Integer examID, @RequestParam(value = "stuNumber") String stuNumber, @RequestParam(value = "upFile", required = false) MultipartFile file) {

        if (file != null) {
            ResultModel res = studentService.upAnswer(file, examID, stuNumber);
            return res;
        }
        return ResultModel.build(500, "试卷上传失败");
    }


    public static String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;

        }
    }
}
