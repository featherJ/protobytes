package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A 16-bit signed integer between -32768 and 32767.
 * 
 * @author Agua.L
 *
 */
public class BuffShort extends BuffBase{
	private Integer value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Integer) value;
		if (this.value < Short.MIN_VALUE || this.value > Short.MAX_VALUE)
			System.err.println("BuffShort 的值超出了 -32768 到 32767 的范围");
	}

	public int getType() {
		return BuffType.SHORT;
	}
}
