## 联系方式
!['我的二维码.jpg'](我的二维码.jpg)

## 服务器推荐配置
| 名称        | CPU | 内存  | 硬盘  |  价格  |
| --------    |----:|:---:|:---:| :----: |
| 服务器(标准型S2机型 5M 双机)       |  2核 | 4G  | 50G |       |
| MySQL高可用版|  1核 | 1G  | 20G |     |


#### 项目说明

# 项目会持续不断完善更新,欢迎关注star,fork
  - gitee源码地址: https://gitee.com/foquanlin/psi
  - github源码地址: https://github.com/foquanlin/psi
### 使用案例

以下为部分案例：

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
- 接口支持微信开放平台、微信公众平台、微信小程序、微信公众号开发
- 移动端已支持微信小程序登录、微信公众号登录、支付宝小程序登录

#### 技术选型：
```
- Spring Boot 2.7.0.RELEASE
- Apache Shiro 1.9.1
- Spring MVC 5.1.2
- MyBatis 3.5.0、MyBatis-Plus 3.5.2
- weixin-java-mp 4.4.0
- weixin-java-miniapp 4.4.0
- weixin-java-pay 4.4.0
- weixin-java-open 4.4.0
- alipay-sdk 4.31.28.ALL
- Quartz 2.3.0
- Gson 2.9.0
- jedis 3.8.0
- lombok 1.18.24
- swagger 3.0.0
- jwt 0.9.1
- easypoi 4.4.0
- Activiti 6.0.0
- Mysql 5.7+
```

#### 项目结构
```
*** 项目结构分组件和模块,可灵活引用
*** 例如:在二次开发中需要使用支付宝接口,在项目pom文件引用相关组件即可
psi
├─ sql  项目SQL语句
├─ init-project 初始化项目脚本
├─ tongyi-component-activemq 消息中间件
├─ tongyi-component-alibaba 阿里云SDK组件
├─ tongyi-component-alipay 支付宝SDK组件
├─ tongyi-component-common 项目通用组件
├─ tongyi-component-elasticsearch 搜索引擎组件
├─ tongyi-component-elk 日志分析组件
├─ tongyi-component-excel excel文件导入导出组件
├─ tongyi-component-freemarker 模板组件(邮件模板,PDF模板,文本模板等等)
├─ tongyi-component-kafka 消息中间件
├─ tongyi-component-mssql mssql jdbc组件
├─ tongyi-component-mybatis mybatis组件
├─ tongyi-component-redis 缓存组件
├─ tongyi-component-shiro 权限组件
├─ tongyi-component-starter-admin 管理后台配置组件
├─ tongyi-component-starter-api api配置组件
├─ tongyi-component-swagger swagger文档组件2.0版本
├─ tongyi-component-tencentcloud 腾讯云SDK组件
├─ tongyi-component-tester 测试配置组件
├─ tongyi-component-utils 工具包组件
├─ tongyi-component-webconfig web服务配置组件
├─ tongyi-core 系统框架核心
├─ tongyi-module-act activiti模块
├─ tongyi-module-gencode 代码生成模块
├─ tongyi-module-job 任务调度模块
├─ tongyi-module-mail 邮件模块
├─ tongyi-module-sys 系统基础模块(系统用户,权限,部门,字典,日志,菜单等)
├─ tongyi-module-thirdstorage 第三方存储模块(阿里云,腾讯云,本地)
├─ tongyi-module-wechatmp 微信公众号模块
├─ tongyi-psi-admin 进销存管理后台(port:8801)
├─ tongyi-psi-api 进销存外部接口(port:8802)
├─ tongyi-psi-core 进销存核心类
├─ tongyi-psi-service 进销存业务逻辑模块

```


## 实现功能
```
- 系统管理
  - 单位管理
  - 仓库管理
  - 邮件系统
  - 菜单管理
  - 组织机构
  - 系统参数
  - 字典管理
  - 文件上传
  - 系统日志
- 库存管理
  - 调拨单管理
  - 批次管理
  - 品牌管理
  - 商品分类
  - 盘点管理
  - 库存明细
  - 商品库存
- 采购管理
  - 供应商管理
  - 采购订单
  - 采购退单
  - 供应商对账
- 销售管理
  - 客户管理
  - 销售订单
  - 销售退单
  - 客户对账
  - 客户订购统计
- 财务管理
  - 银行账户管理
  - 运杂费管理
  - 非销售收入
  - 非采购支出
- 报表统计
  - 分类统计
  - 采购销售统计
  - 销售排行
  - 序列号销售统计
  - 客户销售统计
  - 商品销售统计
  - 商品分类统计
  - 销售货款统计
  - 采购货款统计
  - 员工销售统计
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
- 演示地址：http://psi.wxngrok.com/#/login
- 账号密码：
  - admin/admin 
  - test/888888

**效果图：**
- 系统管理
  !['商品单位管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品单位管理.png)
  !['仓库管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/仓库管理.png)
  !['品牌管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/品牌管理.png)
  !['商品分类.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品分类.png)
  !['菜单管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/菜单管理.png)
- 库存管理
  !['库存调拨.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/库存调拨.png)
  !['新增调拨单.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/新增调拨单.png)
  !['盘点管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/盘点管理.png)
  !['盘点详情.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/盘点详情.png)
  !['商品入库.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品入库.png)
  !['商品出库.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品出库.png)
  !['商品库存.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品库存.png)
  !['商品明细.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品明细.png)
  !['商品详情.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/商品详情.png)
  !['规格管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/规格管理.png)
  !['库存明细.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/库存明细.png)
- 采购管理
  !['供应商管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/供应商管理.png)
  !['采购订单.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/采购订单.png)
  !['采购退单.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/采购退单.png)
- 销售管理
  !['客户管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/客户管理.png)
  !['销售订单.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/销售订单.png)
  !['销售退单.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/销售退单.png)
- 财务管理
  !['银行账户管理.jpg'](https://kutian-cms.oss-cn-zhangjiakou.aliyuncs.com/psi/进销存截图/银行账户管理.png)
- 报表统计

#### 后端部署
- 通过git下载源码
- 创建数据库tongyi-psi
- mysql执行sql/mysql.sql文件(oracle执行sql/oracle.sql)，初始化数据
- 修改admin、api模块下application-dev.yml，修改MySQL、Oracle驱动、账号和密码
- 运行AdminApplication.java启动后台管理接口服务
       
      -接口：http://localhost:8801/admin
- 运行ApiApplication.java启动api接口服务
 
      -接口：http://localhost:8802/api
        
- Swagger路径：http://localhost:8802/api/doc.html

#### 提交反馈
1. 欢迎提交 issue，请写清楚遇到问题的原因，开发环境，复显步骤。

### 官方QQ群：425910002

#### 常用API
- [Mybatis-Plus](https://baomidou.gitee.io/mybatis-plus-doc/#/quick-start)
- [Vue](https://cn.vuejs.org/v2/api/)
- [element-ui](http://element-cn.eleme.io/#/zh-CN/component/installation)
- [echarts](https://www.echartsjs.com/api.html#echarts)
- [iconfont](https://www.iconfont.cn/search/index)