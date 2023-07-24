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
import {traverseTree} from '@/utils/TreeUtils'
import {computed, h, ref} from 'vue'
import CIcon from '@/components/icon/index.vue'
import {RouterLink} from 'vue-router'
import {useStore} from 'vuex'
import {deepClone} from '@/utils/ObjectUtils'

//region 菜单相关
const menuOptions = ref([])
const renderIcon = (icon) => {
  return () => h(CIcon, {icon: icon, color: 'rgb(14, 122, 13)'})
}

//构建菜单树
const store = useStore()
const menuTree = computed(() => store.state.routerMenu.menuTree)
let menus = deepClone(menuTree.value)
traverseTree(menus, (node) => {
  console.log(node)
  node.icon = renderIcon(node.icon)
  //菜单按钮
  if (node.menuType === 'm') {
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
  } else
    node.label = node.menuName
})
menuOptions.value = menus
</script>

<style lang="scss">

</style>
