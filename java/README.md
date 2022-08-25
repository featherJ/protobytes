# Java ProtoBytes library
用于二进制通信协议序列化与反序列化的底层依赖库。库内容精简，不与任何网络功能关联，只提供数据对象序列化与反序列化功能。

对于 ProtoBytes 的详细介绍可以查看：[protobytes](https://github.com/featherJ/protobytes)

## 使用
使用 [protobytes](https://github.com/featherJ/protobytes) 创建的协议模板编译得到的 `ExampleInfo` 对象来举例:

* ExampleInfo 的序列化
```java
ExampleInfo info = new ExampleInfo();
info.byteProp = 1;
info.shortProp = 2;
info.doubleProp = Math.PI;
info.longstringProp = "hello word";
info.intListProp.add(1);
info.intListProp.add(2);
info.intListProp.add(3);
info.intListProp.add(4);
info.intListProp.add(5);

AttInfo attInfo = new AttInfo();
attInfo.attId = 1;
info.attInfo1ListProp.add(attInfo);

attInfo = new AttInfo();
attInfo.attId = 2;
info.attInfo1ListProp.add(attInfo);

byte[] bytes = ExampleInfo.toBytes(info);
```

* ExampleInfo 的反序列化
```java
byte[] bytes = ...;
ExampleInfo info = ExampleInfo.fromBytes(bytes);
```

## 安装
将 `build` 文件夹下的 `protobytes.jar` 引入到项目中即可。