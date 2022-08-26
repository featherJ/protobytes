package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A single-precision (32-bit) floating-point number.
 * 
 * @author Agua.L
 *
 */
public class BuffFloat extends BuffBase {
	private Double value = 0.0d;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Double) value;
	}

	public int getType() {
		return BuffType.FLOAT;
	}
}
