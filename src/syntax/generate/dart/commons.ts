import { LanguageType } from "../../../commons/language";
import { DocType, getDocHelper } from "../../../utils/codes";
import { getTypeConvertor } from "../../../utils/types";
import { CodeGenerator } from "../commons";

export abstract class DartCodeGenerator extends CodeGenerator {
    constructor() {
        super();
        this._packageLib = "proto_bytes/com/protobytes/";
    }

    public init(author: string, packageDes: string, packageLib: string): void {
        super.init(author, packageDes, packageLib);
        if (this._packageDes) {
            this._packageDes = this._packageDes.replace(/\\/g, '/');
        }
        if (this._packageDes && this._packageDes.charAt(this._packageDes.length - 1) != '/') {
            this._packageDes += '/'
        }

        if (this._packageLib) {
            this._packageLib = this._packageLib.replace(/\\/g, '/');
        }
        if (this._packageLib && this._packageLib.charAt(this._packageLib.length - 1) != '/') {
            this._packageLib += '/'
        }
    }

    protected getDocHelper = () => getDocHelper(DocType.DART_DOC);
    protected getTypeConvertor = () => getTypeConvertor(LanguageType.DART);
    public get name(): string {
        return this._name + '.dart';
    }
}