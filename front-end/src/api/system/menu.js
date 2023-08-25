import request from '@/frame/axios/request'

//获取登录用户菜单树
export function getUserMenuTree() {
    return request({
        url: '/menu/role/tree',
        method: 'get',
    })
}

//查询菜单数据列表
export function getBaseMenuTreeList(query) {
    return request({
        url: '/menu/list', method: 'get', params: query
    })
}

//新增菜单数据
export function addBaseMenu(data) {
    return request({
        url: '/menu/add', method: 'post', data: data
    })
}

//修改菜单数据
export function editBaseMenu(data) {
    return request({
        url: '/menu/edit', method: 'put', data: data
    })
}

//删除菜单数据
export function deleteBaseMenu(menuId) {
    return request({
        url: `/menu/delete/${menuId}`, method: 'delete'
    })
}


//获取系统操作权限接口列表
export function getPermitUrl() {
    return request({
        url: '/menu/permit/url',
        method: 'get',
    })
}

//新增菜单操作接口
export function addPermitUrls(data) {
    return request({
        url: '/menu/permit/add', method: 'post', data: data
    })
}

//查询菜单权限树（Tree组件）
export function getBaseMenuTreeSelect() {
    return request({
        url: '/menu/select/tree', method: 'get'
    })
}
