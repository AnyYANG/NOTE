package CollectionCompartor;

import java.util.Arrays;

/**
 * 冒泡排序的思想，把从第一个开始比较，大的就往后挪动。知道不能挪动为止
 *
 */
public class maopao {

    public static void main(String args[]) {
        int[] array =new int[]{110,310,54,46,288,114,82,95,997,58,44,12,204,859,454,423,1420};
        paixu(array);
    }
    public static void paixu(int[] x){
        int count = 0;
        boolean flag =true;
        for (int i=0 ;i<x.length; i++){
              for(int j=0;j<x.length-1;j++){
                  if(x[i]<x[j]){
                      int temp =x[i];
                      x[i]=x[j];
                      x[j]=temp;

                  }
              }
            count++;
            printintarray(x,count);
        }

    }
    public static void printintarray(int[] array,int count){
        System.out.println("count"+count);
        Arrays.stream(array).forEach((a)->System.out.print(" "+a));
    }
}
