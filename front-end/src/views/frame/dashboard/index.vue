<template>
  还在研究
  <button @click="toggle">Toggle</button>
  <transition name="fade">
    <h1 v-show="show">显示测试</h1>
  </transition>

</template>

<script setup>
import {ref} from 'vue'

const show = ref(false)

const toggle = () => {

  show.value = !show.value
}
</script>

<style lang="scss">
.fade-enter, .fade-leave-to {
  opacity: 0;
}

.fade-enter-to, .fade-leave {
  opacity: 1;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 1.5s
}

</style>
