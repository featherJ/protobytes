import * as path from "path";
import { configTemplate, protoTemplate } from "../template/templates";
import { writeFile } from "../utils/files";


export function createProtoTemplate(dir:string,name:string):string{
    var filename = path.join(dir,name);
    writeFile(filename,protoTemplate);
    return filename;
}
export function createConfigTemplate(dir:string,name:string):string{
    var filename = path.join(dir,name);
    writeFile(filename,configTemplate);
    return filename;
}

