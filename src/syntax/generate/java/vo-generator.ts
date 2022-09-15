import { BeanNode } from "../../tree/bean";
import { IBeanCodeGenerator, IBeansCodeGenerator, ICodeGenerator } from "../commons";
import { JavaCodeGenerator } from "./commons";

export class JavaVOGenerator extends JavaCodeGenerator implements IBeanCodeGenerator {
    public decode(bean: BeanNode): void {
        this._name = bean.name.charAt(0).toLocaleUpperCase() + bean.name.slice(1);

        this._code = "package " + (this._packageDes ? this._packageDes + "." : "");
        this._code += "infos;\n";
        this._code += "\n";
        if (this.checkHasList(bean)) {
            this._code += "import java.util.ArrayList;\n";
        }
        this._code += "import java.util.List;\n";
        this._code += "import " + this._packageLib + ".buffers.core.*;\n";
        this._code += "import " + this._packageLib + ".buffers.core.commons.*;\n";

        this._code += "import " + (this._packageDes ? this._packageDes + "." : "") + "utils.BuffConverter;\n";
        if (this.checkHasList(bean)) {
            this._code += "import " + (this._packageDes ? this._packageDes + "." : "") + "utils.BuffListConverter;\n";
        }

        this._code += "import " + this._packageLib + ".buffers.utils.BuffBytesUtil;\n";
        this._code += "import " + this._packageLib + ".utils.ByteArray;\n";
        this._code += "\n";
        this._code += this.getDocHelper().getClassDoc(bean.note, 0, this._author);
        this._code += "public class " + this._name + " {\n";

        for (var i = 0; i < bean.attList.length; i++) {
            if (bean.attList[i].note)
                this._code += this.getDocHelper().getAttrDoc(bean.attList[i].note, 1);
            var type: string;
            if (bean.attList[i].isList) {
                type = this.getTypeConvertor().hasType(bean.attList[i].type) ?
                    "List<" + this.getTypeConvertor().convertTypeTo(bean.attList[i].type, true) + ">" :
                    "List<" + bean.attList[i].type.charAt(0).toLocaleUpperCase() + bean.attList[i].type.slice(1) + ">";
                this._code += this.getDocHelper().getIndent(1) + "public " + type + " " + bean.attList[i].name + " = new ArrayList<" + (
                    this.getTypeConvertor().hasType(bean.attList[i].type) ? this.getTypeConvertor().convertTypeTo(bean.attList[i].type, true) :
                        bean.attList[i].type.charAt(0).toLocaleUpperCase() + bean.attList[i].type.slice(1)
                ) + ">();\n";
            } else {
                type = this.getTypeConvertor().hasType(bean.attList[i].type) ?
                    this.getTypeConvertor().convertTypeTo(bean.attList[i].type) :
                    bean.attList[i].type.charAt(0).toLocaleUpperCase() + bean.attList[i].type.slice(1);
                this._code += this.getDocHelper().getIndent(1) + "public " + type + " " + bean.attList[i].name;
                var defaultValue: String = this.getTypeConvertor().hasType(bean.attList[i].type) ? this.getTypeConvertor().getDefaultValue(type) : "";
                if (defaultValue)
                    this._code += " = " + defaultValue + ";\n";
                else
                    this._code += " = new "+type+"();\n";
            }
        }

        this._code += "\n";
        this._code += this.getDocHelper().getIndent(1) + "public static final " + this._name + " fromBytes(byte[] bytes) {\n";
        this._code += this.getDocHelper().getIndent(2) +"ByteArray byteArray = new ByteArray(bytes);\n";
        this._code += this.getDocHelper().getIndent(2) +"byteArray.setPosition(0);\n";
        this._code += this.getDocHelper().getIndent(2) + this._name + " info = new " + this._name + "();\n";
        this._code += this.getDocHelper().getIndent(2) + "BuffObject buff = (BuffObject) BuffBytesUtil.fromBytes(byteArray);\n";
        this._code += this.getDocHelper().getIndent(2) + "byteArray.clear();\n";
        this._code += this.getDocHelper().getIndent(2) + "List<IBuffInfo> atts = buff.getAttributes();\n";
        for (var j = 0; j < bean.attList.length; j++) {
            this._code += this.getDocHelper().getIndent(2) + "if (" + j + " < atts.size())\n";
            if (this.getTypeConvertor().hasType(bean.attList[j].type)) {
                type = bean.attList[j].type;
            } else {
                type = bean.attList[j].type.charAt(0).toLocaleLowerCase() + bean.attList[j].type.slice(1);
            }

            if (bean.attList[j].isList) {
                this._code += this.getDocHelper().getIndent(3) + "info." + bean.attList[j].name + " = BuffListConverter.getInstance()." +
                    type + "ListFromBuff(\"" + bean.attList[j].name +
                    "\", atts.get(" + j + "));\n";

            } else {
                this._code += this.getDocHelper().getIndent(3) + "info." + bean.attList[j].name + " = BuffConverter.getInstance()." +
                    type + "FromBuff(\"" + bean.attList[j].name +
                    "\", atts.get(" + j + "));\n";
            }
        }
        this._code += this.getDocHelper().getIndent(2) + "return info;\n";
        this._code += this.getDocHelper().getIndent(1) + "}\n";

        this._code += "\n";
        this._code += this.getDocHelper().getIndent(1) + "public static final byte[] toBytes(" + this._name + " info) {\n";
        this._code += this.getDocHelper().getIndent(2) + "BuffObject buff = new BuffObject();\n";

        for (j = 0; j < bean.attList.length; j++) {
            if (this.getTypeConvertor().hasType(bean.attList[j].type)) {
                type = bean.attList[j].type;
            } else {
                type = bean.attList[j].type.charAt(0).toLocaleLowerCase() + bean.attList[j].type.slice(1);
            }
            if (bean.attList[j].isList) {
                this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffListConverter.getInstance()." +
                    type + "ListToBuff(info." + bean.attList[j].name + ", \"" + bean.attList[j].name + "\"));\n"
            } else {
                this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffConverter.getInstance()." +
                    type + "ToBuff(info." + bean.attList[j].name + ", \"" + bean.attList[j].name + "\"));\n"
            }
        }

        this._code += this.getDocHelper().getIndent(2) + "ByteArray byteArray = BuffBytesUtil.toBytes(buff);\n";
        this._code += this.getDocHelper().getIndent(2) + "byte[] bytes = byteArray.getBytes();\n";
        this._code += this.getDocHelper().getIndent(2) + "byteArray.clear();\n";
        this._code += this.getDocHelper().getIndent(2) + "return bytes;\n";
        this._code += this.getDocHelper().getIndent(1) + "}\n";

        this._code += "}\n";
    }

    private checkHasByteArray(bean: BeanNode): boolean {
        for (var i = 0; i < bean.attList.length; i++) {
            if (bean.attList[i].isList) {
                if (this.getTypeConvertor().hasType(bean.attList[i].type)) {
                    if (this.getTypeConvertor().convertTypeTo(bean.attList[i].type, true) == "ByteArray") {
                        return true;
                    }
                }
            } else {
                if (this.getTypeConvertor().hasType(bean.attList[i].type)) {
                    if (this.getTypeConvertor().convertTypeTo(bean.attList[i].type) == "ByteArray") {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private checkHasList(bean: BeanNode): boolean {
        for (var i = 0; i < bean.attList.length; i++) {
            if (bean.attList[i].isList) {
                return true;
            }
        }
        return false;
    }
}

// export class JavaVOUtilGenerator extends JavaCodeGenerator implements IBeansCodeGenerator {
//     public decode(beans: BeanNode[]): void {
//         this._name = "ProtoVOUtil";

//         this._code = "package " + (this._packageDes ? this._packageDes : "") + ";\n";
//         this._code += "\n";
//         this._code += "import " + this._packageLib + ".buffers.utils.BuffBytesUtil;\n";
//         this._code += "import " + this._packageLib + ".buffers.core.commons.IBuffInfo;\n";
//         this._code += "import " + this._packageLib + ".utils.ByteArray;\n";
//         this._code += "import " + (this._packageDes ? this._packageDes + "." : "") + "messages.infoVO.*;\n";
//         this._code += "import " + (this._packageDes ? this._packageDes + "." : "") + "messages.utils.BuffConverter;\n";
//         this._code += "\n";

//         this._code += this.getDocHelper().getClassDoc("协议实体类序列化与反序列化工具", 0, this._author);
//         this._code += "public class " + this._name + " {\n";
//         this._code += this.getDocHelper().getAttrDoc("序列化错误常量", 1);
//         this._code += this.getDocHelper().getIndent(1) + "" +
//             "private final static String ERROR_SERIALIZE = \"仅支持" +
//             (this._packageDes ? this._packageDes + "." : "") + "messages.infoVO.IProtoVO" + "接口的类\";\n";
//         this._code += this.getDocHelper().getAttrDoc("反序列化错误常量", 1);
//         this._code += this.getDocHelper().getIndent(1) + "private final static String ERROR_DESERIALIZE = \"仅支持通过" +
//             (this._packageDes ? this._packageDes + "." : "") + "ProtoVOUtil"
//             + "序列化得到的二进制\";\n"

//         this._code += this.getDocHelper().getFuncDoc(
//             "序列化实体类",
//             1,
//             [{ name: "target", notes: (this._packageDes ? this._packageDes + "." : "") + "messages.infoVO.IProtoVO接口的实体类" }],
//             "序列化后的二进制");
//         this._code += this.getDocHelper().getIndent(1) + "public static ByteArray serialize(IProtoVO target) throws Exception {\n";
//         this._code += this.getDocHelper().getIndent(2) + "ByteArray tempBuffBytes = new ByteArray();\n";
//         this._code += this.getDocHelper().getIndent(2) + "ByteArray tempTypeBytes = new ByteArray();\n";
//         this._code += this.getDocHelper().getIndent(2) + "ByteArray bytes = null;\n";
//         this._code += this.getDocHelper().getIndent(2) + "IBuffInfo buff = null;\n";

//         if (beans.length == 1) {
//             var beanClassName = beans[0].name.charAt(0).toLocaleUpperCase() + beans[0].name.slice(1) + "VO";
//             var beanName = beans[0].name.charAt(0).toLocaleLowerCase() + beans[0].name.slice(1) + "VO"
//             this._code += this.getDocHelper().getIndent(2) + "if (target instanceof " + beanClassName + ") {\n";
//             this._code += this.getDocHelper().getIndent(3) + "buff = BuffConverter.getInstance()." + beanName + "ToBuff((" + beanClassName + ") target, \"\");\n";
//             this._code += this.getDocHelper().getIndent(3) + "tempTypeBytes.writeString(\"" + beanClassName + "\");\n";
//             this._code += this.getDocHelper().getIndent(2) + "}\n";
//         } else if (beans.length > 1) {
//             for (var i = 0; i < beans.length; i++) {
//                 beanClassName = beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "VO";
//                 beanName = beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) + "VO"
//                 if (i == 0) {
//                     this._code += this.getDocHelper().getIndent(2) + "if (target instanceof " + beanClassName + ") {\n";
//                     this._code += this.getDocHelper().getIndent(3) + "buff = BuffConverter.getInstance()." + beanName + "ToBuff((" + beanClassName + ") target, \"\");\n";
//                     this._code += this.getDocHelper().getIndent(3) + "tempTypeBytes.writeString(\"" + beanClassName + "\");\n";
//                 } else if (i == beans.length - 1) {
//                     this._code += this.getDocHelper().getIndent(2) + "} else {\n";
//                     this._code += this.getDocHelper().getIndent(3) + "throw new Exception(ERROR_SERIALIZE);\n";
//                     this._code += this.getDocHelper().getIndent(2) + "}\n";
//                 } else {
//                     this._code += this.getDocHelper().getIndent(2) + "} else if (target instanceof " + beanClassName + ") {\n";
//                     this._code += this.getDocHelper().getIndent(3) + "buff = BuffConverter.getInstance()." + beanName + "ToBuff((" + beanClassName + ") target, \"\");\n";
//                     this._code += this.getDocHelper().getIndent(3) + "tempTypeBytes.writeString(\"" + beanClassName + "\");\n";
//                 }
//             }
//         }

//         this._code += this.getDocHelper().getIndent(2) + "tempBuffBytes = BuffBytesUtil.toBytes(buff);\n";
//         this._code += this.getDocHelper().getIndent(2) + "bytes = new ByteArray();\n";
//         this._code += this.getDocHelper().getIndent(2) + "bytes.writeInt(tempTypeBytes.getLength());\n";
//         this._code += this.getDocHelper().getIndent(2) + "bytes.writeBytes(tempTypeBytes.getbyteArray());\n";
//         this._code += this.getDocHelper().getIndent(2) + "bytes.writeBytes(tempBuffBytes.getbyteArray());\n";
//         this._code += this.getDocHelper().getIndent(2) + "return bytes;\n";
//         this._code += this.getDocHelper().getIndent(1) + "}\n";
//         this._code += "\n";

//         this._code += this.getDocHelper().getFuncDoc(
//             "反序列化实体类",
//             1,
//             [
//                 { name: "bytes", notes: "经过serialize方法得到的二进制" }
//             ],
//             "反序列化后的实体类");
//         this._code += this.getDocHelper().getIndent(1) + "public static IProtoVO deserialize(ByteArray bytes) throws Exception {\n";
//         this._code += this.getDocHelper().getIndent(2) + "bytes.setPosition(0);\n";
//         this._code += this.getDocHelper().getIndent(2) + "IProtoVO target = null;\n";
//         this._code += this.getDocHelper().getIndent(2) + "IBuffInfo buff = null;\n";
//         this._code += this.getDocHelper().getIndent(2) + "ByteArray tempBuffBytes = new ByteArray();\n";
//         this._code += this.getDocHelper().getIndent(2) + "String type;\n";
//         this._code += this.getDocHelper().getIndent(2) + "int len;\n";
//         this._code += this.getDocHelper().getIndent(2) + "try {\n";

//         this._code += this.getDocHelper().getIndent(3) + "len = bytes.readInt();\n";
//         this._code += this.getDocHelper().getIndent(3) + "type = bytes.readString(len);\n";
//         this._code += this.getDocHelper().getIndent(3) + "tempBuffBytes.writeBytes(bytes.readbytes(bytes.getBytesAvilableLength()));\n";
//         this._code += this.getDocHelper().getIndent(3) + "buff = BuffBytesUtil.fromBytes(tempBuffBytes);\n";

//         if (beans.length == 1) {
//             beanClassName = beans[0].name.charAt(0).toLocaleUpperCase() + beans[0].name.slice(1) + "VO";
//             beanName = beans[0].name.charAt(0).toLocaleLowerCase() + beans[0].name.slice(1) + "VO"
//             this._code += this.getDocHelper().getIndent(3) + "if (type.equals(\"" + beanClassName + "\")) {\n";
//             this._code += this.getDocHelper().getIndent(4) + "target = BuffConverter.getInstance()." + beanName + "FromBuff(\"\", buff);\n";
//             this._code += this.getDocHelper().getIndent(3) + "}\n";
//         } else if (beans.length > 1) {
//             for (i = 0; i < beans.length; i++) {
//                 beanClassName = beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "VO";
//                 beanName = beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) + "VO"
//                 if (i == 0) {
//                     this._code += this.getDocHelper().getIndent(3) + "if (type.equals(\"" + beanClassName + "\")) {\n";
//                     this._code += this.getDocHelper().getIndent(4) + "target = BuffConverter.getInstance()." + beanName + "FromBuff(\"\", buff);\n";
//                 } else if (i == beans.length - 1) {
//                     this._code += this.getDocHelper().getIndent(3) + "} else {\n";
//                     this._code += this.getDocHelper().getIndent(4) + "throw new Exception(ERROR_DESERIALIZE);\n";
//                     this._code += this.getDocHelper().getIndent(3) + "}\n";
//                 } else {
//                     this._code += this.getDocHelper().getIndent(3) + "} else if (type.equals(\"" + beanClassName + "\")) {\n";
//                     this._code += this.getDocHelper().getIndent(4) + "target = BuffConverter.getInstance()." + beanName + "FromBuff(\"\", buff);\n";
//                 }
//             }
//         }

//         this._code += this.getDocHelper().getIndent(2) + "} catch (Exception e) {\n";
//         this._code += this.getDocHelper().getIndent(3) + "throw new Exception(ERROR_DESERIALIZE);\n";
//         this._code += this.getDocHelper().getIndent(2) + "}\n";
//         this._code += this.getDocHelper().getIndent(2) + "return target;\n";
//         this._code += this.getDocHelper().getIndent(1) + "}\n";
//         this._code += "}\n";

//     }
// }