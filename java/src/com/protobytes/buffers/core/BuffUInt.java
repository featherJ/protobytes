package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A 32-bit unsigned integer between 0 and 4294967295.
 * 
 * @author Agua.L
 *
 */
public class BuffUInt extends BuffBase {
	private Long value = 0l;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Long) value;
		if (this.value < 0l || this.value > ((long)Integer.MAX_VALUE - (long)Integer.MIN_VALUE))
			System.err.println("BuffUInt 的值超出了 0 到 4294967295 的范围");
	}

	public int getType() {
		return BuffType.UNSIGNED_INT;
	}
}
