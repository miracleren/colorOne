import router from '@/router/index'
import store from "@/frame/store"
import {getToken} from "@/utils/system/token"


//不经权限验证的白名单
const whiteList = ['/login']

router.beforeEach((to, from, next) => {
    if (getToken()) {
        //token存在时，如果访问登录页跳转到首页
        if (to.path === whiteList[0]) {
            next({path: '/home'})
        } else {
            console.log("store.state.loginUser.userName", store.state.loginUser.userName)
            //用户登录相关信息不存在
            if (!store.state.loginUser.userName) {
                store.dispatch("userInfoSet").then(res => {
                    //处理路由权限等信息
                    console.log("userInfoSet", res)

                }).catch(err => {
                    store.dispatch('userLoginOut').then(() => {
                        window.$message.error(err)
                        next({path: '/'})
                    })
                })
            }
            console.log('router has token')
            next()
        }
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
