import {createRouter, createWebHistory} from 'vue-router'

/**
 * Note: 路由配置项
 *
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 */

// 公共静态路由配置
const routes = [

    {
        path: '/login',
        component: () => import('../views/system/login'),
    },
    {
        //基础父路由
        path: '/',
        name: 'bas-layout',
        component: () => import('../views/system/home'),
        children: []
        // children: [
        //     {
        //         path: '/system/user',
        //         component: () => import('../views/system/management/bas-user'),
        //     },
        //     {
        //         path: '/system/menu',
        //         component: () => import('../views/system/management/bas-menu'),
        //     }
        // ]
    }

// {
//   path: '/404',
//   component: (resolve) => require(['@/views/error/404'], resolve),
//   hidden: true
// },
// {
//   path: '/401',
//   component: (resolve) => require(['@/views/error/401'], resolve),
//   hidden: true
// }
]

const router = createRouter({
    history: createWebHistory(),  // hash路由模式
    routes
})

export default router
