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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test0416 {


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

        List<User> users=new ArrayList<>();
        for(int i=0;i<5;i++){
            User user=new User();
            user.setUsername("用户"+i);
            user.setPassword("密码"+i);
            user.setType(3);
            users.add(user);
        }
    }

    @Test
    public void TestNotifyID() {

        try {
            User user = new User();
            user.setUserID(13);
            user.setUsername("哈哈");
            user.setPassword("密码");
            user.setType(3);
            user.setIp("123");
            userDao.notifyByID(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestDeleteUsers(){
        List<String> list=new ArrayList<>();
        list.add(0+"");
        list.add(1+"");
        list.add(2+"");
        list.add(3+"");
        list.add(4+"");

        try {
            userDao.deleteByUserNames(list);
        } catch (SQLException e) {
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
