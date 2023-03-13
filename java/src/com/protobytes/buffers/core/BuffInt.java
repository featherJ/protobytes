package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A 32-bit signed integer between -2147483648 and 2147483647.
 * 
 * @author Agua.L
 *
 */
public class BuffInt extends BuffBase {
	private Integer value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Integer) value;
		if (this.value < Integer.MIN_VALUE || this.value > Integer.MAX_VALUE)
			System.err.println("BuffInt 的值超出了 -2147483648 到 2147483647 的范围");
	}

	public int getType() {
		return BuffType.INT;
	}
}
