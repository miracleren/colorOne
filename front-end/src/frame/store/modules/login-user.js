// initial state
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
        commit("userSetId", userInfo.userId)
        commit("userSetName", userInfo.userName)
        commit("userSetNickName", userInfo.nickName)
        commit("userSetToken", userInfo.token)
        //commit("userSetRoles",userInfo.roles)
    }
    // async getAllProducts({commit}) {
    //     const products = await shop.getProducts()
    //     commit('setProducts', products)
    // }
}


export default {
    state,
    getters,
    actions,
    mutations
}
