package com.protobytes.buffers.core.commons;

import java.util.List;

/**
 * 自定义对象
 * 
 * @author Agua.L
 *
 */
public interface IBuffObject extends IBuffInfo {
	/**
	 * 添加属性
	 * 
	 * @param attribute
	 */
	void addAttribute(IBuffInfo attribute);

	/**
	 * 得到属性列表
	 * 
	 * @return
	 */
	List<IBuffInfo> getAttributes();
}
