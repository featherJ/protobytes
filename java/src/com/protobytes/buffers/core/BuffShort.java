package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * 2个字节，介于 -32768 和 32767 之间的 16 位带符号整数。
 * 
 * @author Agua.L
 *
 */
public class BuffShort extends BuffBase{
	private Short value = 0;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Short) value;
		if (this.value.shortValue() < Short.MIN_VALUE || this.value.shortValue() > Short.MAX_VALUE)
			System.err.println("BuffShort的值超出了-32768到32767的范围");
	}

	public int getType() {
		return BuffType.SHORT;
	}
}
