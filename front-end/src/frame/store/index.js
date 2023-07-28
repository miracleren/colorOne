import {createStore, createLogger} from 'vuex'
import loginUser from '@/frame/store/modules/login-user'
import routerMenu from '@/frame/store/modules/router-menu'
import tagsView from '@/frame/store/modules/tags-view'


const debug = process.env.NODE_ENV !== 'production'

export default createStore({
    modules: {
        loginUser,
        routerMenu,
        tagsView
    },
    strict: debug,
    plugins: debug ? [createLogger()] : []
})
