/**
 * 通过绑定的url判读是当前操作是否有权限，有陈姐展示，无则隐藏
 * @param {*} key
 * @returns
 */

function checkPermissionUrl(key) {
    let arr = ["base/add", "base/update"]
    return arr.indexOf(key) > -1
}

const authUrl = {
    mounted(el, binding) {
        // 获取到对应的值
        let url = binding.value
        if (url) {
            let hasPermission = checkPermissionUrl(url)
            if (!hasPermission) {
                // 没有权限 移除Dom元素
                el.parentNode && el.parentNode.removeChild(el)
            }
        }
    }
}

export default authUrl

