import com.exam.domain.Exam;
import com.exam.service.StudentService;
import com.exam.utlis.ResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestStudent {
    @Resource
    private StudentService studentService;

    @Test
    public void  testStudentService(){

        ResultModel res=studentService.getExamsByNumberState("1610121165","");
        List<Exam> list=(List<Exam>)res.getData();
        for(Exam e:list){
            System.out.println(e.toString());
        }

    }
}
