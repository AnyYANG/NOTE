package Lambda.AppleCase;

import sun.applet.AppletListener;

import java.util.Arrays;
import java.util.List;

public class Apple {
    String color;
    double weight;

    public Apple(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static List<Apple> getAppleList(){
        return  Arrays.asList(new Apple("green",50),new Apple("green",50),new Apple("red",60),new Apple("green",45));
    }
}
