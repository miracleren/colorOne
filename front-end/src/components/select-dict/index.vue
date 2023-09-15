<template>
  <n-select
      v-model:value="selectValue"
      :placeholder="props.placeholder"
      :options="options"
      :multiple="multiple"
      clearable/>
</template>

<script setup>
import {computed, ref} from 'vue'
import {getSelectDictList} from '@/api/system/dict'

const props = defineProps({
  value: [String, Number, Array],
  multiple: {type: Boolean, default: false},
  type: String,
  placeholder: String,
  disabled: Array,
  number: {type: Boolean, default: false},
})

const emit = defineEmits(['update:value'])
const selectValue = computed({
  get: () => {
    return props.value
  },
  set: (val) => {
    console.log(typeof val)
    if (val !== selectValue.value) {
      emit('update:value', val)
    }
  }
})

/** 通过字典类型生成选择下接 **/
const options = ref([])
getSelectDictList(props.type).then(res => {
  //数字类型处理
  console.log('typeof props.value ', props.number)
  if (props.number) {
    for (let item of res.data)
      item.value = parseInt(item.value)
  }

  //部分不可选配置
  if (props.disabled != null && props.disabled.length > 0) {
    for (let item of res.data) {
      if (props.disabled.includes(item.value))
        item.disabled = true
    }
  }

  options.value = res.data
})
</script>

<style lang="scss">

</style>
