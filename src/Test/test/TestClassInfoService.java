import com.exam.dao.ClassInfoDao;
import com.exam.domain.ClassInfo;
import com.exam.service.ClassInfoService;
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
public class TestClassInfoService {


    @Resource
    private ClassInfoService classInfoService;


    @Test
    public void Test1(){
        try {
            ResultModel res=classInfoService.getAllClassInfo();
            List<ClassInfo> list= (List<ClassInfo>) res.getData();
            for(ClassInfo c:list)
                System.out.println(c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test2(){

    }





}
