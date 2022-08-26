package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A 8-bit signed integer between -128 and 127.
 * 
 * @author Agua.L
 *
 */
public class BuffByte extends BuffBase {
	private Integer value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Integer) value;
		if (this.value < Byte.MIN_VALUE || this.value > Byte.MAX_VALUE)
			System.err.println("BuffByte 的值超出了 -128 到 127 的范围");
	}

	public int getType() {
		return BuffType.BYTE;
	}
}
