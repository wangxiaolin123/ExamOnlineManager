import com.exam.dao.ExamDao;
import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.*;
import com.exam.service.AdminTService;
import com.exam.service.ExamService;
import com.exam.service.UserService;
import com.exam.utlis.MD5_Encoding;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ExamServiceTest {

    private static Logger logger=Logger.getLogger(ExamServiceTest.class);

    @Resource
    private ExamService examService;
    @Resource
    private ExamDao examDao;

    @Test
    public void test(){

        Map<String,Object> mapInfo=examService.GetExamming("1610121165");


        String teaNumber= (String) mapInfo.get("teaNumber");

        List<Student> students= (List<Student>) mapInfo.get("students");

        System.out.println(teaNumber);
        for (Student s:students)
            System.out.println(s.toString());
    }


    @Test
    public void te1(){

        Exam exam= null;
        try {
            exam = examDao.queryExamById(2);
            System.out.println(exam.toString());
            boolean f =examService.CleanExam(exam);
            System.out.println(f);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExportStudentInfo(){
        List<ExportStudentInfo> list=examService.ExportExamInfo(1);
        for (ExportStudentInfo s:list)
            System.out.println(s.toString());
    }

}
