package Lambda.AppleCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorApple {
    public static void main(String args[]){
        List<Apple> applelist = Apple.getAppleList();//自动生成一个List  每次都是一样的
        testAppleSort(applelist);
    }


    private static void testAppleSort(List<Apple> applelist) {
        applelist.sort(new Comparator<Apple>(){
            public int compare(Apple a1,Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }
    private static void testAppleSort2(List<Apple> applelist) {
    //    applelist.sort((a1,a2)->Apple::getWeight);

    }

}
