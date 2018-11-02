package Lambda.AppleCase;

import java.util.Comparator;
import java.util.List;

/**
 * Lambda表达式方法的引用
 */
public class ComparatorApple {
    public static void main(String args[]) {
        List<Apple> applelist = Apple.getAppleList();//自动生成一个List  每次都是一样的
        compartor(applelist);
    }

    private static void compartor(List<Apple> applelist) {
        applelist.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }

    /**
     * 使用函数式编程来实现这个比较方法  使用Lambda表达式来表述比较过程
     * @param applelist
     */
    private static void compartorLambdaA(List<Apple> applelist) {
        applelist.sort((A, B) -> A.getWeight().compareTo(B.getWeight()));
    }

    /**
     * 使用方法引用才代替Lambda表达式，方法引用比Lambda表达式更加直观。代码可读性更好。
     * @param applelist
     */
    private static void compartorLambdaB(List<Apple> applelist) {
        applelist.sort(Comparator.comparing(Apple::getWeight));
    }
}
