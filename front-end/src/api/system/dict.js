import request from '@/frame/axios/request'

//通过字典类型获取字典数据
export function getSelectDictList(type) {
    return request({
        url: `/dict/select/${type}`,
        method: 'get',
    })
}


//查询字典列表数据+ '?params%5BstartTime%5D%3D1690819200000&params%5BendTime%5D%3D1692115200000',',
export function getDictList(query) {
    return request({
        url: '/dict/list',
        method: 'get',
        params: query
    })
}
