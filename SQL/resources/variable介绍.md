# SQL 变量的常规知识

### char 与 varchar
   char是固定长度字符串。 长度是0-65位 ，使用的时候直接声明为char(5),这里的5指的是字符长度。如果存入一个只有一个字符，
 char会自动在字符串尾部添加空格填充到5位。char是固定字符长度的变量类型，char时候存储长度一样长的数据。例如MD5值

   varchar是可变长度的固定字符串。长度是0-65535位不定的。使用的时候直接声明为varchar(5)，这里的长度为字符长度。具体存入
 数据库长度，具体占用字节数是由编码集和字符长度决定。 在这里， "abc" 和 "马力大", 所占用的字符长度是一样的，
 都是占用三字符 也不会很蠢的给不够长度的varchar加空格来补齐长度。 也就是说当保存一个"forever " varchar不会将最后的空格
 给trim掉。
 
---

 >  mysql官方规定 数据行最大长度不能超过65535个字节。
 
--- 

 所以，在规定varchar的时候一定要注意整体数据行的空间大小考虑

```mysql

-- 设计一张表， 表内只有一个varchar是utf-8的类型。 则varchar长度是65535-3/4=21845个 也就是说这张表中单条记录最多存储 21845个数据
drop table t1;
CREATE TABLE  t1 (name varchar(21845) default  null) charset = utf8;

-- 设计一张表， 表内只有一个varchar是latin1的编码类型。 则varchar长度是65535-3=65532个长度。 也就是说这张表中单条记录最多存储 21845个数据
drop table t2;
CREATE TABLE  t2 (name varchar(65532) default  null) charset = latin1;


```