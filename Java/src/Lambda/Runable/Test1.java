package Lambda.Runable;

import Lambda.AppleCase.Apple;

import java.util.*;
import java.util.function.*;

public class Test1 {
    public static void main(String args[]) {
        /**
         List<String> list = Arrays.asList("green", "red", "blue", "dark");
         List<Apple>  result = getInstantList(list,Apple::new);
         log(result,(Apple a)->System.out.println(a.getColor()+":"+a.getWeight()));
         **/
      //  ComparatorLamada();
        HashMap<String ,Integer> map =   new HashMap<String,Integer>();
        map.put("a",12);
        map.put("b",23);
        map.put("c",63);
        map.put("d",23);
        biFunctionInstance(map);
    }

    public static void TestLambdaThread() {
        Thread thread = new Thread(() -> System.out.println("helloworld"));
        thread.run();
        IntFunction function = (int i) -> {
            return i++;
        };
        IntToDoubleFunction int2double = (int i) -> {
            return Double.parseDouble(i + "");
        };
        Function<Integer, Apple> appleFunction = Apple::new;
        appleFunction.apply(10);

    }

    /**
     * lambda的范例
     */
    public void testLambdaCase() {
        //布尔表达式 Predicate<T>函数式接口 用于 接受一个变量，然后返回一个布尔值
        // 判断苹果是否大于40G的拉玛达表达式。
        Predicate<Apple> applePredicate = (Apple a) -> a.getWeight() > 40;
        //消费一个对象 Customer<T>   要求传入一个参数T。 返回值为void
        //输出apple的信息
        Consumer<Apple> appleConsumer = (Apple a) -> System.out.println(a.getColor() + a.getWeight());
        //创建对象 Supplier<T>函数式接口  用于返回对象
        Supplier<Apple> appleSupplier = () -> {
            return new Apple();
        };
        Supplier<Apple> appleSupplier1 = () -> new Apple();
        //Function<T,R> T经过函数运算，得到了结果R
        //获取苹果的质量
        Function<Apple, Integer> weightFunction = Apple::getWeight;
        //BiFunction<T,R,U> 合并两个不同类型的参数，返回一个第三个类型的参数
        //合并两个数
        BiFunction<Integer, Double, String> sum = (Integer a, Double b) -> a * b + "";
        // Comparable<T>   仅有一个参数的时候  括号可以被省略
        Comparable<Apple> appleComparable = a -> a.getWeight().compareTo(10);

    }

    /**
     * lambda的适用范围    使用lamdbad来创建对象
     */
    public void testLambda() {
        List<String> list = Arrays.asList("a", "b", "A", "B");
        BiPredicate<List<String>, String> contains = (List, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains2 = List::contains;
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        BiFunction<String, Integer, Apple> c2 = Apple::new;
        c2.apply("green", 40);
        List<Apple> result = getInstantList(list, Apple::new);
    }

    /**
     * 使用lambda来获取一组对象
     *
     * @param list
     * @param conduct
     * @return
     */
    public static List<Apple> getInstantList(List<String> list, BiFunction<String, Integer, Apple> conduct) {
        List<Apple> resultList = new ArrayList<>();
        for (String str : list) {
            resultList.add(conduct.apply(str, 10));
        }
        return resultList;
    }

    /**
     * 遍历输出一个map
     * @param map
     * @return
     */
    public static List<Apple> biFunctionInstance(Map<String, Integer> map) {

        List<Apple> appleList = new ArrayList<>();
        // BiFunction<String,Integer,Apple> appleBiFunction=Apple::new;
        BiConsumer<String, Integer> appleConsumer = (a, b) -> System.out.println(a + ":" + b);
        map.forEach(appleConsumer);
        //  Apple apple = appleBiFunction.apply()
        return appleList;
    }

    /**
     * 使用lambda来输出对象信息
     *
     * @param apples
     * @param consumer
     */
    public static void log(List<Apple> apples, Consumer<Apple> consumer) {
        for (Apple app : apples) {
            consumer.accept(app);
        }

    }

    /**
     *  lamdba输出苹果列表信息
     */
    public static void ComparatorLamada() {
        Comparator<Apple> appleComparator = Comparator.comparing(a -> a.getWeight());
        List<Apple> appleList = getInstantList(Arrays.asList("green", "red", "blue"), Apple::new);
        appleList.sort(appleComparator);
        log(appleList, a -> System.out.println(a.getColor() + ":" + a.getWeight()));
    }
}
