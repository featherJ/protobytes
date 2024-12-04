# Flutter proto_bytes library
[中文文档](README_CN.md) | English

[![pub](https://img.shields.io/pub/v/proto_bytes?color=success)](https://pub.dev/packages/proto_bytes)

This is the underlying dependency library for binary communication protocol serialization and deserialization. The library is lightweight, with no network-related functionality, and focuses solely on providing data object serialization and deserialization features.

For more detailed information about `ProtoBytes`, please refer to: [protobytes](https://github.com/featherJ/protobytes)

## Usage
Let's take the `ExampleInfo` object, compiled from the protocol template created by [protobytes](https://github.com/featherJ/protobytes), as an example:

* Serialization of `ExampleInfo`
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

* Deserialization of `ExampleInfo`
```dart
List<int> bytes = ...;
ExampleInfo info = ExampleInfo.fromBytes(bytes);
```