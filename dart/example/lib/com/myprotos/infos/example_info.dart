import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/utils/buff_bytes_util.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';
import 'package:example/com/myprotos/utils/buff_converter.dart';
import 'package:example/com/myprotos/utils/buff_list_converter.dart';
import 'package:example/com/myprotos/infos/att_info.dart';

/// Example information description
class ExampleInfo {
	///  Property of byte format. A 8-bit signed integer between -128 and 127.
	int byteProp = 0;

	///  Property of short format. A 16-bit signed integer between -32768 and 32767.
	int shortProp = 0;

	///  Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
	int intProp = 0;

	///  Property of int format. A 64-bit signed integer between -9223372036854775808 and 9223372036854775807.
	int int64Prop = 0;

	///  Property of byte format. A 8-bit unsigned integer between 0 and 255.
	int ubyteProp = 0;

	///  Property of short format. A 16-bit unsigned integer between 0 and 65535.
	int ushortProp = 0;

	///  Property of int format. A 32-bit unsigned integer between 0 and 4294967295.
	int uintProp = 0;

	///  Property of float format. A single-precision (32-bit) floating-point number.
	double floatProp = 0;

	///  Property of double format. A double-precision (64-bit) floating-point number.
	double doubleProp = 0;

	///  Property of bool format. An 8-bit signed integer
	bool boolProp = false;

	///  Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	String stringProp = "";

	///  Property of longstring format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an int indicating the length in bytes.
	String longstringProp = "";

	///  Property of bytes format. An 8-bit signed integer
	ByteArray bytesProp = ByteArray();

	///  Property of AttInfo.
	AttInfo att1Prop = AttInfo();

	///  Property of AttInfo.
	AttInfo att2Prop = AttInfo();

	///  Byte list property.
	List<int> byteListProp = [];

	///  Short list property.
	List<int> shortListProp = [];

	///  Int list property.
	List<int> intListProp = [];

	///  Ubyte list property.
	List<int> ubyteListProp = [];

	///  Ushort list property.
	List<int> ushortListProp = [];

	///  Uint list property.
	List<int> uintListProp = [];

	///  Int64 list property.
	List<int> int64ListProp = [];

	///  Float list property.
	List<double> floatListProp = [];

	///  Double list property.
	List<double> doubleListProp = [];

	///  Bool list property.
	List<bool> boolListProp = [];

	///  String list property.
	List<String> stringListProp = [];

	///  Longstring list property.
	List<String> longstringListProp = [];

	///  Bytes list property.
	List<ByteArray> bytesListProp = [];

	///  AttInfo list property.
	List<AttInfo> attInfo1ListProp = [];

	///  AttInfo list property.
	List<AttInfo> attInfo2ListProp = [];


	static ExampleInfo fromBytes(List<int> bytes) {
		ByteArray byteArray = ByteArray(bytes);
		byteArray.position = 0;
		ExampleInfo info = ExampleInfo();
		BuffObject buff = BuffBytesUtil.fromBytes(byteArray) as BuffObject;
		List<IBuffInfo> atts = buff.attributes;
		if (atts.isNotEmpty) {
			info.byteProp = BuffConverter.instance.byteFromBuff("byteProp", atts[0]);
		}
		if (1 < atts.length) {
			info.shortProp = BuffConverter.instance.shortFromBuff("shortProp", atts[1]);
		}
		if (2 < atts.length) {
			info.intProp = BuffConverter.instance.intFromBuff("intProp", atts[2]);
		}
		if (3 < atts.length) {
			info.int64Prop = BuffConverter.instance.int64FromBuff("int64Prop", atts[3]);
		}
		if (4 < atts.length) {
			info.ubyteProp = BuffConverter.instance.ubyteFromBuff("ubyteProp", atts[4]);
		}
		if (5 < atts.length) {
			info.ushortProp = BuffConverter.instance.ushortFromBuff("ushortProp", atts[5]);
		}
		if (6 < atts.length) {
			info.uintProp = BuffConverter.instance.uintFromBuff("uintProp", atts[6]);
		}
		if (7 < atts.length) {
			info.floatProp = BuffConverter.instance.floatFromBuff("floatProp", atts[7]);
		}
		if (8 < atts.length) {
			info.doubleProp = BuffConverter.instance.doubleFromBuff("doubleProp", atts[8]);
		}
		if (9 < atts.length) {
			info.boolProp = BuffConverter.instance.boolFromBuff("boolProp", atts[9]);
		}
		if (10 < atts.length) {
			info.stringProp = BuffConverter.instance.stringFromBuff("stringProp", atts[10]);
		}
		if (11 < atts.length) {
			info.longstringProp = BuffConverter.instance.longstringFromBuff("longstringProp", atts[11]);
		}
		if (12 < atts.length) {
			info.bytesProp = BuffConverter.instance.bytesFromBuff("bytesProp", atts[12]);
		}
		if (13 < atts.length) {
			info.att1Prop = BuffConverter.instance.attInfoFromBuff("att1Prop", atts[13]);
		}
		if (14 < atts.length) {
			info.att2Prop = BuffConverter.instance.attInfoFromBuff("att2Prop", atts[14]);
		}
		if (15 < atts.length) {
			info.byteListProp = BuffListConverter.instance.byteListFromBuff("byteListProp", atts[15]);
		}
		if (16 < atts.length) {
			info.shortListProp = BuffListConverter.instance.shortListFromBuff("shortListProp", atts[16]);
		}
		if (17 < atts.length) {
			info.intListProp = BuffListConverter.instance.intListFromBuff("intListProp", atts[17]);
		}
		if (18 < atts.length) {
			info.ubyteListProp = BuffListConverter.instance.ubyteListFromBuff("ubyteListProp", atts[18]);
		}
		if (19 < atts.length) {
			info.ushortListProp = BuffListConverter.instance.ushortListFromBuff("ushortListProp", atts[19]);
		}
		if (20 < atts.length) {
			info.uintListProp = BuffListConverter.instance.uintListFromBuff("uintListProp", atts[20]);
		}
		if (21 < atts.length) {
			info.int64ListProp = BuffListConverter.instance.int64ListFromBuff("int64ListProp", atts[21]);
		}
		if (22 < atts.length) {
			info.floatListProp = BuffListConverter.instance.floatListFromBuff("floatListProp", atts[22]);
		}
		if (23 < atts.length) {
			info.doubleListProp = BuffListConverter.instance.doubleListFromBuff("doubleListProp", atts[23]);
		}
		if (24 < atts.length) {
			info.boolListProp = BuffListConverter.instance.boolListFromBuff("boolListProp", atts[24]);
		}
		if (25 < atts.length) {
			info.stringListProp = BuffListConverter.instance.stringListFromBuff("stringListProp", atts[25]);
		}
		if (26 < atts.length) {
			info.longstringListProp = BuffListConverter.instance.longstringListFromBuff("longstringListProp", atts[26]);
		}
		if (27 < atts.length) {
			info.bytesListProp = BuffListConverter.instance.bytesListFromBuff("bytesListProp", atts[27]);
		}
		if (28 < atts.length) {
			info.attInfo1ListProp = BuffListConverter.instance.attInfoListFromBuff("attInfo1ListProp", atts[28]);
		}
		if (29 < atts.length) {
			info.attInfo2ListProp = BuffListConverter.instance.attInfoListFromBuff("attInfo2ListProp", atts[29]);
		}
		return info;
	}

	static List<int> toBytes(ExampleInfo info) {
		BuffObject buff = BuffObject();
		buff.addAttribute(BuffConverter.instance.byteToBuff(info.byteProp, "byteProp"));
		buff.addAttribute(BuffConverter.instance.shortToBuff(info.shortProp, "shortProp"));
		buff.addAttribute(BuffConverter.instance.intToBuff(info.intProp, "intProp"));
		buff.addAttribute(BuffConverter.instance.int64ToBuff(info.int64Prop, "int64Prop"));
		buff.addAttribute(BuffConverter.instance.ubyteToBuff(info.ubyteProp, "ubyteProp"));
		buff.addAttribute(BuffConverter.instance.ushortToBuff(info.ushortProp, "ushortProp"));
		buff.addAttribute(BuffConverter.instance.uintToBuff(info.uintProp, "uintProp"));
		buff.addAttribute(BuffConverter.instance.floatToBuff(info.floatProp, "floatProp"));
		buff.addAttribute(BuffConverter.instance.doubleToBuff(info.doubleProp, "doubleProp"));
		buff.addAttribute(BuffConverter.instance.boolToBuff(info.boolProp, "boolProp"));
		buff.addAttribute(BuffConverter.instance.stringToBuff(info.stringProp, "stringProp"));
		buff.addAttribute(BuffConverter.instance.longstringToBuff(info.longstringProp, "longstringProp"));
		buff.addAttribute(BuffConverter.instance.bytesToBuff(info.bytesProp, "bytesProp"));
		buff.addAttribute(BuffConverter.instance.attInfoToBuff(info.att1Prop, "att1Prop"));
		buff.addAttribute(BuffConverter.instance.attInfoToBuff(info.att2Prop, "att2Prop"));
		buff.addAttribute(BuffListConverter.instance.byteListToBuff(info.byteListProp, "byteListProp"));
		buff.addAttribute(BuffListConverter.instance.shortListToBuff(info.shortListProp, "shortListProp"));
		buff.addAttribute(BuffListConverter.instance.intListToBuff(info.intListProp, "intListProp"));
		buff.addAttribute(BuffListConverter.instance.ubyteListToBuff(info.ubyteListProp, "ubyteListProp"));
		buff.addAttribute(BuffListConverter.instance.ushortListToBuff(info.ushortListProp, "ushortListProp"));
		buff.addAttribute(BuffListConverter.instance.uintListToBuff(info.uintListProp, "uintListProp"));
		buff.addAttribute(BuffListConverter.instance.int64ListToBuff(info.int64ListProp, "int64ListProp"));
		buff.addAttribute(BuffListConverter.instance.floatListToBuff(info.floatListProp, "floatListProp"));
		buff.addAttribute(BuffListConverter.instance.doubleListToBuff(info.doubleListProp, "doubleListProp"));
		buff.addAttribute(BuffListConverter.instance.boolListToBuff(info.boolListProp, "boolListProp"));
		buff.addAttribute(BuffListConverter.instance.stringListToBuff(info.stringListProp, "stringListProp"));
		buff.addAttribute(BuffListConverter.instance.longstringListToBuff(info.longstringListProp, "longstringListProp"));
		buff.addAttribute(BuffListConverter.instance.bytesListToBuff(info.bytesListProp, "bytesListProp"));
		buff.addAttribute(BuffListConverter.instance.attInfoListToBuff(info.attInfo1ListProp, "attInfo1ListProp"));
		buff.addAttribute(BuffListConverter.instance.attInfoListToBuff(info.attInfo2ListProp, "attInfo2ListProp"));
		ByteArray bytes = BuffBytesUtil.toBytes(buff);
		return bytes.bytes;
	}
}
