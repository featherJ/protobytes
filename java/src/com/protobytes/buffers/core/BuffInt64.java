package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 *  A 64-bit signed integer between -9223372036854775808 and 9223372036854775807.
 * 
 * @author Agua.L
 *
 */
public class BuffInt64 extends BuffBase {
	private Long value = 0L;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Long) value;
		if (this.value < Long.MIN_VALUE || this.value > Long.MAX_VALUE)
			System.err.println("BuffInt64 的值超出了 -9223372036854775808 到 9223372036854775807 的范围");
	}

	public int getType() {
		return BuffType.INT64;
	}
}
