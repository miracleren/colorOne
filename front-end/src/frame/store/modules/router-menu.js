/**
 * 用户角色菜单、权限及路由
 */
import {getUserMenuTree} from '@/api/system/menu'
import {traverseTree} from '@/utils/TreeUtils'

const state = () => ({
    routes: [],
    menuTree: []
})

// mutations
const mutations = {
    routesSet(state, routes) {
        state.routes = routes
    },
    menuTreeSet(state, menus) {
        state.menuTree = menus
    }
}

// getters
const getters = {}

// actions
const actions = {
    routerGenerate({commit}) {
        return new Promise((resolve, reject) => {
            //获取用户角色菜单树
            getUserMenuTree().then(res => {
                let menuTree = res.data
                commit('menuTreeSet', menuTree)

                let addRoutes = []
                traverseTree(menuTree, (node) => {
                    //遍历树生成配置，把动太路由默认加载到home下
                    if (node.menuType === 'm') {
                        //生成路由列表
                        let route = {
                            path: node.path,
                            name: node.menuName,
                            component: () => import(`@/views${node.component}`),
                        }
                        addRoutes.push(route)
                    }

                })
                commit('routesSet', addRoutes)
                resolve(addRoutes)

            }).catch(() => {
                reject()
            })
        })

    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
