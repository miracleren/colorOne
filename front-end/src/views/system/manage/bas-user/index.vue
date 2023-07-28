<template>
  用户列表{{ count}}<br/>
  <n-button type="primary" @click="()=>{count=count+1}">
    primary
  </n-button>
</template>

<script setup>
import {ref} from 'vue'

console.log('用户列表')
const count = ref(0)

</script>
