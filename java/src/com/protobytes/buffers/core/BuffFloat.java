package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * 4个字节，单精度（32 位）浮点数。
 * 
 * @author Agua.L
 *
 */
public class BuffFloat extends BuffBase {
	private Float value = 0.0f;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Float) value;
	}

	public int getType() {
		return BuffType.FLOAT;
	}
}
