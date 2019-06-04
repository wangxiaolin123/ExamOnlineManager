import com.exam.utlis.TimeJudge;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJudgeTime {

    @Test
    public void testJude(){
        boolean flag=false;

        Date date=new Date();
        String string = "2019-06-04 17:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date=sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flag=TimeJudge.JudgeBucket(date,15);
        System.out.println(flag);
    }
}
