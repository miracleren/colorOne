/**
 * 用户多标签管理
 */

const state = () => ({
    tags: [],
    currentTag: {}
})

// mutations
const mutations = {
    tagsAdd(state, tag) {
        //重复tag不添加
        for (let t of state.tags) {
            if (t.id === tag.id)
                return
        }
        state.tags.push(tag)
    },
    tagsDelete(state, indexTags) {
        state.tags.splice(indexTags, 1)
    },
    currentTagSet(state, tag) {
        state.currentTag = tag
    }
}

// getters
const getters = {}

// actions
const actions = {
    tagsAdd({commit}, tag) {
        return new Promise((resolve, reject) => {
            commit('tagsAdd', tag)
            commit('currentTagSet', tag)
            resolve(tag)
        })
    },
    tagsClose({state, commit}, index) {
        return new Promise((resolve, reject) => {
            //关闭当路打开的路由,跳转到前面的tag
            let indexTags = state.tags.findIndex((item) => item.id === index)
            if (indexTags !== -1) {
                if (index === state.currentTag.id) {
                    commit('currentTagSet', indexTags === 0 ? {} : state.tags[indexTags - 1])
                    console.log('lastTag', indexTags === 0 ? {} : state.tags[indexTags - 1])
                }
                commit('tagsDelete', indexTags)
            }

            resolve(index)
        })
    },
    tagsSetCur({state, commit}, index) {
        return new Promise((resolve, reject) => {
            let tagList = state.tags.filter(item => item.id === index)
            let tag = tagList.length >= 1 ? tagList[0] : {}
            commit('currentTagSet', tag)
            resolve(tag)
        })
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
