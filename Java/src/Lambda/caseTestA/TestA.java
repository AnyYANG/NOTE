package Lambda.caseTestA;

import java.nio.file.DirectoryStream;
import java.util.List;

class TestA {
    public void main(String args[]) {
        DirectoryStream.Filter<List<String>> listFilter = (List<String> list) -> list.isEmpty();
        //()-> new Apple(10);
        System.out.println("helloworld");
    }
    public void Instant(){}
    class Apple{
        private double weight;
        private String color;
        public Apple(){}
        public Apple(double weight){
            this.weight=weight;
        }
        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}