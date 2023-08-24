import request from '@/frame/axios/request'

//查询字典数据列表
export function getBaseDictList(query) {
    return request({
        url: '/dict/list', method: 'get', params: query
    })
}

//查询字典数据树列表
export function getBaseDictTreeList(query) {
    return request({
        url: '/dict/tree', method: 'get', params: query
    })
}

//新增字典数据
export function addBaseDict(data) {
    return request({
        url: '/dict/add', method: 'post', data: data
    })
}

//修改字典数据
export function editBaseDict(data) {
    return request({
        url: '/dict/edit', method: 'put', data: data
    })
}

//删除字典数据
export function deleteBaseDict(dictId) {
    return request({
        url: `/dict/delete/${dictId}`, method: 'delete'
    })
}


//通过字典类型获取字典数据
export function getSelectDictList(type) {
    return request({
        url: `/dict/select/${type}`, method: 'get',
    })
}

//查询子字典数据列表
export function getBaseDictChildren(dictId) {
    return request({
        url: `/dict/children/${dictId}`, method: 'get'
    })
}
