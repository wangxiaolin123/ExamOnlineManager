import com.exam.dao.ClassInfoDao;
import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.ClassInfo;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.service.AdminTService;
import com.exam.service.UserService;
import com.exam.utlis.MD5_Encoding;
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
public class TestClassInfo {


    @Resource
    private ClassInfoDao classInfoDao;


    @Test
    public void Test1(){
        try {
            List<ClassInfo> list=classInfoDao.queryClassInfos();
            for(ClassInfo c:list)
                System.out.println(c.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test2(){
        try {
            ClassInfo classInfo=new ClassInfo();
            classInfo.setClassName("中文");
            classInfo.setClassID(7);
          //classInfoDao.modify(classInfo);
            classInfoDao.delete(8);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
