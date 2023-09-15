<template>
  <n-breadcrumb separator=">" class="router-breadcrumb">
    <transition-group name="switch">
      <!-- 需要覆盖 n-breadcrumb-item 原动画效果-->
      <n-breadcrumb-item
          :clickable="false" v-for="item in breadcrumb"
          :key="item.menuId ">
        <icon :icon="item.icon" :color="'#607D8B'" size="18"></icon>
        {{ item.menuName }}
      </n-breadcrumb-item>
    </transition-group>
  </n-breadcrumb>
</template>

<script setup>

import {useStore} from 'vuex'
import {computed, ref, watch} from 'vue'
import {traverseTreePaths} from '@/utils/TreeUtils'
import icon from '@/components/icon/index.vue'

const breadcrumb = ref([])

/*动态根据当前标签对应路由生成面包屑*/
const store = useStore()
const menuTree = computed(() => store.state.routerMenu.menuTree)
const currentTag = computed(() => store.state.tagsView.currentTag)


//currentTag当前标签值
watch(currentTag, (n, o) => {
  console.log('currentTag当前标签值', n)
  if (n !== o) {
    traverseTreePaths(menuTree.value, 'menuId', n.id, (menus) => {
      console.log('traverseTreePaths', menus)
      menus = menus.reverse()
      breadcrumb.value = menus
    })
  }
})
</script>

<style lang="scss">

</style>
