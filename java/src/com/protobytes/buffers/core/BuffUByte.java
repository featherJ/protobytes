package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A 8-bit unsigned integer between 0 and 255.
 * 
 * @author Agua.L
 *
 */
public class BuffUByte extends BuffBase {
	private Integer value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Integer) value;
		if (this.value < 0 || this.value > Byte.MAX_VALUE - Byte.MIN_VALUE)
			System.err.println("BuffUByte 的值超出了 0 到 255 的范围");
	}

	public int getType() {
		return BuffType.UNSIGNED_BYTE;
	}
}
