package com.protobytes.buffers.core;

import com.protobytes.buffers.BuffType;

/**
 * 2个字节无符号短整型表示长度+实际字符串内容
 * @author Agua.L
 *
 */
public class BuffString extends BuffBase{
	 private String value = "";
	  
	  public Object getValue() {
	    return this.value;
	  }
	  
	  public void setValue(Object value) {
	    this.value = (String)value;
	  }
	  
	  public int getType() {
	    return BuffType.STRING;
	  }
}
