# dod
Database of Documents

《面向对象与多线程实验课程》档案管理项目主分支
dod-v1命令行交互方式，用户和档案数据存放于container中。
dod-v2 图形化用户界面的桌面应用程序。
dod-v3 基于JDBC，将用户和档案数据持久化到Mysql的document数据库中。
dod-server-v4 基于Socket和多线程的服务器端程序。
dod-client-v4 依赖于服务器端的两个客户端程序，分别支持命令行交互和图形化用户界面。

————————— 版本说明————————— 
dod-v1 命令行界面，数据持久化至二进制文件，执行入口ConsoleApplication.java。
dod-v2 图形化用户界面，数据持久化至二进制文件，执行入口GuiApplication.java。
dod-v3 数据持久化至关系数据库document，支持命令行界面 和 图形化用户界面，执行入口分别为ConsoleApplication.java和GuiApplication.java。
dod-v4-server 网络版的服务器端程序，执行入口 ServerApplication.java。
dod-v4-client 网络版的客户端程序，支持命令行界面 和 图形化用户界面，执行入口分别为ConsoleApplication.java和GuiApplication.java。

————————— 项目依赖说明————————— 
在各个项目的属性对话框中配置项目构建依赖关系，操作如下：
右键项目，在Build Path上下文菜单中选择Configure Build Path....
在Java Build Path中选择Projects选项卡，点击Add...
在Required Project Selection对话框中，勾选依赖的项目。

dod-v2依赖于dod-v1。
dod-v3依赖于dod-v1和dod-v2。
dod-v4-client依赖于dod-v1和dod-v2。
dod-v4-server依赖于dod-v1、dod-v2和dod-v3。


————————— 项目代码说明—————————
所有常量包含在Constants.java，其中存放文件路径和数据库端口号根据各自实验环境进行调整。

领域模型类位于包：edu.whut.cs.oo.domain 包含User及其三个子类，业务实体类Document（档案）和Archive（案宗）。
Administrator、Operator、Browser中通过字符串数组定义了各自的功能列表，可以随时进行增减调整。

所有自定义异常类位于包：edu.whut.cs.oo.exception 包含自定义异常的父类BaseException，以及文件访问、业务实体不存在、密码错误等自定义异常子类。

项目采用分层架构，包含：控制器（界面）层、业务服务层、数据访问层，各层之间针对接口interface进行调用。
命令行界面的控制器层位于包：edu.whut.cs.oo.action。
图形化用户界面的控制器与界面位于包：edu.whut.cs.oo.frame。
业务服务层接口和实现类分别位于包：edu.whut.cs.oo.service 和 edu.whut.cs.oo.service.impl。
数据访问层接口和实现类分别位于包：edu.whut.cs.oo.dao 和 edu.whut.cs.oo.dao.impl（包含内存容器实现类、二进制文件实现类、JDBC数据库实现类）。

————————— 组件装配中心 Application.java ————————— 
currentUser对象属性代表当前登陆用户。
实例化具体的Dao数据访问对象，注入到对象属性userService、documentService和archiveService中，供上层的各个控制器统一使用。
项目所有组件的装配注入在Application.java中实现，根据不同版本需求，实例化service层和dao层接口的实现类impl。

————————— 网络版客户端————————— 
复用dod-v1，dod-v2和dod-v3中的控制器层（action和frame）、service层。
新增service层的网络访问实现类：BaseServiceClient.java, DocumentServiceClient.java, UserServiceClient.java。
新增网络访问异常类。
新增封装应用层协议的Message.java，作为与服务器端通信的载体。

————————— 网络版服务器端————————— 
复用dod-v1，dod-v2和dod-v3中的service层和dao层类。
新增监听器组件Listener.java，可以根据实际环境调整监听端口常量。
根据业务功能需求新增一组服务器端控制器，位于edu.whut.cs.oo.controller包。
新增请求分配器Dispatcher.java，根据客户端的请求类型导向至不同的控制器进行处理。

