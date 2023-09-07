<template>
  还在研究
  <button @click="toggle">Toggle</button>
  <transition name="switch">
    <span :key="data[3]">{{ '<' + data[3] }}</span>
  </transition>
</template>

<script setup>
import {ref} from 'vue'

const data = ref(['AAA', 'BBB', 'CCC'])
const toggle = () => {
  data.value[3] = Math.random()
}
</script>

<style lang="scss">


</style>
