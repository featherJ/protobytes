import * as fs from 'fs';
import * as path from 'path';

export function search(dir: string, ext: string = null): string[] {
    if (ext) {
        if (ext.charAt(0) != '.') {
            ext = '.' + ext;
        }
        ext = ext.toLocaleLowerCase();
    }
    var paths: string[] = [];
    var doSearch = (dir: string, ext: string) => {
        var files = fs.readdirSync(dir);
        files.forEach(name => {
            var fileDir = path.join(dir, name);
            var state = fs.statSync(fileDir);
            if (state.isDirectory()) {
                doSearch(fileDir, ext);
            } else if (state.isFile()) {
                var extname = path.extname(fileDir);
                if (ext) {
                    if (extname.toLocaleLowerCase() == ext) {
                        paths.push(fileDir);
                    }
                } else {
                    paths.push(fileDir);
                }
            }
        });
    }
    doSearch(dir, ext);
    return paths;
}

export function readFile(fileDir: string): string {
    return fs.readFileSync(fileDir, 'utf-8');
}

function mkdir(dir: string): boolean {
    if (fs.existsSync(dir)) {
        return true;
    } else if (mkdir(path.dirname(dir))) {
        fs.mkdirSync(dir);
        return true;
    }
}


export function writeFile(filename: string, content: string): void;
export function writeFile(dir: string, name: string, content: string): void;
export function writeFile(arg1, arg2, arg3 = null): void {
    var filename:string = "";
    var content:string = "";
    if(arg3 == null){
        filename = arg1;
        content = arg2;
    }else{
        filename = path.join(arg1,arg2);
        content = arg3;
    }
    var dir = path.dirname(filename);
    mkdir(dir);
    fs.writeFileSync(filename, content);
}

export function removeDir(dir: string) {
    if (fs.existsSync(dir)) {
        fs.readdirSync(dir).forEach((file) => {
            var curPath = path.join(dir, file);
            if (fs.statSync(curPath).isDirectory()) {
                removeDir(curPath);
            } else {
                fs.unlinkSync(curPath);
            }
        });
        fs.rmdirSync(dir);
    }
}