package com.protobytes.buffers.core.commons;

import java.util.List;

/**
 * 数组，2个字节无符号短整型表示长度+长度这么多个项
 * 
 * @author Agua.L
 *
 */
public interface IBuffList extends IBuffInfo {
	/**
	 * 添加一项到结尾
	 * 
	 * @param info
	 */
	void push(IBuffInfo info);

	/**
	 * 得到所有项
	 * 
	 * @return
	 */
	List<IBuffInfo> getItems();
}