import example.infos.AttInfo;
import example.infos.ExampleInfo;

public class Main {
	public static void main(String[] args) {
		ExampleInfo info = new ExampleInfo();
		info.byteProp = 1;
		info.shortProp = 2;
		info.doubleProp = Math.PI;
		info.longstringProp = "hello word";
		info.int64Prop = 1678692045823l;
		info.intListProp.add(1);
		info.intListProp.add(2);
		info.intListProp.add(3);
		info.intListProp.add(4);
		info.intListProp.add(5);

		AttInfo attInfo = new AttInfo();
		attInfo.attId = 1;
		info.attInfo1ListProp.add(attInfo);

		attInfo = new AttInfo();
		attInfo.attId = 2;
		info.attInfo1ListProp.add(attInfo);

		byte[] bytes = ExampleInfo.toBytes(info);
		System.out.println(bytes);
		ExampleInfo info2 = ExampleInfo.fromBytes(bytes);
		System.out.println(info2);
	}

	public static String log(byte[] bytes) {
		String string = "";
		string += "[";
		for (int i = 0; i < bytes.length; i++) {
			string += bytes[i];
			if (i != bytes.length - 1) {
				string += ",";
			}
		}
		string += "]";
		return string;
	}

}
