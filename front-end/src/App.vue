<template>
  <div id="app">
    <!--全局提示-->
    <n-message-provider>
      <message-api/>
    </n-message-provider>

    <router-view/>
  </div>
</template>

<script setup>
import messageApi from "@/components/message-api"
</script>

<style>
#app {
  height: 100%;
  overflow: auto;
}
</style>
