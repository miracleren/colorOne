<template>
  <n-radio-group v-model:value="selectValue" name="radiogroup">
    <n-space>
      <n-radio v-for="song in options" :key="song.value" :value="song.value">
        {{ song.label }}
      </n-radio>
    </n-space>
  </n-radio-group>
</template>

<script setup>
import {computed, ref} from 'vue'
import {getSelectDictList} from '@/api/system/dict'


const props = defineProps({
  value: [String, Number],
  type: String,
  number: {type: Boolean, default: false},
})

const emit = defineEmits(['update:value'])
const selectValue = computed({
  get: () => {
    return props.value
  },
  set: (val) => {
    console.log(typeof val)
    if (val !== selectValue.value)
      emit('update:value', val)
  }
})

/** 通过字典类型生成选择项 **/
const options = ref([])
getSelectDictList(props.type).then(res => {
  if (props.number)
    for (let item of res.data)
      item.value = parseInt(item.value)

  options.value = res.data
})
</script>

<style lang="scss">

</style>
