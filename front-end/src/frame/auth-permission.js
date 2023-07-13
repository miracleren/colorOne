import router from '@/router/index'
import store from "@/frame/store"
import {getToken} from "@/utils/system/token"

//不经权限验证的白名单
const whiteList = ['/login']
router.beforeEach((to, from, next) => {
    if (getToken()) {
        console.log('router has token')
        next();
    } else {
        console.log('router no token')
        //token不存在时
        if (whiteList.indexOf(to.path) !== -1) {
            next()
        } else {
            next(`/login?redirect=${to.path}`)
        }

    }
})
