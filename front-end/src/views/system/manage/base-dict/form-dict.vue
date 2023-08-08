<template>
  <n-form
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="12" label="标签组名" v-bind="validator.required('dictLabel','请输入标签组名')">
        <n-input v-model:value="model.dictLabel" placeholder="请输入标签组名"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="类型" v-bind="validator.required('dictType','请输入类型')">
        <n-input v-model:value="model.dictType" placeholder="请输入类型"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="排序" v-bind="validator.number('dictSort','请输入小于 999 数字序号',null,999)">
        <n-input-number v-model:value="model.dictSort" placeholder="请输入排序"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="状态" v-bind="validator.required('status','请选择状态')">
        <select-dict type="base_status" v-model:value="model.status" class="input-140"></select-dict>
      </n-form-item-gi>
      <n-form-item-gi :span="24" label="备注">
        <n-input v-model:value="model.remark" placeholder="请输入备注"/>
      </n-form-item-gi>
    </n-grid>
  </n-form>

  <div class="form-action">
    <n-button type="primary" @click="handlePost">提交</n-button>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import SelectDict from '@/components/select-dict'
import validator from '@/utils/ValidatorUtils'
import {addBaseDict, editBaseDict} from '@/api/system/dict'

const props = defineProps({
  modelValue: Object,
  config: Object
})

/** 初始化相关数据 **/
const model = ref({})
onMounted(() => {
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)
})

/** 提交数据 **/
const handlePost = () => {
  //新增数据
  if (props.config.type === 'add') {
    addBaseDict(model.value).then(res => {
      if (res.data) {
        props.config.show = false
        props.config.success(model.value)
        window.$message.success('成功新增数据')
      }
    })
  }
  //修改数据
  else if (props.config.type === 'edit') {
    editBaseDict(model.value).then(res => {
      if (res.data) {
        props.config.show = false
        props.config.success(model.value)
        window.$message.success('成功修改数据')
      }
    })
  }
}
</script>

<style lang="scss" scoped>

</style>
