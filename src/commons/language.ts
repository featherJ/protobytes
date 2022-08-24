export enum LanguageType {
    JAVA = "java",
    DART = "dart"
}

var languages = [LanguageType.DART, LanguageType.JAVA];

/**
 * 查看是否支持某个语言
 * @param language 
 * @returns 
 */
export function supportLanguage(language: string): boolean {
    return languages.indexOf(language as LanguageType) != -1;
}