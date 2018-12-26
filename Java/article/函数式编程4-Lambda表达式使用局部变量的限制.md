>微信公众号：**[刘洋说](#liuyangspeak)**
>关注可了解更多技术信息。问题或建议，请公众号留言;
>**[如果你觉得[刘洋说]你有帮助，欢迎赞赏](#jump_20)[^1]**

###内容目录

[TOC]
###   Lambda表达式使用局部变量的限制
   Lambda中所使用的局部变量默认为final类型的。 即使不显示的声明出final。 因为在java中局部变量是被分配到栈中的，而实例变量是被分配到内存中去的。局部变量在statck中分配空间，当方法执行完毕后，栈中所占用空间自动被清空。Lambda线程在使用局部变量的时候，是拷贝了局部变量的副本，然后放在Lambda中使用。 如果局部变量的值被改变。则Lambda线程中的副本将毫无意义。 所以，局部变量可以被释放，但是局部变量的值不可以被改变。

```java
  public class Variable {
    private static int i = 1;

    public static void main(String args[]) {
        final int j = 1;
        int k = 1;
        System.out.println(i);
        Runnable run1 = () -> System.out.println(i++);
        //Lambda中使用的局部变量必须是final的 不能更改局部变量的值
        Runnable run2 = () -> System.out.println(j);
        Runnable run3 = () -> System.out.println(k);
        //这一句是错误示例。局部变量的值不能被改变，哪怕是在Lambda中先被lambda线程使用了。然后发生了改变。 也是不行的。
        //Runnable run4 = () -> System.out.println(k++);
        // 变量k一旦被lambda所使用即被默认成了final类型。不能改变。无论是之前改变还是之后的改变。
        // k++;
        run1.run();
        run2.run();
        run3.run();
        i = 100;
        System.out.println(i);
    }
}
```

###  拓展Java内存内存空间区域
* 计数器  Program counter Resigter
由于在JVM中，多线程是通过线程轮流切换来获得CPU执行时间的，因此，在任一具体时刻，一个CPU的内核只会执行一条线程中的指令，因此，为了能够使得每个线程都在线程切换后能够恢复在切换之前的程序执行位置，每个线程都需要有自己独立的程序计数器，并且不能互相被干扰，否则就会影响到程序的正常执行次序。因此，可以这么说，程序计数器是每个线程所私有的。在JVM规范中规定，如果线程执行的是非native方法，则程序计数器中保存的是当前需要执行的指令的地址；如果线程执行的是native方法，则程序计数器中的值是undefined。由于程序计数器中存储的数据所占空间的大小不会随程序的执行而发生改变，因此，对于程序计数器是不会发生内存溢出现(OutOfMemory)的。

* 本地方法区 Native method statck
  虚拟机使用到的Native方法服务的栈，例如调用系统底层服务所使用到的栈。

* 方法区   Method Area

  与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据

* 栈   Heap
  虚拟机栈描述的是Java方法执行的内存模型。每个方法被执行的时候都会同时创建一个栈帧（Stack Frame）用于存储局部变量表、操作栈、动态链接、方法出口等信息。每一个方法被调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。

* 堆  Stack

  唯一一个可供程序员管理的内存区域，可以主动new 或者释放对象。
