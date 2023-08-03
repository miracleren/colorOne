/**
 * 时间日期处理工具类
 */


//默认时间格式
export const defaultDateFormat = 'yyyy年MM月dd日 HH时mm分ss秒'
export const defaultFormFormat = 'yyyy-MM-dd HH:mm:ss'
export const defaultTimeFormat = 'HH时mm分ss秒'

/**
 * 时间日期格式转换
 * @param date
 * @param format
 * @returns {*}
 */
export function formatDate(date, format) {
    if (!format) format = defaultDateFormat

    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate()
    let hour = date.getHours()
    let minute = date.getMinutes()
    let second = date.getSeconds()
    format = format.replace('yyyy', year)
    format = format.replace('MM', month < 10 ? '0' + month : month)
    format = format.replace('dd', day < 10 ? '0' + day : day)
    format = format.replace('HH', hour < 10 ? '0' + hour : hour)
    format = format.replace('mm', minute < 10 ? '0' + minute : minute)
    format = format.replace('ss', second < 10 ? '0' + second : second)
    return format
}


/**
 * 计算天数差的函数
 * @param startTime
 * @param endTime
 * @returns {number}
 */
export function dateDiff(startTime, endTime) {
    return parseInt(Math.abs(endTime - startTime) / 1000 / 60 / 60 / 24)
}


/**
 * 计算时间时差函数
 * @param startTime
 * @param endTime
 * @returns {number}
 */
export function divTime(startTime, endTime) {
    return Math.abs(parseInt((endTime - startTime) / 1000 / 3600))
}

/**
 * 时间组件范值加载到值到model里，默认全局关键字为startTime，endTime
 * @param range
 */
export function formRangeTime(range) {
    if (range && range.length === 2) {
        return {
            startTime: range[0],
            endTime: range[1]
        }
    }
    return {}
}
