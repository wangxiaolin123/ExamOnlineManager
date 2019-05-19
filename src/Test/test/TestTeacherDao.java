import com.exam.dao.TeacherDao;
import com.exam.domain.Exam;
import com.exam.domain.Teacher;
import com.exam.service.StudentService;
import com.exam.utlis.ResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestTeacherDao {
    @Resource
    private TeacherDao teacherDao;

    @Test
    public void  test1(){


        List<Teacher> list= null;
        try {
            list = teacherDao.getByIsAdmin(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()==0)
        System.out.println(list);
        for(Teacher e:list){
            System.out.println(e.toString());
        }

    }
}
