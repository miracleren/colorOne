/**
 * 数据验证规则公共方法
 * path 对应model数据源的键值，否则取不了值，参考naive数据验证结构
 * 该方法只把通过属性抽离出来，更易管理
 */

/**
 * 必填项
 * @param path
 * @param message
 * @returns {{path, rule: {trigger: string[], message: string, required: boolean}}}
 */
const required = (path, message) => {
    return {
        path: path,
        rule: {
            required: true,
            message: message || '请输入相关内容',
            trigger: ['blur', 'input']
        }
    }
}

/**
 * 数字校验
 * @param path
 * @param message
 * @param min
 * @param max
 * @returns {{path, rule: {trigger: string[], type: string, message: string}}}
 */
const number = (path, message, min = null, max = null) => {
    return {
        path: path,
        rule: {
            type: 'number',
            required: true,
            validator(rule, value) {
                if (min)
                    return value >= min
                if (max)
                    return value <= max
            },
            trigger: ['blur', 'change'],
            message: message || '请输入有效数值',
        }
    }
}

let validator = {
    required, number
}
export default validator
