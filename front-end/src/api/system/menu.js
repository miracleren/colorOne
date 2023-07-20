import request from "@/frame/axios/request"

//获取登录用户菜单树
export function getUserMenuTree() {
    return request({
        url: "/menu/role/tree",
        method: "get",
    })
}
