export enum DocType {
    JAVA_DOC = "javaDoc",
    DART_DOC = "dartDoc"
}

/**
 * 代码工具类
 * @author Agua.L
 */
abstract class DocHelper {
    /**
     * 得到类说明doc 
     * @param describe 描述内容
     * @param indent 空格数量
     * @param author 作者
     * @param internal 是否为内部
     * @returns 
     */
    public getClassDoc(describe: string, indent: number = 1, author: string = null, internal: boolean = false): string {
        return ""
    }

    /**
     * 得到方法的说明doc 
     * @param describe 描述内容
     * @param indent 空格数量
     * @param paramsDes 参数描述
     * @param returnDes 返回值描述
     * @returns 
     */
    public getFuncDoc(describe: string, indent: number = 1, paramsDes: { name: string, notes: string }[] = null, returnDes: string = null): string {
        return "";
    }

    /**
     * 得到属性的说明doc
     * @param describe 描述内容
     * @param indent 空格数量
     */
    public getAttrDoc(describe: string, indent: number = 1): string {
        return "";
    }

    /**
     * 得到import
     * @param packages 包列表
     */
    public getImport(packages: string[], indent: number = 1): string {
        return "";
    }

    /**
     * 得到缩进
     * @param indent 
     */
    public getIndent(indent: number = 1): string {
        var indentStr = "";
        for (var i = 0; i < indent; i++) {
            indentStr += "\t";
        }
        return indentStr;
    }
}

class JavaDocHelper extends DocHelper {
    /**
     * 得到类说明doc 
     * @param describe 描述内容
     * @param indent 空格数量
     * @param author 作者
     * @param internal 是否为内部
     * @returns 
     */
    public getClassDoc(describe: string, indent: number = 1, author: string = null, internal: boolean = false): string {
        var str = "";
        str += this.getIndent(indent);
        str += "/**\n"
        str += this.getIndent(indent);
        str += " * " + describe + "\n";
        if (author) {
            str += this.getIndent(indent);
            str += " * \n";
            str += this.getIndent(indent);
            str += " * @author " + author + "\n";
        }
        if (internal) {
            str += this.getIndent(indent);
            str += " * \n";
            str += this.getIndent(indent);
            str += " * @internal\n";
        }
        str += this.getIndent(indent);
        str += " */\n";
        return str;
    }

    /**
     * 得到方法的说明doc 
     * @param describe 描述内容
     * @param indent 空格数量
     * @param paramsDes 参数描述
     * @param returnDes 返回值描述
     * @returns 
     */
    public getFuncDoc(describe: string, indent: number = 1, paramsDes: { name: string, notes: string }[] = null, returnDes: string = null): string {
        var str = "";
        str += this.getIndent(indent);
        str += "/**\n"
        str += this.getIndent(indent);
        str += " * " + describe + "\n";
        if (paramsDes && paramsDes.length > 0) {
            str += this.getIndent(indent);
            str += " * \n";
            for (var i = 0; i < paramsDes.length; i++) {
                str += this.getIndent(indent);
                str += " * @param " + paramsDes[i].name + " " + paramsDes[i].notes + "\n";
            }
        }
        if (returnDes) {
            str += this.getIndent(indent);
            str += " * @return " + returnDes + "\n";
        }
        str += this.getIndent(indent);
        str += " */\n";
        return str
    }

    /**
     * 得到属性的说明doc
     * @param describe 描述内容
     * @param indent 空格数量
     */
    public getAttrDoc(describe: string, indent: number = 1): string {
        return this.getFuncDoc(describe, indent);
    }

    /**
     * 得到import
     * @param packages 包列表
     */
    public getImport(packages: string[], indent: number = 1): string {
        var str = "";
        for (var i = 0; i < packages.length; i++) {
            str += this.getIndent(indent);
            str += "import " + packages[i] + ";\n";
        }
        return str;
    }
}


class DartDocHelper extends DocHelper {
    /**
     * 得到类说明doc 
     * @param describe 描述内容
     * @param indent 空格数量
     * @param author 作者
     * @param internal 是否为内部
     * @returns 
     */
    public getClassDoc(describe: string, indent: number = 1, author: string = null, internal: boolean = false): string {
        var str = "";
        str += this.getIndent(indent);
        str += "/// "
        str += describe;
        str += '\n';
        return str;
    }

    /**
     * 得到方法的说明doc 
     * @param describe 描述内容
     * @param indent 空格数量
     * @param paramsDes 参数描述
     * @param returnDes 返回值描述
     * @returns 
     */
    public getFuncDoc(describe: string, indent: number = 1, paramsDes: { name: string, notes: string }[] = null, returnDes: string = null): string {
        var str = "";
        str += this.getIndent(indent);
        str += "/// "
        str += describe;
        str += '\n';
        return str
    }

    /**
     * 得到属性的说明doc
     * @param describe 描述内容
     * @param indent 空格数量
     */
    public getAttrDoc(describe: string, indent: number = 1): string {
        return this.getFuncDoc(describe, indent);
    }

    /**
     * 得到import
     * @param packages 包列表
     */
    public getImport(packages: string[], indent: number = 1): string {
        var str = "";
        for (var i = 0; i < packages.length; i++) {
            str += this.getIndent(indent);
            str += "import 'package:" + packages[i] + "';\n";
        }
        return str;
    }
}

var codeHelperMap: { [type: string]: DocHelper } = {};
codeHelperMap[DocType.JAVA_DOC] = new JavaDocHelper();
codeHelperMap[DocType.DART_DOC] = new DartDocHelper();

/**
 * 得到指定语言的类型转换器
 * @param type 
 * @returns 
 */
export function getDocHelper(type: DocType | string): DocHelper {
    return codeHelperMap[type];
}