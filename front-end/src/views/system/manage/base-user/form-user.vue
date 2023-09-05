<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="24" label="部门" v-bind="validator.selectRequired('deptId','请选择部门',true)">
        <n-tree-select
            :options="deptsOptions"
            :default-value="model.deptId"
            label-field="deptName"
            key-field="deptId"
            children-field="children"
            v-model:value="model.deptId"
        />
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="用户账号"
                      v-bind="validator.required('userName','请输入用户账号')">
        <n-input v-model:value="model.userName" placeholder="请输入用户账号" :disabled="props.config.type!=='add'"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="密码" v-bind="validator.password('password')" v-if="props.config.type==='add'">
        <n-input type="password" v-model:value="model.password" placeholder="请输入用户密码"/>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="用户昵称" v-bind="validator.required('nickName','请输入用户昵称')">
        <n-input v-model:value="model.nickName" placeholder="请输入用户昵称"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="邮箱" v-bind="validator.email('email')">
        <n-input v-model:value="model.email" placeholder="请输入用户邮箱"/>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="手机号码" v-bind="validator.phone('phone')">
        <n-input v-model:value="model.phone" placeholder="请输入手机号码"/>
      </n-form-item-gi>
      <n-form-item-gi :span="12" label="性别">
        <select-dict type="base_user_sex"
                     v-model:value="model.sex"
                     class="input-180"></select-dict>
      </n-form-item-gi>

      <n-form-item-gi :span="12" label="角色">
        <n-select
            v-model:value="model.roles"
            placeholder="请输选择用户角色"
            :options="rolesOptions"
            style="width: 220px;"
            multiple
            clearable/>
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
import {addBaseUser, editBaseUser} from '@/api/system/user'
import {getSelectRoleList, getUserRoles} from '@/api/system/role'
import {getBaseDeptTreeList} from '@/api/system/dept'


const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)
const rolesOptions = ref([])        //用户角色选择数据
const deptsOptions = ref([])      //部门选择数据

/** 初始化相关数据 **/
const model = ref({})
onMounted(() => {
  console.log(props.modelValue)
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)


  //加载角色选择列表
  getSelectRoleList().then(res => {
    rolesOptions.value = res.data
    if (props.config.type === 'edit') {
      getUserRoles(model.value.userId).then(u => {
        model.value.roles = u.data
      })
    }
  })

  getBaseDeptTreeList({status: 0}).then(d => {
    deptsOptions.value = d.data
  })


})

/** 提交数据 **/
const handlePost = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      //新增数据
      if (props.config.type === 'add') {
        addBaseUser(model.value).then(res => {
          if (res.data) {
            props.config.show = false
            props.config.success(model.value)
            window.$message.success('成功新增数据')
          }
          //window.$message.error(res.msg)
        })
      }
      //修改数据
      else if (props.config.type === 'edit') {
        editBaseUser(model.value).then(res => {
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
