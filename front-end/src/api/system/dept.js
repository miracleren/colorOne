import request from '@/frame/axios/request'

//查询部门数据列表
export function getBaseDeptList(query) {
    return request({
        url: '/dept/list', method: 'get', params: query
    })
}

//查询部门数据树列表
export function getBaseDeptTreeList(query) {
    return request({
        url: '/dept/tree', method: 'get', params: query
    })
}

//新增部门数据
export function addBaseDept(data) {
    return request({
        url: '/dept/add', method: 'post', data: data
    })
}

//修改部门数据
export function editBaseDept(data) {
    return request({
        url: '/dept/edit', method: 'put', data: data
    })
}

//删除部门数据
export function deleteBaseDept(deptId) {
    return request({
        url: `/dept/delete/${deptId}`, method: 'delete'
    })
}


//停用或启用部门
export function setBaseDeptStatus(data) {
    return request({
        url: '/dept/set/status', method: 'put', data: data
    })
}
