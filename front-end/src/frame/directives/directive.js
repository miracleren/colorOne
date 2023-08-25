import permit from './permit'

/**
 * 全局注册自定义指令
 * @param Vue
 */
const install = (Vue) => {
  Vue.directive('permit', permit)
}

export default {
  install
}


