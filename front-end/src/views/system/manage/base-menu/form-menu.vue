<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="12" label="上级菜单" v-show="props.config.type !== 'edit'">
        <n-tag type="success">
          {{ model.parentName || '无父级菜单' }}
        </n-tag>
      </n-form-item-gi>

      <n-form-item-gi v-if="model.menuType !== 'b'" :span="12" label="菜单类型"
                      v-bind="validator.selectRequired('menuType','请选择菜单类型')">
        <select-dict type="base_menu_type" v-model:value="model.menuType" class="input-220"
                     :disabled="['b']"></select-dict>
      </n-form-item-gi>

      <n-form-item-gi :span="24" label="名称"
                      v-bind="validator.required('menuName','请输入名称')">
        <n-input v-model:value="model.menuName" placeholder="请输入名称"/>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="排序" v-bind="validator.number('orderNum','请输入小于 999 数字序号',null,999)">
        <n-input-number v-model:value="model.orderNum" placeholder="请输入排序"/>
      </n-form-item-gi>

      <n-form-item-gi v-if="model.menuType !== 'b'" :span="12" label="组件路径"
                      v-bind="validator.required('component','请输入组件路径')">
        <n-input v-model:value="model.component" placeholder="请输入组件路径"/>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="地址">
        <n-input v-model:value="model.path" placeholder="请输入path"/>
      </n-form-item-gi>

      <n-form-item-gi v-if="model.menuType !== 'b'" label="菜单图标" :span="12">
        <!--        <n-input v-model:value="model.icon" placeholder="请输入菜单图标"/>-->
        <select-icon v-model:value="model.icon"></select-icon>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="状态" v-bind="validator.selectRequired('status','请选择状态',true)">
        <radio-dict type="base_status" :number="true" v-model:value="model.status"></radio-dict>
      </n-form-item-gi>

      <n-form-item-gi v-if="model.menuType !== 'b'" :span="12" label="是否显示"
                      v-bind="validator.selectRequired('visible','请选择是否显示',true)">
        <radio-dict type="base_visible" :number="true" v-model:value="model.visible"></radio-dict>
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
import RadioDict from '@/components/radio-dict'
import validator from '@/utils/system/validator'
import {editBaseDict} from '@/api/system/dict'
import {addBaseMenu, editBaseMenu} from '@/api/system/menu'
import SelectIcon from '@/components/select-icon'

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
      if (props.config.type === 'add') {
        addBaseMenu(model.value).then(res => {
          if (res.data) {
            props.config.show = false
            props.config.success(model.value)
            window.$message.success('成功新增数据')
          }
        })
      }
      //修改数据
      else if (props.config.type === 'edit') {
        editBaseMenu(model.value).then(res => {
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
