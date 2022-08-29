import example.infos.AttInfo;
import example.infos.ExampleInfo;

public class Main {
	public static void main(String[] args) {
		ExampleInfo info = new ExampleInfo();
		info.byteProp = 1;
		info.shortProp = 2;
		info.doubleProp = Math.PI;
		info.longstringProp = "hello word";
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
}
