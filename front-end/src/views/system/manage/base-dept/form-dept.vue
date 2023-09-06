<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="12" label="上级部门" v-show="props.config.type !== 'edit'">
        <n-tag type="success">
          {{ model.parentName || '无父级部门' }}
        </n-tag>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="部门名称"
                      v-bind="validator.required('deptName','请输入部门名称')">
        <n-input v-model:value="model.deptName" placeholder="请输入部门名称"/>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="负责人">
        <n-input v-model:value="model.leader" placeholder="请输入部门负责人"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="联系方式">
        <n-input v-model:value="model.contact" placeholder="请输入部门负责人联系方式"/>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="排序" v-bind="validator.number('deptSort','请输入小于 999 数字序号',null,999)">
        <n-input-number v-model:value="model.deptSort" placeholder="请输入排序"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="状态" v-bind="validator.selectRequired('status','请选择状态')"
                      v-if="props.config.type === 'add'">
        <select-dict type="base_status"
                     v-model:value="model.status"
                     class="input-140"></select-dict>
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
import {addBaseDept, editBaseDept} from '@/api/system/dept'


const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)

/** 初始化相关数据 **/
const model = ref({})
onMounted(() => {
  console.log(props.modelValue)
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)
})

/** 提交数据 **/
const handlePost = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      //新增数据
      if (props.config.type === 'add') {
        addBaseDept(model.value).then(res => {
          if (res.data) {
            props.config.show = false
            props.config.success(model.value)
            window.$message.success('成功新增数据')
          }
        })
      }
      //修改数据
      else if (props.config.type === 'edit') {
        editBaseDept(model.value).then(res => {
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
