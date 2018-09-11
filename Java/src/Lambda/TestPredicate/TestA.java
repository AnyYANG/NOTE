package Lambda.TestPredicate;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Ly on 2018/9/11.
 */
public class TestA {
    public static void main(String args[]) {
        List<Student> listStudent = Arrays.asList(new Student("a0",59),new Student("A2",98),new Student("b3",70));
        EmptyFilterObject<Student> filter= (Student student)-> student.score>60;
        List<Student> resultList=predicateStudent(listStudent,filter);
        log(resultList);
    }

    public static List<Student> predicateStudent(List<Student> students, EmptyFilterObject filterObject) {
        List<Student> listresult = new ArrayList<Student>();
        for (Student stu : students) {
            if (filterObject.test(stu)) {
                listresult.add(stu);
            }
        }
        return listresult;
    }

    public static void log(List<Student> studentList){
        for (Student student : studentList){
            System.out.println(student.getName()+":"+student.getScore());
        }
    }
}
