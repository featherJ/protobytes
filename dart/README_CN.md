# Flutter proto_bytes library
中文文档 | [English](README.md)

[![pub](https://img.shields.io/pub/v/proto_bytes?color=success)](https://pub.dev/packages/proto_bytes)


用于二进制通信协议序列化与反序列化的底层依赖库。库内容精简，不与任何网络功能关联，只提供数据对象序列化与反序列化功能。

对于 `ProtoBytes` 的详细介绍可以查看：[protobytes](https://github.com/featherJ/protobytes)

## 使用
使用 [protobytes](https://github.com/featherJ/protobytes) 创建的协议模板编译得到的 `ExampleInfo` 对象来举例:

* `ExampleInfo` 的序列化
```dart
ExampleInfo info = ExampleInfo();
info.byteProp = 1;
info.shortProp = 2;
info.doubleProp = pi;
info.longstringProp = 'hello word';
info.intListProp.addAll([1, 2, 3, 4, 5]);

AttInfo attInfo = AttInfo();
attInfo.attId = 1;
info.attInfo1ListProp.add(attInfo);

attInfo = AttInfo();
attInfo.attId = 2;
info.attInfo1ListProp.add(attInfo);

List<int> bytes = ExampleInfo.toBytes(info);
```

* `ExampleInfo` 的反序列化
```dart
List<int> bytes = ...;
ExampleInfo info = ExampleInfo.fromBytes(bytes);
```