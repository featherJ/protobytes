package com.protobytes.buffers;

/**
 * Base buff type
 * 
 * @author Agua.L
 *
 */
public class BuffType {
	/**
	 * An byte between -128 and 127.
	 */
	public static final int BYTE = 0;
	/**
	 * A 16-bit signed integer between -32768 and 32767.
	 */
	public static final int SHORT = 1;
	/**
	 * A 32-bit signed integer between -2147483648 and 2147483647.
	 */
	public static final int INT = 2;
	/**
	 * A single-precision (32-bit) floating-point number.
	 */
	public static final int FLOAT = 3;
	/**
	 * A double-precision (64-bit) floating-point number.
	 */
	public static final int DOUBLE = 4;
	/**
	 * A UTF-8 string from the byte stream. The string is assumed to be prefixed
	 * with an short indicating the length in bytes.
	 */
	public static final int STRING = 5;
	/**
	 * An 8-bit signed integer
	 */
	public static final int BOOLEAN = 6;
	/**
	 * A List
	 */
	public static final int LIST = 7;
	/**
	 * A Custom object
	 */
	public static final int OBJECT = 8;
	/**
	 * A bytes from the byte stream. The bytes is assumed to be prefixed with an int
	 * indicating the length in bytes.
	 */
	public static final int BYTES = 9;
	/**
	 * A UTF-8 string from the byte stream. The string is assumed to be prefixed
	 * with an int indicating the length in bytes.
	 */
	public static final int LONG_STRING = 10;

	public static String getTypeName(int type) {
		String typeName = "-";
		switch (type) {
		case BYTE:
			return "byte";
		case SHORT:
			return "short";
		case INT:
			return "int";
		case FLOAT:
			return "float";
		case DOUBLE:
			return "double";
		case STRING:
			return "string";
		case BOOLEAN:
			return "bool";
		case LIST:
			return "list";
		case OBJECT:
			return "object";
		case BYTES:
			return "bytes";
		case LONG_STRING:
			return "longstring";
		}
		return typeName;
	}
}