<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="12" :label="props.config.type==='addChildren'? '字典名称':'字典分组名称'"
                      v-bind="validator.required('dictLabel','请输入名称')">
        <n-input v-model:value="model.dictLabel" placeholder="请输入名称"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="类型" v-bind="validator.required('dictType','请输入类型')">
        <n-input v-model:value="model.dictType" placeholder="请输入类型" :disabled="props.config.type==='addChildren'"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="排序" v-bind="validator.number('dictSort','请输入小于 999 数字序号',null,999)">
        <n-input-number v-model:value="model.dictSort" placeholder="请输入排序"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="状态" v-bind="validator.selectRequired('status','请选择状态',true)">
        <select-dict type="base_status"
                     :number="true"
                     v-model:value="model.status"
                     class="input-140"></select-dict>
      </n-form-item-gi>
      <n-form-item-gi label="字典键值" v-if="props.config.type==='addChildren' || (props.config.type==='edit' && !!model.dictValue)" :span="12"
                      v-bind="validator.required('dictValue','请输入字典键值')">
        <n-input v-model:value="model.dictValue" placeholder="请输入字典键值"/>
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
import validator from '@/utils/system/validator'
import {addBaseDict, editBaseDict} from '@/api/system/dict'


const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)

/** 初始化相关数据 **/
const model = ref({})
onMounted(() => {
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)
  console.log(model.value)
})

/** 提交数据 **/
const handlePost = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      //新增数据
      if (props.config.type === 'add' || props.config.type === 'addChildren') {
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
  })
}
</script>

<style lang="scss" scoped>

</style>
