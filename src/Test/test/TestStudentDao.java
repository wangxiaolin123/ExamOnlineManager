import com.exam.dao.StudentDao;
import com.exam.domain.Student;
import com.exam.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestStudentDao {
    @Resource
    private StudentDao studentDao;

    @Test
    public void  testStudentDao(){

      /*  ResultModel res=studentService.getExamsByNumberState("1610121165","");
        List<Exam> list=(List<Exam>)res.getData();
        for(Exam e:list){
            System.out.println(e.toString());
        }*/
      List<Student> studentList=new ArrayList<>();

        List<Student> list=new ArrayList<>();
        for(int i=0;i<5;i++){
           Student student=new Student();
           student.setStuName("姓名"+i);
           student.setStuNumber("学号"+i);
           student.setClassID(i);
            list.add(student);
        }

        for(int i=0;i<list.size();i++){
            Student student=list.get(i);
            Student student1=null;
            try {
                student1= studentDao.getStudentByStuNumber(student.getStuNumber());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(student1==null){
                studentList.add(student);
                System.out.println(student.getStuNumber()+"不存在");

            }else {
                System.out.println(student.getStuNumber()+"存在");
            }

        }

    }

    @Test
    public void testGetALLStudents(){
        try {
            List<Student> list=studentDao.getAllStudents();
            for(Student student:list){
                System.out.println(student.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testgetStudentID(){
        try {

            Student student=studentDao.getStudnetByID(2);
                System.out.println(student.toString());

        } catch (SQLException e) {
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
           studentDao.deleteByStuNumbers(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGetClassID(){
        try {
            List<Student> list=studentDao.queryStudentsByClass(1);
            for(Student s:list)
                System.out.println(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
