/**
 * 对象数组尝试拷贝
 * @param source
 * @returns {*[]|{}}
 */
export function deepClone(source) {
    if (!source || typeof source !== 'object') {
        throw new Error('error arguments', 'shallowClone')
    }
    var targetObj = source.constructor === Array ? [] : {}
    for (var keys in source) {
        if (source.hasOwnProperty(keys)) {
            if (source[keys] && typeof source[keys] === 'object') {
                targetObj[keys] = source[keys].constructor === Array ? [] : {}
                targetObj[keys] = deepClone(source[keys])
            } else {
                targetObj[keys] = source[keys]
            }
        }
    }
    return targetObj
}

/**
 * 判断对象是否不为空
 * @param data
 * @returns {boolean}
 * @constructor
 */
export function ObjectIsNotEmpty(data) {
    return !ObjectIsEmpty(data)
}

/**
 * 判断对象是否为空
 * @param data
 * @returns {boolean}
 * @constructor
 */
export function ObjectIsEmpty(data) {
    console.log('ObjectIsEmpty', !(data === null || Object.keys(data).length === 0))
    return data === null || Object.keys(data).length === 0
}
