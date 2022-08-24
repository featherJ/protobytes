import { AttributeNode } from "./attribute";

/**
 * bean 
 * @author Agua.L
 * 
 */
export class BeanNode {
    /**
     * bean名 
     */
    public name: string = "";
    /**
     * bean注释
     */
    public note: string = "";
    /**
     * 属性列表 
     */
    public attList: AttributeNode[];

    public toString(): String {
        var str: String = "Bean:" + this.name + ", note:" + this.note + "\n";
        for (var i = 0; i < this.attList.length; i++) {
            str += "	" + this.attList[i] + "\n";
        }
        return str;
    }
}