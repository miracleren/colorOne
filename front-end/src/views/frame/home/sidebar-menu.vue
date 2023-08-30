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
        @update:value="selectMenu"
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
import router from '@/frame/router'

//region 菜单相关
const menuOptions = ref([])
const renderIcon = (icon) => {
  return () => h(CIcon, {icon: icon, color: 'rgb(14, 122, 13)'})
}

/*构建初始化菜单树、初始化tags标签、权限数据*/
const store = useStore()
const menuTree = computed(() => store.state.routerMenu.menuTree)
let menus = deepClone(menuTree.value)
traverseTree(menus, (node) => {
  console.log(node)
  if (node.menuType === 'b') {
    console.log('node.menuType === \'b\'')
    node = {}
    return
  }
  node.icon = renderIcon(node.icon)
  //菜单按钮,通过RouterLink切换路由
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
  node.show = !node.visible

  //初始化当前路由页面对应标签
  let curPath = router.currentRoute.value.fullPath
  if (curPath !== '/' && curPath === node.path) {
    let tag = {
      id: node.menuId,
      name: node.menuName,
      path: node.path
    }
    store.dispatch('tagsAdd', tag)
    store.dispatch('tagsSetCur', tag.id)
  }

})
menuOptions.value = menus
console.log('menuOptions.value', menuOptions.value)

/*菜单选定触发事件*/
const selectMenu = (key, item) => {
  let tag = {
    id: item.menuId,
    name: item.menuName,
    path: item.path
  }
  console.log('selectMenu', tag)
  store.dispatch('tagsAdd', tag)
}
</script>

<style lang="scss">

</style>
