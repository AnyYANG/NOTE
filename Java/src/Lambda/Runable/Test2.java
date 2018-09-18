package Lambda.Runable;

import Lambda.AppleCase.Apple;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Test2 {
    static Map<String, Integer> map = new HashMap<>();

    static {
        map.putIfAbsent("green", 50);
        map.putIfAbsent("red", 80);
        map.putIfAbsent("yellow", 69);
        map.putIfAbsent("blue", 33);
        map.put("white1", 60);
        map.put("white2", 33);
        map.put("white3", 60);
    }

    public static void main(String args[]) {
        BiFunction<String, Integer, Apple> appleBiFunction = (a, b) -> new Apple(a, b);
        BiFunction<String, Integer, Apple> appleBiFunction2 = Apple::new;
        List<Apple> appleList= initAppleList(map, appleBiFunction);
        System.out.println();
        compareApple(appleList);
    }

    /**
     * 初始化一个列表
     *
     * @param map
     * @param biFunction
     * @return
     */
    public static List<Apple> initAppleList(Map<String, Integer> map, BiFunction<String, Integer, Apple> biFunction) {
        List<Apple> appleList = new ArrayList<>();
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> settemp = iterator.next();
            Apple apple = biFunction.apply(settemp.getKey(), settemp.getValue());
            appleList.add(apple);
        }
        log(appleList);
        return appleList;
    }

    /**
     * 根据重量排序
     * @param apples
     */
    public static void compareApple(List<Apple> apples) {
        Comparator<Apple> appleComparator=( a, b)->a.getWeight().compareTo(b.getWeight());
        apples.sort(appleComparator);
        log(apples);
    }

    /**
     * 使用lambda来输出对象信息
     *
     * @param apples
     */
    public static void log(List<Apple> apples) {
        Consumer<Apple> consumer = (apple) -> System.out.println(apple.getColor() + ":" + apple.getWeight());
        for (Apple app : apples) {
            consumer.accept(app);
        }
    }
}
