import request from "@/utils/system/request";

//用户登陆
export function loginUser(data) {
    return request({
        url: "/login/user",
        data: data,
        method: "post",
    });
}


//用户登出
export function loginUserOut() {
    return request({
        url: "/login/user/out",
        method: "post",
    });
}
