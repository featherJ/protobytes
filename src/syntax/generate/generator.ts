import { LanguageType } from "../../commons/language";
import { IBeanCodeGenerator, IBeansCodeGenerator, ICodeGenerator } from "./commons";
import { DartBuffConvertorGenerator } from "./dart/buff-convertor-generator";
import { DartBuffListConvertorGenerator } from "./dart/buff-list-converter-generator";
import { DartVOGenerator } from "./dart/vo-generator";
import { JavaBuffConvertorGenerator } from "./java/buff-convertor-generator";
import { JavaBuffListConvertorGenerator } from "./java/buff-list-converter-generator";
import { JavaVOGenerator } from "./java/vo-generator";

export interface ICodeGenerators {
    init(author: string, packageDes: string, packageLib: string): void;
    get buffConvertor(): IBeansCodeGenerator;
    get buffListConvertor(): IBeansCodeGenerator;
    get voGenerator(): IBeanCodeGenerator;
}
class JavaCodeGenerators implements ICodeGenerators {
    public buffConvertor: IBeansCodeGenerator = new JavaBuffConvertorGenerator();
    public buffListConvertor: IBeansCodeGenerator = new JavaBuffListConvertorGenerator();
    public voGenerator: IBeanCodeGenerator = new JavaVOGenerator();
    public init(author: string, packageDes: string, packageLib: string): void {
        this.buffConvertor.init(author, packageDes, packageLib);
        this.buffListConvertor.init(author, packageDes, packageLib);
        this.voGenerator.init(author, packageDes, packageLib);
    }
}
class DartCodeGenerators implements ICodeGenerators {
    public buffConvertor: IBeansCodeGenerator = new DartBuffConvertorGenerator();
    public buffListConvertor: IBeansCodeGenerator = new DartBuffListConvertorGenerator();
    public voGenerator: IBeanCodeGenerator = new DartVOGenerator();
    public init(author: string, packageDes: string, packageLib: string): void {
        this.buffConvertor.init(author, packageDes, packageLib);
        this.buffListConvertor.init(author, packageDes, packageLib);
        this.voGenerator.init(author, packageDes, packageLib);
    }
}


var generatorMap: { [type: string]: ICodeGenerators } = {};
generatorMap[LanguageType.JAVA] = new JavaCodeGenerators();
generatorMap[LanguageType.DART] = new DartCodeGenerators();


/**
 * 得到代码生成器
 * @param type 
 * @returns 
 */
export function getGenerators(type: LanguageType | string): ICodeGenerators {
    return generatorMap[type];
}