import router, {addError} from '@/frame/router/index'
import store from '@/frame/store'
import {getToken} from '@/utils/system/token'


//不经权限验证的白名单
const whiteList = ['/login']

/*路由守卫*/
router.beforeEach((to, from, next) => {
    if (getToken()) {
        //token存在时，如果访问登录页跳转到首页
        if (to.path === whiteList[0]) {
            next({path: '/'})
        } else {
            //用户登录相关信息不存在
            console.log('store userName', store._state.data.loginUser)
            if (!store._state.data.loginUser.name) {
                //用户登录相关信息不存在
                store.dispatch('userInfoSet').then(res => {
                    //处理路由权限等信息
                    store.dispatch('routerGenerate').then(routes => {
                        //在父路由添加动态路由
                        let layout = router.getRoutes().find(r => {
                            return r.name === 'bas-layout'
                        })
                        layout.children = routes.concat(layout.children)
                        //layout.children = routes
                        router.addRoute(layout)
                        //动态添加404
                        addError()

                        console.log('addRoute', router.getRoutes())
                        //确保路由加载完成
                        next({...to, replace: true})
                    })
                }).catch(err => {
                    store.dispatch('userLoginOut').then(() => {
                        window.$message.error(err)
                        next({path: '/'})
                    })
                })
            } else {
                next()
            }
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
