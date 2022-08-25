/// 基本的单位，数值或者字符串等
abstract class IBuffInfo {
  /// 得到该节点的实例或引用
  dynamic get value;

  /// 设置该节点的实例或引用
  set value(dynamic value);

  /// 得到这个buff的类型
  int get type;

  /// 得到该节点的key
  String get key;

  /// 设置该节点的key
  set key(String value);

  @override
  String toString();
}

/// 数组，2个字节无符号短整型表示长度+长度这么多个项
abstract class IBuffList extends IBuffInfo {
  /// 添加一项到结尾
  void push(IBuffInfo info);

  /// 得到所有项
  List<IBuffInfo> get items;
}

/// 自定义对象
abstract class IBuffObject extends IBuffInfo {
  /// 添加属性
  void addAttribute(IBuffInfo attribute);

  /// 得到属性列表
  List<IBuffInfo> get attributes;
}
