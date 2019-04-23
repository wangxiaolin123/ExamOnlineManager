import com.exam.domain.User;
import com.exam.exception.UserException;
import com.exam.service.UserService;
import com.exam.utlis.ResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestLoginService {


    @Resource
    private UserService userService;

    @Test
    public void TestLogin(){
        User loginUser=new User();
        loginUser.setUsername("1010120002");
        loginUser.setPassword("卢老师");
        loginUser.setType(1);
        loginUser.setIp("127.0.0.1");

        try {
            ResultModel login = userService.isLogin(loginUser);
            System.out.println(login.getData().toString());
        } catch (UserException e) {
            e.printStackTrace();
        }

    }
}
