import request from '@/frame/axios/request'

//查询用户数据列表
export function getBaseUserList(query) {
    return request({
        url: '/user/list', method: 'get', params: query
    })
}

//新增用户数据
export function addBaseUser(data) {
    return request({
        url: '/user/add', method: 'post', data: data
    })
}

//修改用户数据
export function editBaseUser(data) {
    return request({
        url: '/user/edit', method: 'put', data: data
    })
}

//删除用户数据
export function deleteBaseUser(userId) {
    return request({
        url: `/user/delete/${userId}`, method: 'delete'
    })
}


//更改当前用户状态数据
export function switchBaseUserStatus(data) {
    return request({
        url: `/user/status/switch`, method: 'put', data: data
    })
}


//重置用户密码
export function resetUserPassword(data) {
    return request({
        url: '/user/reset/password', method: 'put', data: data
    })
}

//重置自己的密码
export function resetPasswordBySelf(data) {
    return request({
        url: '/user/reset/password/self', method: 'put', data: data
    })
}
