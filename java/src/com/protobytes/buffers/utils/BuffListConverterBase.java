package com.protobytes.buffers.utils;

import java.util.ArrayList;
import java.util.List;

import com.protobytes.buffers.core.BuffList;
import com.protobytes.buffers.core.commons.IBuffInfo;
import com.protobytes.utils.ByteArray;

public class BuffListConverterBase {
	private static BuffListConverterBase _instance;

	public static BuffListConverterBase getInstance() {
		if (_instance == null)
			_instance = new BuffListConverterBase();
		return _instance;
	}

	public List<Integer> byteListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Integer> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().byteFromBuff("", item));
		return target;
	}

	public BuffList byteListToBuff(List<Integer> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Integer value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().byteToBuff(value, ""));
		return buff;
	}

	public List<Integer> ubyteListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Integer> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().ubyteFromBuff("", item));
		return target;
	}

	public BuffList ubyteListToBuff(List<Integer> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Integer value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().ubyteToBuff(value, ""));
		return buff;
	}

	public List<Integer> shortListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Integer> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().shortFromBuff("", item));
		return target;
	}

	public BuffList shortListToBuff(List<Integer> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Integer value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().shortToBuff(value, ""));
		return buff;
	}

	public List<Integer> ushortListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Integer> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().ushortFromBuff("", item));
		return target;
	}

	public BuffList ushortListToBuff(List<Integer> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Integer value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().ushortToBuff(value, ""));
		return buff;
	}

	public List<Integer> intListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Integer> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().intFromBuff("", item));
		return target;
	}

	public BuffList intListToBuff(List<Integer> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Integer value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().intToBuff(value, ""));
		return buff;
	}

	public List<Long> uintListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Long> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().uintFromBuff("", item));
		return target;
	}

	public BuffList uintListToBuff(List<Long> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Long value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().uintToBuff(value, ""));
		return buff;
	}

	public List<Long> int64ListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Long> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().int64FromBuff("", item));
		return target;
	}

	public BuffList int64ListToBuff(List<Long> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Long value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().int64ToBuff(value, ""));
		return buff;
	}

	public List<Double> floatListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Double> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().floatFromBuff("", item));
		return target;
	}

	public BuffList floatListToBuff(List<Double> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Double value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().floatToBuff(value, ""));
		return buff;
	}

	public List<Double> doubleListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Double> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().doubleFromBuff("", item));
		return target;
	}

	public BuffList doubleListToBuff(List<Double> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Double value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().doubleToBuff(value, ""));
		return buff;
	}

	public List<String> stringListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<String> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().stringFromBuff("", item));
		return target;
	}

	public BuffList stringListToBuff(List<String> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (String value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().stringToBuff(value, ""));
		return buff;
	}

	public List<Boolean> boolListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<Boolean> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().boolFromBuff("", item));
		return target;
	}

	public BuffList boolListToBuff(List<Boolean> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (Boolean value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().boolToBuff(value, ""));
		return buff;
	}

	public List<ByteArray> bytesListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<ByteArray> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().bytesFromBuff("", item));
		return target;
	}

	public BuffList bytesListToBuff(List<ByteArray> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (ByteArray value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().bytesToBuff(value, ""));
		return buff;
	}

	public List<String> longstringListFromBuff(String key, IBuffInfo buff) {
		buff.setKey(key);
		List<String> target = new ArrayList<>();
		List<IBuffInfo> items = ((BuffList) buff).getItems();
		for (IBuffInfo item : items)
			target.add(BuffConverterBase.getInstance().longstringFromBuff(key, item));
		return target;
	}

	public BuffList longstringListToBuff(List<String> target, String key) {
		if (target == null)
			target = new ArrayList<>();
		BuffList buff = new BuffList();
		buff.setKey(key);
		for (String value : target)
			buff.push((IBuffInfo) BuffConverterBase.getInstance().longstringToBuff(value, ""));
		return buff;
	}
}
