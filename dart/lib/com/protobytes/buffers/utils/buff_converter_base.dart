import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';

/// Buff与值的转换器基类
class BuffConverterBase {
  static BuffConverterBase? _instance;

  static BuffConverterBase get instance {
    _instance ??= BuffConverterBase();
    return _instance!;
  }

  int byteFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    int target = buff.value as int;
    return target;
  }

  BuffByte byteToBuff(int target, String key) {
    BuffByte buff = BuffByte();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  int shortFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    int target = buff.value as int;
    return target;
  }

  BuffShort shortToBuff(int target, String key) {
    BuffShort buff = BuffShort();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  int intFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    int target = buff.value as int;
    return target;
  }

  BuffInt intToBuff(int target, String key) {
    BuffInt buff = BuffInt();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  double floatFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    double target = buff.value as double;
    return target;
  }

  BuffFloat floatToBuff(double target, String key) {
    BuffFloat buff = BuffFloat();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  double doubleFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    double target = buff.value as double;
    return target;
  }

  BuffDouble doubleToBuff(double target, String key) {
    BuffDouble buff = BuffDouble();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  bool boolFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    bool target = buff.value as bool;
    return target;
  }

  BuffBoolean boolToBuff(bool target, String key) {
    BuffBoolean buff = BuffBoolean();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  String stringFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    String target = buff.value as String;
    return target;
  }

  BuffString stringToBuff(String target, String key) {
    BuffString buff = BuffString();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  String longstringFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    String target = buff.value as String;
    return target;
  }

  BuffLongString longstringToBuff(String target, String key) {
    BuffLongString buff = BuffLongString();
    buff.key = key;
    buff.value = target;
    return buff;
  }

  ByteArray bytesFromBuff(String key, IBuffInfo buff) {
    buff.key = key;
    ByteArray target = buff.value as ByteArray;
    return target;
  }

  BuffBytes bytesToBuff(ByteArray target, String key) {
    BuffBytes buff = BuffBytes();
    buff.key = key;
    buff.value = target;
    return buff;
  }
}
