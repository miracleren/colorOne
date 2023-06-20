import {createRouter, createWebHistory} from 'vue-router';

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边菜单显示
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    title: 'title'               // 设置路由显示名称
    icon: 'svg-name'             // 设置路由图标
  }
 */

// 公共路由配置
const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        component: () => import('../views/system/login'),
        hidden: true
    },
    {
        path: '/home',
        component: () => import('../views/system/home'),
        hidden: true
    },
    {
        path: '/test',
        component: () => import('../views/system/test'),
        hidden: true
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
});

export default router;
