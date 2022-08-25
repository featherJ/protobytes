package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * 1个字节， 介于-128 和 127 之间的8位带符号整数
 * 
 * @author Agua.L
 *
 */
public class BuffByte extends BuffBase {
	private Byte value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Byte) value;
		if (this.value.byteValue() < Byte.MIN_VALUE || this.value.byteValue() > Byte.MAX_VALUE)
			System.err.println("BuffByte 的值超出了 -128 到 127 的范围");
	}

	public int getType() {
		return BuffType.BYTE;
	}
}
