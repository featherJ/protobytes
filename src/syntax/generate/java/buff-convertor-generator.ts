import { AttributeNode } from "../../tree/attribute";
import { BeanNode } from "../../tree/bean";
import { IBeansCodeGenerator } from "../commons";
import { JavaCodeGenerator } from "./commons";

export class JavaBuffConvertorGenerator extends JavaCodeGenerator implements IBeansCodeGenerator {
    public decode(beans: BeanNode[]): void {
        this._name = "BuffConverter";

        this._code = "package " + (this._packageDes ? this._packageDes + "." : "");
        this._code += "utils;\n";
        this._code += "\n";
        this._code += "import java.util.List;\n";
        this._code += "\n";
        this._code += "import " + (this._packageDes ? this._packageDes + "." : "") + "infos.*;\n"
        this._code += "import " + this._packageLib + ".buffers.core.BuffObject;\n"
        this._code += "import " + this._packageLib + ".buffers.core.commons.IBuffInfo;\n"

        this._code += "import " + this._packageLib + ".buffers.utils.BuffConverterBase;\n"
        this._code += "\n";

        this._code += this.getDocHelper().getClassDoc("Buff转换器", 0, this._author);
        this._code += "public class " + this._name + " extends BuffConverterBase {\n";
        this._code += this.getDocHelper().getIndent() + "private static " + this._name + " _instance;\n"
        this._code += "\n";
        this._code += this.getDocHelper().getIndent(1) + "public static " + this._name + " getInstance() {\n";
        this._code += this.getDocHelper().getIndent(2) + "if (_instance == null)\n";
        this._code += this.getDocHelper().getIndent(3) + "_instance = new " + this._name + "();\n";
        this._code += this.getDocHelper().getIndent(2) + "return _instance;\n";
        this._code += this.getDocHelper().getIndent(1) + "}\n";
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


            this._code += this.getDocHelper().getIndent(1) + "public " + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + " " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "FromBuff(String key, IBuffInfo buff) {\n";
            this._code += this.getDocHelper().getIndent(2) + "buff.setKey(key);\n";
            this._code += this.getDocHelper().getIndent(2) + "List<IBuffInfo> atts = ((BuffObject) buff).getAttributes();\n";
            this._code += this.getDocHelper().getIndent(2) + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + " target = new " +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "();\n"
            for (var j = 0; j < attList.length; j++) {
                this._code += this.getDocHelper().getIndent(2) + "if (" + j + " < atts.size())\n";
                if (this.getTypeConvertor().hasType(attList[j].type)) {
                    type = attList[j].type;
                    isDefault = true;
                } else {
                    type = attList[j].type.charAt(0).toLocaleLowerCase() + attList[j].type.slice(1);
                    isDefault = false;
                }
                if (attList[j].isList) {
                    this._code += this.getDocHelper().getIndent(3) + "target." + attList[j].name + " = BuffListConverter.getInstance()." +
                        type + "ListFromBuff(\"" + attList[j].name +
                        "\", atts.get(" + j + "));\n";

                } else {
                    this._code += this.getDocHelper().getIndent(3) + "target." + attList[j].name + " = BuffConverter.getInstance()." +
                        type + "FromBuff(\"" + attList[j].name +
                        "\", atts.get(" + j + "));\n";
                }
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
            this._code += this.getDocHelper().getIndent(1) + "public BuffObject " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "ToBuff(" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1)
                + " target" + ", String key) {\n";
            this._code += this.getDocHelper().getIndent(2) + "if (target == null)\n"
            this._code += this.getDocHelper().getIndent(3) + "target = new " +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "();\n"
            this._code += this.getDocHelper().getIndent(2) + "BuffObject buff = new BuffObject();\n";
            this._code += this.getDocHelper().getIndent(2) + "buff.setKey(key);\n";
            for (j = 0; j < attList.length; j++) {
                if (this.getTypeConvertor().hasType(attList[j].type)) {
                    type = attList[j].type;
                } else {
                    type = attList[j].type.charAt(0).toLocaleLowerCase() + attList[j].type.slice(1);
                }

                if (attList[j].isList) {
                    this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffListConverter.getInstance()." +
                        type + "ListToBuff(target." + attList[j].name + ", \"" + attList[j].name + "\"));\n"
                } else {
                    this._code += this.getDocHelper().getIndent(2) + "buff.addAttribute(BuffConverter.getInstance()." +
                        type + "ToBuff(target." + attList[j].name + ", \"" + attList[j].name + "\"));\n"
                }
            }
            this._code += this.getDocHelper().getIndent(2) + "return buff;\n"
            this._code += this.getDocHelper().getIndent(1) + "}\n"
            this._code += "\n";
        }

        this._code += "}\n"
    }
}