
轻量级Rpc框架guobbo使用说明：

服务提供者调用：
InitServer.init(zookeeperAddress,localAddress);  完成服务注册以及端口监听。

服务调用者调用：
InitClient.init(zooleeperAddress);  完成服务发现。

服务暴露:
在服务实现类上添加注解@RpcService（value=interface.class） 完成服务暴露。

服务调用:
interface bean=RpcProxy(interface.class);  透明化完成对象代理以及网络通信。
