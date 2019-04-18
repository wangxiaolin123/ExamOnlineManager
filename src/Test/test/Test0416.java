import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.exception.AdminException;
import com.exam.exception.UserException;
import com.exam.service.AdminService;
import com.exam.service.UserService;
import com.exam.service.UserServiceImpl;
import com.exam.utlis.MD5_Encoding;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

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
    public void Test0418() {


        try {
            User loginUser=new User();
            loginUser.setUsername("1610121165");
            loginUser.setPassword("王晓林");
            loginUser.setType(1);
            loginUser.setIp("tset");
            userService.isLogin(loginUser);
            System.out.println(userDao.getUserByName("1610121165"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Resource
    private AdminService adminService;
    @Test
    public void TestTeacherDaoAndAdminService(){

        try{
            List<Teacher> teacherList=adminService.getAllTeachers();
            for(Teacher teacher:teacherList){
                System.out.println(teacher.toString());
            }
        }catch (AdminException e)
        {
            e.printStackTrace();
        }



    }

}
