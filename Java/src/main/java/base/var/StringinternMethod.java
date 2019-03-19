package base.var;


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
 * intern方法的详细解释。简单来说就是intern用来返回常量池中的某字符串，如果常量池中已经存在该字符串，则直接返回常量池中该对象的引用。否则，在常量池中加入该对象，然后 返回引用。
 */
public class StringinternMethod {
    public static void main(String args[]){
//        test1();
//        test2();
//       test3();
        test4();
    }


   public static void test1(){
        String a="";
        String b="";// b的值是从常量池中取到的
        System.out.println(a==b);
    }

    /**

     */
    public static void test2(){
        String a="";//这样赋值型的 会现在常量池中查找，发现有了就返回常量池中引用。没有则在常量池创建一个新的对象。这个a指向的是常量池的地址
        String b=new String("");// 使用new String的时候
        // 不再判断常量池中是否含有这个字符串，都会直接在堆创建一个新的对象
        System.out.println(a==b);  //比较地址，两个值是不一样的
        System.out.println(a.equals(b)); //比较值，两者的值是相等的

    }

    public static void test3(){
        String a="";
        String b=new String("");// b始终指向的是堆内存地址
        String a1=new String("1");
        String b1="1";
        System.out.println(a==b);
        System.out.println(a1==b1);
    }

    public static void test4(){
        String temp = "asdf";
        String a="asdf"+temp;//字符串相加在堆上进行的。所以字符串相加得到的在堆上新的字符串
        String c="asdfasdf";//
        String b=("asdf"+temp).intern();  //b的值是从常量池中取到的地址
        System.out.println(a==c);
        System.out.println(a==a.intern());
        System.out.println(a==b);
        System.out.println(b==c);
    }

}
