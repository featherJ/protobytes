import { getConstLowerStr } from "../../../utils/contansts";
import { BeanNode } from "../../tree/bean";
import { IBeansCodeGenerator } from "../commons";
import { DartCodeGenerator } from "./commons";

export class DartBuffListConvertorGenerator extends DartCodeGenerator implements IBeansCodeGenerator {
    public decode(beans: BeanNode[]): void {
        var className = "BuffListConverter";
        this._name = getConstLowerStr(className);

        var imports = [
            this._packageLib + "buffers/core/buffs.dart",
            this._packageLib + "buffers/core/i_buff_info.dart",
            this._packageLib + "buffers/utils/buff_list_converter_base.dart",
            this._packageDes + "/utils/buff_converter.dart"
        ];

        for (var i = 0; i < beans.length; i++) {
            var name = getConstLowerStr(beans[i].name);
            imports.push(this._packageDes + "infos/" + name + ".dart");
        }

        this._code += this.getDocHelper().getImport(imports, 0);
        this._code += "\n";

        this._code += this.getDocHelper().getClassDoc("Buff转换器", 0, this._author);

        this._code += "class " + className + " extends BuffListConverterBase {\n";

        this._code += this.getDocHelper().getIndent(1) + "static "+className+"? _instance;\n"
        this._code += this.getDocHelper().getIndent(1) + "static "+className+" get instance {\n"
        this._code += this.getDocHelper().getIndent(2) + "_instance ??= "+className+"();\n"
        this._code += this.getDocHelper().getIndent(2) + "return _instance!;\n"
        this._code += this.getDocHelper().getIndent(1) + "}\n"
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


            this._code += this.getDocHelper().getIndent(1)  + "List<" +
                beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "> " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "ListFromBuff(String key, IBuffInfo buff) {\n";

            this._code += this.getDocHelper().getIndent(2) + "buff.key = key;\n";
            this._code += this.getDocHelper().getIndent(2) + "List<IBuffInfo> items = (buff as BuffList).items;\n";
            this._code += this.getDocHelper().getIndent(2) + "List<" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) + "> target" + " = [];\n";

            this._code += this.getDocHelper().getIndent(2) + "for (int i = 0; i < items.length; i++){\n";
            this._code += this.getDocHelper().getIndent(3) + "target.add(BuffConverter.instance." +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) + "FromBuff(\"\", items[i]));\n";
             this._code += this.getDocHelper().getIndent(2) + "}\n"
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

            this._code += this.getDocHelper().getIndent(1) + "BuffList " +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) +
                "ListToBuff(List<" + beans[i].name.charAt(0).toLocaleUpperCase() + beans[i].name.slice(1) +
                "> target,String key) {\n";
            this._code += this.getDocHelper().getIndent(2) + "BuffList buff = BuffList();\n";
            this._code += this.getDocHelper().getIndent(2) + "buff.key = key;\n";
            this._code += this.getDocHelper().getIndent(2) + "for(int i = 0; i<target.length; i++){\n";
            this._code += this.getDocHelper().getIndent(3) + "buff.push(BuffConverter.instance." +
                beans[i].name.charAt(0).toLocaleLowerCase() + beans[i].name.slice(1) + "ToBuff(target[i], \"\"));\n";
            this._code += this.getDocHelper().getIndent(2) + "}\n"
            this._code += this.getDocHelper().getIndent(2) + "return buff;\n";
            this._code += this.getDocHelper().getIndent(1) + "}\n"
            this._code += "\n";
        }

        this._code += "}\n"
    }
}