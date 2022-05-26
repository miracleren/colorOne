// 全局引入naive ui 不建议
// import naive from 'naive-ui'

/**
 * 全局按需安装组件naive ui
 */
import {
    // create naive ui
    create,
    // component
    NButton,NCard,NInput,NForm,NFormItem,NIcon
} from 'naive-ui'

const naive = create({
    components: [NButton,NCard,NInput,NForm,NFormItem,NIcon]
})

export default naive
