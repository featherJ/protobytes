import { BeanTokenizer } from "./bean-tokenizer";
import { Word } from "./tokens/word";
import { SyntaxNodeTree } from "./tree/syntax-tree";

class SyntaxAnalyzer {
    private str: string;
    private syntaxTree: SyntaxNodeTree;
    public analyse(str: string, syntaxTree: SyntaxNodeTree): void {
        this.str = str;
        this.loc = 0;
        this.currentWord = "";
        this.words = [];
        this.rowId = 0;
        this.syntaxTree = syntaxTree;
        this.nextChar();
    }

    private currentWord: string = "";
    private words: Word[];
    private loc = 0;
    private isNote = false;
    private rowId = 0;
    private nextChar(): void {
        while (true) {
            if (this.loc >= this.str.length) {
                this.anslyseWorlds();
                break;
            }

            if (this.loc < this.str.length - 2 && this.str.charAt(this.loc + 1) == "/" && this.str.charAt(this.loc) == "/") {
                this.isNote = true;
            }

            if (this.isNote) {
                if (this.str.charAt(this.loc) != "\n" && this.str.charAt(this.loc) != "\r") {
                    this.currentWord += this.str.charAt(this.loc);
                } else {
                    this.isNote = false;
                    if (this.currentWord.length > 0) {
                        this.words.push({ row: this.rowId, word: this.currentWord });
                    }
                    this.rowId++;
                    this.currentWord = "";
                }
            } else {
                if(this.str.charAt(this.loc) == "{" || this.str.charAt(this.loc) == "}"){
                    if (this.currentWord.length > 0) {
                        this.words.push({ row: this.rowId, word: this.currentWord });
                        this.currentWord = "";
                    }
                    this.currentWord += this.str.charAt(this.loc);
                    this.words.push({ row: this.rowId, word: this.currentWord });
                    this.currentWord = "";
                }else if (this.str.charAt(this.loc) != "\n" && this.str.charAt(this.loc) != "\r" && this.str.charAt(this.loc) != " " && this.str.charAt(this.loc) != ";" && this.str.charAt(this.loc) != "	") {
                    this.currentWord += this.str.charAt(this.loc);
                } else {
                    if (this.currentWord.length > 0) {
                        this.words.push({ row: this.rowId, word: this.currentWord });
                    }
                    if (this.str.charAt(this.loc) == "\n" || this.str.charAt(this.loc) == "\r")
                        this.rowId++;
                    this.currentWord = "";
                }
            }
            if (this.loc == this.str.length - 1) {
                if (this.currentWord.length > 0) {
                    this.words.push({ row: this.rowId, word: this.currentWord });
                }
                this.currentWord = "";
            }
            this.loc++;
        }
    }

    private anslyseWorlds(): void {
        if (this.words.length == 0) return;
        //去掉注释行
        for (var i = 0; i < this.words.length; i++) {
            if (this.words[i].word.indexOf("//") == 0 && this.words[i].word.indexOf("///") == -1) {
                this.words.splice(i, 1);
                i--;
            }
        }
        //合并相连的注释。
        for (i = 0; i < this.words.length - 1; i++) {
            if (this.words[i].word.indexOf("///") == 0 && this.words[i + 1].word.indexOf("///") == 0) {
                this.words[i].word = this.words[i].word + this.words[i + 1].word.slice(3);
                i--;
            }
        }
        for (i = 0; i < this.words.length; i++) {
            if (this.words[i].word == "}") {
                var tempWords = this.words.slice(0, i + 1);
                this.words = this.words.slice(i + 1);
                if ((tempWords.length >= 1 && tempWords[0].word == "vo") || (tempWords.length >= 2 && tempWords[1].word == "vo")) {
                    var bean: BeanTokenizer = new BeanTokenizer(tempWords);
                    this.syntaxTree.beans.push(bean.getResult());
                }
                this.anslyseWorlds();
            }
        }
    }
}

export var syntaxAnalyzer: SyntaxAnalyzer = new SyntaxAnalyzer();