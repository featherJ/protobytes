/// 输出工具类
class BuffLogUtil {
  static String formatLog(String log, int indent) {
    if (indent > 0) {
      List<String> arr = log.split("\n");
      if (arr[arr.length - 1].isEmpty) {
        arr[arr.length - 1] = "";
      }
      String newlog = "";
      for (int i = 0; i < arr.length; i++) {
        newlog += getIndent(indent) + arr[i];
        newlog += "\n";
      }
      return newlog;
    }
    return log;
  }

  static String indentStr = "\t";

  static String getIndent(int indent) {
    String space = "";
    for (int i = 0; i < indent; i++) {
      space += indentStr;
    }
    return space;
  }
}
