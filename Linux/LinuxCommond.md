---
layout: post
title: Linux command
permalink: new-page.html
description:  This something about linux
date: 2018-07-17 22:00:08 +08:00
tags: "Linux"
---

#Linux command   man
--------------------
##  man 的九层境界
1.  用户在shell下的可以操作的命令或可执行文件
2.  用户内核可调用的函数和工具
3.  一些常用的函数与函数库，大部分为C语言的函数库
4.  设备文件的说明，通常在/dev 下的文件
5.  配置文件或者是某些文件的格式
6.   游戏
7.   惯例与协议等
8.   系统管理员可用的管理命令
9.   跟kernel有关的文件
常见要记住的 是 1（用户可执行），5（配置文件），8（系统管理员可执行）


## Run level 
*  run level 0  关机  可以使用init 0 直接关机
*  run level 3   命令行模式
*  run level 5   含有图形界面模式
*  run level 6   重启  init 6 可以直接重启电脑

## service   firewalld 服务进程 
* 启动一个服务：systemctl start firewalld.service
* 关闭一个服务：systemctl stop firewalld.service
* 重启一个服务：systemctl restart firewalld.service
* 显示一个服务的状态：systemctl status firewalld.service
* 在开机时启用一个服务：systemctl enable firewalld.service
* 在开机时禁用一个服务：systemctl disable firewalld.service
* 查看服务是否开机启动：systemctl is-enabled firewalld.service;echo $?
* 查看已启动的服务列表：systemctl list-unit-files|grep enabled

## linux 配置静态ip方法 
 0.  使用 ip addr 查看当前本机ip 网卡信息。
 1.  进入到# cd /etc/sysconfig/network-scripts    文件夹 找到对应的网卡配置文件
 2.  修改如下   
      >BOOTPROTO=static #dhcp换成static  使用静态ip
      >ONBOOT=yes #将no换成yes    表示系统接口将在系统启动时候开启
      >IPADDR=192.168.86.88
      >NETMASK=255.255.255.0
      >GATEWAY=192.168.86.2
 3.  重启网络大功告成  
     service network restart   ||  systemctl restart network.service
 4.  查看修改后 的ip 是否正确   ip addr

 ## 用户组的操作
  
  *  添加一个用户组   用户组名称为grpone gid为10086（Gid是系统中唯一标识，不能重复的）
     > groupadd -g 10086  grpone
  *  查看用户组列表   nano /etc/group  文件内有所有组的信息
  *  删除一个用户组
     > groupdel grpone
  *  修改一个用户组
  *  显示用户所属的用户组
     >groups
  *  newgrp 切换到相应用用户组?
  ## 用户
   ###基本操作
    * useradd  
    * userdel
    * usermod
    > 修改用户主要组， 将admin从A组脱离去B组.  
    >> usermod -G B  admin  这时候admin会自动脱离A组进入B组
    > 修改用户附加组   在保留A组的前提下，将admin加入B组
    >> usermod -a(--append) -G B admin   这个的执行结果就是usermod 同时在A 和B 两个小组内
    >从用户组中删除用户
    >>编辑/etc/group 找到GROUP1那一行，删除 A或者用命gpasswd -d A GROUP
    * grep liuyang /etc/passwd   在passwd文件中查找liuyang
   ###基本参数
    *  -d 目录     指定用户主目录，（默认是在/home目录下创建和用户名一样的目录）
    *  -g 用户组   指定用户所属的用户组(主组）vi
    *  -G 用户组   指定用户所属的附加组（这些组必需事先已经增加过了或者是系统中已经存在）
    *  -s Shell    指定用户的登录Shell
    *  -u UID      指定用户的用户号，如果同时有-o选项，则可以重复使用其他用户的标识号
    *  -c 描述     指定一段注释性描述
    *  -m          使用者目录若不存在则自动建立（默认选项）
    ##例子
    > useradd -g usergrouptwo -G admins -u 666  -s /sbin/nologin admin2
    添加一个用户的主组是 usergrouptwo 的用户， 同时附加组有admins 同时用户的Gid为666
    然后进入 /etc/group中查看可以看到
    > admins:x:995:admin2
      liuyang:x:1000:
      usergrouptwo:x:777:
    进入 /etc/passwd中可以看到
    > liuyang:x:1000:1000::/home/liuyang:/bin/bash
      admin2:x:666:777::/home/admin2:/sbin/nologin
    说明了我设置成功了一个  Gid为666的，在Gid为777的主组中，同时又在附加组Gid为995的安全组中
    让普通用户拥有root权限
    > 修改/etc/passwd 中用户的UID 和GID 全部都为0  登陆普通用户即为root
    > cat /etc/shadow 可以查看用户名
  ## 文件管理
     chgrp    change group  改变文件所属用户组
     >修改文件夹下所有文件的归属文件组
     >>chgrp  -r w
     chown    change owner  改变文件拥有者
     >chown -R admin   Dirctiroy  遍历修改文件夹下所有的文件的拥有着是admin
     chmod    change mode   改变文件权限 
     >chmod  777  filename      4+3+1 = read + write + run     三组权限的读写执行
     >owner、group和others三种身份各自的三个权限，我们用u、g、o代表三种身份，用a（all）代表所有身份
     >> chmod -R u=rwx,go=rx testdir   给testdir里面所有文件及文件夹 拥有者读写的权限，组和其他人读取和运行的权限
     >> chmod a+w testdir  给所有用户 文件写的权限
     >> chmod a-x testdir  去掉所有用户执行的权限
## 禁止root的远程登陆 和禁用22端口
   1. 进入/etc/ssh/ssh_config文件中，取消Port端口注释，修改22为10002
   2. 进入/etc/ssh/sshd_config文件中 ，修改Port 10002
   3. 修改sshd_config PermitRootLogin 为no  禁止root登陆
   4. 使用命令重启 systemctl  restart sshd.service


## 查看系统版本信息 uanme
   >uname -a (--all)
   >Linux iZj6c2p9s8t15rrzq3vszoZ 3.10.0-514.16.1.el7.x86_64 #1 SMP Wed Apr 12 15:04
    * 操作系统       >uname  -s sysname
    * 网络主机名称   >uname -n  nodename
    * 发行版本号     >uname -r  release
    * 系统内核架构  处理器类型 >uname -p   processor  
 
## Linux 的文件种类  
   使用ll 查看文件列表
   * [-]  
   * [d]  目录文件夹
   * [p]  pipe 管道 first in first out  IO序列化流吧
   * [s]  套接字 socket类型文件  数据接口文件。
   * [b]  byte设备文件 一般指的是硬盘。
   * [c]  字符设备文件， 串口设备 如鼠标，键盘等llll

 ##  相对路径和绝对路径
   * 使用cd  /ect/init.d 这样的称之为绝对路径
   * 使用cd  ./a/ab/ 这里的的 ./是相对路径 指的是当前目录下的
   * 使用cd  ../b/c  这里的 ../ 值得是相对路径 当前文件夹下的上一个目录的 /b/c文件夹。 等同于  cd ..  cd /b/c
   * 使用cd - 查看上一个目录历史
 ## 重要的一些文件夹
   * sbin   system bin    有两个位置 一个是 usr/sbin/  非系统运行的重要执行文件，网络服务器启动之类  一个是user/local/sbin这个是安装的第三方软件的系统执行文件
   * bin  重要的执行文件，一般在 user/bin中。 如yum   su     du jobs等等
   * usr unix software  resource
   * opt 装在本机使用第三方软件 。   如games
   * dev device 设备文件
   * srv service  www ,ftp server
   * etc 配置文件
   * var 软件运行时的数据文件 如mysql的数据库可以考虑放在var中
   * mnt 和media作用相同，只是额外的设备， U盘，手写板之类的c'd
   * media  挂载的CD 软驱， dvd  floppy cdrom
   * proc 虚拟文件系统，他放置的数据都在内存中。  如内核，进程，网络状态，外部设备状态。比较重要的有cpuinfo  dma  interrupts
   * sys  跟proc类似不占用系统磁盘空间，虚拟文件系统 记录内核相关信息

## 文件系统
  文件的创建 mkdir
  * 创建多层文件夹 mkdir -p  /home/Test/beat/
  * 删除多级空文件夹  rmdir -p /home/Test/beat 会将上上面建立的Test 和beat文件夹删除。因为他们都空了。

  文件的复制 cp
  * 复制完整属性的文件 即文件拥有着不变，文件所在组不变。 文件创建时间不变的三不变备份文件
    >cp -a  a.txt  b.txt
    得到的结果如下(在使用root权限的前提下)
    >-rwxr-----. 1 liuyang admins 10 7月  19 09:03 a.txt
    >-rwxr-----. 1 liuyang admins 10 7月  19 09:03 b.txt
     得到的结果如下(在使用root权限的前提下，不使用-a参数情况下)
    >-rwxr-----. 1 liuyang admins 10 7月  19 09:03 a.txt
    >-rwxr-----. 1 root root 10 7月  19 09:05 b.txt
    可以发现-a 可以完整的复制文件的属性信息，保证原始文件权限的完整性。不被修改。
    复制文件夹的时候 注意加上-r  才能递归复制，否之会复制直白
    >cp -a  /etc/  .<-------这里的点指的是把etc文件夹复制到当前文件夹
    硬连接和软连接
    >cp -s a.txt  a  软连接   相当于window 中快捷方式  在ll中会有提示   a-> a.txt
    >cp -l a.txt  b  硬链接   
    运行结果
    >lrwxrwxrwx. 1 root root   5 7月  25 22:44 a -> a.txt
    >-rw-r--r--. 2 root root 117 7月  25 22:45 a.txt
    >-rw-r--r--. 2 root root 117 7月  25 22:45 b
    分析： 
       不同点： 
            硬核连接比较牛逼一点是硬核连接是一个文件，保留了a文件的属性
            软连接就是一个快捷方式是link类型的，同时软连接权限好低
        相同点：
           都可以vi修改，修改后，三者文件仍是一致的。

    使用cp -u 备份文件   使用-u参数的时候， cp命令会比较两个文件有差异的时候才会复制。没差异就不复制了。
    > cp a.txt b.txt
    > cp -u a.txt b.txt
    使用cp -d 复制软连接
    在复制软连接的时候， 默认会把源文件复制过去， 如果仅仅想复制软连接需要加cp -d
    >  cp -s a.txt a_slink
    >  cp -d a_slink a_slink1
    >  cp    a_slink a_slikn2
    >  ls
    运行结果可以看到 -d复制的是link   不加-d默认复制的是文件
    >   lrwxrwxrwx. 1 root root   5 7月  25 22:44 a -> a.txt
    >   -rw-r--r--. 1 root root 117 7月  25 23:47 a_slink
    >   lrwxrwxrwx. 1 root root   5 7月  25 23:48 a_slink2 -> a.txt

## 文件的查看     
     *cat 从第一行开始显示文件内容
     *tac 从最后一行开始显示文件内容， （cat的反过来）
     *nl  显示的时候 顺便输出个行号
     *more 一页一页显示文件内容
     *less 和more一样，但是比more好用，可以往前翻页
     *head 只看头几行
     *tail 只看后几行
     > tail -n 10   message.txt  查看十行信息
     > tail -f catlinna.out  可以持续查看文件输出内容，直到ctrl+c中止才结束
     *od   二进制看文件
     怎样第10 -20行之间的文字
     >head -n 20 a.txt|tail-n 10
## 文件的三个时间
    1. modification time 最后修改事件  mtime   ，文件被修改的时候 会修改mtime（默认的）
    2. statu time   文件权限与属性最后被修改事件， 如修改小组的时候 会更改ctime
    3. access time 文件最后一次被命令调用的时间， 如cat a.txt  时候机会更新atime（这个时间不可以被修改）
    查看这三个时间
    >ls  -l a.txt(默认的mtime)
    >ls  -l --time =atime a.txt
    >ls  -l --time =ctime a.txt  
    touch（创建文件或者修改文件时间，可以修改atime和mtime）
    >touch test   (创建一个文件)
    查看文件所有时间的属性
    >ll test; ll  --time=atime  test ;ll --time=ctime(查看文件的时间属性)
    修改文件的时间（默认同时修改 mtime 和atime 如果要单独修改可加参数-a[access] 或者-m[modifiation]）
    >touch -d "1 days ago"  test  #取一天前日期
    >touch -t "0805141212"  test  #直接指定日期

    umask命令用来设置限制新建文件权限的掩码。当新文件被创建时，其最初的权限由文件创建掩码决定。用户每次注册进入系统时，umask命令都被执行， 并自动设置掩码mode来限制新文件的权限。用户可以通过再次执行umask命令来改变默认值，新的权限将会把旧的覆盖掉。
    >umask
    >0022    =======> 跟权限有关的是后面三个数字，有几就拿7减去几，user group other    user拥有7的权限，group拥有5的权限，other也是拥有5的权限
    >umask -S   ===> u=rwx   g=rx o=rx 表示用户创建文件的三种权限
    显示出来的是被去掉的权限
    文件因为可以被执行，所以是那7去减。
    目录因为不可以被执行，所以是拿6去减。
    以下是0022默认情况，新建的文件 和新建的文件夹的默认权限
    >-rw-r--r--. 1 root root 0 8月   1 21:53 liuyang（7-0 7-2 7-2）
    >drwxr-xr-x. 2 root root 6 8月   1 21:54 liuyangs（6-0 6-2 6-2）
    #隐藏文件
   