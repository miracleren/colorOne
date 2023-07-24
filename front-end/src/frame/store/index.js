import {createStore, createLogger} from 'vuex'
import loginUser from './modules/login-user'
import routerMenu from './modules/router-menu'


const debug = process.env.NODE_ENV !== 'production'

export default createStore({
    modules: {
        loginUser,
        routerMenu
    },
    strict: debug,
    plugins: debug ? [createLogger()] : []
})
