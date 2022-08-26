package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A 16-bit unsigned integer between 0 and 65535.
 * 
 * @author Agua.L
 *
 */
public class BuffUShort extends BuffBase {
	private Integer value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Integer) value;
		if (this.value < 0 || this.value > Short.MAX_VALUE - Short.MIN_VALUE)
			System.err.println("BuffUShort 的值超出了 0 到 65535 的范围");
	}

	public int getType() {
		return BuffType.SHORT;
	}
}
