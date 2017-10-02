### 使用须知

- 本项目支持传统项目架构,暂不支持互联网服务架构
- 支持MySQL及Oracle单机数据库,暂不支持架构式DB集群,可从应用DB层做分布式集群
- 默认支持ehcache缓存,支持2.6.8版本,支持集群,可自行配置,数据在100k以下可使用ehcache,大于请选择其他方案
- 本系统支持hibernate及MyBatis两种持久层框架,可任选其一
- 除了在webapp下编写前端代码外,不推荐在本工程下编写java代码,代码会通过[代码生成器](https://github.com/ijson/in-spring-mvc-common)生成


### 使用说明
1. 首先将in-spring-mvc导入到项目中
2. 然后将代码生成器生成的项目到idea或者eclipse中,然后在in-spring-mvc中添加生成项目的dependency
3. 在in-sprign-mvc项目下webapp->WEB-INF->applicationContext.xml文件中,导入生成项目的spring文件,一般都在生成项目resources下的spring目录下,命名规则为:in-spring-biz-${projectName}.xml,在applicationContext.xml中导入
   ```
       <import resource="classpath:spring/in     -spring-biz-${projectName}.xml"/>
   ```
4. 修改in-spring-mvc 下resources-> autoconf->in-db下的数据库文件

4. 启动tomcat即可访问controller
