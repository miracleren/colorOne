import request from '@/frame/axios/request'

//查询登录日志数据列表
export function getBaseLogLoginList(query) {
    return request({
        url: '/log/login/list', method: 'get', params: query
    })
}
