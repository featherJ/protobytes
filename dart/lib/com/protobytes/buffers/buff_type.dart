/// Base buff type
class BuffType {
  /// A 8-bit signed integer between -128 and 127.
  static const int byteType = 0;

  /// A 8-bit unsigned integer between 0 and 255.
  static const int unsignedByteType = 1;

  /// A 16-bit signed integer between -32768 and 32767.
  static const int shortType = 2;

  /// A 16-bit unsigned integer between 0 and 65535.
  static const int unsignedShortType = 3;

  /// A 32-bit signed integer between -2147483648 and 2147483647.
  static const int intType = 4;

  /// A 32-bit unsigned integer between 0 and 4294967295.
  static const int unsignedIntType = 5;

  /// A single-precision (32-bit) floating-point number.
  static const int floatType = 6;

  /// A double-precision (64-bit) floating-point number.
  static const int doubleType = 7;

  /// A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
  static const int stringType = 8;

  /// An 8-bit signed integer
  static const int booleanType = 9;

  /// A List
  static const int listType = 10;

  /// A Custom object
  static const int objectType = 11;

  /// A bytes from the byte stream. The bytes is assumed to be prefixed with an int indicating the length in bytes.
  static const int bytesType = 12;

  /// A UTF-8 string from the byte stream. The string is assumed to be prefixed with an int indicating the length in bytes.
  static const int longstringType = 13;

  /// A 64-bit signed integer between -9223372036854775808 and 9223372036854775807.
  static const int int64Type = 14;

  static String getTypeName(int type) {
    String typeName = "-";
    switch (type) {
      case byteType:
        return "byte";
      case unsignedByteType:
        return "ubyte";
      case shortType:
        return "short";
      case unsignedShortType:
        return "ushort";
      case intType:
        return "int";
      case unsignedIntType:
        return "uint";
      case floatType:
        return "float";
      case doubleType:
        return "double";
      case stringType:
        return "string";
      case booleanType:
        return "bool";
      case listType:
        return "list";
      case objectType:
        return "object";
      case bytesType:
        return "bytes";
      case longstringType:
        return "longstring";
      case int64Type:
        return "int64";
    }
    return typeName;
  }
}
