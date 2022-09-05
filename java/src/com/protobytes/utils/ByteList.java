package com.protobytes.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装了一个byte数组的对象
 * 
 * @author Agua.L
 *
 */
class Bytes {
	public byte[] data;

	public Bytes(byte[] data) {
		this.data = data;
	}
}

/**
 * 一个byte数组和他有效区间的描述
 * 
 * @author Agua.L
 *
 */
class BytesDes {
	public byte[] data;
	public int start = 0;
	public int end = 0;
}

/**
 * 由多个byte[]组成的数据结构
 * 
 * @author Agua.L
 *
 */
public class ByteList {
	private static List<BytesDes> desPool = new ArrayList<BytesDes>();
	private static BytesDes getDes() {
		if(desPool.size() > 0) {
			return desPool.remove(0);
		}
		return new BytesDes();
	}
	private static void freeDes(BytesDes des) {
		des.data = null;
		desPool.add(des);
	}
	
	
	
	private Bytes initedBytes;
	private List<Bytes> bytesList = new ArrayList<>();
	private int bufSize;

	public ByteList(int bufSize) {
		this.bufSize = bufSize;
	}

	public ByteList(byte[] bytes, int bufSize) {
		initedBytes = new Bytes(bytes);
		endPoint = initedBytes.data.length;
		this.bufSize = bufSize;
	}

	private int endPoint = 0;

	private void extEndPoint(int point) {
		if (point > endPoint) {
			endPoint = point;
		}
	}

	public int getLength() {
		return endPoint;
	}

	public void setLength(int value) {
		endPoint = value;
		int end = endPoint;
		if (initedBytes != null) {
			end -= initedBytes.data.length;
		}
		if (end <= 0) {
			return;
		}
		int listLength = (int) Math.ceil((double) end / (double) bufSize);
		if (listLength < bytesList.size()) {
			int offset = bytesList.size() - listLength;
			for (int i = 0; i < offset; i++) {
				bytesList.remove(bytesList.size() - 1);
			}
		} else if (listLength > bytesList.size()) {
			int offset = listLength - bytesList.size();
			for (int i = 0; i < offset; i++) {
				bytesList.add(new Bytes(new byte[bufSize]));
			}
		}
	}

	public byte get(int index) {
		if (initedBytes != null) {
			if (index < initedBytes.data.length) {
				return initedBytes.data[index];
			}
			index -= initedBytes.data.length;
		}
		int listIndex = index / bufSize;
		Bytes bytes = bytesList.get(listIndex);
		int offsetIndex = index % bufSize;
		return bytes.data[offsetIndex];
	}

	public void set(int index, byte element) {
		extEndPoint(index + 1);
		if (initedBytes != null) {
			if (index < initedBytes.data.length) {
				initedBytes.data[index] = element;
				return;
			}
			index -= initedBytes.data.length;
		}
		int listIndex = index / bufSize;
		int offsetIndex = index % bufSize;
		// 补全
		int offsetListNum = listIndex + 1 - bytesList.size();
		for (int i = 0; i < offsetListNum; i++) {
			bytesList.add(new Bytes(new byte[bufSize]));
		}
		Bytes bytes = bytesList.get(listIndex);
		bytes.data[offsetIndex] = element;
	}

	public void ensure(int length) {
		if (initedBytes != null) {
			if (length < initedBytes.data.length) {
				return;
			}
			length -= initedBytes.data.length;
		}
		double value = (double) length / (double) bufSize;
		int listLength = (int) Math.ceil(value);
		int extLength = listLength - bytesList.size();
		for (int i = 0; i < extLength; i++) {
			bytesList.add(new Bytes(new byte[bufSize]));
		}
	}

	public byte[] toBytes() {
		return getRange(0, endPoint);
	}

	public void clear() {
		initedBytes = null;
		bytesList.clear();
		endPoint = 0;
	}

	public byte[] getRange(int offset, int length) {
		if (length + offset > endPoint) {
			length = endPoint - offset;
		}
		byte[] bytes = new byte[length];

		int writePos = 0;
		int readPos = offset;
		int readEnd = offset + length;

		if (initedBytes != null && readPos < initedBytes.data.length) {
			int readLen = Math.min(readEnd - readPos, initedBytes.data.length - readPos);
			System.arraycopy(initedBytes.data, readPos, bytes, writePos, readLen);
			readPos += readLen;
			writePos += readLen;
			if (readPos == readEnd) {
				return bytes;
			}
		}

		int tempReadPos = readPos;
		if (initedBytes != null) {
			tempReadPos -= initedBytes.data.length;
		}
		int startIndex = (int) (tempReadPos / bufSize);
		int curReadPos = tempReadPos % bufSize;

		for (int i = startIndex; i < bytesList.size(); i++) {
			byte[] curBytes = bytesList.get(i).data;
			int readLen = Math.min(readEnd - readPos, curBytes.length - curReadPos);
			System.arraycopy(curBytes, curReadPos, bytes, writePos, readLen);
			readPos += readLen;
			writePos += readLen;
			if (readPos == readEnd) {
				return bytes;
			}
			curReadPos = 0;
		}
		return bytes;
	}

	public void setRange(int offset, byte[] bytes) {
		ensure(offset + bytes.length);
		extEndPoint(offset + bytes.length);
		int readPos = 0;
		int readEnd = bytes.length;
		int writePos = offset;
		if (initedBytes != null && writePos < initedBytes.data.length) {
			int writeLen = Math.min(readEnd - readPos, initedBytes.data.length - writePos);
			System.arraycopy(bytes, readPos, initedBytes.data, writePos, writeLen);
			writePos += writeLen;
			readPos += writeLen;
			if (readPos == readEnd) {
				return;
			}
		}

		int tempWritePos = writePos;
		if (initedBytes != null) {
			tempWritePos -= initedBytes.data.length;
		}
		int startIndex = (int) (tempWritePos / bufSize);
		int curWritePos = tempWritePos % bufSize;
		for (int i = startIndex; i < bytesList.size(); i++) {
			byte[] curBytes = bytesList.get(i).data;
			int writeLen = Math.min(readEnd - readPos, curBytes.length - curWritePos);
			System.arraycopy(bytes, readPos, curBytes, curWritePos, writeLen);
			writePos += writeLen;
			readPos += writeLen;
			if (readPos == readEnd) {
				return;
			}
			curWritePos = 0;
		}
	}

	private List<BytesDes> getRanges(int offset, int length) {
		List<BytesDes> dess = new ArrayList<>();
		int readPos = offset;
		int readEnd = offset + length;

		if (initedBytes != null && readPos < initedBytes.data.length) {
			int readLen = Math.min(readEnd - readPos, initedBytes.data.length - readPos);

			BytesDes des = getDes();
			des.data = initedBytes.data;
			des.start = readPos;
			des.end = readPos + readLen;
			dess.add(des);

			readPos += readLen;
			if (readPos == readEnd) {
				return dess;
			}
		}

		int tempReadPos = readPos;
		if (initedBytes != null) {
			tempReadPos -= initedBytes.data.length;
		}
		int startIndex = (int) (tempReadPos / bufSize);
		int curReadPos = tempReadPos % bufSize;

		for (int i = startIndex; i < bytesList.size(); i++) {
			byte[] curBytes = bytesList.get(i).data;
			int readLen = Math.min(readEnd - readPos, curBytes.length - curReadPos);

			BytesDes des = getDes();
			des.data = curBytes;
			des.start = curReadPos;
			des.end = curReadPos + readLen;
			dess.add(des);

			readPos += readLen;
			if (readPos == readEnd) {
				return dess;
			}
			curReadPos = 0;
		}
		return dess;
	}

	private void setRangeDes(int offset, BytesDes des) {
		int readPos = des.start;
		int readEnd = des.end;
		int writePos = offset;
		if (initedBytes != null && writePos < initedBytes.data.length) {
			int writeLen = Math.min(readEnd - readPos, initedBytes.data.length - writePos);
			System.arraycopy(des.data, readPos, initedBytes.data, writePos, writeLen);
			freeDes(des);
			writePos += writeLen;
			readPos += writeLen;
			if (readPos == readEnd) {
				return;
			}
		}
		int tempWritePos = writePos;
		if (initedBytes != null) {
			tempWritePos -= initedBytes.data.length;
		}
		int startIndex = (int) (tempWritePos / bufSize);
		int curWritePos = tempWritePos % bufSize;
		for (int i = startIndex; i < bytesList.size(); i++) {
			byte[] curBytes = bytesList.get(i).data;
			int writeLen = Math.min(readEnd - readPos, curBytes.length - curWritePos);
			System.arraycopy(des.data, readPos, curBytes, curWritePos, writeLen);
			freeDes(des);
			writePos += writeLen;
			readPos += writeLen;
			if (readPos == readEnd) {
				return;
			}
			curWritePos = 0;
		}
	}

	private void setRanges(int offset, List<BytesDes> dess) {
		int length = 0;
		for (int i = 0; i < dess.size(); i++) {
			BytesDes des = dess.get(i);
			length += des.end - des.start;
		}
		ensure(offset + length);
		extEndPoint(offset + length);
		for (int i = 0; i < dess.size(); i++) {
			BytesDes des = dess.get(i);
			setRangeDes(offset, des);
			offset += des.end - des.start;
		}
	}

	/**
	 * 将一段指定二进制拷贝到目标 ByteList 的指定位置中
	 * 
	 * @param srcPos  starting position in this.
	 * @param dest    the destination ByteList.
	 * @param destPos starting position in the destination data.
	 * @param length  the number of bytes to be copied.
	 */
	public void copyTo(int srcPos, ByteList dest, int destPos, int length) {
		if (length + srcPos > endPoint) {
			length = endPoint - srcPos;
		}
		List<BytesDes> dess = getRanges(srcPos, length);
		dest.setRanges(destPos, dess);
	}
	
	public String toString() {
		String string = "";
		string += "[";
		for (int i = 0; i < getLength(); i++) {
			string += get(i);
			if (i != getLength() - 1) {
				string += ",";
			}
		}
		string += "]";
		return string;
	}
}
