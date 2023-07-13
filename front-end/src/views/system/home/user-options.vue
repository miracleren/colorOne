<template>
  <div class="user-options">
    <n-popover :overlap="false" placement="bottom-end" trigger="click">
      <template #trigger>
        <n-avatar size="small"
                  :style="{cursor: 'pointer',color: '#f3f3b6',backgroundColor: '#4CAF50'}">
          {{ nickName }}
        </n-avatar>
      </template>
      <div class="user-options-bar">
        <div class="n-menu ">
          <n-switch @update:value="changeTheme"/>
          <span>深色</span>
        </div>
        <div class="n-menu " @click="loginOut">
          <icon size="18"></icon>
          <span>退出</span>
        </div>
      </div>
    </n-popover>
  </div>
</template>

<script setup>
import {defineEmits, ref} from 'vue';
import {useStore} from 'vuex'
import icon from "@/components/icon/index.vue"


//更换主题
const emit = defineEmits(['aboutExeVisible'])
const changeTheme = () => {
  emit('changeTheme')
}

//获取用户信息
const store = useStore()
const nickName = ref(null)
nickName.value = store.state.nickName
console.log(store.state)

//退出登录
// const loginOut = () => {
//   console.log("loginOut")
//   window.$dialog.warning({
//     title: '警告',
//     content: '你确定退出当时登录用户吗？',
//     positiveText: '确定',
//     negativeText: '关闭',
//     onPositiveClick: () => {
//
//     },
//     onNegativeClick: () => {
//
//     }
//   })
// }
</script>

<style lang="scss" scoped>

.user-options-bar {
  .n-menu {
    &:hover {
      background-color: rgb(243, 243, 245);
    }

    display: flex;
    padding: 5px;
    align-items: center;
    cursor: pointer;

    span {
      margin: 0 5px 0 5px;
    }
  }
}

</style>
