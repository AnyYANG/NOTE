>微信公众号：**[刘洋说](#liuyangspeak)**
关注可了解更多技术信息。问题或建议，请公众号留言;
**[如果你觉得[刘洋说]你有帮助，欢迎赞赏](#jump_20)[^1]**

###内容目录

[TOC]

###  为什么要使用Lambda

在上一个篇公众号中，介绍函数式接口，在上一节中，为了使用不同过滤条件，定义了三个不同的比较的方法。分别是：testLength,testLiu,testEmpty。在定义这三个方法的时候很多代码是重复冗余的，并且不灵活。本次将介绍Lambda的一些基本用法。重构这些方法，让使用函数式编程像写数学公式一样的方便。Lambda表达式可以理解为一种匿名函数表达式，无需写太多模板代码。学习使用Lambda表达式能够在平时工作中起到事半功倍的效果

### Lambda表达式结构

（Parameters参数）-> {expression逻辑块};
Lamda表达式由三部分组成，参数表，箭头分隔符，Java逻辑代码块。现在用重写的方法。来把上一小节例子重写。

Before

```java
    
         List<String> listS = Arrays.asList("134dff", "Asadf32", "B", "aliuyang", "", "12");
         List<String> result = filter(listS, FilterStringList::testEmpty);
         List<String> resultlength = filter(listS, FilterStringList::testLength);
         List<String> resultliu = filter(listS, FilterStringList::testConLiu);

```

Now
```java
        List<String> resultNow = filter(listS, (String s)->s.isEmpty());
        List<String> resultlengthNow = filter(listS,(String s)->s.length()>5);
        List<String> resultliuNow = filter(listS,(String s)->s.contains("liu"));

```
可以看到Lambda表达式对一些简单的方法的定义更加清晰，省去很多模板代码。下面写下Lambdab表达式的常见结构方式
| Lambda表达式 | 说明|
|-----|-----|
| （）->System.out.println("123")|   输出语句|
| （a）->System.out.println(a)| 输出a|
| （）->new Student()| 返回new的student对象|
| （a,b）->{a*b}| 返回a*b的乘积|
| (String s) -> s.length()|  取得对象中值，返回字符串长度|
| (String s,String b) -> {String result=s+b; return result;}|  取得对象中值，返回字符串长度|

上面这些都是Lambda表达式常见的结构。 从结构上，并不是很复杂，但是会让人困惑的是返回值和Lambda内部局部变量定义的灵活性。下面从Lambda使用说起。

### Lambda介入
#### 介入Lambda时机
Lambda函数式编程不是万能的，但是在关键时候，能多块好省。例如实现Java逻辑的时候，例如，在比较农产品水果品质的时候，可能采用质量比较，可能采用质量，颜色比较，或者采用质量，产地比较。这样如果传统做法是每一种比较方式都要写一种专有方法，然后需要什么调用什么。但是这并不能满足需求灵活多变。这时候，考虑能不能使用函数式编程，将我需要挑选的标准传入，和水果列表传入，然后得到结果。
#### 介入前需要的准备
首先去确定要参数化的方法表达式。首先需要一个函数式接口，在这里使用java8在package java.util.function函数接口库中的Predicate函数式接口。
   
```java
@FunctionalInterface
public interface Predicate<T> {

    /**
     * 需求传入一个苹果 ，判断苹果品质是否达标，然后返回结果
     */
    boolean test(T t);
}
```
定义Apple.java
```java
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
}
```

创建函数式接口执行函数
```java
    /**
     * 
     * @param apples     苹果集合
     * @param predicate  判断条件
     */
    public static void checkApple(List<Apple> apples, Predicate<Apple> predicate) {
        for (Apple app : apples) {
            if (predicate.test(app)) {
                System.out.println("产地：" + app.getOrigin() + " 质量：" + app.getWeight() + " 颜色：" + app.getColor());
            }
        }
    }
```

#### Lambda付诸实践
**输出筛选出质量大于50g的Apple**

```java
  //写法1 直接把Lambda表达式传入。
 checkApple(apples,(Apple a)->a.getWeight()>50);
  //写法二 先把Lambda表达式 赋值给函数式接口，再将其作为变量传入。
 Predicate<Apple> weightBest=(Apple a)->a.getWeight()>50;
 checkApple(apples,weightBest);

```
上面两种传入参数写法都可以。只是如果以变量形式传入的时候，对Lambda表达式再进行 or and 运算的时候 更加方便。 如下：
```java
 //非中国生产的Apple
 Predicate<Apple> originCN= (Apple a)->"cn".equals(a.getOrigin());
 checkApple(apples,originCN.negate());
```
以上代码中出现了negate这个方法。这也是Java8的新特性之一，就是default方法。default方法是Java的一种妥协， 之前的java版本中，接口中方法必须要实现，然后很多传统接口一旦确定后期修改就特别麻烦，接口中添加一个方法，基本要把所有继承的实现的子类全部重写一遍。所以，Java8在解决增加接口功能同时，又要去兼容以前代码。采用了default方法，用default来定义这个方法，方法在接口中直接有方法体实现。继承的接口的类，如果没有实现default方法，就默认调用接口内的default方法。
default方法体
```java
  /**
     * Returns a predicate that represents the logical negation of this
     * predicate.  返回逻辑表达式结果相反的函数接口表达式。
     */
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
```
***因此在调用originCN.negate()的时候等同于!originCN.test(t)*** 
在查找原产地非中国苹果的时候 自需要求中国的非集合即可。

**筛选出原产地是和中国，且是红色的苹果。**
```java
        Predicate<Apple> colorRed= (Apple a)->"red".equals(a.getColor().toLowerCase());
        checkApple(apples,colorRed.and(originCN));
```
这里采用了Lambda的与运算，即是中国产的， 又是红色的苹果。 Java8 提供给Predicate函数式接口还支持或运算在接口中都有相应的定义。
```java
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }
```
***在使用and or not  运算的时候，lambda表达式也是遵循着从左到右的顺序进行运算。***
例如，筛选非中国产的质量大于50g的青苹果。
```java
  checkApple(apples,originCN.negate().and(weightBest.and((Apple a)->"green".equals(a.getColor().toLowerCase()))));
```
因为我定义的color筛选是筛选红色的。 所以，绿色筛选直接写Lambda表达式传入。简单明了粗暴。这句在执行的时候 是从做到右，先筛选非中国的，再筛选质量重的，再筛选绿色的。 所以Lambda在执行或与非的时候是从左到右顺序进行。
### 总结一下
Java8引入函数式接口的目的是为了行为参数化，而引入lambda是为了让行为匿名化 。通过Lambda这种方式，让方法实现更灵活。减少啰嗦冗余代码。提高开发效率!
### 关注我
