import {createApp} from 'vue'
import App from './App.vue'
import store from '@/frame/store'

//路由配置
import router from '@/frame/router/index'
//naive 按需全局引入组件
import naive from '@/frame/naive-plugin'
//icons svg 图标引入
import '@/assets/icons';
//自定义指令
import directive from '@/frame/directives/directive'
//全局路由权限守卫
import '@/frame/auth-permission'

/**
 * 样式css
 */
//全局样式
import '@/assets/styles/global.scss'


/**
 * 创建应用程序
 */
const app = createApp(App)
app.use(router)
    .use(naive)
    .use(directive)
    .use(store)
    .mount('#app')




