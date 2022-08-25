import 'package:proto_bytes/com/protobytes/buffers/buff_type.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/buffers/utils/buff_log_util.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';

/// 一个最基本的buff包
abstract class BuffBase implements IBuffInfo {
  String _key = "";

  @override
  String get key => _key;
  @override
  set key(String value) {
    _key = key;
  }

  @override
  String toString() {
    return "(${BuffType.getTypeName(type)})$key:$value";
  }
}

/// 1个字节， 真假值
class BuffBoolean extends BuffBase {
  bool _value = false;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as bool;
  }

  @override
  int get type => BuffType.booleanType;
}

/// 1个字节， 介于-128 和 127 之间的8位带符号整数
class BuffByte extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < -128 || _value > 127) {
      print("Warning: BuffByte 的值超出了 -12 8到 127 的范围");
    }
  }

  @override
  int get type => BuffType.byteType;
}

/// 2个字节，介于 -32768 和 32767 之间的 16 位带符号整数。
class BuffShort extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < -32768 || _value > 32767) {
      print("Warning: BuffShort 的值超出了 -32768 到 32767 的范围");
    }
  }

  @override
  int get type => BuffType.shortType;
}

/// 4个字节，介于 -2147483648 和 2147483647 之间的 32 位带符号整数。
class BuffInt extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < -2147483648 || _value > 2147483647) {
      print("Warning: BuffInt 的值超出了 -2147483648 到 2147483647 的范围");
    }
  }

  @override
  int get type => BuffType.intType;
}

/// 4个字节，单精度（32 位）浮点数。
class BuffFloat extends BuffBase {
  double _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as double;
  }

  @override
  int get type => BuffType.floatType;
}

/// 8个字节，双精度（64 位）浮点数。
class BuffDouble extends BuffBase {
  double _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as double;
  }

  @override
  int get type => BuffType.doubleType;
}

/// 4个字节无符号短整型表示长度+实际二进制内容
class BuffBytes extends BuffBase {
  ByteArray _value = ByteArray();
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as ByteArray;
  }

  @override
  int get type => BuffType.bytesType;
}

/// 2个字节无符号短整型表示长度+实际字符串内容
class BuffString extends BuffBase {
  String _value = "";
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as String;
  }

  @override
  int get type => BuffType.stringType;
}

/// 4个字节无符号短整型表示长度+实际字符串内容
class BuffLongString extends BuffBase {
  String _value = "";
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as String;
  }

  @override
  int get type => BuffType.longstringType;
}

/// 对象，2个字节无符号短整型表示长度+长度这么多个属性
class BuffObject extends BuffBase implements IBuffObject {
  final List<IBuffInfo> _attributes = [];
  @override
  void addAttribute(IBuffInfo attribute) {
    _attributes.add(attribute);
  }

  @override
  List<IBuffInfo> get attributes => _attributes;
  @override
  String toString() {
    String str = "";
    str += "(${BuffType.getTypeName(type)})$key{";
    str += "\n";
    for (var i = 0; i < attributes.length; i++) {
      str += BuffLogUtil.formatLog(attributes[i].toString(), 1);
    }
    str += "}";
    return str;
  }

  @override
  dynamic get value => null;
  @override
  set value(dynamic value) {}
  @override
  int get type => BuffType.objectType;
}

/// 数组，2个字节无符号短整型表示长度+长度这么多个项
class BuffList extends BuffBase implements IBuffList {
  final List<IBuffInfo> _items = [];

  @override
  void push(IBuffInfo node) {
    _items.add(node);
  }

  @override
  List<IBuffInfo> get items => _items;

  @override
  String toString() {
    String str = "";
    str += "(${BuffType.getTypeName(type)})$key[";
    str += "\n";
    for (var i = 0; i < items.length - 1; i++) {
      str += BuffLogUtil.formatLog("${items[i].toString()},", 1);
    }
    if (items.isNotEmpty) {
      str += BuffLogUtil.formatLog(items[items.length - 1].toString(), 1);
    }
    str += "]";
    return str;
  }

  @override
  dynamic get value => null;
  @override
  set value(dynamic value) {}

  @override
  int get type => BuffType.listType;
}
