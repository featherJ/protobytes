import { BeanNode } from "../tree/bean";

export interface ICodeGeneratorBase {
    init(author: string, packageDes: string, packageLib: string): void;
    get name(): string;
    get code(): string;
}

export interface ICodeGenerator extends ICodeGeneratorBase {
    decode(): void;
}
export interface IBeanCodeGenerator extends ICodeGeneratorBase {
    decode(bean: BeanNode): void;
}
export interface IBeansCodeGenerator extends ICodeGeneratorBase {
    decode(beans: BeanNode[]): void;
}


export abstract class CodeGenerator {
    protected _author: string;
    protected _packageDes: string;
    protected _packageLib: string
    protected _name: string;
    protected _code: string = "";

    public init(author: string, packageDes: string, packageLib: string): void {
        this._author = author;
        this._packageDes = packageDes;
        if(packageLib){
            this._packageLib = packageLib;
        }
    }

    public get name(): string {
        return this._name;
    }

    public get code(): string {
        return this._code;
    }

}