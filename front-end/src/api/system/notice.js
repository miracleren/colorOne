import request from '@/frame/axios/request'

//查询公告数据列表
export function getBaseNoticeList(query) {
    return request({
        url: '/notice/list', method: 'get', params: query
    })
}

//新增公告数据
export function addBaseNotice(data) {
    return request({
        url: '/notice/add', method: 'post', data: data
    })
}

//修改公告数据
export function editBaseNotice(data) {
    return request({
        url: '/notice/edit', method: 'put', data: data
    })
}

//删除公告数据
export function deleteBaseNotice(noticeId) {
    return request({
        url: `/notice/delete/${noticeId}`, method: 'delete'
    })
}
