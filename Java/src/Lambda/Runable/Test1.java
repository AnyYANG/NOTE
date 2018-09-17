package Lambda.Runable;

import Lambda.AppleCase.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Test1 {
    public static void main(String args[]) {

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
        Consumer<Apple> appleConsumer = (Apple a) -> System.out.println(a.getColor()+a.getWeight());
        //创建对象 Supplier<T>函数式接口  用于返回对象
        Supplier<Apple> appleSupplier = ()->{return new Apple();};
        Supplier<Apple> appleSupplier1=()->new Apple();
        //Function<T,R> T经过函数运算，得到了结果R
        //获取苹果的质量
        Function<Apple,Integer> weightFunction =Apple::getWeight;
        //BiFunction<T,R,U> 合并两个不同类型的参数，返回一个第三个类型的参数
        //合并两个数
        BiFunction<Integer,Double,String> sum=(Integer a,Double b)-> a*b+"";
        // Comparable<T>   仅有一个参数的时候  括号可以被省略
        Comparable<Apple>  appleComparable =  a->a.getWeight().compareTo(10);
    }
    /**
     * lambda的适用范围
     */
  public void  testLambda(){
      List<String> str = Arrays.asList("a","b","A","B");
    BiPredicate<List<String>,String>  contains=(List,element) ->list.contains(element);
  }


}
