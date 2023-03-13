import 'dart:math';

import 'package:example/com/myprotos/infos/att_info.dart';
import 'package:example/com/myprotos/infos/example_info.dart';
import 'package:proto_bytes/com/protobytes/utils/byte_array.dart';

void main() {
  ExampleInfo info = ExampleInfo();
  info.byteProp = 1;
  info.shortProp = 2;
  info.doubleProp = pi;
  info.longstringProp = 'hello word';
  info.int64Prop = 1678692045823;
  info.intListProp.addAll([1, 2, 3, 4, 5]);

  AttInfo attInfo = AttInfo();
  attInfo.attId = 1;
  info.attInfo1ListProp.add(attInfo);

  attInfo = AttInfo();
  attInfo.attId = 2;
  info.attInfo1ListProp.add(attInfo);

  List<int> bytes = ExampleInfo.toBytes(info);
  print(bytes);

  ExampleInfo info2 = ExampleInfo.fromBytes(bytes);
  print(info2);
}
