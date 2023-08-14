<template>
  <n-select
      v-model:value="selectValue"
      :placeholder="props.placeholder"
      :options="options"

      clearable/>
  <!--    @update:value="changeValue"    -->
</template>

<script setup>
import {computed, ref, watch} from 'vue'
import {getSelectDictList} from '@/api/system/dict'


const props = defineProps({
  modelValue: String,
  type: String,
  placeholder: String,
  valueType: String
})

const emit = defineEmits(['update:value'])
const selectValue = computed({
  get: () => {
    return props.modelValue
  },
  set: (val) => {
    if (val !== selectValue.value)
      emit('update:value', val)
  }
})

/** 监听父值得变化 **/
//const selectValue = ref('1')
// watch(() => props.modelValue, (n, o) => {
//   console.log('props.modelValue', props.modelValue)
//   console.log('props.modelValue', selectValue.value)
//   if (n !== o) {
//     //selectValue.value = props.modelValue
//   }
// })

// /** 更新值到父 **/
// const emit = defineEmits(['update:value'])
// const changeValue = (value, option) => {
//   //emit('update:value', value !== null ? (props.valueType === 'number' ? parseInt(value) : value) : null)
// }

/** 通过字典类型生成选择下接 **/
const options = ref([])
getSelectDictList(props.type).then(res => {
  if (props.valueType === 'number')
    for (let item of res.data)
      item.value = parseInt(item.value)

  options.value = res.data
  console.log(options.value)
})
</script>

<style lang="scss">

</style>
