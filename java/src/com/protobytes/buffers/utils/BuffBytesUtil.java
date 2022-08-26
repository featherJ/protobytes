package com.protobytes.buffers.utils;

import java.util.List;

import com.protobytes.buffers.BuffType;
import com.protobytes.buffers.core.BuffBoolean;
import com.protobytes.buffers.core.BuffByte;
import com.protobytes.buffers.core.BuffBytes;
import com.protobytes.buffers.core.BuffDouble;
import com.protobytes.buffers.core.BuffFloat;
import com.protobytes.buffers.core.BuffInt;
import com.protobytes.buffers.core.BuffList;
import com.protobytes.buffers.core.BuffLongString;
import com.protobytes.buffers.core.BuffObject;
import com.protobytes.buffers.core.BuffShort;
import com.protobytes.buffers.core.BuffString;
import com.protobytes.buffers.core.BuffUByte;
import com.protobytes.buffers.core.BuffUInt;
import com.protobytes.buffers.core.BuffUShort;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.buffers.core.commons.IBuffList;
import com.protobytes.buffers.core.commons.IBuffObject;
import com.protobytes.utils.ByteArray;
import com.protobytes.utils.Endian;

/**
 * buff的二进制工具
 * 
 * @author Agua.L
 *
 */
public class BuffBytesUtil {
	/**
	 * 将buff转换为二进制
	 * 
	 * @param buff
	 * @return
	 */
	public static ByteArray toBytes(IBuffInfo buff) {
		ByteArray bytes = new ByteArray();
		bytes.setEndian(Endian.LITTLE_ENDIAN);
		bytes.writeByte((byte) buff.getType());

		// 基本数据类型
		if (buff.getType() == BuffType.BYTE || buff.getType() == BuffType.UNSIGNED_BYTE) {
			bytes.writeByte((int) buff.getValue());
		} else if (buff.getType() == BuffType.SHORT || buff.getType() == BuffType.UNSIGNED_SHORT) {
			bytes.writeShort((int) buff.getValue());
		} else if (buff.getType() == BuffType.INT) {
			bytes.writeInt((int) buff.getValue());
		} else if (buff.getType() == BuffType.UNSIGNED_INT) {
			bytes.writeUnsignedInt((long) buff.getValue());
		} else if (buff.getType() == BuffType.FLOAT) {
			bytes.writeFloat((double) buff.getValue());
		} else if (buff.getType() == BuffType.DOUBLE) {
			bytes.writeDouble((double) buff.getValue());
		} else if (buff.getType() == BuffType.STRING) {
			bytes.writeUTF((String) buff.getValue());
		} else if (buff.getType() == BuffType.LONG_STRING) {
			ByteArray strBytes = new ByteArray();
			strBytes.writeUTFBytes((String) buff.getValue());
			strBytes.setPosition(0);
			bytes.writeInt(strBytes.getLength());
			bytes.writeBytes(strBytes, 0, strBytes.getLength());
		} else if (buff.getType() == BuffType.BYTES) {
			bytes.writeInt(((ByteArray) buff.getValue()).getLength());
			bytes.writeBytes((ByteArray) buff.getValue(), 0, ((ByteArray) buff.getValue()).getLength());
		} else if (buff.getType() == BuffType.BOOLEAN) {
			bytes.writeBoolean((Boolean) buff.getValue());
		} else if (buff.getType() == BuffType.OBJECT) {
			List<IBuffInfo> atts = ((IBuffObject) buff).getAttributes();
			bytes.writeShort(atts.size());
			for (int i = 0; i < atts.size(); i++) {
				ByteArray attBytes = BuffBytesUtil.toBytes(atts.get(i));
				bytes.writeBytes(attBytes, 0, attBytes.getLength());
			}
		} else if (buff.getType() == BuffType.LIST) {
			List<IBuffInfo> items = ((IBuffList) buff).getItems();
			bytes.writeShort(items.size());
			for (int i = 0; i < items.size(); i++) {
				ByteArray itemBytes = BuffBytesUtil.toBytes(items.get(i));
				bytes.writeBytes(itemBytes, 0, itemBytes.getLength());
			}
		}
		return bytes;
	}

	/**
	 * 从二进制获取buff内容
	 * 
	 * @param bytes
	 * @return
	 */
	public static IBuffInfo fromBytes(ByteArray bytes) {
		int i;
		bytes.setEndian(Endian.LITTLE_ENDIAN);
		int type = bytes.readByte();
		IBuffInfo buff = null;
		if (type == BuffType.BYTE) {
			buff = new BuffByte();
			buff.setValue(bytes.readByte());
		} else if (type == BuffType.UNSIGNED_BYTE) {
			buff = new BuffUByte();
			buff.setValue(bytes.readUnsignedByte());
		} else if (type == BuffType.SHORT) {
			buff = new BuffShort();
			buff.setValue(bytes.readShort());
		} else if (type == BuffType.UNSIGNED_SHORT) {
			buff = new BuffUShort();
			buff.setValue(bytes.readUnsignedShort());
		} else if (type == BuffType.INT) {
			buff = new BuffInt();
			buff.setValue(bytes.readInt());
		} else if (type == BuffType.UNSIGNED_INT) {
			buff = new BuffUInt();
			buff.setValue(bytes.readUnsignedInt());
		} else if (type == BuffType.FLOAT) {
			buff = new BuffFloat();
			buff.setValue(bytes.readFloat());
		} else if (type == BuffType.DOUBLE) {
			buff = new BuffDouble();
			buff.setValue(bytes.readDouble());
		} else if (type == BuffType.STRING) {
			buff = new BuffString();
			buff.setValue(bytes.readUTF());
		} else if (type == BuffType.LONG_STRING) {
			buff = new BuffLongString();
			int len = bytes.readInt();
			buff.setValue(bytes.readUTFBytes(len));
		} else if (type == BuffType.BYTES) {
			buff = new BuffBytes();
			int len = bytes.readInt();
			if (len > 0) {
				bytes.readBytes((ByteArray) buff.getValue(), 0, len);
			}
		} else if (type == BuffType.BOOLEAN) {
			buff = new BuffBoolean();
			buff.setValue(bytes.readBoolean());
		} else if (type == BuffType.OBJECT) {
			buff = new BuffObject();
			int attLen = bytes.readUnsignedShort();
			for (i = 0; i < attLen; i++) {
				IBuffInfo att = BuffBytesUtil.fromBytes(bytes);
				((BuffObject) buff).addAttribute(att);
			}
		} else if (type == BuffType.LIST) {
			buff = new BuffList();
			int itemLen = bytes.readUnsignedShort();
			for (i = 0; i < itemLen; i++) {
				IBuffInfo item = BuffBytesUtil.fromBytes(bytes);
				((BuffList) buff).push(item);
			}
		}
		return buff;
	}
}
