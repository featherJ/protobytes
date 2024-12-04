# ProtoBytes
[![NPM Version](http://img.shields.io/npm/v/protobytes.svg?style=flat)](https://www.npmjs.org/package/protobytes)
[![Install Size](https://packagephobia.now.sh/badge?p=protobytes)](https://packagephobia.now.sh/result?p=protobytes)
[![Donation](https://img.shields.io/static/v1?label=Donation&message=❤️&style=social)](https://ko-fi.com/V7V7141EHB)

[中文文档](README_CN.md) | English

`ProtoBytes` is a tool for binary communication protocol serialization and deserialization, suitable for applications such as binary communication or local persistent storage.
`ProtoBytes` consists of a command-line compilation tool and runtime parsing libraries for supported programming languages.

## Introduction
The current command-line tool compiles custom data types defined in `proto` protocol files into concrete code implementations for specified programming languages. Utilizing the underlying dependency libraries, it enables serialization and deserialization of these custom data type objects.

### Features
- During updates or iterations of communication protocol formats, if one end adds new attributes to a specific type in the `proto` file without changing the types or order of existing attributes, the other end does not need to update the protocol file or recompile. This process will not affect existing program logic or cause errors during serialization and deserialization of entity objects. The only effect is that unmatched attributes will be ignored during data parsing.
- Attribute names in a `proto` protocol file can be modified freely without affecting the binary output during serialization and deserialization, as long as the types and order remain unchanged. The serialized binary relies only on the attribute order and type, not on the attribute names. This ensures that changes to attribute names on one end do not impact data parsing on the other end during multi-end communication.
- Lightweight and efficient, focusing solely on the serialization and deserialization of entity objects without including any additional functionality.

## Installation
### Installing the Command-Line Compilation Tool
The compilation tool is developed using Node.js and can be installed globally with the following command

Install via npm：
```
npm install protobytes -g
```

### Installing the Runtime Libraries
Currently, ProtoBytes supports the following programming languages. You can find the corresponding implementations and related documentation in the source files:

| Language | Source File |
|-|-|
| Java | [java](java)                                |
| Dart| [dart](dart)                                |

Support for additional languages will be available in future versions of ProtoBytes.

## Principles
The process from binary data to the final data object involves three layers:
### Binary Layer
This is the fundamental layer of raw binary data. The basic data types include:
* `bool`: 8-bit signed integer; non-zero is `true`, zero is `false`.
* `byte`: 8-bit signed integer.
* `short`: 16-bit signed integer.
* `int`: 32-bit signed integer.
* `int64`: 64-bit signed integer.
* `ubyte`: 8-bit unsigned integer.
* `ushort`: 16-bit unsigned integer.
* `uint`: 32-bit unsigned integer.
* `float`:  IEEE 754 single-precision (32-bit) floating point.
* `double`: IEEE 754 double-precision (64-bit) floating point.
* `string`: A UTF-8 character sequence prefixed by a 16-bit unsigned integer indicating its length.
* `longstring`: A UTF-8 character sequence prefixed by a 32-bit signed integer indicating its length.
* `bytes`: A sequence of binary data prefixed by a 32-bit signed integer indicating its length.

Additionally, `list` and `object` types are provided to represent arrays and custom objects, respectively. Both types are composed of the aforementioned basic types internally.

### Data Exchange Layer
Each of the basic binary data types has a corresponding data exchange object type. This layer is responsible for converting between the basic binary types and their corresponding data exchange objects, facilitating the serialization and deserialization processes.

In this layer, the binary representation of each data exchange object consists of an 8-bit integer indicating the data type, followed by the actual data value. For example:
```
BuffByte <-----> type(8-bit) + value(bytes)
```

The data exchange layer supports the serialization and deserialization of complex data structures formed by combining and nesting the basic binary types. Its structure is similar to JSON, but it lacks any node names in the hierarchical tree structure—only node types are present.

For more details on the implementation of conversions between the data exchange and binary layers, refer to the `BuffBytesUtil` class.

### Entity Object Layer
In the data exchange layer, the data tree contains only the type and value of each node without any node names, and sibling nodes are stored in a list-like structure. Although this design provides flexibility in serialization and deserialization, it doesn't meet practical needs: you cannot directly access a node’s attribute value by its name.

The entity object layer addresses this by converting data exchange objects into entity objects, mapping child nodes from the data exchange structure to specific attributes in the entity object. This mapping facilitates easier access to attribute values during development.

The concrete code implementation for entity objects is generated by the command-line compilation tool from custom data types defined in `proto` protocol files.

## Proto Syntax
The syntax of `proto` is simple and can be referred to in the following example file:
```proto
/// Example information description
vo ExampleInfo
{	
	byte byteProp;				/// Property of byte format. A 8-bit signed integer between -128 and 127.
	short shortProp;			/// Property of short format. A 16-bit signed integer between -32768 and 32767.
	int intProp;				/// Property of int format. A 32-bit signed integer between -2147483648 and 2147483647.
	ubyte byteProp;				/// Property of byte format. A 8-bit unsigned integer between 0 and 255.
	ushort shortProp;			/// Property of short format. A 16-bit unsigned integer between 0 and 65535.
	uint intProp;				/// Property of int format. A 32-bit unsigned integer between 0 and 4294967295.
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
	List<ubyte> byteListProp;				/// Ubyte list property.
	List<ushort> shortListProp;				/// Ushort list property.
	List<uint> intListProp;					/// Uint list property.
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
}
```

## Command-Line Arguments
You can use the command-line tool to create `proto` protocol template files or compile configuration files. It allows you to compile a specified `proto` folder into the desired language. The specific commands are as follows:

* **Options**:
    * `protobytes -V` - View the current version of the command-line compilation tool.
    * `protobytes -h` - View command help.
* **Commands**:
    * `protobytes create [options] <string>` - Create a protocol or compilation configuration example file.
    * `protobytes compile [options]` - Compile the protocol file into the specified language.
    * `support` - View the languages currently supported.
    * `help [command] ` - View help for a specific command.