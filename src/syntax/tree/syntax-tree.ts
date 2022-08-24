import { BeanNode } from "./bean";

/**
 * 语法树
 * @author Agua.L
 * 
 */
export class SyntaxNodeTree {
    public beans: BeanNode[] = [];

    public toString(): String {
        var str: string = "";
        for (var i = 0; i < this.beans.length; i++) {
            str += this.beans[i];
        }
        return str;
    }
}