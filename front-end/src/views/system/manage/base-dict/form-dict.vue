<template>
  <edit-form>
    <template #form>
      <n-form-item-gi :span="12" label="Input">
        <n-input v-model:value="model.inputValue" placeholder="Input"/>
      </n-form-item-gi>
    </template>
    <template #action>
    </template>
  </edit-form>
</template>

<script setup>
import EditForm from '@/components/edit-form'
import {ref, watch} from 'vue'

const props = defineProps({
  modelValue: Object
})

const model = ref({})

</script>

<style lang="scss" scoped>

</style>
