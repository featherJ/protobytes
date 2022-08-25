package com.protobytes.buffers.core;

import java.util.ArrayList;
import java.util.List;

import com.protobytes.buffers.BuffType;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.buffers.core.commons.IBuffObject;
import com.protobytes.buffers.utils.BuffLogUtil;

/**
 * 对象，2个字节无符号短整型表示长度+长度这么多个属性
 * 
 * @author Agua.L
 *
 */
public class BuffObject extends BuffBase implements IBuffObject {
	private List<IBuffInfo> attributes = new ArrayList<>();

	public void addAttribute(IBuffInfo attribute) {
		this.attributes.add(attribute);
	}

	public List<IBuffInfo> getAttributes() {
		return this.attributes;
	}

	public String toString() {
		String str = "";
		str += "(" + BuffType.getTypeName(this.getType()) + ")" + this.getKey() + "{\n";
		for (var i = 0; i < this.attributes.size(); i++) {
			str += BuffLogUtil.formatLog(this.attributes.get(i).toString(), 1);
		}
		str += "}";
		return str;
	}

	public Object getValue() {
		return null;
	}

	public void setValue(Object value) {
	}

	public int getType() {
		return BuffType.OBJECT;
	}
}
