## cs_java

### 目的

空闲时间练手

### 系统描述

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成后台业务系统，通用操作，减少重复工作代码开发时间。整合常用的中间件技术，为后续系统性能扩展进行准备；

## 详细的使用及说明文档

**语雀(ps:刚开始写，边开发边完善)**：https://www.yuque.com/wuzhiaite/ailopg/vfia6q

**线上地址(ps:服务器只有1M带宽，首次访问慢的会让人发指)**：http://www.wuzhiaite.top/

### 使用到的技术点及相关文档

#### 使用的技术点

```
1.基础框架使用springboot,整合安全框架springSecurity;

2.Excel数据导出功能，整合阿里的EasyExcel;

3.整合分布式的定时任务，使用的是许雪里开源的XXL项目

4.Word文档打印，使用POI

5.整合了Redis,RabbitMQ,ElasticSearch中间件

6.整合activiti 6.0.0,完成通用流程开发

7.持久层框架使用mybatis-plus

7.nacos进行配置统一托管
```
#### 技术参考文档

**SpringSecurity:**

**activiti:** https://www.activiti.org/userguide/#bpmnStartEvents

**XXL:** https://www.xuxueli.com/

**EasyExcel**https://www.yuque.com/easyexcel/doc/fill

**Springboot 整合 RabbiMQ:** https://docs.spring.io/spring-amqp/docs/2.2.6.RELEASE/api/org/springframework/amqp/rabbit/annotation/EnableRabbit.html

**springboot 整合ElasticSearch:**

**springboot 整合quartz:**





### 功能实现：
```
1.整合springsecurity,登录校验；

2.人员配置，部门配置，角色配置，菜单动态配置；

3.通用字段配置；

4.通用列表页面配置生成，通用列表数据展示查询；

5.后台数据缓存，字典数据存储ES，异步数据RabbitMQ处理等

6.通用列表EXCEL文档导出；
```


#### 开发备注

##### 单表报表统计

**单表查询校验链UML图**
![avatar](img/uml/checkchain_uml.jpg)
**JWT密钥生成**
````
生成jwt非堆成加密文件  

keytool -genkey -alias jwt -keyalg  RSA -keysize 1024 -validity 365 -keystore jwt.jks
````







