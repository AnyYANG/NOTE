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
     chown    change owner  改变文件拥有者
     chmod    change mode   改变文件权限 



## 查看系统版本信息 uanme
   >uname -a (--all)
   >Linux iZj6c2p9s8t15rrzq3vszoZ 3.10.0-514.16.1.el7.x86_64 #1 SMP Wed Apr 12 15:04
    * 操作系统       >uname  -s sysname
    * 网络主机名称   >uname -n  nodename
    * 发行版本号     >uname -r  release
    * 系统内核架构  处理器类型 >uname -p   processor  
  
