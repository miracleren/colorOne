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
function randomInteger(min, max) {
    return Math.floor(Math.random() * (max - min)) + min
}
