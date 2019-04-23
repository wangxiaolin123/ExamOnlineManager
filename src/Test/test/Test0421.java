import com.exam.dao.ExamDao;
import com.exam.dao.ExamStudentDao;
import com.exam.dao.StudentDao;
import com.exam.domain.Exam;
import com.exam.domain.ExamStudent;
import com.exam.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test0421 {

    @Resource
    private ExamDao examDao;
    @Resource
    private ExamStudentDao examStudentDao;
    @Resource
    private StudentDao studentDao;


    @Test
    public void ExamDaoTest(){

       /* Exam exam=new Exam();
        exam.setExamName("updat3");
        exam.setStartTime(new Date());
        exam.setEndTime(new Date());
        exam.setExamPath("/test/1");
        exam.setisAuto(false);
        exam.setState("before");
        exam.setTeaNumber("1610121165");*/

        try {

            //examDao.insert(exam);

            //examDao.delete(3);//删除id为3的记录

            //exam.setExamID(1);

            //examDao.modify(exam);

            List<Exam> list=examDao.queryExamsByTeaNumber("1610121165");
            for (Exam e:list){
                System.out.println(e.getEndTime());
            }

           /* Exam exam1=examDao.queryExamsById(1);
            System.out.println(exam1.toString());*/

           //examDao.updateState(1,"before");
/*
            List<Exam> list=examDao.getExamsByState("starting");
            for (Exam e:list)
                System.out.println(e.toString());*/

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void ExamStudentDaoTest(){
        ExamStudent examStudent=new ExamStudent();
        examStudent.setStuNumber("1610121160");
        examStudent.setExamID(6);
        examStudent.setAnswerPath("/b");
        examStudent.setAnswerTime(new Date());

        try {

            //examStudentDao.insert(examStudent);

            //examStudentDao.deleteBySidEid("1610121160",6);

            //examStudentDao.deleteByExamID(2);

            examStudentDao.updateAnswerPathByStuNumber(2,"1610121165","/publicasdawfd",new Date());

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    @Test
    public void StudentDaoTest(){
        Student student=new Student();
        student.setStuID(5);
        student.setStuNumber("1610120002");
        student.setStuName("测试2");
        student.setClassID(4);
        try{

            //studentDao.insert(student);

            //studentDao.deleteByStuNumber("1610120001");

           /*Student student1= studentDao.getStudentByStuNumber("1610120001");
            System.out.println(student1.toString());*/

          /* List<Student> list=studentDao.queryStudentsByClass(1);
           for(Student s:list)
               System.out.println(s.toString());*/

          studentDao.modifyByID(student);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
