import request from "@/utils/system/request";

//用户登陆
export function loginUser(data) {
    return request({
        url: "/login/user",
        data: data,
        method: "post",
    });
}
