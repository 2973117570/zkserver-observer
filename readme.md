## zkserver-observer项目简介

- 提供Zookeeper Server集群上的文件创建和删除。
- 其它应用可以通过此文件包，将观察者注册到Zookeeper上
- 如果Zookeeper上的配置文件有变更，则通知注册方
- 注册方收到通知后，就可以进行相关配置信息的操作。

## zkserver-observer项目用途

- 可以用于配置文件同步
- 用于分布式集群协调各个节点的信息同步。


## zkserver-observer运行示例
- 运行TestZkConfig下的Main函数
- ZkConfigChangeSubscriberImplTest  TestC();
- 观察Zookeeper上的 test1.properties，修改时 监听会打印出：
  test1接收到数据变更通知: key=test1.properties, value=test=12
  
## zkserver-observer使用场景
- 分布式应用配置文件同步
- 微服务的状态一致性设计
- 多个分布式节点数据的协调


