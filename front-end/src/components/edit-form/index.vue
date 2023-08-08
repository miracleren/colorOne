<template>
  <n-modal v-model:show="props.config.show" preset="card" :title="props.config.title" class="edit-from">
    <n-form
        v-model="props.modelValue"
        label-placement="top"
        :rules="rules"
    >
      <slot name="form"></slot>
    </n-form>

    <template #footer>
      <n-button type="success">提交</n-button>
      <n-button type="warning" @click="closeForm">关闭</n-button>
      <slot name="action"></slot>
    </template>
  </n-modal>
</template>

<script setup>
import {onMounted} from 'vue'

const props = defineProps({
  modelValue: Object,
  config: Object
})

const model = props.modelValue
/** 初始化相关数据 **/
onMounted(() => {
  console.log('edit form mounted')
})

const closeForm = () => {
  props.config.show = false
}

const rules = {
  fieldA: {
    required: true,
    validator(rule, value) {
      // 自定义验证  规则
      let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (!value) {
        return new Error('请输入身份证号')
      } else if (!reg.test(value)) {
        return new Error('身份证格式有误')
      }
      return true
    },
    trigger: ['blur', 'input']
  },
  fieldB: {
    required: true,
    min: 2,
    message: '最短长度为 2',
    trigger: 'blur'
  }
}

const inputValidationStatus = () => {

}
</script>

<style lang="scss" scoped>

</style>
