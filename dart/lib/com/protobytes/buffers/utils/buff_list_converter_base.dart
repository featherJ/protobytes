import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/buffers/utils/buff_converter_base.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';

class BuffListConverterBase {
  static BuffListConverterBase? _instance;

  static BuffListConverterBase get instance {
    _instance ??= BuffListConverterBase();
    return _instance!;
  }

  List<int> byteListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<int> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.byteFromBuff("", item));
    }
    return target;
  }

  BuffList byteListToBuff(List<int> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.byteToBuff(value, ""));
    }
    return buff;
  }

  List<int> ubyteListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<int> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.ubyteFromBuff("", item));
    }
    return target;
  }

  BuffList ubyteListToBuff(List<int> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.ubyteToBuff(value, ""));
    }
    return buff;
  }

  List<int> shortListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<int> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.shortFromBuff("", item));
    }
    return target;
  }

  BuffList shortListToBuff(List<int> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.shortToBuff(value, ""));
    }
    return buff;
  }

  List<int> ushortListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<int> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.ushortFromBuff("", item));
    }
    return target;
  }

  BuffList ushortListToBuff(List<int> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.ushortToBuff(value, ""));
    }
    return buff;
  }

  List<int> intListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<int> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.intFromBuff("", item));
    }
    return target;
  }

  BuffList intListToBuff(List<int> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.intToBuff(value, ""));
    }
    return buff;
  }

  List<int> uintListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<int> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.uintFromBuff("", item));
    }
    return target;
  }

  BuffList uintListToBuff(List<int> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.uintToBuff(value, ""));
    }
    return buff;
  }

  List<double> floatListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<double> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.floatFromBuff("", item));
    }
    return target;
  }

  BuffList floatListToBuff(List<double> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.floatToBuff(value, ""));
    }
    return buff;
  }

  List<double> doubleListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<double> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.doubleFromBuff("", item));
    }
    return target;
  }

  BuffList doubleListToBuff(List<double> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.doubleToBuff(value, ""));
    }
    return buff;
  }

  List<String> stringListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<String> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.stringFromBuff("", item));
    }
    return target;
  }

  BuffList stringListToBuff(List<String> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.stringToBuff(value, ""));
    }
    return buff;
  }

  List<bool> boolListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<bool> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.boolFromBuff("", item));
    }
    return target;
  }

  BuffList boolListToBuff(List<bool> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.boolToBuff(value, ""));
    }
    return buff;
  }

  List<ByteArray> bytesListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<ByteArray> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.bytesFromBuff("", item));
    }
    return target;
  }

  BuffList bytesListToBuff(List<ByteArray> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.bytesToBuff(value, ""));
    }
    return buff;
  }

  List<String> longstringListFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    List<String> target = [];
    List<IBuffInfo> items = (buff as BuffList).items;
    for (var item in items) {
      target.add(BuffConverterBase.instance.longstringFromBuff(key, item));
    }
    return target;
  }

  BuffList longstringListToBuff(List<String> target, String key) {
    BuffList buff = BuffList();
    buff.key = key;
    for (var value in target) {
      buff.push(BuffConverterBase.instance.longstringToBuff(value, ""));
    }
    return buff;
  }
}
