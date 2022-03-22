## Book-sales-management-system（图书销售管理系统）
使用JavaSE+Javaawt+MySQL搭建的一个图书销售管理系统，主要涉及一个数据库，内含四张SQL表，管理员信息表，用户信息表，订单信息表，图书信息表。其中用户表，图书表，订单表之间建立了关联关系。系统拥有
用户管理，图书管理，图书销售，信息查询四大功能模块。


### 主要实现的功能

1、用户注册，用户登录，修改密码，用户注销，购买图书，订单查询

2、（管理员）添加图书，修改图书，删除图书，查询库存，促销设置

3、（管理员）添加用户账号，删除用户账号，查询用户账号。

4、（管理员）订单查询，图书信息查询


### 涉及的技术
1、主要编程语言：Java（**JDK15**）

1、用户界面设计：Java awt+WindowsBuilder

2、数据库：MySQL 8.0 + JDBC（**mysql-connector-java-8.0.26.jar**）


### 数据库概念模型

![](https://s3.bmp.ovh/imgs/2022/03/4be09afb07c4d23e.png)


### 最终效果图

![](https://s3.bmp.ovh/imgs/2022/03/110881cbd2392a67.jpg)
![](https://s3.bmp.ovh/imgs/2022/03/b18afc759db7e880.jpg)
![](https://s3.bmp.ovh/imgs/2022/03/b51e63f24abc9e20.jpg)


### 备注
使用前请先确定Mysql版本大于8.0（否则需要修改jdbcUrl内容）并于Connect.java中配置Mysql账号信息，使用sql脚本创建初始数据库，导入项目配置好JDBC驱动并确保JDK版本为15或更高。

