package com.protobytes.buffers;

/**
 * Base buff type
 * 
 * @author Agua.L
 *
 */
public class BuffType {
	/**
	 * A 8-bit signed integer between -128 and 127.
	 */
	public static final int BYTE = 0;
	/**
	 * A 8-bit unsigned integer between 0 and 255.
	 */
	public static final int UNSIGNED_BYTE = 1;
	/**
	 * A 16-bit signed integer between -32768 and 32767.
	 */
	public static final int SHORT = 2;
	/**
	 * A 16-bit unsigned integer between 0 and 65535.
	 */
	public static final int UNSIGNED_SHORT = 3;
	/**
	 * A 32-bit signed integer between -2147483648 and 2147483647.
	 */
	public static final int INT = 4;
	/**
	 * A 32-bit unsigned integer between 0 and 4294967295.
	 */
	public static final int UNSIGNED_INT = 5;
	/**
	 * A single-precision (32-bit) floating-point number.
	 */
	public static final int FLOAT = 6;
	/**
	 * A double-precision (64-bit) floating-point number.
	 */
	public static final int DOUBLE = 7;
	/**
	 * A UTF-8 string from the byte stream. The string is assumed to be prefixed
	 * with an unsigned short indicating the length in bytes.
	 */
	public static final int STRING = 8;
	/**
	 * An 8-bit signed integer
	 */
	public static final int BOOLEAN = 9;
	/**
	 * A List
	 */
	public static final int LIST = 10;
	/**
	 * A Custom object
	 */
	public static final int OBJECT = 11;
	/**
	 * A bytes from the byte stream. The bytes is assumed to be prefixed with an int
	 * indicating the length in bytes.
	 */
	public static final int BYTES = 12;
	/**
	 * A UTF-8 string from the byte stream. The string is assumed to be prefixed
	 * with an int indicating the length in bytes.
	 */
	public static final int LONG_STRING = 13;
	/**
	 * A 64-bit signed integer between -9223372036854775808 and 9223372036854775807.
	 */
	public static final int INT64 = 14;
	
	

	public static String getTypeName(int type) {
		String typeName = "-";
		switch (type) {
		case BYTE:
			return "byte";
		case UNSIGNED_BYTE:
			return "ubyte";
		case SHORT:
			return "short";
		case UNSIGNED_SHORT:
			return "ushort";
		case INT:
			return "int";
		case UNSIGNED_INT:
			return "uint";
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
		case INT64:
			return "int64";
		}
		return typeName;
	}
}