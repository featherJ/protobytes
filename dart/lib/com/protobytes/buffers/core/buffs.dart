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

/// Boolean value with a 8-bit integer
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

/// A 8-bit signed integer between -128 and 127.
class BuffByte extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < -128 || _value > 127) {
      print("Warning: BuffByte 的值超出了 -128 到 127 的范围");
    }
  }

  @override
  int get type => BuffType.byteType;
}

/// A 8-bit unsigned integer between 0 and 255.
class BuffUByte extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < 0 || _value > 255) {
      print("Warning: BuffByte 的值超出了 0 到 255 的范围");
    }
  }

  @override
  int get type => BuffType.unsignedByteType;
}

/// A 16-bit signed integer between -32768 and 32767.
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

/// A 16-bit unsigned integer between 0 and 65535.
class BuffUShort extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < 0 || _value > 65535) {
      print("Warning: BuffShort 的值超出了 0 到 65535 的范围");
    }
  }

  @override
  int get type => BuffType.unsignedShortType;
}

/// A 32-bit signed integer between -2147483648 and 2147483647.
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

/// A 32-bit unsigned integer between 0 and 4294967295.
class BuffUInt extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < 0 || _value > 4294967295) {
      print("Warning: BuffInt 的值超出了 0 到 4294967295 的范围");
    }
  }

  @override
  int get type => BuffType.unsignedIntType;
}

/// A 64-bit signed integer between -9223372036854775808 and 9223372036854775807.
class BuffInt64 extends BuffBase {
  int _value = 0;
  @override
  dynamic get value => _value;
  @override
  set value(dynamic value) {
    _value = value as int;
    if (_value < -9223372036854775808 || _value > 9223372036854775807) {
      print(
          "Warning: BuffInt64 的值超出了 -9223372036854775808 到 9223372036854775807 的范围");
    }
  }

  @override
  int get type => BuffType.int64Type;
}

/// A single-precision (32-bit) floating-point number.
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

/// A double-precision (64-bit) floating-point number.
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

/// A bytes from the byte stream. The bytes is assumed to be prefixed with an int indicating the length in bytes.
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

/// A UTF-8 string from the byte stream. The string is assumed to be prefixed with an unsigned short indicating the length in bytes.
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

/// A UTF-8 string from the byte stream. The string is assumed to be prefixed with an int indicating the length in bytes.
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
