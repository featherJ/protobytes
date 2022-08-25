package example.utils;

import java.util.ArrayList;
import java.util.List;

import example.infos.*;
import com.protobytes.buffers.core.BuffList;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.buffers.utils.BuffListConverterBase;

/**
 * BuffList转换器
 * 
 * @author MyName
 */
public class BuffListConverter extends BuffListConverterBase {
	private static BuffListConverter _instance;

	public static BuffListConverter getInstance() {
		if (_instance == null)
			_instance = new BuffListConverter();
		return _instance;
	}

	/**
	 * 从Buff中获取ExampleInfoInfo的列表
	 * 
	 * @param key 该ExampleInfoInfo列表的Key值
	 * @param buff 获取自的Buff
	 * @return 获取的ExampleInfo列表
	 */
	public List<ExampleInfo> exampleInfoListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		List<ExampleInfo> target = new ArrayList<ExampleInfo>();
		for (int i = 0; i < items.size(); i++)
			target.add(BuffConverter.getInstance().exampleInfoFromBuff("", items.get(i)));
		return target;
	}

	/**
	 * 将一个ExampleInfoInfo的列表转换成Buff
	 * 
	 * @param target 需要转换的ExampleInfoInfo列表
	 * @param key 该ExampleInfoInfo列表的Key值
	 * @return 转换得到的BuffList
	 */
	public BuffList exampleInfoListToBuff(List<ExampleInfo> target,String key) {
		if (target == null)
			target = new ArrayList<ExampleInfo>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for(int i = 0; i<target.size(); i++)
			buff.push(BuffConverter.getInstance().exampleInfoToBuff(target.get(i), ""));
		return buff;
	}

	/**
	 * 从Buff中获取AttInfoInfo的列表
	 * 
	 * @param key 该AttInfoInfo列表的Key值
	 * @param buff 获取自的Buff
	 * @return 获取的AttInfo列表
	 */
	public List<AttInfo> attInfoListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		List<AttInfo> target = new ArrayList<AttInfo>();
		for (int i = 0; i < items.size(); i++)
			target.add(BuffConverter.getInstance().attInfoFromBuff("", items.get(i)));
		return target;
	}

	/**
	 * 将一个AttInfoInfo的列表转换成Buff
	 * 
	 * @param target 需要转换的AttInfoInfo列表
	 * @param key 该AttInfoInfo列表的Key值
	 * @return 转换得到的BuffList
	 */
	public BuffList attInfoListToBuff(List<AttInfo> target,String key) {
		if (target == null)
			target = new ArrayList<AttInfo>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for(int i = 0; i<target.size(); i++)
			buff.push(BuffConverter.getInstance().attInfoToBuff(target.get(i), ""));
		return buff;
	}

}
