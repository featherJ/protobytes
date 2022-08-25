package example.utils;

import java.util.List;

import example.infos.*;
import com.protobytes.buffers.core.BuffObject;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.buffers.utils.BuffConverterBase;

/**
 * Buff转换器
 * 
 * @author MyName
 */
public class BuffConverter extends BuffConverterBase {
	private static BuffConverter _instance;

	public static BuffConverter getInstance() {
		if (_instance == null)
			_instance = new BuffConverter();
		return _instance;
	}

	/**
	 * 从Buff中获取ExampleInfo
	 * 
	 * @param key 该ExampleInfoInfo的Key值
	 * @param buff 获取自的Buff
	 * @return 获取的ExampleInfo
	 */
	public ExampleInfo exampleInfoFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<IBuffInfo> atts = ((BuffObject) buff).getAttributes();
		ExampleInfo target = new ExampleInfo();
		if (0 < atts.size())
			target.byteProp = BuffConverter.getInstance().byteFromBuff("byteProp", atts.get(0));
		if (1 < atts.size())
			target.shortProp = BuffConverter.getInstance().shortFromBuff("shortProp", atts.get(1));
		if (2 < atts.size())
			target.intProp = BuffConverter.getInstance().intFromBuff("intProp", atts.get(2));
		if (3 < atts.size())
			target.floatProp = BuffConverter.getInstance().floatFromBuff("floatProp", atts.get(3));
		if (4 < atts.size())
			target.doubleProp = BuffConverter.getInstance().doubleFromBuff("doubleProp", atts.get(4));
		if (5 < atts.size())
			target.boolProp = BuffConverter.getInstance().boolFromBuff("boolProp", atts.get(5));
		if (6 < atts.size())
			target.stringProp = BuffConverter.getInstance().stringFromBuff("stringProp", atts.get(6));
		if (7 < atts.size())
			target.longstringProp = BuffConverter.getInstance().longstringFromBuff("longstringProp", atts.get(7));
		if (8 < atts.size())
			target.bytesProp = BuffConverter.getInstance().bytesFromBuff("bytesProp", atts.get(8));
		if (9 < atts.size())
			target.att1Prop = BuffConverter.getInstance().attInfoFromBuff("att1Prop", atts.get(9));
		if (10 < atts.size())
			target.att2Prop = BuffConverter.getInstance().attInfoFromBuff("att2Prop", atts.get(10));
		if (11 < atts.size())
			target.byteListProp = BuffListConverter.getInstance().byteListFromBuff("byteListProp", atts.get(11));
		if (12 < atts.size())
			target.shortListProp = BuffListConverter.getInstance().shortListFromBuff("shortListProp", atts.get(12));
		if (13 < atts.size())
			target.intListProp = BuffListConverter.getInstance().intListFromBuff("intListProp", atts.get(13));
		if (14 < atts.size())
			target.floatListProp = BuffListConverter.getInstance().floatListFromBuff("floatListProp", atts.get(14));
		if (15 < atts.size())
			target.doubleListProp = BuffListConverter.getInstance().doubleListFromBuff("doubleListProp", atts.get(15));
		if (16 < atts.size())
			target.boolListProp = BuffListConverter.getInstance().boolListFromBuff("boolListProp", atts.get(16));
		if (17 < atts.size())
			target.stringListProp = BuffListConverter.getInstance().stringListFromBuff("stringListProp", atts.get(17));
		if (18 < atts.size())
			target.longstringListProp = BuffListConverter.getInstance().longstringListFromBuff("longstringListProp", atts.get(18));
		if (19 < atts.size())
			target.bytesListProp = BuffListConverter.getInstance().bytesListFromBuff("bytesListProp", atts.get(19));
		if (20 < atts.size())
			target.attInfo1ListProp = BuffListConverter.getInstance().attInfoListFromBuff("attInfo1ListProp", atts.get(20));
		if (21 < atts.size())
			target.attInfo2ListProp = BuffListConverter.getInstance().attInfoListFromBuff("attInfo2ListProp", atts.get(21));
		return target;
	}

	/**
	 * 将一个ExampleInfoInfo转换成Buff
	 * 
	 * @param target 需要转换的ExampleInfo
	 * @param key 该ExampleInfoInfo的Key值
	 * @return 转换得到的BuffObject
	 */
	public BuffObject exampleInfoToBuff(ExampleInfo target, String key) {
		if (target == null)
			target = new ExampleInfo();
		BuffObject buff = new BuffObject();
		buff.setKey(key);
		buff.addAttribute(BuffConverter.getInstance().byteToBuff(target.byteProp, "byteProp"));
		buff.addAttribute(BuffConverter.getInstance().shortToBuff(target.shortProp, "shortProp"));
		buff.addAttribute(BuffConverter.getInstance().intToBuff(target.intProp, "intProp"));
		buff.addAttribute(BuffConverter.getInstance().floatToBuff(target.floatProp, "floatProp"));
		buff.addAttribute(BuffConverter.getInstance().doubleToBuff(target.doubleProp, "doubleProp"));
		buff.addAttribute(BuffConverter.getInstance().boolToBuff(target.boolProp, "boolProp"));
		buff.addAttribute(BuffConverter.getInstance().stringToBuff(target.stringProp, "stringProp"));
		buff.addAttribute(BuffConverter.getInstance().longstringToBuff(target.longstringProp, "longstringProp"));
		buff.addAttribute(BuffConverter.getInstance().bytesToBuff(target.bytesProp, "bytesProp"));
		buff.addAttribute(BuffConverter.getInstance().attInfoToBuff(target.att1Prop, "att1Prop"));
		buff.addAttribute(BuffConverter.getInstance().attInfoToBuff(target.att2Prop, "att2Prop"));
		buff.addAttribute(BuffListConverter.getInstance().byteListToBuff(target.byteListProp, "byteListProp"));
		buff.addAttribute(BuffListConverter.getInstance().shortListToBuff(target.shortListProp, "shortListProp"));
		buff.addAttribute(BuffListConverter.getInstance().intListToBuff(target.intListProp, "intListProp"));
		buff.addAttribute(BuffListConverter.getInstance().floatListToBuff(target.floatListProp, "floatListProp"));
		buff.addAttribute(BuffListConverter.getInstance().doubleListToBuff(target.doubleListProp, "doubleListProp"));
		buff.addAttribute(BuffListConverter.getInstance().boolListToBuff(target.boolListProp, "boolListProp"));
		buff.addAttribute(BuffListConverter.getInstance().stringListToBuff(target.stringListProp, "stringListProp"));
		buff.addAttribute(BuffListConverter.getInstance().longstringListToBuff(target.longstringListProp, "longstringListProp"));
		buff.addAttribute(BuffListConverter.getInstance().bytesListToBuff(target.bytesListProp, "bytesListProp"));
		buff.addAttribute(BuffListConverter.getInstance().attInfoListToBuff(target.attInfo1ListProp, "attInfo1ListProp"));
		buff.addAttribute(BuffListConverter.getInstance().attInfoListToBuff(target.attInfo2ListProp, "attInfo2ListProp"));
		return buff;
	}

	/**
	 * 从Buff中获取AttInfo
	 * 
	 * @param key 该AttInfoInfo的Key值
	 * @param buff 获取自的Buff
	 * @return 获取的AttInfo
	 */
	public AttInfo attInfoFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<IBuffInfo> atts = ((BuffObject) buff).getAttributes();
		AttInfo target = new AttInfo();
		if (0 < atts.size())
			target.attId = BuffConverter.getInstance().shortFromBuff("attId", atts.get(0));
		if (1 < atts.size())
			target.attName = BuffConverter.getInstance().stringFromBuff("attName", atts.get(1));
		if (2 < atts.size())
			target.attDesc = BuffConverter.getInstance().stringFromBuff("attDesc", atts.get(2));
		if (3 < atts.size())
			target.attValue = BuffConverter.getInstance().intFromBuff("attValue", atts.get(3));
		return target;
	}

	/**
	 * 将一个AttInfoInfo转换成Buff
	 * 
	 * @param target 需要转换的AttInfo
	 * @param key 该AttInfoInfo的Key值
	 * @return 转换得到的BuffObject
	 */
	public BuffObject attInfoToBuff(AttInfo target, String key) {
		if (target == null)
			target = new AttInfo();
		BuffObject buff = new BuffObject();
		buff.setKey(key);
		buff.addAttribute(BuffConverter.getInstance().shortToBuff(target.attId, "attId"));
		buff.addAttribute(BuffConverter.getInstance().stringToBuff(target.attName, "attName"));
		buff.addAttribute(BuffConverter.getInstance().stringToBuff(target.attDesc, "attDesc"));
		buff.addAttribute(BuffConverter.getInstance().intToBuff(target.attValue, "attValue"));
		return buff;
	}

}
