import request from '@/frame/axios/request'

//查询角色数据列表
export function getBaseRoleList(query) {
    return request({
        url: '/role/list', method: 'get', params: query
    })
}

//新增角色数据
export function addBaseRole(data) {
    return request({
        url: '/role/add', method: 'post', data: data
    })
}

//修改角色数据
export function editBaseRole(data) {
    return request({
        url: '/role/edit', method: 'put', data: data
    })
}

//删除角色数据
export function deleteBaseRole(roleId) {
    return request({
        url: `/role/delete/${roleId}`, method: 'delete'
    })
}

//查询角色菜单权限编号列表
export function getRoleMenuIds(roleId) {
    return request({
        url: `/role/menus/${roleId}`, method: 'get'
    })
}
