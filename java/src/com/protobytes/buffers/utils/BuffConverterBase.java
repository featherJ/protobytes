package com.protobytes.buffers.utils;

import com.protobytes.buffers.core.BuffBoolean;
import com.protobytes.buffers.core.BuffByte;
import com.protobytes.buffers.core.BuffBytes;
import com.protobytes.buffers.core.BuffDouble;
import com.protobytes.buffers.core.BuffFloat;
import com.protobytes.buffers.core.BuffInt;
import com.protobytes.buffers.core.BuffLongString;
import com.protobytes.buffers.core.BuffShort;
import com.protobytes.buffers.core.BuffString;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.utils.ByteArray;

/**
 * Buff与值的转换器基类
 * 
 * @author Agua.L
 *
 */
public class BuffConverterBase {
	private static BuffConverterBase _instance;

	public static BuffConverterBase getInstance() {
		if (_instance == null)
			_instance = new BuffConverterBase();
		return _instance;
	}

	public Byte byteFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		Byte target = (Byte) buff.getValue();
		return target;
	}

	public BuffByte byteToBuff(Byte target, String key) {
		BuffByte buff = new BuffByte();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public Short shortFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		Short target = (Short) buff.getValue();
		return target;
	}

	public BuffShort shortToBuff(Short target, String key) {
		BuffShort buff = new BuffShort();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public Integer intFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		Integer target = (Integer) buff.getValue();
		return target;
	}

	public BuffInt intToBuff(Integer target, String key) {
		BuffInt buff = new BuffInt();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public Float floatFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		Float target = (Float) buff.getValue();
		return target;
	}

	public BuffFloat floatToBuff(Float target, String key) {
		BuffFloat buff = new BuffFloat();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public Double doubleFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		Double target = (Double) buff.getValue();
		return target;
	}

	public BuffDouble doubleToBuff(Double target, String key) {
		BuffDouble buff = new BuffDouble();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public Boolean boolFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		Boolean target = (Boolean) buff.getValue();
		return target;
	}

	public BuffBoolean boolToBuff(Boolean target, String key) {
		BuffBoolean buff = new BuffBoolean();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public String stringFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		String target = (String) buff.getValue();
		return target;
	}

	public BuffString stringToBuff(String target, String key) {
		BuffString buff = new BuffString();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public String longstringFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		String target = (String) buff.getValue();
		return target;
	}

	public BuffLongString longstringToBuff(String target, String key) {
		BuffLongString buff = new BuffLongString();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}

	public ByteArray bytesFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		ByteArray target = (ByteArray) buff.getValue();
		return target;
	}

	public BuffBytes bytesToBuff(ByteArray target, String key) {
		BuffBytes buff = new BuffBytes();
		buff.setKey(key);
		buff.setValue(target);
		return buff;
	}
}
