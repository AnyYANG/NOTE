package Steam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

/**
 * Created by  liuyang
 * 2018/9/27    11:41
 * Steam
 * All Right Reserved by liuyang.
 **/

public class FindAndMatch {
    public static void main(String args[]) {
         //match1();
        //  match2();
        // match3();
       // match4();
       // match5();
        match6();
    }

    /**
     *  求数组每个值的平方能被2整除的值
     */
    public static void match1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        list.stream().map(t -> t * t).filter(t -> t % 2 == 0).forEach((t) -> System.out.println(t));
    }

    public static void match2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> list2 = Arrays.asList(10, 20, 30, 40, 50, 60, 70);
        List<int[]> result = list.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j})).collect(toList());
        result.stream().forEach(
                t -> {
                    Arrays.stream(t).forEach(x -> System.out.print(x + " "));
                    System.out.println();
                }
        );
    }

    public static void match3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.println(t));
    }

    public static void match4() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        //Optional<Integer> integerOptional=  list.stream().filter(t->t%2==0).findAny();
        Optional<Integer> integerOptional = list.stream().filter(t -> t % 2 == 0).findFirst();
        integerOptional.ifPresent(t -> System.out.println(t));
    }

   //循环计算
    public static void match5() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        //累乘  1*1*2*3*4
        int result = list.stream().reduce(1, (a, b) -> a * b);
        Optional<Integer> optional =list.stream().reduce( (a, b) -> a * b);
        System.out.println(result);
        optional.ifPresent(a-> System.out.println(a));
    }
     //筛选最小
    public static void match6() {
        List<Integer> list = Arrays.asList(132, 23, 443, 324, 35, 26, 17);
        Optional<Integer> result = list.stream().reduce(Integer::min);
        System.out.println(result.get());
    }

}
