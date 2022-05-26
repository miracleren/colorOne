import {createApp} from 'vue'
import App from './App.vue'
//路由配置
import router from '@/router/index'
//naive 按需全局引入组件
import naive from '@/store/naive-plugin'
//icons svg 图标引入
import '@/assets/icons';

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
    .mount('#app')



