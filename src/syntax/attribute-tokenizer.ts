import { Word } from "./tokens/word";
import { AttributeNode } from "./tree/attribute";

export class AttributeTokenizer {
    private words: Word[];
    constructor(words: Word[]) {
        this.words = words;
        this.analyseAttributes();
    }

    private atts: AttributeNode[] = [];
    private analyseAttributes(): void {
        if (this.words.length < 2) return;
        var att: AttributeNode = new AttributeNode();
        var hasNote: Boolean = false;
        if (this.words.length >= 3) {
            if (this.words[0].word.length > 2 && this.words[0].word.charAt(0) == "/" && this.words[0].word.charAt(1) == "/" && this.words[0].word.charAt(2) == "/") {
                att.note = this.words[0].word.slice(3);
                att.name = this.words[2].word;
                att.type = this.words[1].word;
                hasNote = true;
            }
            if (this.words[2].word.length > 2 && this.words[2].word.charAt(0) == "/" && this.words[2].word.charAt(1) == "/" && this.words[2].word.charAt(2) == "/" &&
                this.words[0].row == this.words[1].row && this.words[1].row == this.words[2].row) {
                att.note = this.words[2].word.slice(3);
                att.type = this.words[0].word;
                att.name = this.words[1].word;
                hasNote = true;
            }
            if (!hasNote) {
                att.type = this.words[0].word;
                att.name = this.words[1].word;
            }
        } else if (this.words.length >= 2) {
            att.type = this.words[0].word;
            att.name = this.words[1].word;
        }
        if (hasNote) {
            this.words = this.words.slice(3);
        } else {
            this.words = this.words.slice(2);
        }
        if (att.type.indexOf("List<") == 0) {
            att.isList = true;
            att.type = att.type.slice(5, att.type.length - 1);
        }
        this.atts.push(att);
        this.analyseAttributes();
    }

    public getResult(): AttributeNode[] {
        return this.atts;
    }
}