import {createStore, createLogger} from 'vuex'
import loginUser from './modules/login-user'


const debug = process.env.NODE_ENV !== 'production'

export default createStore({
    modules: {
        loginUser
    },
    strict: debug,
    plugins: debug ? [createLogger()] : []
})
