package Lambda.AppleCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AppleCase {

    public static void main(String arg[]){
        AppleCase  casecase=new AppleCase();
        List<Apple> list= Arrays.asList(new Apple("green",50),new Apple("green",50),new Apple("red",60),new Apple("green",45));
        List<Apple> result = filterApple(list,casecase::isRedApple);

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result= new ArrayList<Apple>();
        for(Apple apple : inventory){
               for(Apple ap : inventory){
                   if("green".equals(ap.getColor())){
                      result.add(ap);
                   }
               }
        }
        return result;
    }

    public   boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }
    public   boolean isRedApple(Apple apple){
        return "red".equals(apple.getColor());
    }
    public static List<Apple> filterApple(List<Apple> lists, Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple  apple : lists){
            if(predicate.test(apple)){
             result.add(apple);
            }
        }
        return  result;
    }

}
