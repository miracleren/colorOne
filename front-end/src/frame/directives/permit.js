import store from '@/frame/store'

/**
 * 通过绑定的url生成的权限代码判读是当前操作是否有权限，有展示，无则隐藏
 * 如操作地址址为 /system/user/edit  权限代则默认生成 system:user:edit
 * @returns
 */
const permit = {
    mounted(el, binding) {
        console.log("v-permit")
        // value 获取用户使用自定义指令绑定的内容
        const {value} = binding
        // 用户所有的权限标识
        const permission = store._state.data.loginUser.permits
        //超级管理员越过权限
        if (permission.includes('*:*:*'))
            return
        // 判断用户使用自定义指令，是否使用正确了
        if (!!value && value.length > 0) {
            // 判断传递进来的按钮权限，用户是否拥有
            const hasPermission = value.some((code) => {
                return permission.includes(code)
            })
            // 当用户没有这个按钮权限时，设置隐藏这个按钮
            if (!hasPermission) {
                el.parentNode && el.parentNode.removeChild(el)
            }
        } else {
            console.error(`权限指令格式出错，使用方法如下： v-permit="['system:user:edit','system:user:delete']"`)
        }
    }
}

export default permit

