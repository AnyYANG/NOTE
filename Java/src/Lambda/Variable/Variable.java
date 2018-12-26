package Lambda.Variable;

/**
 * Created by Ly on 2018/11/25.
 * Lambda 为什么要使用final类型的局部变量
 *  1 实例变量在堆stack中，局部变量在栈heap上。
 *  2 栈上的东西，用完就消失，new创建出来的东西都在堆上。用完之后靠垃圾回收机制不定期清除。
 *  3 Lambda线程在访问局部变量的时候 是在访问局部变量的副本。如果副本被回收，或值发生改变Lambda访问的则不是原始变量。
 *
 */
public class Variable {
    private static int i = 1;

    public static void main(String args[]) {
        final int j = 1;
        int k = 1;
        System.out.println(i);
        Runnable run1 = () -> System.out.println(i++);
        //Lambda中使用的局部变量必须是final的 不能更改局部变量的值
        Runnable run2 = () -> System.out.println(j);
        Runnable run3 = () -> System.out.println(k);
        //这一句是错误示例。
        //Runnable run4 = () -> System.out.println(k++);
        // 变量k一旦被lambda所使用即被默认成了final类型。不能改变。无论是之前改变还是之后的改变。
        // k++;
        run1.run();
        run2.run();
        run3.run();
        i = 100;
        System.out.println(i);
    }
}
