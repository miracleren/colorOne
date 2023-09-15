<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="12" label='角色名称' v-bind="validator.required('roleName','请输入名称')">
        <n-input v-model:value="model.roleName" placeholder="请输入名称"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="角色标识" v-bind="validator.required('roleKey','请输入角色标识')">
        <n-input v-model:value="model.roleKey" placeholder="请输入角色标识"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="排序"
                      v-bind="validator.number('roleSort','请输入小于 999 数字序号',null,999)">
        <n-input-number v-model:value="model.roleSort" placeholder="请输入排序"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="状态" v-bind="validator.selectRequired('status','请选择状态',true)">
        <select-dict type="base_status"
                     :number="true"
                     v-model:value="model.status"
                     class="input-140"></select-dict>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="菜单权限">
        <n-space vertical style="width: 100%">
          <n-switch v-model:value="cascade">
            <template #checked>
              开启级联选择
            </template>
            <template #unchecked>
              关闭级联选择
            </template>
          </n-switch>
          <n-tree
              style="height: 250px;overflow: auto;width: 100%;"
              block-line
              :cascade="cascade"
              checkable
              :data="menuData"
              v-if="isShowTree"
              :default-checked-keys="defaultCheckedKeys"
              @update:checked-keys="updateCheckedKeys"
          />
        </n-space>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="数据权限">
        <select-dict type="role_data_scope"
                     :multiple="true"
                     v-model:value="scopeList"
                     class="input-220"></select-dict>
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
import {onBeforeMount, onMounted, ref} from 'vue'
import SelectDict from '@/components/select-dict'
import validator from '@/utils/system/validator'
import {editBaseDict} from '@/api/system/dict'
import {getBaseMenuTreeSelect} from '@/api/system/menu'
import {addBaseRole, editBaseRole, getRoleMenuIds} from '@/api/system/role'


const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)

/** 菜单权限树 **/
const cascade = ref(false)
const scopeList = ref([])
const menuData = ref([])
let checkPermits = []
const defaultCheckedKeys = ref([])
//解决tree组件初始默认值无法加载问题
const isShowTree = ref(false)
const updateCheckedKeys = (v) => {
  checkPermits = v
}

/** 初始化相关数据 **/
const model = ref({})
onMounted(() => {
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)
  scopeList.value = model.value.scope ? model.value.scope.split(' or ') : []

  //加载菜单权限树数据
  getBaseMenuTreeSelect().then(res => {
    menuData.value = res.data
  })

  //初始菜单树已选值
  if (props.config.type === 'edit') {
    getRoleMenuIds(props.modelValue.roleId).then(res => {
      defaultCheckedKeys.value = res.data
      isShowTree.value = true
    })
  } else
    isShowTree.value = true
})

/** 提交数据 **/
const handlePost = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      model.value.params = {permits: checkPermits}
      model.value.scope = scopeList.value.join(' or ')
      //新增数据
      if (props.config.type === 'add') {
        addBaseRole(model.value).then(res => {
          if (res.data) {
            props.config.show = false
            props.config.success(model.value)
            window.$message.success('成功新增数据')
          }
        })
      }
      //修改数据
      else if (props.config.type === 'edit') {
        editBaseRole(model.value).then(res => {
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
