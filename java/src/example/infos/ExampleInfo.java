package example.infos;

import java.util.ArrayList;
import java.util.List;
import com.protobytes.buffers.core.*;
import com.protobytes.buffers.core.commons.*;
import example.utils.BuffConverter;
import example.utils.BuffListConverter;
import com.protobytes.buffers.utils.BuffBytesUtil;
import com.protobytes.utils.ByteArray;

/**
 * Example information description
 * 
 * @author MyName
 */
public class ExampleInfo {
	/**
	 *  Property of byte format. A 8-bit signed integer between -128 and 127.
	 */
	public int byteProp = 0;
	/**
	 *  Property of short format. A 16-bit signed integer between -32768 and 32767.
	 */
	public int shortProp = 0;
	/**
	 *  Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
	 */
	public int intProp = 0;
	/**
	 *  Property of byte format. A 8-bit unsigned integer between 0 and 255.
	 */
	public int ubyteProp = 0;
	/**
	 *  Property of short format. A 16-bit unsigned integer between 0 and 65535.
	 */
	public int ushortProp = 0;
	/**
	 *  Property of int format. A 32-bit unsigned integer between 0 and 4294967295.
	 */
	public long uintProp = 0l;
	/**
	 *  Property of float format. A single-precision (32-bit) floating-point number.
	 */
	public double floatProp = 0;
	/**
	 *  Property of double format. A double-precision (64-bit) floating-point number.
	 */
	public double doubleProp = 0;
	/**
	 *  Property of bool format. An 8-bit signed integer
	 */
	public boolean boolProp = false;
	/**
	 *  Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	 */
	public String stringProp = "";
	/**
	 *  Property of longstring format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an int indicating the length in bytes.
	 */
	public String longstringProp = "";
	/**
	 *  Property of bytes format. An 8-bit signed integer
	 */
	public ByteArray bytesProp = new ByteArray();
	/**
	 *  Property of AttInfo.
	 */
	public AttInfo att1Prop = new AttInfo();
	/**
	 *  Property of AttInfo.
	 */
	public AttInfo att2Prop = new AttInfo();
	/**
	 *  Byte list property.
	 */
	public List<Integer> byteListProp = new ArrayList<Integer>();
	/**
	 *  Short list property.
	 */
	public List<Integer> shortListProp = new ArrayList<Integer>();
	/**
	 *  Int list property.
	 */
	public List<Integer> intListProp = new ArrayList<Integer>();
	/**
	 *  Ubyte list property.
	 */
	public List<Integer> ubyteListProp = new ArrayList<Integer>();
	/**
	 *  Ushort list property.
	 */
	public List<Integer> ushortListProp = new ArrayList<Integer>();
	/**
	 *  Uint list property.
	 */
	public List<Long> uintListProp = new ArrayList<Long>();
	/**
	 *  Float list property.
	 */
	public List<Double> floatListProp = new ArrayList<Double>();
	/**
	 *  Double list property.
	 */
	public List<Double> doubleListProp = new ArrayList<Double>();
	/**
	 *  Bool list property.
	 */
	public List<Boolean> boolListProp = new ArrayList<Boolean>();
	/**
	 *  String list property.
	 */
	public List<String> stringListProp = new ArrayList<String>();
	/**
	 *  Longstring list property.
	 */
	public List<String> longstringListProp = new ArrayList<String>();
	/**
	 *  Bytes list property.
	 */
	public List<ByteArray> bytesListProp = new ArrayList<ByteArray>();
	/**
	 *  AttInfo list property.
	 */
	public List<AttInfo> attInfo1ListProp = new ArrayList<AttInfo>();
	/**
	 *  AttInfo list property.
	 */
	public List<AttInfo> attInfo2ListProp = new ArrayList<AttInfo>();

	public static final ExampleInfo fromBytes(byte[] bytes) {
		ByteArray byteArray = new ByteArray(bytes);
		byteArray.setPosition(0);
		ExampleInfo info = new ExampleInfo();
		BuffObject buff = (BuffObject) BuffBytesUtil.fromBytes(byteArray);
		List<IBuffInfo> atts = buff.getAttributes();
		if (0 < atts.size())
			info.byteProp = BuffConverter.getInstance().byteFromBuff("byteProp", atts.get(0));
		if (1 < atts.size())
			info.shortProp = BuffConverter.getInstance().shortFromBuff("shortProp", atts.get(1));
		if (2 < atts.size())
			info.intProp = BuffConverter.getInstance().intFromBuff("intProp", atts.get(2));
		if (3 < atts.size())
			info.ubyteProp = BuffConverter.getInstance().ubyteFromBuff("ubyteProp", atts.get(3));
		if (4 < atts.size())
			info.ushortProp = BuffConverter.getInstance().ushortFromBuff("ushortProp", atts.get(4));
		if (5 < atts.size())
			info.uintProp = BuffConverter.getInstance().uintFromBuff("uintProp", atts.get(5));
		if (6 < atts.size())
			info.floatProp = BuffConverter.getInstance().floatFromBuff("floatProp", atts.get(6));
		if (7 < atts.size())
			info.doubleProp = BuffConverter.getInstance().doubleFromBuff("doubleProp", atts.get(7));
		if (8 < atts.size())
			info.boolProp = BuffConverter.getInstance().boolFromBuff("boolProp", atts.get(8));
		if (9 < atts.size())
			info.stringProp = BuffConverter.getInstance().stringFromBuff("stringProp", atts.get(9));
		if (10 < atts.size())
			info.longstringProp = BuffConverter.getInstance().longstringFromBuff("longstringProp", atts.get(10));
		if (11 < atts.size())
			info.bytesProp = BuffConverter.getInstance().bytesFromBuff("bytesProp", atts.get(11));
		if (12 < atts.size())
			info.att1Prop = BuffConverter.getInstance().attInfoFromBuff("att1Prop", atts.get(12));
		if (13 < atts.size())
			info.att2Prop = BuffConverter.getInstance().attInfoFromBuff("att2Prop", atts.get(13));
		if (14 < atts.size())
			info.byteListProp = BuffListConverter.getInstance().byteListFromBuff("byteListProp", atts.get(14));
		if (15 < atts.size())
			info.shortListProp = BuffListConverter.getInstance().shortListFromBuff("shortListProp", atts.get(15));
		if (16 < atts.size())
			info.intListProp = BuffListConverter.getInstance().intListFromBuff("intListProp", atts.get(16));
		if (17 < atts.size())
			info.ubyteListProp = BuffListConverter.getInstance().ubyteListFromBuff("ubyteListProp", atts.get(17));
		if (18 < atts.size())
			info.ushortListProp = BuffListConverter.getInstance().ushortListFromBuff("ushortListProp", atts.get(18));
		if (19 < atts.size())
			info.uintListProp = BuffListConverter.getInstance().uintListFromBuff("uintListProp", atts.get(19));
		if (20 < atts.size())
			info.floatListProp = BuffListConverter.getInstance().floatListFromBuff("floatListProp", atts.get(20));
		if (21 < atts.size())
			info.doubleListProp = BuffListConverter.getInstance().doubleListFromBuff("doubleListProp", atts.get(21));
		if (22 < atts.size())
			info.boolListProp = BuffListConverter.getInstance().boolListFromBuff("boolListProp", atts.get(22));
		if (23 < atts.size())
			info.stringListProp = BuffListConverter.getInstance().stringListFromBuff("stringListProp", atts.get(23));
		if (24 < atts.size())
			info.longstringListProp = BuffListConverter.getInstance().longstringListFromBuff("longstringListProp", atts.get(24));
		if (25 < atts.size())
			info.bytesListProp = BuffListConverter.getInstance().bytesListFromBuff("bytesListProp", atts.get(25));
		if (26 < atts.size())
			info.attInfo1ListProp = BuffListConverter.getInstance().attInfoListFromBuff("attInfo1ListProp", atts.get(26));
		if (27 < atts.size())
			info.attInfo2ListProp = BuffListConverter.getInstance().attInfoListFromBuff("attInfo2ListProp", atts.get(27));
		return info;
	}

	public static final byte[] toBytes(ExampleInfo info) {
		BuffObject buff = new BuffObject();
		buff.addAttribute(BuffConverter.getInstance().byteToBuff(info.byteProp, "byteProp"));
		buff.addAttribute(BuffConverter.getInstance().shortToBuff(info.shortProp, "shortProp"));
		buff.addAttribute(BuffConverter.getInstance().intToBuff(info.intProp, "intProp"));
		buff.addAttribute(BuffConverter.getInstance().ubyteToBuff(info.ubyteProp, "ubyteProp"));
		buff.addAttribute(BuffConverter.getInstance().ushortToBuff(info.ushortProp, "ushortProp"));
		buff.addAttribute(BuffConverter.getInstance().uintToBuff(info.uintProp, "uintProp"));
		buff.addAttribute(BuffConverter.getInstance().floatToBuff(info.floatProp, "floatProp"));
		buff.addAttribute(BuffConverter.getInstance().doubleToBuff(info.doubleProp, "doubleProp"));
		buff.addAttribute(BuffConverter.getInstance().boolToBuff(info.boolProp, "boolProp"));
		buff.addAttribute(BuffConverter.getInstance().stringToBuff(info.stringProp, "stringProp"));
		buff.addAttribute(BuffConverter.getInstance().longstringToBuff(info.longstringProp, "longstringProp"));
		buff.addAttribute(BuffConverter.getInstance().bytesToBuff(info.bytesProp, "bytesProp"));
		buff.addAttribute(BuffConverter.getInstance().attInfoToBuff(info.att1Prop, "att1Prop"));
		buff.addAttribute(BuffConverter.getInstance().attInfoToBuff(info.att2Prop, "att2Prop"));
		buff.addAttribute(BuffListConverter.getInstance().byteListToBuff(info.byteListProp, "byteListProp"));
		buff.addAttribute(BuffListConverter.getInstance().shortListToBuff(info.shortListProp, "shortListProp"));
		buff.addAttribute(BuffListConverter.getInstance().intListToBuff(info.intListProp, "intListProp"));
		buff.addAttribute(BuffListConverter.getInstance().ubyteListToBuff(info.ubyteListProp, "ubyteListProp"));
		buff.addAttribute(BuffListConverter.getInstance().ushortListToBuff(info.ushortListProp, "ushortListProp"));
		buff.addAttribute(BuffListConverter.getInstance().uintListToBuff(info.uintListProp, "uintListProp"));
		buff.addAttribute(BuffListConverter.getInstance().floatListToBuff(info.floatListProp, "floatListProp"));
		buff.addAttribute(BuffListConverter.getInstance().doubleListToBuff(info.doubleListProp, "doubleListProp"));
		buff.addAttribute(BuffListConverter.getInstance().boolListToBuff(info.boolListProp, "boolListProp"));
		buff.addAttribute(BuffListConverter.getInstance().stringListToBuff(info.stringListProp, "stringListProp"));
		buff.addAttribute(BuffListConverter.getInstance().longstringListToBuff(info.longstringListProp, "longstringListProp"));
		buff.addAttribute(BuffListConverter.getInstance().bytesListToBuff(info.bytesListProp, "bytesListProp"));
		buff.addAttribute(BuffListConverter.getInstance().attInfoListToBuff(info.attInfo1ListProp, "attInfo1ListProp"));
		buff.addAttribute(BuffListConverter.getInstance().attInfoListToBuff(info.attInfo2ListProp, "attInfo2ListProp"));
		ByteArray bytes = BuffBytesUtil.toBytes(buff);
		return bytes.getBytes();
	}
}
