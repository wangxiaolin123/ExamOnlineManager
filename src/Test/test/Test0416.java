import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.service.AdminTService;
import com.exam.service.UserService;
import com.exam.utlis.MD5_Encoding;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test0416 {

    private static Logger logger=Logger.getLogger(Test0416.class);

    @Resource
    private UserService userService;
    @Resource
    private UserDao userDao;


    @Test
    public void Test1(){
        System.out.println(userService);
        User user1=new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setType(1);

        logger.info(user1.getUsername());

    }

    @Test
    public void Test0417() {
        String string = "王晓林";

        MD5_Encoding md5_encoding = new MD5_Encoding();
        String str = md5_encoding.getMD5ofStr(string);
        System.out.println(str);
    }

    @Test
    public void TestUserDao() {


        try {
           userDao.deleteByUserName("teacher");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Resource
    private  TeacherDao teacherDao;
    @Test
    public void TestTeacherDao(){

        try{
          /*  Teacher teacher=new Teacher();
            teacher.setTeaNumber("1610120007");
            teacher.setTeaName("教师07");
            teacher.setIsadmin(false);
            System.out.println(teacherDao.insertTeacher(teacher));*/
            teacherDao.deleteByteaNumber("1610120000");
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Resource
    private AdminTService adminTService;

    @Test
    public void TestTeacherManager(){
        Teacher teacher=new Teacher();
        teacher.setTeaID(1);
        teacher.setTeaName("鲁见过");
        teacher.setTeaNumber("1012");
        teacher.setIsadmin(true);
        adminTService.updateTeacher(teacher,"卢老师");
        System.out.println(new MD5_Encoding().getMD5ofStr("卢老师"));

    }


}
