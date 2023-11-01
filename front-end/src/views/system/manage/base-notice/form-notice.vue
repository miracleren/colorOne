<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="24" label='公告标题' v-bind="validator.required('noticeTitle','请输入公告标题')">
        <n-input v-model:value="model.noticeTitle" placeholder="请输入公告标题"/>
      </n-form-item-gi>

      <n-form-item-gi :span="16" label="有效日期（留空默认不过期）">
        <n-date-picker v-model:value="rangeDate"
                       update-value-on-close type="daterange"
                       clearable
                       class="input-360"/>
      </n-form-item-gi>

      <n-form-item-gi :span="8" label="公告类型" v-bind="validator.selectRequired('noticeType','请选择公告类型')">
        <select-dict type="base_notice_type"
                     v-model:value="model.noticeType"
                     class="input-140" placeholder="请选择公告类型">
        </select-dict>
      </n-form-item-gi>

      <n-form-item-gi :span="24" label="公告内容">
        <n-input v-model:value="model.noticeContent" type="textarea" maxlength="800" show-count/>
      </n-form-item-gi>

      <n-form-item-gi :span="24">
        <upload-files title="公告附件管理"
                      :readonly="props.config.type === 'view'"
                      v-model:files="uploadInfo"
                      ref="fileCom"></upload-files>
      </n-form-item-gi>
    </n-grid>

  </n-form>

  <div class="form-action">
    <n-button type="primary" @click="handlePost"
              v-if="props.config.type !== 'view'">提交
    </n-button>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import SelectDict from '@/components/select-dict'
import validator from '@/utils/system/validator'
import {addBaseNotice, editBaseNotice} from '@/api/system/notice'
import UploadFiles from '@/components/upload-files'


const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)
/** 附件管理 */
const uploadInfo = ref({
  name: 'base_notice',
  id: null,
  classDict: 'base_notice_files'
})

const fileCom = ref(null)
/** 初始化相关数据 **/
const model = ref({})
const rangeDate = ref(null)
onMounted(() => {
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)
  uploadInfo.value.id = model.value.noticeId

  if (!!model.value.startDate && !!model.value.endDate) {
    rangeDate.value = [new Date(model.value.startDate), new Date(model.value.endDate)]
  }
})

/** 提交数据 **/
const handlePost = () => {
  formRef.value?.validate((errors) => {
    if (!!rangeDate.value) {
      model.value.startDate = rangeDate.value[0]
      model.value.endDate = rangeDate.value[1]
    }

    if (!errors) {
      //新增数据
      if (props.config.type === 'add') {
        //保存更新添加的附件
        model.value.params = {fileRefId: uploadInfo.value.id}
        addBaseNotice(model.value).then(res => {
          if (res.data) {
            props.config.show = false
            props.config.success(model.value)
            window.$message.success('成功新增数据')
          }
        })
      }
      //修改数据
      else if (props.config.type === 'edit') {
        editBaseNotice(model.value).then(res => {
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
