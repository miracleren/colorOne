/**
 * 组件通用配置，减少代码重复，统一配置结构
 */
import {ref} from 'vue'

/**
 * 分页管理通用配置属性
 * @type {Ref<UnwrapRef<{pageSizes: number[], onChange: onChange, prefix({itemCount: *}): string, pageSize: number, page: number, showSizePicker: boolean, onUpdatePageSize: onUpdatePageSize, itemCount: number}>>}
 */
export const paginationOption = (change) => {
    console.log('paginationOption')
    const config = ref({
        page: 1,
        pageSize: 15,
        itemCount: 0,
        showSizePicker: true,
        pageSizes: [10, 15, 20],
        prefix({itemCount}) {
            return `共计 ${itemCount} 条`
        },
        onChange: (page) => {
            config.value.page = page
            if (typeof (change) == 'function')
                change()
        },
        onUpdatePageSize: (pageSize) => {
            config.value.pageSize = pageSize
            config.value.page = 1
            if (typeof (change) == 'function')
                change()
        }
    })
    return config
}


let pluginConfig = {
    paginationOption
}
export default pluginConfig
