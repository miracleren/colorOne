// initial state
import {removeToken, setToken} from "@/utils/system/token"

const state = () => ({
    id: '',
    name: '',
    nickName: '',
    token: '',
    roles: [],
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
    }
}

// getters
const getters = {}

// actions
const actions = {
    userLogin({commit}, userInfo) {
        setToken(userInfo.token)

        commit("userSetId", userInfo.userId)
        commit("userSetName", userInfo.userName)
        commit("userSetNickName", userInfo.nickName)
        commit("userSetToken", userInfo.token)
    },

    userLoginOut({commit}) {
        removeToken()

        commit("userSetId", '')
        commit("userSetName", '')
        commit("userSetNickName", '')
        commit("userSetToken", '')
    }

}


export default {
    state,
    getters,
    actions,
    mutations
}
