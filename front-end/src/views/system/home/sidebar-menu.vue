<template>
  <n-layout-sider
      bordered
      show-trigger
      collapse-mode="width"
      :collapsed-width="64"
      :width="200"
      :native-scrollbar="false"
  >
    <n-menu
        accordion
        :collapsed-width="64"
        :collapsed-icon-size="22"
        :options="menuOptions"
        key-field="menuId"
    />
  </n-layout-sider>
</template>

<script setup>
import {traverseTree} from "@/utils/TreeUtils"
import {getUserMenuTree} from "@/api/system/menu"
import {h, ref} from "vue"
import CIcon from '@/components/icon/index.vue'
import {RouterLink} from 'vue-router'

//region 菜单相关
const menuOptions = ref([])
const renderIcon = (icon) => {
  return () => h(CIcon, {icon: icon, color: 'rgb(14, 122, 13)'})
}

//获取菜单树
getUserMenuTree().then(res => {
  let menuTree = res.data

  //遍历树生成配置
  traverseTree(menuTree, (node) => {
    node.icon = renderIcon(node.icon)
    console.log(node)
    if (node.menuType === 'c')
      node.label = node.menuName
    else
      node.label = () =>
          h(
              RouterLink,
              {
                to: {
                  path: node.path
                }
              },
              {default: () => node.menuName}
          )
  })
  console.log("getUserMenuTree", menuTree)
  //渲染菜单
  menuOptions.value = menuTree
})
//endregion
</script>

<style lang="scss">

</style>
