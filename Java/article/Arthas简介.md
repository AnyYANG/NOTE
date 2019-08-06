### Arthas简介
阿尔萨斯是一款集监控，反编译，查询异常，线上debug的综合监控平台。Arthas平台，
提供了丰富的类Liunx命令工具去排查线上代码问题。 [arthas in github](https://alibaba.github.io/arthas/)


### Arthas的下载和安装

[Arthas](https://alibaba.github.io/arthas)可以直接github上下载，
```shell
wget https://alibaba.github.io/arthas/arthas-boot.jar
java -jar arthas-boot.jar
```
如果后台没有运行的Java线程，Arthas会报错，如果有，会让你选择具体去监控哪个Java进程的状态

```shell
$ java -jar arthas-boot.jar
* [1]: 11111 demo0
  [2]: 22222 demo1.jar
##直接输入1 则进入第一个java线程。 默认是从1开始。
$1
```

>Atrhas提供了两个示例工程，一个是[arthas-demo](https://alibaba.github.io/arthas/)随机数生成的java示例，另一个是[demo-arthas-spring-boot](https://github.com/hengyunabc/katacoda-scenarios/) springboot
的web应用


在终端输入1后，屏幕打印后，则证明监控成功
```
[INFO] Try to attach process 71560
[INFO] Attach process 71560 success.
[INFO] arthas-client connect 127.0.0.1 3658
  ,---.  ,------. ,--------.,--.  ,--.  ,---.   ,---.
 /  O  \ |  .--. ''--.  .--'|  '--'  | /  O  \ '   .-'
|  .-.  ||  '--'.'   |  |   |  .--.  ||  .-.  |`.  `-.
|  | |  ||  |\  \    |  |   |  |  |  ||  | |  |.-'    |
`--' `--'`--' '--'   `--'   `--'  `--'`--' `--'`-----'
 
wiki: https://alibaba.github.io/arthas
version: 3.0.5.20181127201536
pid: 71560
time: 2019-08-06 19:16:24
```

在终端输入help  查看可用命令
```
$ help
 NAME         DESCRIPTION
 help         Display Arthas Help
 keymap       Display all the available keymap for the specified connection.
 sc           Search all the classes loaded by JVM
 sm           Search the method of classes loaded by JVM
 classloader  Show classloader info
 jad          Decompile class
 getstatic    Show the static field of a class
 monitor      Monitor method execution statistics, e.g. total/success/failure count, average rt, fail rate, etc.
 stack        Display the stack trace for the specified class and method
 thread       Display thread info, thread stack
 trace        Trace the execution time of specified method invocation.
 watch        Display the input/output parameter, return object, and thrown exception of specified method invocation
 tt           Time Tunnel
 jvm          Display the target JVM information
 ognl         Execute ognl expression.
 mc           Memory compiler, compiles java files into bytecode and class files in memory.
 redefine     Redefine classes. @see Instrumentation#redefineClasses(ClassDefinition...)
 dashboard    Overview of target jvm's thread, memory, gc, vm, tomcat info.
 dump         Dump class byte array from JVM
 options      View and change various Arthas options
 cls          Clear the screen
 reset        Reset all the enhanced classes
 version      Display Arthas version
 shutdown     Shutdown Arthas server and exit the console
 session      Display current session information
 sysprop      Display, and change the system properties.
 sysenv       Display the system env.
 history      Display command history
 cat          Concatenate and print files
 pwd          Return working directory name
 mbean        Display the mbean information
```
贴心的阿里团队已经把每个命令的作用全部用英文注释到后面了。

### Arthas的使用
1. demo1  使用dashboard  查看现在jvm运行状态，打开大屎面板，查看系统状态。
```shell
$ dashboard

```


三部分组成 Thread status  ,Memory status, Runtime status, 囊括了系统宏观上配置，运行，内存数据。很是赞！


2. demo2  使用trace追踪方法执行时间,这也是一个超级推荐的命令，可以用来监控方法中每个调用执行时间。
进而针对性优化，提高系统接口的瓶颈


```
trace demo.MathGame run  

```
然后就会输出MathGame这个方法中每个方法具体调用时间如下图所示：


不仅仅如此，还能做筛选
```
##  筛选运行时间大于10ms的路径
trace demo.MathGame run  '#cost > 10'  
##  过滤掉JDK函数， 因为jdk函数我们不可能去优化的   
trace -j  demo.MathGame run
```
这个命令评价：赞！

3. demo3 支持OGNL表达式，这个功能很屌！


```
$ognl '@demo.MathGame@random'
@Random[
    serialVersionUID=@Long[3905348978240129619],
    seed=@AtomicLong[253598304640403],
    multiplier=@Long[25214903917],
    addend=@Long[11],
    mask=@Long[281474976710655],
    DOUBLE_UNIT=@Double[1.1102230246251565E-16],
    BadBound=@String[bound must be positive],
    BadRange=@String[bound must be greater than origin],
    BadSize=@String[size must be non-negative],
    seedUniquifier=@AtomicLong[-3282039941672302964],
    nextNextGaussian=@Double[0.0],
    haveNextNextGaussian=@Boolean[false],
    serialPersistentFields=@ObjectStreamField[][isEmpty=false;size=3],
    unsafe=@Unsafe[sun.misc.Unsafe@75c01f22],
    seedOffset=@Long[24],
]

```


### 总结一下
 Arthas 为处理线上环境问题的剖析器，程序员能够只管看到自己代码到底有没有运行，有没有提交成功，针对线下正常，线上出问题的情况给了一
 推荐大家使用
### 关注我
