package Lambda.TestPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Ly on 2018/9/11.
 */
public class EmptyFilterStringList {
    public static void main(String args[]) {
        List<String> listS = Arrays.asList("1", "A", "B", "asdf", "", "12");
        Predicate<String> predicate = (String s) -> !s.isEmpty();
        List<String> result = filter(listS,predicate);
        log(result);
    }

    public static List<String> filter(List<String> stringList, Predicate<String> predicate) {
        List<String> resultList = new ArrayList<String>();
        for (String str : stringList) {
            if (!str.isEmpty()) {
                resultList.add(str);
            }
        }
        return resultList;
    }
    public  static void log(List<String> stringList){
        for(String s:stringList){
            System.out.println("SystemOutLine:"+s);
        }
    }
}
