import {createRouter, createWebHistory} from 'vue-router'

/**
 * Note: 路由配置项
 *
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 */

// 公共静态路由配置
const routes = [
    {
        //基础父路由
        path: '/',
        name: 'bas-layout',
        component: () => import('@/views/frame/home/index.vue'),
        redirect: '/index',
        children: [
            {
                path: '/index',
                name: 'dashboard',
                component: () => import('@/views/frame/dashboard/index.vue'),
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/frame/login.vue'),
    },
    {
        path: '/404',
        name: 'not-found',
        component: () => import('@/views/frame/error/404.vue'),
        hidden: true
    }
]

export function addError() {
    router.addRoute({path: '/:pathMatch(.*)*', redirect: '/404'})
}

const router = createRouter({
    history: createWebHistory(),  // hash路由模式
    routes
})

export default router
