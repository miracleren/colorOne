import request from '@/frame/axios/request'

//用户登陆
export function loginUser(data) {
    return request({
        url: '/login/user',
        data: data,
        method: 'post',
    })
}

//用户登出
export function loginUserOut() {
    return request({
        url: '/login/user/out',
        method: 'post',
    })
}

//获取登录用户信息
export function loginUserInfo() {
    return request({
        url: '/login/user/info',
        method: 'get',
    })
}

//获取登录验证码
export function captchaImage() {
    return request({
        url: '/login/captcha/image',
        method: 'get'
    })
}
