package Lambda.LambdaPredicateCompounding;

import Lambda.AppleCase.Apple;
import org.junit.jupiter.api.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Ly on 2018/11/26.
 * Lambda 表达式的复合
 */
public class TestCompounding {
    /**
     * @BeforeAll 只能用在静态方法前。
     */
    List<Apple> appleList = null;

    @BeforeAll
    public static void runbeforestatic() {
        System.out.println("init ..");
    }

    /**
     * 用于非静态方法的测试前。
     */
    @BeforeEach
    public void runbefore() {
        appleList = Apple.getAppleList();
    }

    /**
     * 逆序
     * 按照重量 正向反向输出排序
     */
    @Test
    public void testReversed() {
        //输出增序
        appleList.sort(Comparator.comparing(Apple::getWeight));
        appleList.forEach(apple -> System.out.println(apple.getColor() + " " + apple.getWeight()));
        //输出减序
        appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
        appleList.forEach(apple -> System.out.println(apple.getColor() + " " + apple.getWeight()));

    }

    /**
     * 比较器链
     */
    @Test
    public void testthenComparing() {
        //输出减序   然后苹果根据产地排序
        appleList.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getOrigin));
        appleList.forEach(apple -> System.out.println(apple.getOrigin() + " " + apple.getColor() + " " + apple.getWeight()));

    }

    /**
     *  谓词复合
     *  negateandor
     */
    @Test
    public void negateandorTest() {
        //筛选 绿苹果
        Predicate<Apple> greenapple = (a)->a.getColor().equals("green");
        //筛选非绿色苹果
        Predicate<Apple> nagetagreenapple = greenapple.negate();
        //筛选非绿大于五十克的苹果
        Predicate<Apple> weigthnagetagreenapple= nagetagreenapple.and(apple -> apple.getWeight()>50);
        //steam是流， 可以通过流来筛选    流接下来会介绍 再此只是讲predicate的思想
        appleList.stream().filter(weigthnagetagreenapple);
    }


    @AfterEach
    public void runaftereach() {

    }

    @AfterAll
    public static void runafterall() {
        System.gc();
    }
}
