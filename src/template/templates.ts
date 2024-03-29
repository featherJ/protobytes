import { CompileConfig } from "../scripts/compile"

export var protoTemplate = `/// Example information description
vo ExampleInfo
{	
	byte byteProp;				/// Property of byte format. A 8-bit signed integer between -128 and 127.
	short shortProp;			/// Property of short format. A 16-bit signed integer between -32768 and 32767.
	int intProp;				/// Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
	int64 int64Prop;			/// Property of int format. A 64-bit signed integer between -9223372036854775808 and 9223372036854775807.
	ubyte ubyteProp;			/// Property of byte format. A 8-bit unsigned integer between 0 and 255.
	ushort ushortProp;			/// Property of short format. A 16-bit unsigned integer between 0 and 65535.
	uint uintProp;				/// Property of int format. A 32-bit unsigned integer between 0 and 4294967295.
	float floatProp;			/// Property of float format. A single-precision (32-bit) floating-point number.
	double doubleProp;			/// Property of double format. A double-precision (64-bit) floating-point number.
	bool boolProp;				/// Property of bool format. An 8-bit signed integer
	string stringProp;			/// Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	longstring longstringProp;	/// Property of longstring format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an int indicating the length in bytes.
	bytes bytesProp;			/// Property of bytes format. An 8-bit signed integer
	
	AttInfo att1Prop;			/// Property of AttInfo.
	AttInfo att2Prop;			/// Property of AttInfo.

	List<byte> byteListProp;				/// Byte list property.
	List<short> shortListProp;				/// Short list property.
	List<int> intListProp;					/// Int list property.
	List<ubyte> ubyteListProp;				/// Ubyte list property.
	List<ushort> ushortListProp;			/// Ushort list property.
	List<uint> uintListProp;				/// Uint list property.
	List<int64> int64ListProp;				/// Int64 list property.
	List<float> floatListProp;				/// Float list property.
	List<double> doubleListProp;			/// Double list property.
	List<bool> boolListProp;				/// Bool list property.
	List<string> stringListProp;			/// String list property.
	List<longstring> longstringListProp;	/// Longstring list property.
	List<bytes> bytesListProp;				/// Bytes list property.

	List<AttInfo> attInfo1ListProp;			/// AttInfo list property.
	List<AttInfo> attInfo2ListProp;			/// AttInfo list property.
}

/// An Attribute Info example
vo AttInfo{
	short attId;			/// Property of short format. A 16-bit signed integer between -32768 and 32767.
	string attName;			/// Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	string attDesc;			/// Property of string format. A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
	int attValue;			/// Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
}`

var configTemplateData:CompileConfig = {
	sourceDir:"./",
	outputs:[
		{
			type:'java',
			dir:'./my-generate/java/com/myprotos',
			package:'com.myprotos',
			author:'MyName',
			clear:false
		},
		{
			type:'dart',
			dir:'./my-generate/dart/com/myprotos',
			package:'com/myprotos',
			author:'MyName',
			clear:false
		}
	]
}
export var configTemplate = JSON.stringify(configTemplateData,null,'\t');