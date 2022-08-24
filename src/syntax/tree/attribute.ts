/**
 * 属性
 * @author Agua.L
 * 
 */
export class AttributeNode {
    /**
     * 变量类型 
     */
    public type: string = "";
    /**
     * 变量名 
     */
    public name: string = "";
    /**
     * 变量注释 
     */
    public note: string = "";
    /**
     * 是否是列表
     */
    public isList: boolean = false;

    public toString(): String {
        return "[type:" + this.type + ", name:" + this.name + ", note:" + this.note + ", isList:" + this.isList + "]";
    }
}