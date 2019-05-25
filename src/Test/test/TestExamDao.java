import com.exam.dao.ExamDao;
import com.exam.domain.Exam;
import com.exam.domain.ExportStudentInfo;
import com.exam.domain.Student;
import com.exam.service.ExamService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestExamDao {

    @Resource
    private ExamDao examDao;

    @Test
    public void te1(){

        Exam exam= new Exam();
        exam.setTeaNumber("1010120002");
        exam.setIsAuto(true);
        exam.setExamName("大数据开发");
        exam.setStartTime(new Date());
        exam.setEndTime(new Date());
        exam.setState("before");
        exam.setIsManager(false);
        try {
            System.out.println(examDao.insert(exam));
            //exam = examDao.queryExamById(1);
            System.out.println(exam.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
