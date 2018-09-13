package base.var;

public class IntegerAndInt {
    public static void main(String args[]) {
        int i1 = 100;
        Integer i2 = 100;
        Integer i3 = 100;
        Integer k1 =128;
        Integer k2 =128;
        Integer j1 = new Integer(100);
        Integer j2 = new Integer(100);
       ///对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false

        System.out.print("int 变量和 Integer对象对比，比较的是对象引用 int i == Integer j--->");
        System.out.println(i1 == i2);
        System.out.print("new Integer对象和new Integer对象对比，比较的是对象引用 Integer i == Integer j--->");
        System.out.println(j1 == j2);
        System.out.print("int变量和Integer对象对比，integer自动拆箱 int i == Integer j--->");
        System.out.println(i1 == j2);

        System.out.print("Integer不使用new创建，默认使用常量池中数值 int i == Integer j--->");
        Integer i23 = 128;
        Integer j23 = 128;
        System.out.println(i23 == j23); //false
    }
}
