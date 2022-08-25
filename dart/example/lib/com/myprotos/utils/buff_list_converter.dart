import 'package:proto_bytes/com/protobytes/buffers/core/buffs.dart';
import 'package:proto_bytes/com/protobytes/buffers/core/i_buff_info.dart';
import 'package:proto_bytes/com/protobytes/buffers/utils/buff_list_converter_base.dart';
import 'package:example/com/myprotos//utils/buff_converter.dart';
import 'package:example/com/myprotos/infos/example_info.dart';
import 'package:example/com/myprotos/infos/att_info.dart';

/// Buff转换器
class BuffListConverter extends BuffListConverterBase {
	static BuffListConverter? _instance;
	static BuffListConverter get instance {
		_instance ??= BuffListConverter();
		return _instance!;
	}

	/// 从Buff中获取ExampleInfoInfo的列表
	List<ExampleInfo> exampleInfoListFromBuff(String key, IBuffInfo buff) {
		buff.key = key;
		List<IBuffInfo> items = (buff as BuffList).items;
		List<ExampleInfo> target = [];
		for (int i = 0; i < items.length; i++){
			target.add(BuffConverter.instance.exampleInfoFromBuff("", items[i]));
		}
		return target;
	}

	/// 将一个ExampleInfoInfo的列表转换成Buff
	BuffList exampleInfoListToBuff(List<ExampleInfo> target,String key) {
		BuffList buff = BuffList();
		buff.key = key;
		for(int i = 0; i<target.length; i++){
			buff.push(BuffConverter.instance.exampleInfoToBuff(target[i], ""));
		}
		return buff;
	}

	/// 从Buff中获取AttInfoInfo的列表
	List<AttInfo> attInfoListFromBuff(String key, IBuffInfo buff) {
		buff.key = key;
		List<IBuffInfo> items = (buff as BuffList).items;
		List<AttInfo> target = [];
		for (int i = 0; i < items.length; i++){
			target.add(BuffConverter.instance.attInfoFromBuff("", items[i]));
		}
		return target;
	}

	/// 将一个AttInfoInfo的列表转换成Buff
	BuffList attInfoListToBuff(List<AttInfo> target,String key) {
		BuffList buff = BuffList();
		buff.key = key;
		for(int i = 0; i<target.length; i++){
			buff.push(BuffConverter.instance.attInfoToBuff(target[i], ""));
		}
		return buff;
	}

}
