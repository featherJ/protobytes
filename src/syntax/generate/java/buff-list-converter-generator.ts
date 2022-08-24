import { BeanNode } from "../../tree/bean";
import { IBeansCodeGenerator } from "../commons";
import { JavaCodeGenerator } from "./commons";
export class JavaBuffListConvertorGenerator extends JavaCodeGenerator implements IBeansCodeGenerator {
    public decode(beans: BeanNode[]): void {
        this._name = "BuffListConverter";

        this._code = "package " + (this._packageDes ? this._packageDes + "." : "");
        this._code += "utils;\n";
        this._code += "\n";
        this._code += "import java.util.ArrayList;\n";
        this._code += "import java.util.List;\n";
        this._code += "\n";
        this._code += "import " + (this._packageDes ? this._packageDes + "." : "") + "infos.*;\n"
        this._code += "import " + this._packageLib + ".buffers.core.BuffList;\n";
        this._code += "import " + this._packageLib + ".buffers.core.commons.IBuffInfo;\n";
        this._code += "import " + this._packageLib + ".buffers.utils.BuffListConverterBase;\n";
        this._code += "\n";
        this._code += this.getDocHelper().getClassDoc("BuffList转换器", 0, this._author);

        this._code += "public class " + this._name + " extends BuffListConverterBase {\n";
        this._code += this.getDocHelper().getIndent(1) + "private static " + this._name + " _instance;\n";
        this._code += "\n";
        this._code += this.getDocHelper().getIndent(1) + "public static " + this._name + " getInstance() {\n";
        this._code += this.getDocHelper().getIndent(2) + "if (_instance == null)\n";
        this._code += this.getDocHelper().getIndent(3) + "_instance = new " + this._name + "();\n";
        this._code += this.getDocHelper().getIndent(2) + "return _instance;\n";
        this._code += this.getDocHelper().getIndent(1) + "}\n";
        this._code += "\n";

        for (var i = 0; i < beans.length; i++) {
            this._code += this.getDocHelper().getFuncDoc(
                "从Buff中获取" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info的列表",
                1,
                [
                    { name: "key", notes: "该" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info列表的Key值" },
                    { name: "buff", notes: "获取自的Buff" }
                ],
                "获取的" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "列表"
            );


            this._code += this.getDocHelper().getIndent(1) + "public " + "List<" +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "> " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "ListFromBuff(String key, IBuffInfo buff) {\n";

            this._code += this.getDocHelper().getIndent(2) + "buff.setKey(key);\n";
            this._code += this.getDocHelper().getIndent(2) + "List<IBuffInfo> items = ((BuffList) buff).getItems();\n";
            this._code += this.getDocHelper().getIndent(2) + "List<" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "> target" + " = new ArrayList<" +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + ">();\n"

            this._code += this.getDocHelper().getIndent(2) + "for (int i = 0; i < items.size(); i++)\n";
            this._code += this.getDocHelper().getIndent(3) + "target.add(BuffConverter.getInstance()." +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) + "FromBuff(\"\", items.get(i)));\n";
            this._code += this.getDocHelper().getIndent(2) + "return target;\n";
            this._code += this.getDocHelper().getIndent(1) + "}\n"
            this._code += "\n";



            this._code += this.getDocHelper().getFuncDoc(
                "将一个" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info的列表转换成Buff",
                1,
                [
                    { name: "target", notes: "需要转换的" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info列表" },
                    { name: "key", notes: "该" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "Info列表的Key值" }
                ],
                "转换得到的BuffList"
            );

            this._code += this.getDocHelper().getIndent(1) + "public BuffList " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "ListToBuff(List<" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) +
                "> target,String key) {\n";
            this._code += this.getDocHelper().getIndent(2) + "if (target == null)\n";
            this._code += this.getDocHelper().getIndent(3) + "target = new ArrayList<" +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + ">();\n"
            this._code += this.getDocHelper().getIndent(2) + "BuffList buff = new BuffList();\n";
            this._code += this.getDocHelper().getIndent(2) + "buff.setKey(key);\n";
            this._code += this.getDocHelper().getIndent(2) + "for(int i = 0; i<target.size(); i++)\n";
            this._code += this.getDocHelper().getIndent(3) + "buff.push(BuffConverter.getInstance()." +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) + "ToBuff(target.get(i), \"\"));\n";
            this._code += this.getDocHelper().getIndent(2) + "return buff;\n";
            this._code += this.getDocHelper().getIndent(1) + "}\n"
            this._code += "\n";
        }
        this._code += "}\n"
    }
}