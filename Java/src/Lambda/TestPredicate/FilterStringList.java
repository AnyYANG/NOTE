package Lambda.TestPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Ly on 2018/9/11.
 */
public class FilterStringList {
    public static void main(String args[]) {
        List<String> listS = Arrays.asList("134dff", "Asadf32", "B", "aliuyang", "", "12");
        List<String> result = filter(listS, FilterStringList::testEmpty);
        List<String> resultlength = filter(listS, FilterStringList::testLength);
        List<String> resultliu = filter(listS, FilterStringList::testConLiu);

        List<String> resultNow = filter(listS, (String s)->s.isEmpty());
        List<String> resultlengthNow = filter(listS,(String s)->s.length()>5);
        List<String> resultliuNow = filter(listS,(String s)->s.contains("liu"));
        log(result);
        log(resultlength);
        log(resultliu);
    }

    public static boolean testEmpty(String test) {
        return test.isEmpty();
    }

    public static boolean testLength(String test) {
        return test.length() > 3;
    }

    public static boolean testConLiu(String test) {
        return test.contains("liu");
    }

    /**
     * @param stringList
     * @param predicate
     * @return
     */
    public static List<String> filter(List<String> stringList, Predicate<String> predicate) {
        List<String> resultList = new ArrayList<String>();
        for (String str : stringList) {
            if (!predicate.test(str)) {
                resultList.add(str);
            }
        }
        return resultList;
    }

    public static void log(List<String> stringList) {
        for (String s : stringList) {
            System.out.println("SystemOutLine:" + s);
        }
    }
}
