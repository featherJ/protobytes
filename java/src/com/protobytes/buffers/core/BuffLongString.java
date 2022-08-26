package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * A UTF-8 string from the byte stream. The string is assumed to be prefixed
 * with an int indicating the length in bytes.
 * 
 * @author Agua.L
 *
 */
public class BuffLongString extends BuffBase {
	private String value = "";

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (String) value;
	}

	public int getType() {
		return BuffType.LONG_STRING;
	}
}
