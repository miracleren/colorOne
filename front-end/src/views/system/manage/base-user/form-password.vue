<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="14" label="密码" v-bind="validator.password('password')">
        <n-input type="password" v-model:value="model.password" placeholder="请输入用户新密码"/>
      </n-form-item-gi>
      <n-form-item-gi :span="10" label="">
        <n-tooltip placement="bottom">
          <template #trigger>
            <n-button secondary type="info" @click="buildPassword"> 点击生成随机密码</n-button>
          </template>
          <span> {{ model.password || '你没有点击我呢' }} </span>
        </n-tooltip>
      </n-form-item-gi>
    </n-grid>

  </n-form>

  <div class="form-action">
    <n-button type="primary" @click="handlePost">重置</n-button>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import validator from '@/utils/system/validator'
import {resetUserPassword} from '@/api/system/user'
import {randomPassword} from '@/utils/RandomUtils'


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
      //重置密码
      if (props.config.type === 'reset') {
        resetUserPassword(model.value).then(res => {
          if (res.data) {
            props.config.show = false
            window.$message.success('成功重置当前用户密码：' + model.value.password)
          }
        })
      }
    }
  })
}

/** 动态生成随机密码 **/
const buildPassword = () => {
  model.value.password = randomPassword()
}
</script>

<style lang="scss" scoped>

</style>
