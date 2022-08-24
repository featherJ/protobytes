/**
 * 得到常量字符串
 * @param str 
 * @param upperCase 
 * @returns 
 */
function getConstStr(str: string, upperCase: boolean): string {
    var reg = /([A-Z][a-z]{1,})|([0-9]{1,})/g;
    var arr = str.split(reg);
    var resultArr = [];
    for (var i = 0; i < arr.length; i++) {
        if (arr[i]) {
            var tempArr = arr[i].split("_");
            for (var j = 0; j < tempArr.length; j++) {
                if (tempArr[j] != "") {
                    if (upperCase) {
                        resultArr.push(String(tempArr[j]).toLocaleUpperCase());
                    } else {
                        resultArr.push(String(tempArr[j]).toLocaleLowerCase());
                    }
                }
            }
        }
    }
    var result = resultArr.join("_");
    return result;
}

export function getConstUpperStr(str: string): string {
    return getConstStr(str, true);
}

export function getConstLowerStr(str: string): string {
    return getConstStr(str, false);
}