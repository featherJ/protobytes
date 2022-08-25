package com.protobytes.buffers.utils;

/**
 * 输出工具类
 * 
 * @author Agua.L
 *
 */
public class BuffLogUtil {
	public static String formatLog(String log, int indent) {
		if (indent > 0) {
			String[] arr = log.split("\n");
			if (arr[arr.length - 1].length() == 0) {
				arr[arr.length - 1] = "";
			}
			String newlog = "";
			for (int i = 0; i < arr.length; i++) {
				newlog = newlog + getIndent(indent) + arr[i] + "\n";
			}
			return newlog;
		}
		return log;
	}

	private static String indentStr = "\t";

	private static String getIndent(int indent) {
		String space = "";
		for (int i = 0; i < indent; i++) {
			space = space + indentStr;
		}
		return space;
	}
}
