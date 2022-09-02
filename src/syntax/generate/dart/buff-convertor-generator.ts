import { getConstLowerStr } from "../../../utils/contansts";
import { AttributeNode } from "../../tree/attribute";
import { BeanNode } from "../../tree/bean";
import { IBeansCodeGenerator } from "../commons";
import { DartCodeGenerator } from "./commons";

export class DartBuffConvertorGenerator extends DartCodeGenerator implements IBeansCodeGenerator {
    public decode(beans: BeanNode[]): void {
        var className = "BuffConverter";
        this._name = getConstLowerStr(className);

        var imports = [
            this._packageLib + "buffers/core/buffs.dart",
            this._packageLib + "buffers/core/i_buff_info.dart",
            this._packageLib + "buffers/utils/buff_converter_base.dart"
        ];
        if (this.containList(beans)) {
            imports.push(this._packageDes + "utils/buff_list_converter.dart");
        }

        for (var i = 0; i < beans.length; i++) {
            var name = getConstLowerStr(beans[i].name);
            imports.push(this._packageDes + "infos/" + name + ".dart");
        }

        this._code += this.getDocHelper().getImport(imports, 0);
        this._code += "\n";

        this._code += this.getDocHelper().getClassDoc("Buff转换器", 0, this._author);


        this._code += "class " + className + " extends BuffConverterBase {\n";

        this._code += this.getDocHelper().getIndent(1) + "static " + className + "? _instance;\n"
        this._code += this.getDocHelper().getIndent(1) + "static " + className + " get instance {\n"
        this._code += this.getDocHelper().getIndent(2) + "_instance ??= " + className + "();\n"
        this._code += this.getDocHelper().getIndent(2) + "return _instance!;\n"
        this._code += this.getDocHelper().getIndent(1) + "}\n"
        this._code += "\n";



        for (var i = 0; i < beans.length; i++) {
            var attList: AttributeNode[] = beans[i].attList;
            var type: String;
            var isDefault: Boolean = false;
            this._code += this.getDocHelper().getFuncDoc(
                "从Buff中获取" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1),
                1,
                [
                    { name: "key", notes: "该" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info的Key值" },
                    { name: "buff", notes: "获取自的Buff" }
                ],
                "获取的" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1)
            );


            this._code += this.getDocHelper().getIndent(1) + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + " " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "FromBuff(String key, IBuffInfo buff) {\n";
            this._code += this.getDocHelper().getIndent(2) + "buff.key = key;\n";
            this._code += this.getDocHelper().getIndent(2) + "List<IBuffInfo> atts = (buff as BuffObject).attributes;\n";
            this._code += this.getDocHelper().getIndent(2) + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + " target = " +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "();\n"
            for (var j = 0; j < attList.length; j++) {
                if (j == 0) {
                    this._code += this.getDocHelper().getIndent(2) + "if (atts.isNotEmpty) {\n";
                } else {
                    this._code += this.getDocHelper().getIndent(2) + "if (" + j + " < atts.length) {\n";
                }
                if (this.getTypeConvertor().hasType(attList[j].type)) {
                    type = attList[j].type;
                    isDefault = true;
                } else {
                    type = attList[j].type.charAt(0).toLocaleLowerCase() + attList[j].type.slice(1);
                    isDefault = false;
                }
                if (attList[j].isList) {
                    this._code += this.getDocHelper().getIndent(3) + "target." + attList[j].name + " = BuffListConverter.instance." +
                        type + "ListFromBuff(\"" + attList[j].name +
                        "\", atts[" + j + "]);\n";
                } else {
                    this._code += this.getDocHelper().getIndent(3) + "target." + attList[j].name + " = BuffConverter.instance." +
                        type + "FromBuff(\"" + attList[j].name +
                        "\", atts[" + j + "]);\n";
                }
                this._code += this.getDocHelper().getIndent(2) + "}\n";
            }
            this._code += this.getDocHelper().getIndent(2) + "return target;\n";
            this._code += this.getDocHelper().getIndent(1) + "}\n"
            this._code += "\n";

            this._code += this.getDocHelper().getFuncDoc(
                "将一个" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info转换成Buff",
                1,
                [
                    { name: "target", notes: "需要转换的" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) },
                    { name: "key", notes: "该" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info的Key值" }
                ],
                "转换得到的BuffObject"

            );
            this._code += this.getDocHelper().getIndent(1) + "BuffObject " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "ToBuff(" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1)
                + " target" + ", String key) {\n";
            this._code += this.getDocHelper().getIndent(2) + "BuffObject buff = BuffObject();\n";
            this._code += this.getDocHelper().getIndent(2) + "buff.key = key;\n";
            for (j = 0; j < attList.length; j++) {
                if (this.getTypeConvertor().hasType(attList[j].type)) {
                    type = attList[j].type;
                } else {
                    type = attList[j].type.charAt(0).toLocaleLowerCase() + attList[j].type.slice(1);
                }

                if (attList[j].isList) {
                    this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffListConverter.instance." +
                        type + "ListToBuff(target." + attList[j].name + ", \"" + attList[j].name + "\"));\n"
                } else {
                    this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffConverter.instance." +
                        type + "ToBuff(target." + attList[j].name + ", \"" + attList[j].name + "\"));\n"
                }
            }
            this._code += this.getDocHelper().getIndent(2) + "return buff;\n"
            this._code += this.getDocHelper().getIndent(1) + "}\n"
            this._code += "\n";
        }


        this._code += "}\n"
    }

    private containList(beans: BeanNode[]): boolean {
        var hasList = false;
        for (var i = 0; i < beans.length; i++) {
            for (var j = 0; j < beans[i].attList.length; j++) {
                if (beans[i].attList[j].isList) {
                    hasList = true;
                    break;
                }
            }
            if (hasList) {
                break;
            }
        }
        return hasList;
    }
}