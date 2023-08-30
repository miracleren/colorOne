<template>
  <div class="tag-view">
    <n-tabs v-model:value="curTag" @update:value="selectTag">
      <n-tab name="index" tab="首页">
      </n-tab>
      <n-tab v-for="tag in tags" :name="tag.id" :tab="tag.name"
             @mouseenter="showClose(tag.id)"
             @mouseleave="tagLeave"
      >
        {{ tag.name }}
        <!--        <transition name="fade" mode="in-out" :duration="1000">-->
        <icon :color="'#9E9E9E'" v-show="tag.id === curOnTag" icon="CloseOutline" size="18"
              @click.stop="closeTag(tag.id)"></icon>
        <!--        </transition>-->
      </n-tab>
    </n-tabs>
  </div>
</template>

<script setup>

import {useStore} from 'vuex'
import {computed, ref, watch} from 'vue'
import router from '@/frame/router'
import icon from '@/components/icon/index.vue'

/*动态生成菜单*/
const store = useStore()
const tags = computed(() => store.state.tagsView.tags)

/*标签切换事件处理*/
const curTag = ref('index')
const currentTag = computed(() => store.state.tagsView.currentTag)
const selectTag = (index) => {
  console.log('selectTag', index)
  //切换标签跳转路由
  store.dispatch('tagsSetCur', index).then(tag => {
    console.log('tagsSetCur', tag)
    router.push(tag.path || '/')
  })
}

/*currentTag当前标签值，切换标签和路由*/
watch(currentTag, (n, o) => {
  console.log('currentTag', n)
  curTag.value = n.id || 'index'
  if (n.path !== o.path)
    router.push(n.path || '/')
})

/*标签关闭处理事件*/
const curOnTag = ref('')
let t
const showClose = (index) => {
  console.log('showClose', index)
  t = setTimeout(() => {
    curOnTag.value = index
  }, 1000)
}
const tagLeave = () => {
  clearTimeout(t)
  setTimeout(() => {
    curOnTag.value = ''
  }, 1000)
}
const closeTag = (index) => {
  store.dispatch('tagsClose', index)
}
</script>

<style lang="scss">
.tag-view {
  .n-tabs {
    .n-tabs-nav {
      .n-tabs-nav-scroll-wrapper {
        .v-x-scroll {
          padding-right: 20px;
        }

        .n-tabs-tab__label {
          position: relative;

          i {
            float: right;
            position: absolute;
            right: -20px;
            z-index: 9999;
          }
        }
      }
    }
  }
}
</style>
