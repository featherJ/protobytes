import { getGenerators } from "../syntax/generate/generator";
import { syntaxAnalyzer } from "../syntax/syntax";
import { SyntaxNodeTree } from "../syntax/tree/syntax-tree";
import { readFile, removeDir, search, writeFile } from "../utils/files";
import * as path from "path";

export interface CompileConfig {
    sourceDir: string,
    outputs: {
        type: string
        dir: string,
        package: string,
        author?: string,
        clear?: boolean,
        packageLib?: string;
    }[]
}

/**
 * 协议编译器
 * @author Agua.L
 */
class Compiler {
    private config: CompileConfig;
    private syntaxNodeTree: SyntaxNodeTree;

    public run(config: CompileConfig) {
        this.config = config;
        this.syntaxNodeTree = new SyntaxNodeTree();
        this.syntaxAnalys();
        this.generate();
    }

    /**
     * 语法分析
     */
    private syntaxAnalys(): void {
        var protoDirs = search(this.config.sourceDir, 'proto');
        protoDirs.forEach(protoDir => {
            var protoContent = readFile(protoDir);
            syntaxAnalyzer.analyse(protoContent, this.syntaxNodeTree);
        });
    }

    /**
     * 生成代码
     */
    private generate(): void {
        this.config.outputs.forEach(output => {
            if (output.clear) {
                removeDir(output.dir);
            }
            var generators = getGenerators(output.type);
            if (!generators) {
                //TODO 报错
            }
            //TODO output
            //voUtil
            generators.init(output.author, output.package, output.packageLib);
            //vo
            this.syntaxNodeTree.beans.forEach(bean => {
                generators.voGenerator.decode(bean);
                writeFile(path.join(output.dir, "/infos"), generators.voGenerator.name, generators.voGenerator.code);
            });
            //buff
            if (generators.buffConvertor) {
                generators.buffConvertor.decode(this.syntaxNodeTree.beans);
                writeFile(path.join(output.dir, "/utils"), generators.buffConvertor.name, generators.buffConvertor.code);
            }
            //buff list
            if (generators.buffListConvertor) {
                generators.buffListConvertor.decode(this.syntaxNodeTree.beans);
                writeFile(path.join(output.dir, "/utils"), generators.buffListConvertor.name, generators.buffListConvertor.code);
            }
        });
    }
}

export function compile(config:CompileConfig):void{
    new Compiler().run(config);
}
