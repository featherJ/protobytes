package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;
import com.protobytes.utils.ByteArray;

/**
 * A bytes from the byte stream. The bytes is assumed to be prefixed with an int indicating the length in bytes.
 * 
 * @author Agua.L
 *
 */
public class BuffBytes extends BuffBase {
	private ByteArray value = new ByteArray();

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = (ByteArray) value;
	}

	public int getType() {
		return BuffType.BYTES;
	}
}
