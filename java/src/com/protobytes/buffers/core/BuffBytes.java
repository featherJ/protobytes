package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;
import com.protobytes.utils.ByteArray;

/**
 * 4个字节无符号短整型表示长度+实际二进制内容
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
