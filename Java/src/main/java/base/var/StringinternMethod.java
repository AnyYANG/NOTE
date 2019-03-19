package base.var;


import org.junit.jupiter.api.Test;

/**
 *  liuyang  日期 2019年3月11日 22点14分
 *   Java中的String被设计成不可变的，出于以下几点考虑：
 *
 * 1. 字符串常量池的需要。字符串常量池的诞生是为了提升效率和减少内存分配。可以说我们编程有百分之八十的时间在处理字符串，而处理的字符串中有很大概率会出现重复的情况。正因为String的不可变性，常量池很容易被管理和优化。
 *
 * 2. 安全性考虑。正因为使用字符串的场景如此之多，所以设计成不可变可以有效的防止字符串被有意或者无意的篡改。从java源码中String的设计中我们不难发现，该类被final修饰，同时所有的属性都被final修饰，在源码中也未暴露任何成员变量的修改方法。（当然如果我们想，通过反射或者Unsafe直接操作内存的手段也可以实现对所谓不可变String的修改）。
 *
 * 3. 作为HashMap、HashTable等hash型数据key的必要。因为不可变的设计，jvm底层很容易在缓存String对象的时候缓存其hashcode，这样在执行效率上会大大提升。
 *
 */
public class StringinternMethod {
    public static void main(String args[]){
        test1();

        test2();

        test3();
    }


   public static void test1(){
        String a="";
        String b="";// b的值是从常量池中取到的
        System.out.println(a==b);
    }

    /**

     */
    public static void test2(){
        String a="";
        String b=new String("");// b的值是一个新的值
        System.out.println(a==b);  //比较地址，两个值是不一样的
        System.out.println(a.equals(b)); //比较值，两者的值是相等的

    }

    public static void test3(){
        String a="";
        String b=new String("").intern();// b的值是从常量池中取到的
        System.out.println(a==b);
    }
}
