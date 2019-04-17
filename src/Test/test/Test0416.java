import com.exam.domain.User;
import com.exam.service.UserService;
import com.exam.utlis.MD5_Encoding;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test0416 {

    private static Logger logger=Logger.getLogger(Test0416.class);

    @Resource
    private UserService userService;


    @Test
    public void Test1(){
        System.out.println(userService);
        User user1=new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setType(1);
        User user=userService.getUser(user1);
        logger.info(user.getUsername());

    }

    @Test
    public void Test0417() {
        String string = "王晓林";

        MD5_Encoding md5_encoding = new MD5_Encoding();
        String str = md5_encoding.getMD5ofStr(string);
        System.out.println(str);
    }
}
