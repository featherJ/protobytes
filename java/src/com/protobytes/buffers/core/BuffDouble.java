package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * 8个字节，双精度（64 位）浮点数。
 * 
 * @author Agua.L
 *
 */
public class BuffDouble extends BuffBase {
	private Double value = 0.0d;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Double) value;
	}

	public int getType() {
		return BuffType.DOUBLE;
	}
}
