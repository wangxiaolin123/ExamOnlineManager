import com.exam.domain.Exam;
import com.exam.service.StudentService;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.ResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestFTP {

    @Test
    public void testZip(){
        List<Map<String,String>> mapList =new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("stuNumber","1610121165");
        map.put("answerPath","/public/1/1556874713561545495.txt");
        Map<String,String> map1=new HashMap<>();
        map1.put("stuNumber","1610121180");
        map1.put("answerPath","/public/1/1556874656712260146.txt");
        mapList.add(map);
        mapList.add(map1);
        HttpServletResponse response=null;
        FtpUtil.zipDownloadFile(response,"test","/public/1/",mapList);
    }

    @Test
    public void testDelete(){

        //删除考试文件信息
        FtpUtil.deleteDirectory("/public/","");

    }
}
