/// Base buff type
class BuffType {
  /// An byte between -128 and 127.
  static const int byteType = 0;

  /// A 16-bit signed integer between -32768 and 32767.
  static const int shortType = 1;

  /// A 32-bit signed integer between -2147483648 and 2147483647.
  static const int intType = 2;

  /// A single-precision (32-bit) floating-point number.
  static const int floatType = 3;

  /// A double-precision (64-bit) floating-point number.
  static const int doubleType = 4;

  /// A UTF-8 string from the byte stream. The string is assumed to be prefixed with an short indicating the length in bytes.
  static const int stringType = 5;

  /// An 8-bit signed integer
  static const int booleanType = 6;

  /// A List
  static const int listType = 7;

  /// A Custom object
  static const int objectType = 8;

  /// A bytes from the byte stream. The bytes is assumed to be prefixed with an int indicating the length in bytes.
  static const int bytesType = 9;

  /// A UTF-8 string from the byte stream. The string is assumed to be prefixed with an int indicating the length in bytes.
  static const int longstringType = 10;

  static String getTypeName(int type) {
    String typeName = "-";
    switch (type) {
      case byteType:
        return "byte";
      case shortType:
        return "short";
      case intType:
        return "int";
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
    }
    return typeName;
  }
}
