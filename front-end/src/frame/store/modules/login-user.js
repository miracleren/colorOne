/**
 * 用户登录信息
 */
import {removeToken, setToken} from '@/utils/system/token'
import {loginUser, loginUserInfo} from '@/api/system/login'

const state = () => ({
    id: '',
    name: '',
    nickName: '',
    token: '',
    roles: [],
    permits: []
})

// mutations
const mutations = {
    userSetId(state, id) {
        state.id = id
    },
    userSetName(state, name) {
        state.name = name
    },
    userSetNickName(state, nickName) {
        state.nickName = nickName
    },
    userSetToken(state, token) {
        state.token = token
    },
    userSetRoles(state, roles) {
        state.roles = roles
    },
    userSetPermits(state, permits) {
        state.permits = permits
    }
}

// getters
const getters = {}

// actions
const actions = {
    userLogin({commit}, loginInfo) {
        return new Promise((resolve, reject) => {
            loginUser(loginInfo).then(res => {
                let token = res.data
                if (token) {
                    setToken(token)
                    commit('userSetToken', token)
                    console.log('handleLogin-resolve')
                    resolve()
                }
            }).catch(() => {
                reject()
            })
        })

    },
    userInfoSet({commit}) {
        return new Promise((resolve, reject) => {
            loginUserInfo().then(res => {
                console.log('loginUserInfo', res)
                let user = res.data.user
                commit('userSetId', user.userId)
                commit('userSetName', user.userName)
                commit('userSetNickName', user.nickName)
                commit('userSetRoles', res.data.roles)
                commit('userSetPermits', res.data.permits)
                resolve()
            }).catch(() => {
                reject()
            })
        })
    },
    userLoginOut({commit}) {
        removeToken()

        commit('userSetId', '')
        commit('userSetName', '')
        commit('userSetNickName', '')
        commit('userSetToken', '')
    }

}


export default {
    state,
    getters,
    actions,
    mutations
}
