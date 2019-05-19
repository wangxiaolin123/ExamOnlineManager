import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.exception.UserException;
import com.exam.service.TeacherService;
import com.exam.service.UserService;
import com.exam.utlis.ResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestTeacherService {


    @Resource
    private TeacherService teacherService;

    @Test
    public void TestImport() {

        Student student = new Student();
        student.setStuNumber("1610121152");
        student.setStuName("测试");
        student.setClassID(2);
        ResultModel res = teacherService.importStudent(student);
    }

    @Test
    public void TestUpdateStudnet() {
        Student student = new Student();
        student.setStuID(144);
        student.setStuName("姓名");
        student.setStuNumber("学号");
        student.setClassID(9);
        teacherService.updateStudent(student);
    }

    @Test
    public void TestdeleteStudnet() {

        teacherService.deleteStudent("1610121180");
    }

}
