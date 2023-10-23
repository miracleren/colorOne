import request from "@/frame/axios/request"

//查询文件数据列表
export function getBaseFilePoolList(query) {
    return request({
        url: '/pool/file/list', method: 'get', params: query
    })
}

//删除文件数据
export function deleteBaseFilePool(fileId) {
    return request({
        url: `/pool/file/delete/${fileId}`, method: 'delete'
    })
}
