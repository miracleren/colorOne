import authUrl from './auth-url'

/**
 * 全局注册自定义指令
 * @param Vue
 */
const install = (Vue) => {
  Vue.directive('auth-url', authUrl)
}

export default {
  install
}


