>微信公众号：**[刘洋说](#liuyangspeak)**
>关注可了解更多技术信息。问题或建议，请公众号留言;
>**[如果你觉得[刘洋说]你有帮助，欢迎赞赏](#jump_20)[^1]**

###内容目录

[TOC]

###  起因
今天在使用Alibaba 的easyexcel的时候，发现maven编译不过去。报错原因如下

```
[WARNING] Rule 1: org.apache.maven.plugins.enforcer.RequireUpperBoundDeps failed with message:
Failed while enforcing RequireUpperBoundDeps. The error(s) are [
Require upper bound dependencies error for commons-codec:commons-codec:1.9 paths to dependency are:
+-cn.*.os.platform:cert-api:0.1
  +-commons-codec:commons-codec:1.9
and
+-cn.*.os.platform:cert-api:0.1
  +-org.apache.httpcomponents:httpclient:4.5.3
    +-commons-codec:commons-codec:1.9
and
+-cn.*.os.platform:cert-api:0.1
  +-cn.alibaba:dubbo:2.8.4a
    +-commons-codec:commons-codec:1.6
and
+-cn.*.os.platform:cert-api:0.1
  +-cn.*:easyexcel:1.0.0
    +-org.apache.poi:poi:3.17
      +-commons-codec:commons-codec:1.10
```

  看说明，大概是commons-codec 这个jar包有问题。现在httpclient ，dubbo  easyexcel 都用到了这个jar包。但是因为加载了一个老版本，但是我easyexcel用了一个新版本，造成了错误。
  在此引用网上Demo来说下问题 B和C的jar包都引入了X。但是X的版本不同，分别是1和2.如果说系统加载了x.1后 ，x.2就不会加载，系统会报错。但是如果系统先加载了X.2，那么X.1不会报错，因为高版本会向低版本进行兼容。
  >这里有一点让我迷糊的版本Apache命名规则，就是版本号， 从  1.0，1.1， 1.2，1.3 ，1.4...1.9再往下，是不是该 2.0,或者1.91。结果直接来了 1.10。 以前没注意过此类问题。但是这一次，开始我深信1.10<1.9的。然后，解决不了问题。后来去了Maven仓库查看了一下这个jar包，1.10版本比之前版本用的人多几倍。而且1.10发行时间更短。我才恍然大悟

```
      Pom.xml
      /    \
    B        C
  /  \      /  \
 X.1  Y   X.2   M
```
###追本溯源
怎样查找和解决此类问题。一般使用maven自带的-Dverbose dependency:tree  用树形式来显示jar包依赖关系，如果想筛选一下，显示我想看到冲突的jar包加参数-Dincludes=commons-codec 。输入命令后，可以看到

```
...
[INFO] +- com.*:easyexcel:jar:1.0.0-gjyy:compile
[INFO] |  \- org.apache.poi:poi:jar:3.17:compile
[INFO] |     \- (commons-codec:commons-codec:jar:1.10:compile - omitted for conflict with 1.9)
[INFO] +- org.apache.httpcomponents:httpcomponents:jar:4.5.3:compile
[INFO] |  \- (commons-codec:commons-codec:jar:1.9:compile - omitted for duplicate)
[INFO] \- com.alibaba:dubbo:jar:2.8.4a:compile
[INFO]    \- (commons-codec:commons-codec:jar:1.6:compile - omitted for conflict with 1.9)
...
```
可以看到这easyexcel，httpcomponents，dubbo三哥们，正好把三种不同状态演绎的淋漓尽致。先介绍一下maven编译jar包的三种状态。
1. compile 已经编译成功，没有冲突或其他问题。如上图所示commons-codec:commons-codec:jar:1.9:compile
2. omitted for conflict with XX   本jar需要的和现在系统已经加载的有冲突。而且系统加载的更老旧，本jar用的更新。如上poi3.17需要1.10的commons-codec但是缺给了1.9版本的
3. omitted for duplicate，就是已经加载过此jar，并且加载的此jar版本比我使用版本更新，所以不去重复加载。如dubbo需要1.6版本，系统提供了1.9版本的。

###解决冲突
#### 方案一 依赖剪除
将引入低版本依赖的jar包中去除低版本jar包

```

		<dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.3</version>
            <exclusions>
				<exclusion>
					<groupId>commons-codec</groupId>
					<artifactId>commons-codec</artifactId>
				</exclusion>
			</exclusions>
        </dependency>
        
```

这样保证引入httpclient 的时候，不被引入低版本的commons-codec。
#### 方案二 简单粗暴直接指定
因为pom文件直接引入的优先级更高一些，通过直接引入，强制大家都是用指定版本
```
	        <dependency>
	            <groupId>commons-codec</groupId>
	            <artifactId>commons-codec</artifactId>
	            <version>1.10</version>
	        </dependency>
```



### 总结一下
在使用maven管理项目的时候，大概率出现各种版本问题，maven提供的树结构工具能够帮助开发者解决各种问题。当然最重要的是，版本兼容很重要。如果高版本不兼容低版本，可能更麻烦，需要重新编译了。
### 关注我
