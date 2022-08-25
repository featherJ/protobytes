package example.infos;

import java.util.List;
import com.protobytes.buffers.core.*;
import com.protobytes.buffers.core.commons.*;
import example.utils.BuffConverter;
import com.protobytes.buffers.utils.BuffBytesUtil;
import com.protobytes.utils.ByteArray;

/**
 * An Attribute Info example
 * 
 * @author MyName
 */
public class AttInfo {
	/**
	 *  Property of short format. A 16-bit signed integer between -32768 and 32767.
	 */
	public short attId = 0;
	/**
	 *  Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	 */
	public String attName = "";
	/**
	 *  Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	 */
	public String attDesc = "";
	/**
	 *  Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
	 */
	public int attValue = 0;

	public static final AttInfo fromBytes(byte[] bytes) {
		ByteArray byteArray = new ByteArray(bytes);
		byteArray.setPosition(0);
		AttInfo info = new AttInfo();
		BuffObject buff = (BuffObject) BuffBytesUtil.fromBytes(byteArray);
		List<IBuffInfo> atts = buff.getAttributes();
		if (0 < atts.size())
			info.attId = BuffConverter.getInstance().shortFromBuff("attId", atts.get(0));
		if (1 < atts.size())
			info.attName = BuffConverter.getInstance().stringFromBuff("attName", atts.get(1));
		if (2 < atts.size())
			info.attDesc = BuffConverter.getInstance().stringFromBuff("attDesc", atts.get(2));
		if (3 < atts.size())
			info.attValue = BuffConverter.getInstance().intFromBuff("attValue", atts.get(3));
		return info;
	}

	public static final byte[] toBytes(AttInfo info) {
		BuffObject buff = new BuffObject();
		buff.addAttribute(BuffConverter.getInstance().shortToBuff(info.attId, "attId"));
		buff.addAttribute(BuffConverter.getInstance().stringToBuff(info.attName, "attName"));
		buff.addAttribute(BuffConverter.getInstance().stringToBuff(info.attDesc, "attDesc"));
		buff.addAttribute(BuffConverter.getInstance().intToBuff(info.attValue, "attValue"));
		ByteArray bytes = BuffBytesUtil.toBytes(buff);
		return bytes.getBytes();
	}
}
