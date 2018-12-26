package Steam.Trade.casea;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Ly on 2018/11/28.
 */

public class StringDemo {

    String [] strarrays = new String[]{"Hello","world"};

    /**  Task
     *  输出不重复的字母  h e l o w r d
     */
    @Test
    public void StringTest() {
       List<String[]> lists = Arrays.stream(strarrays).map((a)->a.split("")).distinct().collect(toList());
    //   lists.stream().forEach((a)-> a.);
    }
}
