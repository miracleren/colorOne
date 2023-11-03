// 全局引入naive ui 不建议
// import naive from 'naive-ui'

/**
 * 全局按需安装组件naive ui
 */
import {
    // create naive ui
    create,
    // component
    NButton, NCard, NInput, NForm, NFormItem, NIcon, NGradientText, NSpace,
    NLayout, NMenu, NSwitch, NLayoutHeader, NLayoutSider, NLayoutFooter,
    NMessageProvider, NConfigProvider, NAvatar, NPopover, NImage,
    NDialogProvider, NTabPane, NTabs, NLayoutContent, NCollapseTransition,
    NTab, NBreadcrumb, NBreadcrumbItem, NDatePicker, NSelect,
    NDataTable, NModal, NGrid, NFormItemGi, NInputNumber,
    NTag, NRadioGroup, NRadio, NTree, NTooltip,
    NTreeSelect, NGi, NPagination, NUpload, NNumberAnimation,
    NStatistic,NProgress,NTimeline,NTimelineItem,NCalendar
} from 'naive-ui'

const naive = create({
    components: [NButton, NCard, NInput, NForm, NFormItem, NIcon, NGradientText, NSpace,
        NLayout, NMenu, NSwitch, NLayoutHeader, NLayoutSider, NLayoutFooter,
        NMessageProvider, NConfigProvider, NAvatar, NPopover, NImage,
        NDialogProvider, NTabPane, NTabs, NLayoutContent, NCollapseTransition,
        NTab, NBreadcrumb, NBreadcrumbItem, NDatePicker, NSelect,
        NDataTable, NModal, NGrid, NFormItemGi, NInputNumber,
        NTag, NRadioGroup, NRadio, NTree, NTooltip,
        NTreeSelect, NGi, NPagination, NUpload, NNumberAnimation,
        NStatistic,NProgress,NTimeline,NTimelineItem,NCalendar]
})

export default naive
