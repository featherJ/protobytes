package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * Boolean value with a 8-bit integer
 * 
 * @author Agua.L
 *
 */
public class BuffBoolean extends BuffBase {
	private Boolean value = false;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (Boolean) value;
	}

	public int getType() {
		return BuffType.BOOLEAN;
	}
}
