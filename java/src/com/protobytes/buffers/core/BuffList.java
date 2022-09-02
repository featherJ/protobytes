package com.protobytes.buffers.core;

import java.util.ArrayList;
import java.util.List;

import com.protobytes.buffers.BuffType;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.buffers.core.commons.IBuffList;
import com.protobytes.buffers.utils.BuffLogUtil;

/**
 * 数组，2个字节无符号短整型表示长度+长度这么多个项
 * 
 * @author Agua.L
 *
 */
public class BuffList extends BuffBase implements IBuffList {
	private List<IBuffInfo> items = new ArrayList<>();

	public void push(IBuffInfo node) {
		this.items.add(node);
	}

	public List<IBuffInfo> getItems() {
		return this.items;
	}

	public String toString() {
		String str = "";
		str += "(" + BuffType.getTypeName(this.getType()) + ")" + this.getKey() + "[\n";
		for (int i = 0; i < this.items.size() - 1; i++) {
			str += BuffLogUtil.formatLog(this.items.get(i).toString() + ",", 1);
		}
		if (this.items.size() > 0) {
			str += BuffLogUtil.formatLog(this.items.get(this.items.size() - 1).toString(), 1);
		}
		str += "]";
		return str;
	}

	public Object getValue() {
		return null;
	}

	public void setValue(Object value) {
	}

	public int getType() {
		return BuffType.LIST;
	}

}
