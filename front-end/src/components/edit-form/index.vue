<template>
  <n-form
      :model="props.modelValue"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <slot name="form"></slot>
    </n-grid>
  </n-form>
  <slot name="action"></slot>
</template>

<script setup>
import {ref, watch} from 'vue'

/**props 属值**/
const props = defineProps({
  modelValue: Object
})

</script>

<style lang="scss" scoped>

</style>
