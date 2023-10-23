/**
 * 随机生成带字符、数字、符号的密码
 * @returns {string}
 */
export function randomPassword() {
    let codeNumber = '0123456789',
        codeLetter = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',
        codeSign = '~!@#$%^&*-=+?.,;:|><'
    let pass = ''
    for (let i = 0; i < 4; i++) {
        pass += codeLetter[randomInteger(0, codeLetter.length - 1)]
        pass += codeNumber[randomInteger(0, codeNumber.length - 1)]
        pass += i < 2 ? codeSign[randomInteger(0, codeSign.length - 1)] : ''
    }
    let randomPass = '', l = pass.length
    for (let j = 0; j < l; j++) {
        let num = randomInteger(0, pass.length - 1)
        randomPass += pass[num]
        pass = pass.replace(pass[num], '')
    }

    return randomPass
}


/**
 * 生成包含范围内的随机整数数
 * @param min
 * @param max
 * @returns {*}
 */
export function randomInteger(min, max) {
    return Math.floor(Math.random() * (max - min)) + min
}


export function randomGuid(prefix = '', length = 8) {
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
    }

    let guid = prefix
    for (let i = 0; i < length; i++) {
        guid += S4()
    }
    return guid
}
