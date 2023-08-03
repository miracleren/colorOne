<template>
  <n-select v-model:value="props.modelValue" :placeholder="props.placeholder" :options="options" clearable/>
</template>

<script setup>
import {ref } from 'vue'
import {getSelectDictList} from '@/api/system/dict'

const props = defineProps({
  modelValue: String,
  type: String,
  placeholder: String
})

/** 通过字典类型生成选择下接 **/
const options = ref([])
getSelectDictList(props.type).then(res => {
  options.value = res.data
})
</script>

<style lang="scss">

</style>
