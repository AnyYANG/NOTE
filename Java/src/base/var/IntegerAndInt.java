package base.var;

public class IntegerAndInt {
    public static void main(String args[]) {
        int i1 = 100;
        Integer i2 = 100;
        System.out.println(i1 == i2);
        Integer j1 = new Integer(100);
        Integer j2 = new Integer(100);
        System.out.println(j1 == j2);


    }
}
