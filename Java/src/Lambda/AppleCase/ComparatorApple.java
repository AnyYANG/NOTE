package Lambda.AppleCase;

import java.util.List;

public class ComparatorApple {
    public static void main(String args[]){
        List<Apple> applelist = Apple.getAppleList();//自动生成一个List  每次都是一样的
        applelist.sort(new Comparator<Apple>(){
            public int compare(Apple a1,Apple a2){
                return a1.getWeight()
            }
        });
    }

}
