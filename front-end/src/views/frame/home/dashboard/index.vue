<template>
  <select-icon v-model:value="curIcon"></select-icon>
</template>

<script setup>
import selectIcon from '@/components/select-icon'
import {ref} from 'vue'

const curIcon = ref('AppsOutline')
</script>
