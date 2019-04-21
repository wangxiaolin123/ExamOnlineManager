import com.exam.dao.ExamDao;
import com.exam.dao.ExamStudentDao;
import com.exam.domain.Exam;
import com.exam.domain.ExamStudent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test0421 {

    @Resource
    private ExamDao examDao;
    @Resource
    private ExamStudentDao examStudentDao;

    @Test
    public void ExamDaoTest(){

        Exam exam=new Exam();
        exam.setExamName("updat3");
        exam.setStartTime(new Date());
        exam.setEndTime(new Date());
        exam.setExamPath("/test/1");
        exam.setisAuto(false);
        exam.setState("before");
        exam.setTeaNumber("1610121165");

        try {

            //examDao.insert(exam);

            //examDao.delete(3);//删除id为3的记录

            //exam.setExamID(1);

            //examDao.modify(exam);

            /*List<Exam> list=examDao.queryExamsByTeaNumber("1610121165","end");
            for (Exam e:list)
                System.out.println(e.toString());*/

           /* Exam exam1=examDao.queryExamsById(1);
            System.out.println(exam1.toString());*/

           //examDao.updateState(1,"before");

            List<Exam> list=examDao.getExamsByState("starting");
            for (Exam e:list)
                System.out.println(e.toString());

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
}
