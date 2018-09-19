package Lambda.FunctionLambda;

import java.util.function.Function;

public class TestFxGx {
    public static void main(String args[]){
        Integer m=0;
        Function<Integer ,Integer> f = a->a+1;
        Function<Integer ,Integer> g = b->b*b;
        //compose 先执行内层函数，再执行外层函数 相当于 g(f(x))
        Function<Integer ,Integer> y = g.compose(f);
        //compose 先执行外层函数得到的结果 再andther 执行F函数 相当于 f(g(x))
        Function<Integer ,Integer>z = g.andThen(f);
        Integer result = y.apply(10);
        Integer result1 = z.apply(10);
        System.out.println(result);
        System.out.println(result1);

    }
}
