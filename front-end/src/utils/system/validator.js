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
const required = (path, message = null) => {
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
const number = (path, message = null, min = null, max = null) => {
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


const selectRequired = (path, message = null, isNumber = false) => {
    return {
        path: path,
        rule: {
            required: true,
            type: isNumber ? 'number' : 'string',
            trigger: ['blur', 'change'],
            message: message || '请选择有效内容',
        }
    }
}

/**
 * 验证是否邮箱
 * @param path
 * @param message
 * @param required
 * @returns {{path, rule: {trigger: string[], message: string, required: boolean}}}
 */
const email = (path, message = null, required = true) => {
    return {
        path: path,
        rule: {
            required: required,
            message: message || '请输入有效的邮箱地址',
            trigger: ['blur', 'input'],
            validator(rule, value) {
                console.log('check email')
                let reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
                return reg.test(value)
            },
        }
    }
}

/**
 * 验证是否手机
 * @param path
 * @param message
 * @param required
 * @returns {{path, rule: {trigger: string[], message: string, required: boolean}}}
 */
const phone = (path, message = null, required = true) => {
    return {
        path: path,
        rule: {
            required: required,
            message: message || '请输入有效的手机号码',
            trigger: ['blur', 'input'],
            validator(rule, value) {
                console.log('check phone')
                let reg = /^1[3456789]\d{9}$/
                return reg.test(value)
            },
        }
    }
}

/**
 * 验证密码格式
 * @param path
 * @param message
 * @param required
 * @returns {{path, rule: {trigger: string[], message: string, required: boolean}}}
 */
const password = (path, message = null, required = true) => {
    return {
        path: path,
        rule: {
            required: required,
            message: message || '密码不能为空且长度在8到30个字符,必须包含数字、字母及符号！',
            trigger: ['blur', 'input'],
            validator(rule, value) {
                console.log('check password')
                let reg = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}/
                return reg.test(value)
            },
        }
    }
}

/**
 * 验证两个路径数据是否一致
 * @param path
 * @param firstValue
 * @param message
 * @param required
 * @returns {{path: *, rule: {validator(*, *): boolean, trigger: string[], message: string, required: boolean}}|boolean}
 */
const isSame = (path, firstValue, message = null, required = true) => {
    return {
        path: path,
        rule: {
            required: required,
            message: message || '重复输入的数据不一致',
            trigger: ['blur', 'input'],
            validator(rule, value) {
                console.log('check isSame')
                return value === firstValue
            }
        }
    }
}

let validator = {
    required, number, selectRequired, email, phone, password, isSame
}
export default validator
