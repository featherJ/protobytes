#!/usr/bin/env node
import { program } from "commander";
import { createConfigTemplate, createProtoTemplate } from "./scripts/create";
import chalk from 'chalk';
import * as path from "path";
import * as fs from "fs";
import { supportLanguage } from "./commons/language";
import { compile, CompileConfig } from "./scripts/compile";
import { readFile } from "./utils/files";

program
    .name('protobytes')
    .description('CLI for compiling proto files to specified languages.')
    .version('0.9.7')
    .on('--help', () => {
        console.log('');
        console.log('Examples:');
        console.log('  $ protobytes -h');
        console.log('  $ protobytes support');
        console.log('  $ protobytes create proto -o ./templates');
        console.log('  $ protobytes create config -o ./templates');
        console.log('  $ protobytes compile -s ./my-protos -t java -o ./my-generate/java/com/myprotos -p com.myprotos -a MyName -c');
        console.log('  $ protobytes compile -s ./my-protos -t java -o ./my-generate/java/com/myprotos -p com.myprotos');
        console.log('  $ protobytes compile -config ./proto-config.json');
    });

program.command('create')
    .argument('<string>', "'proto' | 'config'")
    .description('Create a protocol or configuration template file.')
    .option('-o, --output-dir <string>', 'Output directory.')
    .option('-n, --name <string>', 'Output file name.')
    .action((argument, options) => {
        if (argument != 'proto' && argument != 'config') {
            console.log(chalk.red(`Error: Unsupported argument '${argument}', only 'proto' or 'config' supported.`));
            return;
        }
        var dir: string = options.outputDir ? options.outputDir : "";
        dir = path.resolve(process.cwd(), dir);
        var name: string = options.name ? options.name : "";
        if (argument == "proto") {
            if (!name) {
                name = "example";
            }
            name = name.split('.')[0];
            name += '.proto';
            let filename = createProtoTemplate(dir, name);
            console.log(`Protocol template created at ${chalk.green(filename)}`);
        } else if (argument == "config") {
            if (!name) {
                name = "proto-config";
            }
            name = name.split('.')[0];
            name += '.json';
            let filename = createConfigTemplate(dir, name);
            console.log(`Configuration template created at ${chalk.green(filename)}`);
        }
    });

program.command('compile')
    .description('Compile proto files to specified languages.')
    .option('-s, --source-dir <string>', "The directory where the protocol files is located.","./")
    .option('-t, --type <string>', `The Type of language to generated. You can view the supported types with command '${chalk.green('support')}'.`)
    .option('-o, --output-dir <string>', `Output directory.`,"./output")
    .option('-p, --package-path <string>', `Package of generated code`)
    .option('-a, --author <string>', `Author of generated code.`, "")
    .option('-c, --clear', `Clean up the output directory.`, false)
    .option('-config, --config-path <string>', `Compile configuration file path.`)
    .action((argument) => {
        var sourceDir:string = argument.sourceDir;
        var type:string = argument.type;
        var outputDir:string = argument.outputDir;
        var packagePath:string = argument.packagePath;
        var author:string = argument.author;
        var clear:boolean = argument.clear;
        var configPath:string = argument.configPath;

        var config:CompileConfig = null;
        var argVerified = true;
        if(configPath){
            configPath = path.resolve(process.cwd(),configPath);
            if(!fs.existsSync(configPath)){
                argVerified = false;
                console.log(chalk.red(`Error: Argument error with '-config'. File '${configPath}' does not exist.`));
                console.log(chalk.red(`You can create a configuration template with command 'create'.`));
            }else{
                if(fs.statSync(configPath).isDirectory()){
                    console.log(chalk.red(`Error: Argument error with '-config'. '${configPath}' is not a file.`));
                    console.log(chalk.red(`You can create a configuration template with command 'create'.`));
                    argVerified = false;
                }else{
                    var configContentStr:string = null;
                    var configContent:CompileConfig = null;
                    try {
                        configContentStr = readFile(configPath);
                    } catch (error) {
                        console.log(chalk.red(error));
                        argVerified = false;
                    }
                    if(configContentStr){
                        try {
                            configContent = JSON.parse(configContentStr);
                        } catch (error) {
                            console.log(chalk.red(`Error: Argument error with '-config'. ${chalk.red(error)}`));
                            console.log(chalk.red(`You can create a configuration template with command 'create'.`));
                            argVerified = false;
                        }
                    }
                    if(configContent){
                        if(!configContent.sourceDir){
                            console.log(chalk.red(`Error: Can not find 'sourceDir' in specified configuration.`));
                            console.log(chalk.red(`You can create a configuration template with command 'create'.`));
                            argVerified = false;
                        }else{
                            configContent.sourceDir = path.resolve(path.dirname(configPath),configContent.sourceDir);
                            if(!fs.existsSync(configContent.sourceDir)){
                                argVerified = false;
                                console.log(chalk.red(`Error: Directory '${configContent.sourceDir}' of 'sourceDir' does not exist.`));
                                argVerified = false;
                            }
                        }
                        if(!configContent.outputs || !configContent.outputs.length){
                            console.log(chalk.red(`Error: Can not find 'outputs' in specified configuration or 'outputs' is empty.`));
                            console.log(chalk.red(`You can create a configuration template with command 'create'.`));
                            argVerified = false;
                        }else{
                            for(var i = 0;i<configContent.outputs.length;i++){
                                var outputConfig = configContent.outputs[i];
                                if(!outputConfig.type){
                                    console.log(chalk.red(`Error: 'type' of 'outputs' not exist.`));
                                    console.log(chalk.red(`You can create a configuration template with command 'create'.`));
                                    argVerified = false;
                                    continue;
                                }
                                var type = outputConfig.type;
                                if(!supportLanguage(type)){
                                    argVerified = false;
                                    console.log(chalk.red(`Error: '${type}' is not supported, you can view the supported types with command 'support'`));
                                    continue;
                                }
                                outputConfig.dir = path.resolve(path.dirname(configPath),outputConfig.dir);
                                if(!outputConfig.package){
                                    argVerified = false;
                                    console.log(chalk.red(`Error: 'package' of generated code must be specified in 'output' config of type '${type}'.`));
                                }
                            }
                        }
                        if(argVerified){
                            config = configContent;
                        }
                    }
                }
            }
        }else{
            if(!sourceDir){
                argVerified = false;
                console.log(chalk.red(`Error: Argument error with '-s'. The directory of the protocol files must be specified.`));
            }else{
                sourceDir = path.resolve(process.cwd(),sourceDir);
                if(!fs.existsSync(sourceDir)){
                    argVerified = false;
                    console.log(chalk.red(`Error: Argument error with '-s'. Directory '${sourceDir}' does not exist.`));
                }
            }
            if(!type){
                argVerified = false;
                console.log(chalk.red(`Error: Argument error with '-t'. The Type of language to generated must be specified.`));
            }else{
                if(!supportLanguage(type)){
                    argVerified = false;
                    console.log(chalk.red(`Error: Argument error with '-t'. '${type}' is not supported, you can view the supported types with command 'support'`));
                }
            }
            if(!outputDir){
                argVerified = false;
                console.log(chalk.red(`Error: Argument error with '-o'. Output directory must be specified.`));
            }else{
                outputDir = path.resolve(process.cwd(),outputDir);
            }
            if(!packagePath){
                argVerified = false;
                console.log(chalk.red(`Error: Argument error with '-p'. Package of generated code must be specified.`));
            }
            if(argVerified){
                config = {
                    sourceDir:sourceDir,
                    outputs:[
                        {
                            type:type,
                            dir:outputDir,
                            package:packagePath,
                            author:author ? author : null,
                            clear:!!clear,
                        }
                    ]
                }
            }
        }
        if(!argVerified){
            return;
        }
        compile(config);
    })
    .on('--help', () => {
        console.log('');
        console.log('Examples:');
        console.log('  $ protobytes compile -s ./my-protos -t java -o ./my-generate/java/com/myprotos -p com.myprotos -a MyName -c');
        console.log('  $ protobytes compile -s ./my-protos -t java -o ./my-generate/java/com/myprotos -p com.myprotos');
        console.log('  $ protobytes compile -config ./proto-config.json');
    });
program.command('support')
    .description('View currently supported languages')
    .action((str, options) => {
        console.log(`Supported: ${chalk.green('Java')}, ${chalk.green('Dart')}`);
        console.log(`·${chalk.green('Java')}: https://github.com/featherJ/protobytes/tree/master/java`);
        console.log(`·${chalk.green('Dart')}: https://github.com/featherJ/protobytes/tree/master/dart`);
    });
program.parse();