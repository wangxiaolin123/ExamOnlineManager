import com.exam.dao.ExamStudentDao;
import com.exam.domain.Exam;
import com.exam.domain.ExamStudent;
import com.exam.service.StudentService;
import com.exam.utlis.ResultModel;
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
public class TestExamStudent {
    @Resource
    private ExamStudentDao examStudentDao;

    @Test
    public void  testStudentService(){

      try{
          List<String> list=examStudentDao.getStuNumberByExam(1);
          for(String e:list){
              System.out.println(e);
          }
      }catch (SQLException e){
          e.printStackTrace();
      }


    }

    @Test
    public void  testExam(){

        try{
            List<Map<String,String>> list;
            list = examStudentDao.getNumPathByExamID(1);
            for(Map<String,String> e:list){
                System.out.println(e);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void  test3(){

        try{
            ExamStudent es=new ExamStudent();
            es.setExamID(6);
            es.setStuNumber("1610121165");
            examStudentDao.insert(es);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
