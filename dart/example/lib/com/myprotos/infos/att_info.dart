import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/utils/buff_bytes_util.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';
import 'package:example/com/myprotos/utils/buff_converter.dart';

/// An Attribute Info example
class AttInfo {
	///  Property of short format. A 16-bit signed integer between -32768 and 32767.
	int attId = 0;

	///  Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	String attName = "";

	///  Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	String attDesc = "";

	///  Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
	int attValue = 0;


	static AttInfo fromBytes(List<int> bytes) {
		ByteArray byteArray = ByteArray(bytes);
		byteArray.position = 0;
		AttInfo info = AttInfo();
		BuffObject buff = BuffBytesUtil.fromBytes(byteArray) as BuffObject;
		List<IBuffInfo> atts = buff.attributes;
		if (atts.isNotEmpty) {
			info.attId = BuffConverter.instance.shortFromBuff("attId", atts[0]);
		}
		if (1 < atts.length) {
			info.attName = BuffConverter.instance.stringFromBuff("attName", atts[1]);
		}
		if (2 < atts.length) {
			info.attDesc = BuffConverter.instance.stringFromBuff("attDesc", atts[2]);
		}
		if (3 < atts.length) {
			info.attValue = BuffConverter.instance.intFromBuff("attValue", atts[3]);
		}
		return info;
	}

	static List<int> toBytes(AttInfo info) {
		BuffObject buff = BuffObject();
		buff.addAttribute(BuffConverter.instance.shortToBuff(info.attId, "attId"));
		buff.addAttribute(BuffConverter.instance.stringToBuff(info.attName, "attName"));
		buff.addAttribute(BuffConverter.instance.stringToBuff(info.attDesc, "attDesc"));
		buff.addAttribute(BuffConverter.instance.intToBuff(info.attValue, "attValue"));
		ByteArray bytes = BuffBytesUtil.toBytes(buff);
		return bytes.bytes;
	}
}
