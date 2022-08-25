# Flutter proto_bytes library

[![pub](https://img.shields.io/pub/v/proto_bytes?color=success)](https://pub.dev/packages/proto_bytes)


用于二进制通信协议序列化与反序列化的底层依赖库。库内容精简，不与任何网络功能关联，只提供数据对象序列化与反序列化功能。

## 介绍
ProtoBytes 通信协议库，可由命令行工具 [protobytes-cli](https://github.com/featherJ/protobytes-cli) 将指定格式的 `proto` 协议文件所描述的自定义数据类型，编译为指定语言的数据对象的具体代码实现。借由当前底层依赖库，实现自定义数据类型对象的序列化与反序列化。

对于 `proto` 协议文件的格式以及具体使用可参考 [protobytes-cli](https://github.com/featherJ/protobytes-cli)。

### 特点
- 在实际工程通信协议格式的更新迭代过程中，一端对 `proto` 中某个具体类型的从末端增加新的属性，在不改变已存在属性的类型和顺序的前提下。另一端不需要更新协议文件以及重新编译，也不会对已存在的所有程序逻辑造成影响，不会在实体数据对象序列化与反序列化过程中报错，只会导致在数据对象解析时不对应的属性丢失。

- 在不改变 `proto` 协议文件中属性的类型与顺序的前提下，可以任意修改任何属性名而不会导致序列化与反序列化过程中二进制的改变。序列化后的二进制不依赖任何节点的属性名，只依赖顺序和类型。即多端通信过程中，一段对协议中的属性名的修改，丝毫不会影响另一端对于数据的解析过程。

- 足够精简，只包含实体对象的序列化与反序列化过程，不关联任何其他功能。

## 原理
从二进制到最终的数据对象分为三层：
### 二进制层
这一层是最基本的二进制数据，其中基本数据类型包括如下：
* `bool`: 8位有符号整数，非零为 true 零为 false。
* `byte`: 8位有符号整数。
* `short`: 16位有符号整数。
* `int`: 32位有符号整数。
* `float`: IEEE 754 单精度（32 位）浮点数。
* `double`: IEEE 754 双精度（64 位）浮点数。
* `string`: 一串 UTF-8 字符串，字符串的前缀是 16 位有符号整数表示长度。
* `longstring`: 一串 UTF-8 字符串，字符串的前缀是 32 位有符号整数表示长度。
* `bytes`: 一串二进制数据，前缀是 32 位有符号整数表示长度。

初次之外，还提供了 `list` 类型和 `object` 类型，分别标识数组和自定义对象。而其内部也均有上述基本类型所实现。

### 数据交换层
以上所有基本数据类型均有对应的一个数据交换对象类型，该层负责对上述二进制基本类型到数据交换对象的相互转换，即对数据交换对象的序列化与反序列化。

所有数据交换对象的二进制形式均为一个8位整数标识数据交换类型加具体数据值，如：
```
BuffByte <-----> type(8-bit) + value(bytes)
```

数据交换层可以满足由上述基本二进制类型所组合以及嵌套形成的复杂数据形式的序列化与反序列化过程，其数据结构形式类似于json，但是树状结构中没有任何节点的名称，只有节点的类型。

对于数据交换层到二进制层的相互转化的具体实现过程可以参考类 `BuffBytesUtil`。

### 实体对象层
由于数据交换层中，数据树中只包含每一个节点的类型和值不包含节点名称，每一个同层的子节点的存储形式列表形式。虽然序列化与反序列化过程灵活，但无法满足实际使用，即：无法通过属性名直接得到某个节点的属性值。

所以该层是将数据交换对象转换为实体对象，将数据交换节点中子节点与实体对象中具体的属性对应起来，这样才能方便开发过程中使用。

实体对象的具体代码实现，是由命令行工具 [protobytes-cli](https://github.com/featherJ/protobytes-cli) 将指定格式的 `proto` 协议文件所描述的自定义数据类型编译得到的。详细对于 `proto` 文件的描述可参考 [protobytes-cli](https://github.com/featherJ/protobytes-cli)

## 使用
使用 [protobytes-cli](https://github.com/featherJ/protobytes-cli) 创建的协议模板编译得到的 `ExampleInfo` 对象来举例:

* ExampleInfo 的序列化
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

* ExampleInfo 的反序列化
```dart
List<int> bytes = ...;
ExampleInfo info = ExampleInfo.fromBytes(bytes);
```