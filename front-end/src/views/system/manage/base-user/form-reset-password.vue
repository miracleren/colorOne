<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-form-item label="当前登录密码" v-bind="validator.required('password')">
      <n-input
          v-model:value="model.password"
          type="password"
          show-password-on="mousedown"
          placeholder="旧密码"
          :maxlength="18"
      />
    </n-form-item>
    <n-form-item label="新密码" v-bind="validator.password('newPassword')">
      <n-input
          v-model:value="model.newPassword"
          type="password"
          show-password-on="mousedown"
          placeholder="新密码"
          :maxlength="18"
      />
    </n-form-item>
    <n-form-item label="重复新密码" v-bind="validator.isSame('newPassword2',model.newPassword)">
      <n-input
          v-model:value="model.newPassword2"
          type="password"
          show-password-on="mousedown"
          placeholder="重新输入新密码，需跟上面新密码一致"
          :maxlength="18"
      />
    </n-form-item>

  </n-form>

  <div class="form-action">
    <n-button type="primary" @click="handlePost">重置</n-button>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import validator from '@/utils/system/validator'
import {resetPasswordBySelf} from '@/api/system/user'

/* 初始参数 */
const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)


const model = ref({})
/** 提交数据 **/
const handlePost = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      //重置密码
      resetPasswordBySelf(model.value).then(res => {
        if (res.data) {
          props.config.show = false
          window.$message.success('成功重置当前登录用户密码：' + model.value.newPassword)
        }
      })
    }
  })
}
</script>

<style lang="scss" scoped>

</style>
