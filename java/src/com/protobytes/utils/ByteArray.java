package com.protobytes.utils;

import java.io.UnsupportedEncodingException;

/**
 * The ByteArray class provides methods and properties to optimize reading,
 * writing, and working with binary data.
 * 
 * @author Agua.L
 *
 */
public class ByteArray {
	private ByteList byteList;

	private int position = 0;
	private String endian = Endian.BIG_ENDIAN;

	public ByteArray() {
		byteList = new ByteList(20000);
	}

	public ByteArray(byte[] bytes) {
		byteList = new ByteList(bytes, 20000);
	}

	/**
	 * The length of the ByteArray object, in bytes.
	 */
	public int getLength() {
		return byteList.getLength();
	}

	/**
	 * The length of the ByteArray object, in bytes.
	 */
	public void setLength(int length) {
		byteList.setLength(length);
		if (this.position > length) {
			this.position = length;
		}
	}

	/**
	 * Get value at specified position;
	 */
	public byte get(int index) {
		return byteList.get(index);
	}

	/**
	 * Set value at specified position;
	 * 
	 * @param index
	 * @param element
	 */
	public void set(int index, byte element) {
		byteList.set(index, element);
	}

	/**
	 * The number of bytes of data available for reading from the current position
	 * in the byte array to the end of the array.
	 */
	public int getBytesAvailable() {
		return getLength() - this.getPosition();
	}

	/**
	 * Changes or reads the byte order for the data; either Endian.BIG_ENDIAN or
	 * Endian.LITTLE_ENDIAN.
	 */
	public String getEndian() {
		return endian;
	}

	/**
	 * Changes or reads the byte order for the data; either Endian.BIG_ENDIAN or
	 * Endian.LITTLE_ENDIAN.
	 */
	public void setEndian(String endian) {
		this.endian = endian;
	}

	/**
	 * Moves, or returns the current position, in bytes, of the file pointer into
	 * the ByteArray object.
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * Moves, or returns the current position, in bytes, of the file pointer into
	 * the ByteArray object.
	 */
	public void setPosition(int position) {
		byteList.ensure(position);
		this.position = position;
	}

	public byte[] getBytes() {
		return byteList.toBytes();
	}

	/**
	 * Clears the contents of the byte array and resets the length and position
	 * properties to 0.
	 */
	public void clear() {
		byteList.clear();
		this.position = 0;
	}

	/**
	 * Reads a Boolean value from the byte stream.
	 * 
	 * @return Returns true if the byte is nonzero, false otherwise.
	 */
	public boolean readBoolean() {
		this.validate(1);
		byte value = get(this.position);
		this.position++;
		return value != 0;
	}

	/**
	 * Writes a Boolean value.
	 * 
	 * @param value A boolean value determining which byte is written. If the
	 *              parameter is true, the method writes a 1; if false, the method
	 *              writes a 0.
	 */
	public void writeBoolean(boolean value) {
		this.set(this.position, value ? (byte) 1 : (byte) 0);
		this.position++;
	}

	/**
	 * Reads a signed byte from the byte stream.
	 * 
	 * @return A 8-bit signed integer between -128 and 127.
	 * 
	 */
	public int readByte() {
		this.validate(1);
		byte value = get(this.position);
		this.position++;
		return value;
	}

	/**
	 * Reads an unsigned byte from the byte stream.
	 * 
	 * @return A 8-bit unsigned integer between 0 and 255.
	 * 
	 */
	public int readUnsignedByte() {
		return readByte() & 0xff;
	}

	/**
	 * Writes a byte to the byte stream.
	 * 
	 * @param value A byte value.
	 */
	public void writeByte(int value) {
		this.set(this.position, (byte) (value & 0xff));
		this.position++;
	}

	/**
	 * Reads a sequence of UTF-8 bytes specified by the length parameter from the
	 * byte stream and returns a string.
	 * 
	 * @param bytes  The ByteArray object to read data into.
	 * @param offset The offset (position) in bytes at which the read data should be
	 *               written.
	 * @param length The number of bytes to read. The default value of 0 causes all
	 *               available data to be read.
	 */
	public void readBytes(ByteArray bytes, int offset, int length) {
		if (bytes == null) {
			return;
		}
		if (length == 0) {
			length = this.getBytesAvailable();
		}
		byte[] sourceBytes = this.readBytes(length);
		bytes.byteList.setRange(offset, sourceBytes);
	}

	/**
	 * Reads a sequence of UTF-8 bytes specified by the length parameter from the
	 * byte stream and returns a string.
	 * 
	 * @param bytes bytes The ByteArray object to read data into.
	 */
	public void readBytes(ByteArray bytes) {
		this.readBytes(bytes, 0, 0);
	}

	/**
	 * Reads a sequence of UTF-8 bytes specified by the length parameter from the
	 * byte stream and returns a string.
	 * 
	 * @param bytes  bytes The ByteArray object to read data into.
	 * @param offset The offset (position) in bytes at which the read data should be
	 *               written.
	 */
	public void readBytes(ByteArray bytes, int offset) {
		this.readBytes(bytes, offset, 0);
	}

	/**
	 * Writes a sequence of length bytes from the specified byte array, bytes,
	 * starting offset(zero-based index) bytes into the byte stream.
	 * 
	 * @param bytes  The ByteArray object.
	 * @param offset A zero-based index indicating the position into the array to
	 *               begin writing.
	 * @param length An unsigned integer indicating how far into the buffer to
	 *               write.
	 */
	public void writeBytes(ByteArray bytes, int offset, int length) {
		int writeLength = 0;
		if (length < 0) {
			return;
		} else if (length == 0) {
			writeLength = bytes.getLength() - offset;
		} else {
			writeLength = Math.min(bytes.getLength() - offset, length);
		}
		byte[] sourceBytes = bytes.byteList.getRange(offset, writeLength);
		writeBytes(sourceBytes);
	}

	/**
	 * Writes a sequence of length bytes from the specified byte array, bytes,
	 * starting offset(zero-based index) bytes into the byte stream.
	 * 
	 * @param bytes The ByteArray object.
	 */
	public void writeBytes(ByteArray bytes) {
		this.writeBytes(bytes, 0, 0);
	}

	/**
	 * Writes a sequence of length bytes from the specified byte array, bytes,
	 * starting offset(zero-based index) bytes into the byte stream.
	 * 
	 * @param bytes  The ByteArray object.
	 * @param offset A zero-based index indicating the position into the array to
	 *               begin writing.
	 */
	public void writeBytes(ByteArray bytes, int offset) {
		this.writeBytes(bytes, offset, 0);
	}

	/**
	 * Reads an 64-bit long from the byte stream.
	 * 
	 * @return value A 64-bit long value.
	 */
	public long readLong() {
		this.validate(8);
		long value = 0;
		if (this.endian.equals(Endian.BIG_ENDIAN)) {
			for (int i = 0; i < 8; i++) {
				value <<= 8;
				value |= (this.get(this.position + i) & 0xFF);
			}
		} else {
			for (int i = 7; i >= 0; i--) {
				value <<= 8;
				value |= (this.get(this.position + i) & 0xFF);
			}
		}
		this.position += 8;
		return value;
	}

	/**
	 * Writes a 64-bit long to the byte stream.
	 * 
	 * @param value A 64-bit long value.
	 */
	public void writeLong(long value) {
		if (this.endian.equals(Endian.BIG_ENDIAN)) {
			for (int i = 0; i < 8; i++) {
				this.set(this.position + i, (byte) (value & 0xFF));
				value >>= 8;
			}
		} else {
			for (int i = 7; i >= 0; i--) {
				this.set(this.position + i, (byte) (value & 0xFF));
				value >>= 8;
			}
		}
		this.position += 8;
	}

	/**
	 * Reads an IEEE 754 double-precision (64-bit) floating-point number from the
	 * byte stream.
	 * 
	 * @return A double-precision (64-bit) floating-point number.
	 * 
	 */
	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	/**
	 * Writes an IEEE 754 double-precision (64-bit) floating-point number to the
	 * byte stream.
	 * 
	 * @param value A double-precision (64-bit) floating-point number.
	 */
	public void writeDouble(double value) {
		long l = Double.doubleToLongBits(value);
		writeLong(l);
	}

	/**
	 * Reads a signed 32-bit integer from the byte stream.
	 * 
	 * @return A 32-bit signed integer between -2147483648 and 2147483647.
	 */
	public int readInt() {
		this.validate(4);
		int value = 0;
		if (this.endian.equals(Endian.BIG_ENDIAN)) {
			for (int i = 0; i < 4; i++) {
				value <<= 8;
				value |= (this.get(this.position + i) & 0xFF);
			}
		} else {
			for (int i = 3; i >= 0; i--) {
				value <<= 8;
				value |= (this.get(this.position + i) & 0xFF);
			}
		}
		this.position += 4;
		return value;
	}

	/**
	 * Writes a 32-bit signed integer to the byte stream.
	 * 
	 * @param value An integer to write to the byte stream.
	 */
	public void writeInt(int value) {
		if (this.endian.equals(Endian.BIG_ENDIAN)) {
			for (int i = 3; i >= 0; i--) {
				this.set(this.position + i, (byte) (value & 0xFF));
				value >>= 8;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				this.set(this.position + i, (byte) (value & 0xFF));
				value >>= 8;
			}
		}
		this.position += 4;
	}

	/**
	 * Reads an unsigned 32-bit integer from the byte stream.
	 * 
	 * @return A 32-bit unsigned integer between 0 and 4294967295.
	 */
	public long readUnsignedInt() {
		return ((long) readInt()) & 0xffffffffl;
	}

	/**
	 * Writes a 32-bit unsigned integer to the byte stream.
	 * 
	 * @param value An unsigned integer to write to the byte stream.
	 */
	public void writeUnsignedInt(long value) {
		writeInt((int) (value & 0xffffffff));
	}

	/**
	 * Reads an IEEE 754 single-precision (32-bit) floating-point number from the
	 * byte stream.
	 * 
	 * @return A single-precision (32-bit) floating-point number.
	 */
	public double readFloat() {
		return (double) Float.intBitsToFloat(readInt());
	}

	/**
	 * Writes an IEEE 754 single-precision (32-bit) floating-point number to the
	 * byte stream.
	 * 
	 * @param value A single-precision (32-bit) floating-point number.
	 */
	public void writeFloat(double value) {
		int i = Float.floatToIntBits((float) value);
		this.writeInt(i);
	}

	/**
	 * Reads a multiple byte string of specified length from the byte stream using
	 * the specified character set.
	 * 
	 * @param length  The number of bytes from the byte stream to read.
	 * @param charSet The string denoting the character set to use to interpret the
	 *                bytes.
	 * @return encoded string.
	 */
	public String readMultiByte(int length, String charSet) {
		if (length == 0) {
			return "";
		}
		byte[] bytes = this.readBytes(length);
		return convertBytesToString(bytes, charSet);
	}

	/**
	 * Writes a multiple byte string to the byte stream using the specified
	 * character set.
	 * 
	 * @param value   The string value to be written.
	 * @param charSet The string denoting the character set to use.
	 */
	public void writeMultiByte(String value, String charSet) {
		if (value.equals("")) {
			return;
		}
		byte[] bytes = this.convertStringToBytes(value, charSet);
		this.writeBytes(bytes);
	}

	/**
	 * Reads a signed 16-bit integer from the byte stream.
	 * 
	 * @return A 16-bit signed integer between -32768 and 32767.
	 */
	public int readShort() {
		this.validate(2);
		short value = 0;
		if (this.endian.equals(Endian.BIG_ENDIAN)) {
			for (int i = 0; i < 2; i++) {
				value <<= 8;
				value |= (this.get(this.position + i) & 0xFF);
			}
		} else {
			for (int i = 1; i >= 0; i--) {
				value <<= 8;
				value |= (this.get(this.position + i) & 0xFF);
			}
		}
		this.position += 2;
		return value;
	}

	/**
	 * Reads an unsigned 16-bit integer from the byte stream.
	 * 
	 * @return A 16-bit unsigned integer between 0 and 65535.
	 */
	public int readUnsignedShort() {
		return readShort() & 0xffff;
	}

	/**
	 * Writes a 16-bit integer to the byte stream.
	 * 
	 * @param value 32-bit integer, whose low 16 bits are written to the byte
	 *              stream.
	 */
	public void writeShort(int value) {
		if (this.endian.equals(Endian.BIG_ENDIAN)) {
			for (int i = 1; i >= 0; i--) {
				this.set(this.position + i, (byte) (value & 0xFF));
				value >>= 8;
			}
		} else {
			for (int i = 0; i < 2; i++) {
				this.set(this.position + i, (byte) (value & 0xFF));
				value >>= 8;
			}
		}
		this.position += 2;
	}

	/**
	 * Reads a UTF-8 string from the byte stream. The string is assumed to be
	 * prefixed with an unsigned short indicating the length in bytes.
	 * 
	 * @return UTF-8 encoded string.
	 */
	public String readUTF() {
		int length = readUnsignedShort();
		return this.readMultiByte(length, "UTF-8");
	}

	/**
	 * Writes a UTF-8 string to the byte stream. The length of the UTF-8 string in
	 * bytes is written first, as a 16-bit integer, followed by the bytes
	 * representing the characters of the string.
	 * 
	 * @param value The string value to be written.
	 */
	public void writeUTF(String value) {
		byte[] bytes = this.convertStringToBytes(value, "UTF-8");
		writeShort(bytes.length);
		writeBytes(bytes);
	}

	/**
	 * Reads a sequence of UTF-8 bytes specified by the length parameter from the
	 * byte stream and returns a string.
	 * 
	 * @param length  Length of the UTF-8 bytes.
	 * @param charSet A string composed of the UTF-8 bytes of the specified length.
	 * @return
	 */
	public String readUTFBytes(int length) {
		byte[] bytes = this.readBytes(length);
		return convertBytesToString(bytes, "UTF-8");
	}

	/**
	 * Writes a UTF-8 string to the byte stream. Similar to the writeUTF() method,
	 * but writeUTFBytes() does not prefix the string with a 16-bit length word.
	 * 
	 * @param value The string value to be written.
	 */
	public void writeUTFBytes(String value) {
		byte[] bytes = this.convertStringToBytes(value, "UTF-8");
		this.writeBytes(bytes);
	}

	public byte[] readBytes(int length) {
		this.validate(length);
		byte[] bytes = byteList.getRange(position, length);
		this.position += length;
		return bytes;
	}

	public void writeBytes(byte[] bytes) {
		byteList.setRange(position, bytes);
		this.position += bytes.length;
	}

	private byte[] convertStringToBytes(String value, String charSet) {
		byte[] bytes = new byte[0];
		if (value != null)
			try {
				bytes = value.getBytes(charSet);
			} catch (UnsupportedEncodingException unsupportedEncodingException) {
				bytes = value.getBytes();
			}
		return bytes;
	}

	private String convertBytesToString(byte[] value, String charSet) {
		String str = null;
		try {
			str = new String(value, charSet);
		} catch (UnsupportedEncodingException unsupportedEncodingException) {
			str = new String(value);
		}
		return str;
	}

	private boolean validate(int len) {
		int bl = byteList.getLength();
		if (bl > 0 && this.position + len <= bl) {
			return true;
		} else {
			throw new Error("End of file was encountered");
		}
	}

	public String toString() {
		String string = "";
		string += "[";
		for (int i = 0; i < byteList.getLength(); i++) {
			string += byteList.get(i);
			if (i != byteList.getLength() - 1) {
				string += ",";
			}
		}
		string += "]";
		return string;
	}
}