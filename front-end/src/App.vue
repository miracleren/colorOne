<template>
  <div id="app-body">
    <!--全局提示-->
    <n-message-provider>
      <message-api/>
    </n-message-provider>
    <n-dialog-provider>
      <dialog-api/>
    </n-dialog-provider>


    <router-view/>
  </div>
</template>

<script setup>
import messageApi from '@/components/message-api'
import dialogApi from '@/components/dialog-api'
</script>

<style>
#app {
  height: 100%;
  overflow: auto;
}

#app-body {
  height: 100%;
}

.n-message-container {
  z-index: 9999;
}

.n-dialog-container {
  z-index: 9999;
}
</style>
