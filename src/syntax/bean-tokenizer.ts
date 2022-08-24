import { AttributeTokenizer } from "./attribute-tokenizer";
import { Word } from "./tokens/word";
import { BeanNode } from "./tree/bean";

export class BeanTokenizer {
    private beanNode: BeanNode;
    private words: Word[];
    constructor(words: Word[]) {
        this.words = words;
        this.beanNode = new BeanNode();
        var hasNote: Boolean = false;
        if (this.words[0].word.length > 2 && this.words[0].word.charAt(0) == "/" && this.words[0].word.charAt(1) == "/" && this.words[0].word.charAt(2) == "/") {
            this.beanNode.note = this.words[0].word.slice(3).trim();
            this.beanNode.name = this.words[2].word.trim();
            hasNote = true;
        } else if (this.words[2].word.length > 2 && this.words[2].word.charAt(0) == "/" && this.words[2].word.charAt(1) == "/" && this.words[2].word.charAt(2) == "/") {
            this.beanNode.note = this.words[2].word.slice(3).trim();
            this.beanNode.name = this.words[1].word.trim();
            hasNote = true;
        } else if (this.words[3].word.length > 2 && this.words[3].word.charAt(0) == "/" && this.words[3].word.charAt(1) == "/" && this.words[3].word.charAt(2) == "/") {
            this.beanNode.note = this.words[3].word.slice(3);
            this.beanNode.name = this.words[1].word.trim();
            hasNote = true;
        }
        if (!hasNote) {
            this.beanNode.name = this.words[1].word;
        }

        var start = 0;
        var end = 0;
        for (var i = 0; i < this.words.length; i++) {
            if (this.words[i].word == "{") start = i;
            if (this.words[i].word == "}") end = i;
        }
        this.words = words.slice(start + 1, end);
        this.analyseAttributes();
    }

    private analyseAttributes(): void {
        var att: AttributeTokenizer = new AttributeTokenizer(this.words);
        this.beanNode.attList = att.getResult();
    }

    public getResult(): BeanNode {
        return this.beanNode;
    }
}