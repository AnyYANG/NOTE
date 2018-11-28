package Steam;

import Lambda.AppleCase.Apple;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

/**
 * Created by  liuyang
 * 2018/9/26    14:35
 * Steam
 * All Right Reserved by liuyang.
 **/

public class AppleTest1 {

    public static void main(String args[]){
        List<Apple> appleList =  Apple.getAppleList();;
        List<String> colorList =   appleList.stream().filter(apple -> {
            System.out.println("filter:"+apple.getColor());
            return apple.getWeight()>50;}).sorted(
                    Comparator.comparing(
                            (Apple a) -> {
                                System.out.println("comparing:"+a.getColor());
                              return  a.getWeight();
                          }
                    )).map(
                (Apple a) -> {
                    System.out.println("map:"+a.getColor());
                    return  a.getColor();
                }
        ).collect(toList());
        List<String> colorList2 =   appleList.parallelStream().filter(apple -> apple.getWeight()>50).sorted(Comparator.comparing(Apple::getWeight)).map(Apple::getColor).collect(toList());
        colorList.stream().forEach((s)-> System.out.println(s));
        colorList2.stream().distinct().forEach((s)-> System.out.println(s));
    }
    public static void log(List<String> apples, Consumer<String> consumer) {
        for (String app : apples) {
            consumer.accept(app);
        }

    }

}
