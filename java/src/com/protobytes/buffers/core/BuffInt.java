package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * 4个字节，介于 -2147483648 和 2147483647 之间的 32 位带符号整数。
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
		if (this.value.intValue() < Integer.MIN_VALUE || this.value.intValue() > Integer.MAX_VALUE)
			System.err.println("BuffInt 的值超出了 -214748364 8到 2147483647 的范围");
	}

	public int getType() {
		return BuffType.INT;
	}
}
