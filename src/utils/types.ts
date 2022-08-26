import { LanguageType } from "../commons/language";

export type KeyMap = { [key: string]: string };
/**
 * 类型工具 
 * @author Agua.L
 * 
 */
export class TypeConvertor {
    private typeMap: KeyMap;
    private typeInListMap: KeyMap;
    private defaultValueMap: KeyMap;

    constructor(typeMap: KeyMap, typeInListMap: KeyMap, defaultValueMap: KeyMap) {
        this.typeMap = typeMap;
        this.typeInListMap = typeInListMap;
        this.defaultValueMap = defaultValueMap;
    }

    /**
     * 将脚本的变量类型，转换为目标平台的变量类型 
     * @param type 变量类型
     * @param isInList 是否是在list中
     * @param platform 目标平台
     * @return 目标平台的变量类型
     * 
     */
    public convertTypeTo(type: string, isInList: boolean = false): string {
        if (isInList)
            return this.typeInListMap[type];
        return this.typeMap[type];
    }

    /**
     * 得到默认值
     * @param type 变量类型
     * @return 
     * 
     */
    public getDefaultValue(type: string): String {
        return this.defaultValueMap[type];
    }

    /**
     * 检查是否含有某个类型 
     * @param type 变量类型
     * @return 是否含有这个变量类型
     * 
     */
    public hasType(type: string): Boolean {
        if (type in this.typeMap)
            return true;
        return false;
    }
}


var convertorMap: { [type: string]: TypeConvertor } = {};
convertorMap[LanguageType.JAVA] = new TypeConvertor({
    "byte": "int",
    "short": "int",
    "int": "int",
    "ubyte": "int",
    "ushort": "int",
    "uint": "long",
    "float": "double",
    "double": "double",
    "bool": "boolean",
    "string": "String",
    "longstring": "String",
    "bytes": "ByteArray"
}, {
    "byte": "Integer",
    "short": "Integer",
    "int": "Integer",
    "ubyte": "Integer",
    "ushort": "Integer",
    "uint": "Long",
    "float": "Double",
    "double": "Double",
    "bool": "Boolean",
    "string": "String",
    "longstring": "String",
    "bytes": "ByteArray"
}, {
    "int": "0",
    "long": "0l",
    "double": "0",
    "boolean": "false",
    "String": "\"\"",
    "ByteArray": "new ByteArray()"
});
convertorMap[LanguageType.DART] = new TypeConvertor({
    "byte": "int",
    "short": "int",
    "int": "int",
    "ubyte": "int",
    "ushort": "int",
    "uint": "int",
    "float": "double",
    "double": "double",
    "bool": "bool",
    "string": "String",
    "longstring": "String",
    "bytes": "ByteArray"
}, {
    "byte": "int",
    "short": "int",
    "int": "int",
    "ubyte": "int",
    "ushort": "int",
    "uint": "int",
    "float": "double",
    "double": "double",
    "bool": "bool",
    "string": "String",
    "longstring": "String",
    "bytes": "ByteArray"
}, {
    "int": "0",
    "double": "0",
    "bool": "false",
    "String": "\"\"",
    "ByteArray": "ByteArray()"
});


/**
 * 得到指定语言的类型转换器
 * @param type 
 * @returns 
 */
export function getTypeConvertor(type: LanguageType | string): TypeConvertor {
    return convertorMap[type];
}