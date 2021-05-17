# grpcDemo1
这是一个grpc的入门Demo，主要内容如下
- proto目录中定义了`.proto`文件，涉及多种类型（嵌套、枚举等）
- 其中，`laptop_service.proto`定义了请求、响应和RPC服务
- java目录中是源程序，结构如下
  - sample：创建proto中定义的消息对象
  - serializer：测试消息对象的序列化与反序列化
  - service：实现一个简单的RPC通信（Client、Server、Service）
