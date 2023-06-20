<template>
  <n-button v-auth-url="'base/add'" @click="click">测试添加</n-button>
  <n-button v-auth-url="'base/delete'" @click="click">测试删除</n-button>
  <n-message-provider>
    <content/>
  </n-message-provider>
</template>

<script setup>
import {useMessage} from 'naive-ui'

window.$message = useMessage()

const click = () => {
  //console.log(e.currentTarget.getAttributeNode('o-url'))
  window.$message.warning("test")
}
</script>

<style lang="scss" scoped>

</style>
