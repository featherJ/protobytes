package com.protobytes.buffers.core.commons;

/**
 * 基本的单位，数值或者字符串等
 * 
 * @author Agua.L
 */
public interface IBuffInfo {
	/**
	 * 得到该节点的实例或引用
	 */
	Object getValue();

	/**
	 * 设置该节点的实例或引用
	 */
	void setValue(Object value);

	/**
	 * 得到这个buff的类型
	 */
	int getType();

	/**
	 * 得到该节点的key
	 */
	String getKey();

	/**
	 * 设置该节点的key
	 */
	void setKey(String key);

	String toString();
}
