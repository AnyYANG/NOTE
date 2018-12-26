>微信公众号：**[刘洋说](#liuyangspeak)**
>关注可了解更多技术信息。问题或建议，请公众号留言;
>**[如果你觉得[刘洋说]你有帮助，欢迎赞赏](#jump_20)[^1]**

###内容目录

[TOC]
###   Lambda复合谓词
 Java 8的很多函数式接口都有为方便而设计的方法。具体而言，许多函数式接口，比如用
于传递Lambda表达式的Comparator、Function和Predicate都提供了允许你进行复合的方法。
这是什么意思呢？在实践中，这意味着你可以把多个简单的Lambda复合成复杂的表达式。比如，
你可以让两个谓词之间做一个or操作，组合成一个更大的谓词。而且，你还可以让一个函数的结
果成为另一个函数的输入

首先用之前定义的苹果类，苹果有三个属性颜色重量和产地
```java
 public class Apple {
    String color;
    Integer weight;
    String origin;
    public Apple() {
    }

    public Apple(Integer  weight) {
        this.weight=weight;
    }
    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer  getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static List<Apple> getAppleList(){
        return  Arrays.asList(new Apple("green",51),new Apple("green",50),new Apple("red",60),new Apple("green",45));
    }
    public static void log(List<String> stringList) {
        for (String s : stringList) {
            System.out.println("SystemOutLine:" + s);
        }
    }
}
 
 
```
然后分别是用  **not**, **and**  来组成多条件的筛选

```java
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

```
可以看到在negateandorTest() 方法的案例中谓词复合是可以灵活筛选出多种条件，构建更复杂的表达式，但读起来仍然和问题的陈述差不多！请注意， and和or方法是按照在表达式链中的位置，从左向右确定优先级的。因此， a.or(b).and(c)可以看作(a || b) && c。

###  拓展Junit5的注解和JUINT4的注解对比说明（括号内JUINT4的注解）
* @BeforeAll（BeforeClass） 只执行一次，执行时机是在所有测试和 @BeforeEach 注解方法之前。 
* @BeforeEach(Before) 在每个测试执行之前执行。
* @Test(Test) 被测试的方法 
* @AfterEach(After) 在每个测试执行之后执行。 
* @AfterAll(AfterClass)只执行一次，执行时机是在所有测试和 @AfterEach 注解方法之后。
测试执行顺序也是按照从上到下，依次执行。同时还有一些其他常见注解帮助测试。在此不再一一赘述。


