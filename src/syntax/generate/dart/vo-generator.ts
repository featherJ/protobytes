import { getConstLowerStr } from "../../../utils/contansts";
import { BeanNode } from "../../tree/bean";
import { IBeanCodeGenerator } from "../commons";
import { DartCodeGenerator } from "./commons";

export class DartVOGenerator extends DartCodeGenerator implements IBeanCodeGenerator {
    public decode(bean: BeanNode): void {
        this._name = getConstLowerStr(bean.name);

        var className = bean.name.charAt(0).toLocaleUpperCase() + bean.name.slice(1);
        this._code = "";

        var imports = [
            this._packageLib + "buffers/core/i_buff_info.dart",
            this._packageLib + "buffers/core/buffs.dart",
            this._packageLib + "buffers/utils/buff_bytes_util.dart",
            this._packageLib + "utils/byte_array.dart",
        ];
        if(this.containNormal(bean)){
            imports.push( this._packageDes + "utils/buff_converter.dart");
        }
        if(this.containList(bean)){
            imports.push( this._packageDes + "utils/buff_list_converter.dart");
        }
        var customTypesMap: { [type: string]: boolean } = {};
        for (var i = 0; i < bean.attList.length; i++) {
            if (!this.getTypeConvertor().hasType(bean.attList[i].type)) {
                if (!customTypesMap[bean.attList[i].type]) {
                    imports.push(this._packageDes + "infos/" + getConstLowerStr(bean.attList[i].type) + ".dart");
                    customTypesMap[bean.attList[i].type] = true;
                }
            }
        }


        this._code += this.getDocHelper().getImport(imports, 0);
        this._code += "\n";


        this._code += this.getDocHelper().getClassDoc(bean.note, 0, this._author);
        this._code += "class " + className + " {\n";

        for (var i = 0; i < bean.attList.length; i++) {
            if (bean.attList[i].note)
                this._code += this.getDocHelper().getAttrDoc(bean.attList[i].note, 1);
            var type: string;
            if (bean.attList[i].isList) {
                type = this.getTypeConvertor().hasType(bean.attList[i].type) ?
                    "List<" + this.getTypeConvertor().convertTypeTo(bean.attList[i].type, true) + ">" :
                    "List<" + bean.attList[i].type.charAt(0).toLocaleUpperCase() + bean.attList[i].type.slice(1) + ">";
                this._code += this.getDocHelper().getIndent(1) + type + " " + bean.attList[i].name + " = [];\n";
            } else {
                type = this.getTypeConvertor().hasType(bean.attList[i].type) ?
                    this.getTypeConvertor().convertTypeTo(bean.attList[i].type) :
                    bean.attList[i].type.charAt(0).toLocaleUpperCase() + bean.attList[i].type.slice(1);
                this._code += this.getDocHelper().getIndent(1) + type + " " + bean.attList[i].name;
                var defaultValue: String = this.getTypeConvertor().hasType(bean.attList[i].type) ? this.getTypeConvertor().getDefaultValue(type) : "";
                if (defaultValue)
                    this._code += " = " + defaultValue + ";\n";
                else
                    this._code += " = " + type + "();\n";
            }
            this._code += '\n';
        }

        this._code += "\n";
        this._code += this.getDocHelper().getIndent(1) + "static " + className + " fromBytes(List<int> bytes) {\n";
        this._code += this.getDocHelper().getIndent(2) + "ByteArray byteArray = ByteArray(bytes);\n";
        this._code += this.getDocHelper().getIndent(2) + "byteArray.position = 0;\n";
        this._code += this.getDocHelper().getIndent(2) + className + " info = " + className + "();\n";
        this._code += this.getDocHelper().getIndent(2) + "BuffObject buff = BuffBytesUtil.fromBytes(byteArray) as BuffObject;\n";
        this._code += this.getDocHelper().getIndent(2) + "List<IBuffInfo> atts = buff.attributes;\n";
        for (var j = 0; j < bean.attList.length; j++) {
            if(j == 0){
                this._code += this.getDocHelper().getIndent(2) + "if (atts.isNotEmpty) {\n";
            }else{
                this._code += this.getDocHelper().getIndent(2) + "if (" + j + " < atts.length) {\n";
            }
            if (this.getTypeConvertor().hasType(bean.attList[j].type)) {
                type = bean.attList[j].type;
            } else {
                type = bean.attList[j].type.charAt(0).toLocaleLowerCase() + bean.attList[j].type.slice(1);
            }

            if (bean.attList[j].isList) {
                this._code += this.getDocHelper().getIndent(3) + "info." + bean.attList[j].name + " = BuffListConverter.instance." +
                    type + "ListFromBuff(\"" + bean.attList[j].name +
                    "\", atts[" + j + "]);\n";
            } else {
                this._code += this.getDocHelper().getIndent(3) + "info." + bean.attList[j].name + " = BuffConverter.instance." +
                    type + "FromBuff(\"" + bean.attList[j].name +
                    "\", atts[" + j + "]);\n";
            }
        this._code += this.getDocHelper().getIndent(2) + "}\n";
        }
        this._code += this.getDocHelper().getIndent(2) + "return info;\n";
        this._code += this.getDocHelper().getIndent(1) + "}\n";

        this._code += "\n";
        this._code += this.getDocHelper().getIndent(1) + "static List<int> toBytes(" + className + " info) {\n";
        this._code += this.getDocHelper().getIndent(2) + "BuffObject buff = BuffObject();\n";

        for (j = 0; j < bean.attList.length; j++) {
            if (this.getTypeConvertor().hasType(bean.attList[j].type)) {
                type = bean.attList[j].type;
            } else {
                type = bean.attList[j].type.charAt(0).toLocaleLowerCase() + bean.attList[j].type.slice(1);
            }
            if (bean.attList[j].isList) {
                this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffListConverter.instance." +
                    type + "ListToBuff(info." + bean.attList[j].name + ", \"" + bean.attList[j].name + "\"));\n"
            } else {
                this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffConverter.instance." +
                    type + "ToBuff(info." + bean.attList[j].name + ", \"" + bean.attList[j].name + "\"));\n"
            }
        }

        this._code += this.getDocHelper().getIndent(2) + "ByteArray bytes = BuffBytesUtil.toBytes(buff);\n";
        this._code += this.getDocHelper().getIndent(2) + "return bytes.bytes;\n";
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

    private containList(bean: BeanNode): boolean {
        for (var i = 0; i < bean.attList.length; i++) {
            if (bean.attList[i].isList) {
                return true;
            }
        }
        return false;
    }

    private containNormal(bean: BeanNode): boolean {
        var hasNormal = false;
            for (var i = 0; i < bean.attList.length; i++) {
                if (!bean.attList[i].isList) {
                    hasNormal = true;
                    break;
                }
            }
        return hasNormal;
    }
}