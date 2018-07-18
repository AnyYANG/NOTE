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

## service 
* 启动一个服务：systemctl start firewalld.service
* 关闭一个服务：systemctl stop firewalld.service
* 重启一个服务：systemctl restart firewalld.service
* 显示一个服务的状态：systemctl status firewalld.service
* 在开机时启用一个服务：systemctl enable firewalld.service
* 在开机时禁用一个服务：systemctl disable firewalld.service
* 查看服务是否开机启动：systemctl is-enabled firewalld.service;echo $?
* 查看已启动的服务列表：systemctl list-unit-files|grep enabled

