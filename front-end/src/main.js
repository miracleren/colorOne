import {createApp} from 'vue'
import App from './App.vue'
//路由配置
import router from '@/router/index'
//naive 组件引入
import naive from '@/store/naive-plugin'


/**
 * 样式css
 */
import '@/assets/styles/global.scss'


/**
 * 创建应用程序
 */
const app = createApp(App)
app.use(router)
    .use(naive)
    .mount('#app')


//app.config.globalProperties.$global =

