package Lambda.Variable;

/**
 * Created by Ly on 2018/11/25.
 * 栈和堆 局部变量都是保存在栈上的。 当方法被执行完的时候 ，空间就被释放掉了。 所以  i的值没有被改变成2
 *
 *
 */
public class HeapandStack {
    public static void main(String args[]) {
        int i = 1;
        show(i);
        System.out.println(i);
    }

    private static void show(int x) {
        x = 2;
    }
}
