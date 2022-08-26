import 'dart:typed_data';

import 'package:proto_bytes/com/protobytes/buffers/buff_type.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';

/// buff的二进制工具
class BuffBytesUtil {
  /// 将buff转换为二进制
  static ByteArray toBytes(IBuffInfo buff) {
    ByteArray bytes = ByteArray();
    bytes.endian = Endian.little;
    bytes.writeByte(buff.type);

    // 基本数据类型
    if (buff.type == BuffType.byteType ||
        buff.type == BuffType.unsignedByteType) {
      bytes.writeByte(buff.value as int);
    } else if (buff.type == BuffType.shortType ||
        buff.type == BuffType.unsignedShortType) {
      bytes.writeShort(buff.value as int);
    } else if (buff.type == BuffType.intType) {
      bytes.writeInt(buff.value as int);
    } else if (buff.type == BuffType.unsignedIntType) {
      bytes.writeUnsignedInt(buff.value as int);
    } else if (buff.type == BuffType.floatType) {
      bytes.writeFloat(buff.value as double);
    } else if (buff.type == BuffType.doubleType) {
      bytes.writeDouble(buff.value as double);
    } else if (buff.type == BuffType.stringType) {
      bytes.writeUTF(buff.value as String);
    } else if (buff.type == BuffType.longstringType) {
      ByteArray strBytes = ByteArray();
      strBytes.writeUTFBytes(buff.value as String);
      strBytes.position = 0;
      bytes.writeInt(strBytes.length);
      bytes.writeBytes(strBytes, 0, strBytes.length);
    } else if (buff.type == BuffType.bytesType) {
      bytes.writeInt((buff.value as ByteArray).length);
      bytes.writeBytes(
          buff.value as ByteArray, 0, (buff.value as ByteArray).length);
    } else if (buff.type == BuffType.booleanType) {
      bytes.writeBoolean(buff.value as bool);
    } else if (buff.type == BuffType.objectType) {
      List<IBuffInfo> atts = (buff as IBuffObject).attributes;
      bytes.writeShort(atts.length);
      for (int i = 0; i < atts.length; i++) {
        ByteArray attBytes = BuffBytesUtil.toBytes(atts[i]);
        bytes.writeBytes(attBytes, 0, attBytes.length);
      }
    } else if (buff.type == BuffType.listType) {
      List<IBuffInfo> items = (buff as IBuffList).items;
      bytes.writeShort(items.length);
      for (int i = 0; i < items.length; i++) {
        ByteArray itemBytes = BuffBytesUtil.toBytes(items[i]);
        bytes.writeBytes(itemBytes, 0, itemBytes.length);
      }
    }
    return bytes;
  }

  /// 从二进制获取buff内容
  static IBuffInfo fromBytes(ByteArray bytes) {
    int i;
    bytes.endian = Endian.little;
    int type = bytes.readByte();
    late IBuffInfo buff;
    if (type == BuffType.byteType) {
      buff = BuffByte();
      buff.value = bytes.readByte();
    } else if (type == BuffType.unsignedByteType) {
      buff = BuffUByte();
      buff.value = bytes.readUnsignedByte();
    } else if (type == BuffType.shortType) {
      buff = BuffShort();
      buff.value = bytes.readShort();
    } else if (type == BuffType.unsignedShortType) {
      buff = BuffUShort();
      buff.value = bytes.readUnsignedShort();
    } else if (type == BuffType.intType) {
      buff = BuffInt();
      buff.value = bytes.readInt();
    } else if (type == BuffType.unsignedIntType) {
      buff = BuffUInt();
      buff.value = bytes.readUnsignedInt();
    } else if (type == BuffType.floatType) {
      buff = BuffFloat();
      buff.value = bytes.readFloat();
    } else if (type == BuffType.doubleType) {
      buff = BuffDouble();
      buff.value = bytes.readDouble();
    } else if (type == BuffType.stringType) {
      buff = BuffString();
      buff.value = bytes.readUTF();
    } else if (type == BuffType.longstringType) {
      buff = BuffLongString();
      int len = bytes.readInt();
      buff.value = bytes.readUTFBytes(len);
    } else if (type == BuffType.bytesType) {
      buff = BuffBytes();
      int len = bytes.readInt();
      if (len > 0) {
        bytes.readBytes(buff.value as ByteArray, 0, len);
      }
    } else if (type == BuffType.booleanType) {
      buff = BuffBoolean();
      buff.value = bytes.readBoolean();
    } else if (type == BuffType.objectType) {
      buff = BuffObject();
      int attLen = bytes.readUnsignedShort();
      for (i = 0; i < attLen; i++) {
        IBuffInfo att = BuffBytesUtil.fromBytes(bytes);
        (buff as BuffObject).addAttribute(att);
      }
    } else if (type == BuffType.listType) {
      buff = BuffList();
      int itemLen = bytes.readUnsignedShort();
      for (i = 0; i < itemLen; i++) {
        IBuffInfo item = BuffBytesUtil.fromBytes(bytes);
        (buff as BuffList).push(item);
      }
    }
    return buff;
  }
}
