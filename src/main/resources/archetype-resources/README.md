快嘉框架是一个面向变化的J2EE开发模型最小实践，它将一个传统的J2EE单体应用模块化，分成了包括war、批处理等在内的主应用和可被依赖的普通jar独立维护；这个模型利用maven构建，主应用通过spring集成它所依赖的所有jar后可以独立部署、启动，对外提供服务。 

**快嘉框架将需求迭代过程中最容易变化的数据库持久层面和控制器层面的代码作为模块独立出来，并通过集成快嘉代码生成插件生产代码；一般开发人员只需要维护针对数据库定义和接口分别维护sql文件和xml文件，就可以通过快嘉代码生成插件迅速获得这部分模块代码，可以在一定程度上减轻工作量。** 

快嘉开发框架将一个标准的J2EE项目拆分为基础模块(${rootArtifactId}-base)、接口和报文定义模块(${rootArtifactId}-bundle)、业务模块(demo-biz)、批处理系统（demo-batch）、接口系统(demo-provider)、接口API模块(demo-api)6个部分并行开发建设，其中基础模块(demo-base)、接口和报文定义模块(demo-bundle)和接口API模块(demo-api)都可以由快嘉代码生成插件生成代码。另外还有一个单元测试模块（demo-test），将业务模块(demo-biz)的单元测试独立出来，集中在这个模块编写，测试框架是testng，使用了内存数据库h2和powermock。

该框架基于spring4+mybatis3.3.0搭建，持久层为mysql，示例demo完整实现了注册、登录和自动登录接口，具体业务逻辑可参考demo-biz模块。

另外本框架还提供了接口模拟系统(${rootArtifactId}-provider-mock)和接口测试模块(${rootArtifactId}-api-test),其中接口模拟系统(demo-provider-mock)依赖接口模拟实现模块(demo-bundle-mock)，集成了swagger-ui，可以作为一个接口文档服务器和即时的联调接口系统对外发布；开发人员可以利用接口测试模块(demo-api-test)可以对指定环境部署的接口进行自动化测试。而接口模拟实现模块(demo-bundle-mock)和接口测试模块(demo-api-test)都可以由快嘉代码生成插件生成相关代码，开发人员直接使用即可。

具体业务逻辑可参考业务模块(${rootArtifactId}-biz)代码实现。