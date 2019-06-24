## 服务器推荐配置
### 服务器配置：
    CPU：2核
    内存：8G
    实例规格：标准型S2机型
    带宽：10M
    价格：3999元/三年

购买说明：如果业务量较大，推荐双机部署。

### MySQL高可用版配置：
    CPU：1核
    内存：1G
    硬盘：100G
    价格：423元/三年

### COS资源包：
    标准型存储容量：
    100GB	1年	88元
    200GB	1年	171元
    下行流量：
    100GB	3个月	37元
    200GB	3个月	71元
    500GB	3个月	177元
[抢购地址：https://cloud.tencent.com](https://cloud.tencent.com/redirect.php?redirect=10562&cps_key=30280f92fc381dfc9e1d9e0e23d25a18)

#### 项目说明
- platform-plus是一个轻量级的，前后端分离的Java快速开发平台
- JavaDoc：https://apidoc.gitee.com/fuyang_lipengjun/platform-plus/
- 前端地址：https://gitee.com/fuyang_lipengjun/platform-plus-ui
 
#### 优势
- 严格遵循阿里编码规约开发，便于阅读及二次开发
- 支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer2005、SQLServer 等多种数据库
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 支持结合ELK实时日志分析系统，方便日志查询，问题排查，上线检查
- 灵活的权限控制，可控制到页面和按钮，满足绝大部分的权限需求
- 可在线生成vue、controller、entity、xml、dao、service、vue、sql代码，增删改查代码一键生成，减少80%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云、本地存储
- 自定义实现swagger文档支持，方便编写API接口文档
- 使用Mybatis拦截器实现数据权限，对代码侵入小
- 完成Activiti6集成，可实现在线流程编辑

#### 技术选型：
```
- Spring Boot 2.1.0.RELEASE
- Apache Shiro 1.4.0
- Spring MVC 5.1.2
- MyBatis 3.5.0、MyBatis-Plus 3.1.0
- weixin-java-mp 3.4.0
- Quartz 2.3.0
- Druid 1.1.10
- lombok 1.18.4
- swagger 2.9.2
- jwt 0.9.1
- easypoi 4.0.0
- Activiti6.0.0
```

#### 项目结构
```
platform-plus
├─sql  项目SQL语句
│
├─platform-admin 管理后台(port:8888)
│ 
├─platform-api 接口服务(port:8889)
│ 
├─platform-mp 公众号服务(port:8890)
│ 
├─platform-biz 业务、数据处理
│ 
└─platform-common 公共类

```


## 实现功能
```
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
- 工作流管理
    - 流程操作
    - 模型管理
- 开发工具
    - 在线用户管理
    - 缓存信息
    - SQL监控
    - 接口文档
    - 代码生成器
- 邮件系统
    - 发送记录
- ELK日志
```

**项目演示**
- 演示地址：http://fly2you.cn/platform-plus/#/login
- 账号密码：
  - admin/admin
  - test/888888
  - test1/888888
  - test2/888888
  - test3/888888
  - test4/888888
  - test5/888888
  - test6/888888
  - test7/888888
  - test8/888888
  - test9/888888
  - test10/888888


**效果图：**
- ELK日志分析
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/elk.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/elk.png "ELK日志分析")
- 流程操作
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/reprocdef.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/reprocdef.png "流程操作")
- 模型管理
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/remodel.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/remodel.png "模型管理")
- 流程编辑
![https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/editor.png](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/platform-plus/editor.png "流程编辑")
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
- 修改admin、api模块下application-dev.yml，修改MySQL、Oracle驱动、账号和密码
- 运行PlatformAdminApplication.java启动后台管理接口服务
       
       -接口：http://localhost:8888/platform-admin
- 运行PlatformApiApplication.java启动api接口服务

        -接口：http://localhost:8889/platform-api
        
- Swagger路径：http://localhost:8889/platform-api/doc.html

#### 提交反馈
1. 欢迎提交 issue，请写清楚遇到问题的原因，开发环境，复显步骤。

2. 官方QQ群：
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=ac742b7481b95fac926a3f2196085108bceeebcdf14bd716cbea519751e69445"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="微同软件 ①群" title="微同软件 ①群"></a>
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=dcb460bfa21213a2712677bab7292fd8eb2138a1914af5af397b58e7c02690c5"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="微同软件 ②群" title="微同软件 ②群"></a>
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=c7657db5d4e65a40e46ee5998534f7b9b9fa56d0347c3af3157c7f6240c0d0dd"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="微同软件 ③群" title="微同软件 ③群"></a>

#### 常用API
- [Mybatis-Plus](https://baomidou.gitee.io/mybatis-plus-doc/#/quick-start)
- [Vue](https://cn.vuejs.org/v2/api/)
- [element-ui](http://element-cn.eleme.io/#/zh-CN/component/installation)
- [echarts](https://www.echartsjs.com/api.html#echarts)
- [iconfont](https://www.iconfont.cn/search/index)
