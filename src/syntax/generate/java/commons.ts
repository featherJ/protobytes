import { LanguageType } from "../../../commons/language";
import { DocType, getDocHelper } from "../../../utils/codes";
import { getTypeConvertor } from "../../../utils/types";
import { CodeGenerator } from "../commons";

export abstract class JavaCodeGenerator extends CodeGenerator {
    constructor(){
        super();
        this._packageLib = "com.protobytes";
    }
    protected getDocHelper = () => getDocHelper(DocType.JAVA_DOC);
    protected getTypeConvertor = () => getTypeConvertor(LanguageType.JAVA);
    public get name(): string {
        return this._name + '.java';
    }
}