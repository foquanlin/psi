#### [腾讯云2019新春采购节](https://cloud.tencent.com/redirect.php?redirect=1036&cps_key=30280f92fc381dfc9e1d9e0e23d25a18&from=console)：
【开年好云 钜惠来袭】2019新春采购节，30款云产品感恩回馈。
[![腾讯云2019新春采购节](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/active/tx/520_120.jpg)](https://cloud.tencent.com/redirect.php?redirect=1036&cps_key=30280f92fc381dfc9e1d9e0e23d25a18&from=console)

#### [阿里云开年采购季](https://www.aliyun.com/acts/product-section-2019/new-users?userCode=i8s6n64p)：
入门爆款云服务器，降低采购成本，开发者和小微企业首选。
[![阿里云开年采购季](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/active/ali/540x250.jpg)](https://www.aliyun.com/acts/product-section-2019/new-users?userCode=i8s6n64p)

#### 项目说明
- platform-plus是一个轻量级的，前后端分离的Java快速开发平台
- 代码生成工具已完美支持MySQL、Oracle
 

#### 具有如下特点
- 严格遵循阿里编码规约开发，便于阅读及二次开发
- 支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer2005、SQLServer 等多种数据库
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面和按钮，满足绝大部分的权限需求
- 完善的代码生成机制，可在线生成vue、controller、entity、xml、dao、service、vue、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云、本地存储
- 自定义实现swagger文档支持，方便编写API接口文档
- 使用Mybatis拦截器实现数据权限，对代码侵入小


#### 技术选型：
- Spring Boot 2.1.0.RELEASE
- Apache Shiro 1.4.0
- Spring MVC 5.1.2
- MyBatis 3.5.0、MyBatis-Plus 3.1.0
- Quartz 2.3.0
- Druid 1.1.10
- lombok 1.18.4
- swagger 2.9.2
- jwt 0.9.1
- easypoi 4.0.0


#### 项目结构
```
platform-plus
├─sql  项目SQL语句
│
├─common 公共模块
│  ├─annotation 自定义注解
│  ├─aspect 系统日志、redis存储
│  ├─exception 异常处理
│  ├─interceptor 日志拦截器
│  ├─session 分布式session管理
│  ├─utils 工具类
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─datascope 数据权限拦截器
│ 
├─datasources 多数据源
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─gen 代码生成模块
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  ├─swaggerbootstrapui 自定义swagger文档模块
│  └─sys 权限模块
│ 
├─PlatformPlusApplication 项目启动类
│  
├──resources 
│  ├─gen 代码生成工具
│  ├─mapper SQL对应的XML文件
│  └─static 自定义swagger文档

```


## 实现功能

- 系统管理
    - 菜单管理
    - 组织机构
    - 系统参数
    - 字典管理
    - 文件上传
    - 系统日志
- 权限管理
    - 管理员列表
    - 角色管理
- 短信平台
    - 短信配置
- 任务调度
    - 定时任务
- 开发工具
    - 在线用户管理
    - 缓存信息
    - SQL监控
    - 接口文档
    - 代码生成器

**项目演示**
- 演示地址：http://fly2you.cn/platform-plus/#/login
- 账号密码：admin/admin


**效果图：**

- 菜单管理
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/platform-plus.jpg](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/platform-plus.jpg "菜单管理")
- 字典管理
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/dict.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/dict.png "字典管理")
- 在线人数
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/users.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/users.png "在线人数")
- 缓存数据
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/doc.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/redis.png "缓存数据")
- 接口文档
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/doc.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/doc.png "接口文档")

#### 后端部署
- 通过git下载源码
- 创建数据库plaftorm-plus
- mysql执行sql/mysql.sql文件(oracle执行sql/oracle.sql)，初始化数据
- 修改application-dev.yml，修改MySQL、Oracle驱动、账号和密码
- Eclipse、IDEA运行PlatformPlusApplication.java，则可启动项目
- Swagger路径：http://localhost:8888/platform-plus/doc.html

#### 提交反馈

1. 欢迎提交 issue，请写清楚遇到问题的原因，开发环境，复显步骤。

2. 不接受`功能请求`的 issue，功能请求可能会被直接关闭。  

3. 官方QQ群：
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=ac742b7481b95fac926a3f2196085108bceeebcdf14bd716cbea519751e69445"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="微同软件 ①群" title="微同软件 ①群"></a>
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=dcb460bfa21213a2712677bab7292fd8eb2138a1914af5af397b58e7c02690c5"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="微同软件 ②群" title="微同软件 ②群"></a>
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=c7657db5d4e65a40e46ee5998534f7b9b9fa56d0347c3af3157c7f6240c0d0dd"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="微同软件 ③群" title="微同软件 ③群"></a>
#### 鸣谢 

- [renrenio](https://www.renren.io)
- [mybatis-plus](http://mp.baomidou.com)
- [easypoi](https://opensource.afterturn.cn)


#### 常用API
- [Mybatis-Plus](https://baomidou.gitee.io/mybatis-plus-doc/#/quick-start)
- [Vue](https://cn.vuejs.org/v2/api/)
- [element-ui](http://element-cn.eleme.io/#/zh-CN/component/installation)
- [echarts](https://www.echartsjs.com/api.html#echarts)
- [iconfont](https://www.iconfont.cn/search/index)