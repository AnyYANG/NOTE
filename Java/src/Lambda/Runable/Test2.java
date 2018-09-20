package Lambda.Runable;

import Lambda.AppleCase.Apple;

import java.util.*;
import java.util.function.*;

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
        List<Apple> appleList = initAppleList(map, appleBiFunction);
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
     *
     * @param apples
     */
    public static void compareApple(List<Apple> apples) {
        Comparator<Apple> appleComparator = (a, b) -> a.getWeight().compareTo(b.getWeight());
        apples.sort(appleComparator.reversed().thenComparing(Apple::getColor));
        log(apples);
    }

    /**
     * 使用lambda来输出对象信息
     *
     * @param apples
     */
    public static void log(List<Apple> apples, Predicate) {
        Consumer<Apple> consumer = (apple) -> System.out.println(apple.getColor() + ":" + apple.getWeight());
        for (Apple app : apples) {
            consumer.accept(app);
        }
    }

    public static void funcitonlambda(List<Apple> apples) {
        Comparator<Apple> appleComparator = (a, b) -> a.getWeight().compareTo(b.getWeight());

    }

    /**
     *
     * @param apples     苹果集合
     * @param predicate  判断条件
     */
    public static void checkApple(List<Apple> apples, Predicate<Apple> predicate) {
        for (Apple app : apples) {
            if (predicate.test(app)) {
                System.out.println("产地：" + app.getOrigin() + " 质量：" + app.getWeight() + " 颜色：" + app.getColor());
            }
        }
    }
    public static void test(List<Apple> apples){

        Predicate<Apple> weightBest=(Apple a)->a.getWeight()>50;
        checkApple(apples,weightBest);
       //非中国生产的Apple
        Predicate<Apple> originCN= (Apple a)->"cn".equals(a.getOrigin());
        checkApple(apples,originCN.negate().and(weightBest.and((Apple a)->"green".equals(a.getColor().toLowerCase()))));

        Predicate<Apple> colorRed= (Apple a)->"red".equals(a.getColor().toLowerCase());
        checkApple(apples,colorRed.and(originCN));


    }
}
