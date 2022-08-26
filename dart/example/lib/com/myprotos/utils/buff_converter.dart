import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/buffers/utils/buff_converter_base.dart';
import 'package:example/com/myprotos/utils/buff_list_converter.dart';
import 'package:example/com/myprotos/infos/example_info.dart';
import 'package:example/com/myprotos/infos/att_info.dart';

/// Buff转换器
class BuffConverter extends BuffConverterBase {
	static BuffConverter? _instance;
	static BuffConverter get instance {
		_instance ??= BuffConverter();
		return _instance!;
	}

	/// 从Buff中获取ExampleInfo
	ExampleInfo exampleInfoFromBuff(String key, IBuffInfo buff) {
		buff.key = key;
		List<IBuffInfo> atts = (buff as BuffObject).attributes;
		ExampleInfo target = ExampleInfo();
		if (atts.isNotEmpty) {
			target.byteProp = BuffConverter.instance.byteFromBuff("byteProp", atts[0]);
		}
		if (1 < atts.length) {
			target.shortProp = BuffConverter.instance.shortFromBuff("shortProp", atts[1]);
		}
		if (2 < atts.length) {
			target.intProp = BuffConverter.instance.intFromBuff("intProp", atts[2]);
		}
		if (3 < atts.length) {
			target.ubyteProp = BuffConverter.instance.ubyteFromBuff("ubyteProp", atts[3]);
		}
		if (4 < atts.length) {
			target.ushortProp = BuffConverter.instance.ushortFromBuff("ushortProp", atts[4]);
		}
		if (5 < atts.length) {
			target.uintProp = BuffConverter.instance.uintFromBuff("uintProp", atts[5]);
		}
		if (6 < atts.length) {
			target.floatProp = BuffConverter.instance.floatFromBuff("floatProp", atts[6]);
		}
		if (7 < atts.length) {
			target.doubleProp = BuffConverter.instance.doubleFromBuff("doubleProp", atts[7]);
		}
		if (8 < atts.length) {
			target.boolProp = BuffConverter.instance.boolFromBuff("boolProp", atts[8]);
		}
		if (9 < atts.length) {
			target.stringProp = BuffConverter.instance.stringFromBuff("stringProp", atts[9]);
		}
		if (10 < atts.length) {
			target.longstringProp = BuffConverter.instance.longstringFromBuff("longstringProp", atts[10]);
		}
		if (11 < atts.length) {
			target.bytesProp = BuffConverter.instance.bytesFromBuff("bytesProp", atts[11]);
		}
		if (12 < atts.length) {
			target.att1Prop = BuffConverter.instance.attInfoFromBuff("att1Prop", atts[12]);
		}
		if (13 < atts.length) {
			target.att2Prop = BuffConverter.instance.attInfoFromBuff("att2Prop", atts[13]);
		}
		if (14 < atts.length) {
			target.byteListProp = BuffListConverter.instance.byteListFromBuff("byteListProp", atts[14]);
		}
		if (15 < atts.length) {
			target.shortListProp = BuffListConverter.instance.shortListFromBuff("shortListProp", atts[15]);
		}
		if (16 < atts.length) {
			target.intListProp = BuffListConverter.instance.intListFromBuff("intListProp", atts[16]);
		}
		if (17 < atts.length) {
			target.ubyteListProp = BuffListConverter.instance.ubyteListFromBuff("ubyteListProp", atts[17]);
		}
		if (18 < atts.length) {
			target.ushortListProp = BuffListConverter.instance.ushortListFromBuff("ushortListProp", atts[18]);
		}
		if (19 < atts.length) {
			target.uintListProp = BuffListConverter.instance.uintListFromBuff("uintListProp", atts[19]);
		}
		if (20 < atts.length) {
			target.floatListProp = BuffListConverter.instance.floatListFromBuff("floatListProp", atts[20]);
		}
		if (21 < atts.length) {
			target.doubleListProp = BuffListConverter.instance.doubleListFromBuff("doubleListProp", atts[21]);
		}
		if (22 < atts.length) {
			target.boolListProp = BuffListConverter.instance.boolListFromBuff("boolListProp", atts[22]);
		}
		if (23 < atts.length) {
			target.stringListProp = BuffListConverter.instance.stringListFromBuff("stringListProp", atts[23]);
		}
		if (24 < atts.length) {
			target.longstringListProp = BuffListConverter.instance.longstringListFromBuff("longstringListProp", atts[24]);
		}
		if (25 < atts.length) {
			target.bytesListProp = BuffListConverter.instance.bytesListFromBuff("bytesListProp", atts[25]);
		}
		if (26 < atts.length) {
			target.attInfo1ListProp = BuffListConverter.instance.attInfoListFromBuff("attInfo1ListProp", atts[26]);
		}
		if (27 < atts.length) {
			target.attInfo2ListProp = BuffListConverter.instance.attInfoListFromBuff("attInfo2ListProp", atts[27]);
		}
		return target;
	}

	/// 将一个ExampleInfoInfo转换成Buff
	BuffObject exampleInfoToBuff(ExampleInfo target, String key) {
		BuffObject buff = BuffObject();
		buff.key = key;
		buff.addAttribute(BuffConverter.instance.byteToBuff(target.byteProp, "byteProp"));
		buff.addAttribute(BuffConverter.instance.shortToBuff(target.shortProp, "shortProp"));
		buff.addAttribute(BuffConverter.instance.intToBuff(target.intProp, "intProp"));
		buff.addAttribute(BuffConverter.instance.ubyteToBuff(target.ubyteProp, "ubyteProp"));
		buff.addAttribute(BuffConverter.instance.ushortToBuff(target.ushortProp, "ushortProp"));
		buff.addAttribute(BuffConverter.instance.uintToBuff(target.uintProp, "uintProp"));
		buff.addAttribute(BuffConverter.instance.floatToBuff(target.floatProp, "floatProp"));
		buff.addAttribute(BuffConverter.instance.doubleToBuff(target.doubleProp, "doubleProp"));
		buff.addAttribute(BuffConverter.instance.boolToBuff(target.boolProp, "boolProp"));
		buff.addAttribute(BuffConverter.instance.stringToBuff(target.stringProp, "stringProp"));
		buff.addAttribute(BuffConverter.instance.longstringToBuff(target.longstringProp, "longstringProp"));
		buff.addAttribute(BuffConverter.instance.bytesToBuff(target.bytesProp, "bytesProp"));
		buff.addAttribute(BuffConverter.instance.attInfoToBuff(target.att1Prop, "att1Prop"));
		buff.addAttribute(BuffConverter.instance.attInfoToBuff(target.att2Prop, "att2Prop"));
		buff.addAttribute(BuffListConverter.instance.byteListToBuff(target.byteListProp, "byteListProp"));
		buff.addAttribute(BuffListConverter.instance.shortListToBuff(target.shortListProp, "shortListProp"));
		buff.addAttribute(BuffListConverter.instance.intListToBuff(target.intListProp, "intListProp"));
		buff.addAttribute(BuffListConverter.instance.ubyteListToBuff(target.ubyteListProp, "ubyteListProp"));
		buff.addAttribute(BuffListConverter.instance.ushortListToBuff(target.ushortListProp, "ushortListProp"));
		buff.addAttribute(BuffListConverter.instance.uintListToBuff(target.uintListProp, "uintListProp"));
		buff.addAttribute(BuffListConverter.instance.floatListToBuff(target.floatListProp, "floatListProp"));
		buff.addAttribute(BuffListConverter.instance.doubleListToBuff(target.doubleListProp, "doubleListProp"));
		buff.addAttribute(BuffListConverter.instance.boolListToBuff(target.boolListProp, "boolListProp"));
		buff.addAttribute(BuffListConverter.instance.stringListToBuff(target.stringListProp, "stringListProp"));
		buff.addAttribute(BuffListConverter.instance.longstringListToBuff(target.longstringListProp, "longstringListProp"));
		buff.addAttribute(BuffListConverter.instance.bytesListToBuff(target.bytesListProp, "bytesListProp"));
		buff.addAttribute(BuffListConverter.instance.attInfoListToBuff(target.attInfo1ListProp, "attInfo1ListProp"));
		buff.addAttribute(BuffListConverter.instance.attInfoListToBuff(target.attInfo2ListProp, "attInfo2ListProp"));
		return buff;
	}

	/// 从Buff中获取AttInfo
	AttInfo attInfoFromBuff(String key, IBuffInfo buff) {
		buff.key = key;
		List<IBuffInfo> atts = (buff as BuffObject).attributes;
		AttInfo target = AttInfo();
		if (atts.isNotEmpty) {
			target.attId = BuffConverter.instance.shortFromBuff("attId", atts[0]);
		}
		if (1 < atts.length) {
			target.attName = BuffConverter.instance.stringFromBuff("attName", atts[1]);
		}
		if (2 < atts.length) {
			target.attDesc = BuffConverter.instance.stringFromBuff("attDesc", atts[2]);
		}
		if (3 < atts.length) {
			target.attValue = BuffConverter.instance.intFromBuff("attValue", atts[3]);
		}
		return target;
	}

	/// 将一个AttInfoInfo转换成Buff
	BuffObject attInfoToBuff(AttInfo target, String key) {
		BuffObject buff = BuffObject();
		buff.key = key;
		buff.addAttribute(BuffConverter.instance.shortToBuff(target.attId, "attId"));
		buff.addAttribute(BuffConverter.instance.stringToBuff(target.attName, "attName"));
		buff.addAttribute(BuffConverter.instance.stringToBuff(target.attDesc, "attDesc"));
		buff.addAttribute(BuffConverter.instance.intToBuff(target.attValue, "attValue"));
		return buff;
	}

}
