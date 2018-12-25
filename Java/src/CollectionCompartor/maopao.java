package CollectionCompartor;

import java.util.Arrays;

/**
 * 冒泡排序的思想，把从第一个开始比较，大的就往后挪动。知道不能挪动为止
 */
public class maopao {

    public static void main(String args[]) {
        int[] array = new int[]{110, 310, 54, 46, 288, 114, 82, 95, 997, 58, 44, 12, 204, 859, 454, 423, 140};
        paixu(array);
        paixuversion2(array);
    }

    /**
     * 这样写有一个问题就是 每次冒泡其实都确定了一个数值的位置。 第二次比较的时候 可以掠过的。 所以会造成效率低下
     *
     * @param x
     */
    public static void paixu(int[] x) {
        int count = 0;
        int countneicheng = 0;
        boolean flag = true;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length - 1; j++) {
                if (x[i] < x[j]) {
                    int temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;

                }
                countneicheng++;
            }
            count++;
            printintarray(x, count);
        }
        System.out.println("内层循环次数" + countneicheng);

    }

    /**
     * 改进版1  每次确定过一个数值的位置后，下次循环的次数就减1  所以第i次循环就会减少i
     *
     * @param x
     */
    public static void paixuversion2(int[] x) {
        int count = 0;
        int countneicheng = 0;
        boolean flag = true;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length - 1 - i; j++) {
                if (x[i] < x[j]) {
                    int temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
                countneicheng++;
            }
            count++;
            printintarray(x, count);
        }
        System.out.println("内层循环次数" + countneicheng);

    }

    public static void printintarray(int[] array, int count) {
        System.out.print("count" + count + " ");
        Arrays.stream(array).forEach((a) -> System.out.print(" " + a));
        System.out.println();
    }
}
