package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;
import com.protobytes.buffers.core.commons.IBuffInfo;

/**
 * 一个最基本的buff包 
 * @author Agua.L
 *
 */
public abstract class BuffBase implements IBuffInfo {
	private String key = "";

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String toString() {
		return "(" + BuffType.getTypeName(getType()) + ")" + this.key + ":" + getValue();
	}
}
