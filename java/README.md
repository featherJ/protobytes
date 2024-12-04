# Java ProtoBytes library
[中文文档](README_CN.md) | English

This is the underlying dependency library for binary communication protocol serialization and deserialization. The library is lightweight, with no network-related functionality, and focuses solely on providing data object serialization and deserialization features.

For more detailed information about `ProtoBytes`, please refer to: [protobytes](https://github.com/featherJ/protobytes)

## Usage
Let's take the `ExampleInfo` object, compiled from the protocol template created by [protobytes](https://github.com/featherJ/protobytes), as an example:

* Serialization of `ExampleInfo`
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

* Deserialization of `ExampleInfo`
```java
byte[] bytes = ...;
ExampleInfo info = ExampleInfo.fromBytes(bytes);
```

## Installation
Simply include the `protobytes.jar` from the `build` folder into your project.
