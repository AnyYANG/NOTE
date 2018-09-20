package Lambda.AppleCase;

import sun.applet.AppletListener;

import java.util.Arrays;
import java.util.List;

public class Apple {
    String color;
    Integer weight;
    String origin;
    public Apple() {
    }
    public Apple(Integer  weight) {
        this.weight=weight;
    }
    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer  getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static List<Apple> getAppleList(){
        return  Arrays.asList(new Apple("green",50),new Apple("green",50),new Apple("red",60),new Apple("green",45));
    }
    public static void log(List<String> stringList) {
        for (String s : stringList) {
            System.out.println("SystemOutLine:" + s);
        }
    }
}
