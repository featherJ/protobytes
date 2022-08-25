package com.protobytes.utils;

/**
 * The Endian class contains values that denote the byte order used to represent
 * multiple byte numbers. The byte order is either bigEndian (most significant byte
 * first) or littleEndian (least significant byte first).
 * 
 * @author Agua.L
 *
 */
public class Endian {
	/**
	 * Indicates the most significant byte of the multiple byte number appears first
	 * in the sequence of bytes.
	 */
	public static final String BIG_ENDIAN = "bigEndian";
	/**
	 * Indicates the least significant byte of the multiple byte number appears
	 * first in the sequence of bytes.
	 */
	public static final String LITTLE_ENDIAN = "littleEndian";
}
