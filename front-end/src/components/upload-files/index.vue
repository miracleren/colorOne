<template>
  <div class="upload-box">
    <n-upload
        action="/api/pool/file/upload"
        v-model:file-list="fileList"
        @before-upload="beforeUpload"
        :headers="{'Authorization': 'Bearer '+ getToken()}"
        :data="{'refName': props.files.name,'refId': props.files.id}"
        @finish="finishUpload"
        :on-remove="removeFile"
    >
      <n-button :disabled="!props.files.name">上传文件</n-button>
    </n-upload>
  </div>
</template>

<script setup>
/**
 * 附件上传组件
 *
 * 附加加载：该附件上传附件统一记录在pool记录表，通过关联名称、初始化记录编号，自动加载附件列表到业务模块。
 * 附件上传逻辑：附件在新增模式时，没有初始化记录编号，会生成一个临时唯一编号，文件是实时上传到服务器，并关联临时编号。
 *    待数据保存后，组件会返回临时编号到后去吧，记录入库后再更新记录的临时编号为记录号，即绑定到记录上。
 *    如附件在编辑模式，此时记录有编号，附件即时上传就会自动绑定到记录上。
 */
import {getToken} from '@/utils/system/token'
import {ref, watch} from 'vue'
import {deleteBaseFilePool, getBaseFilePoolList} from '@/api/system/file-pool'
import {randomGuid} from '@/utils/RandomUtils'

/** 配置属性 **/
const props = defineProps({
  files: {
    type: Object, default: () => ({
      name: '',
      id: ''
    })
  }
})

/** 上传文件格式控制 **/
const baseFileUrl = '/api/pool/file/download/{fileId}?t=' + getToken()
const fileTypeList = ['image/png']
const fileSize = 100      //大小单位KB
const fileLink = {}       //组件文件id无法修改，通过对象关联
const fileList = ref([])

watch(() => props.files.id, (newVal, oldVal) => {
  if (!newVal) {
    //无关联id为新增附件
    props.files.id = randomGuid('files-', 4)
  } else {
    //有关联id为修改，加载已有附件
    getData()
  }
})


/** 加载关联的附件 **/
const getData = () => {
  let query = {refName: props.files.name, refId: props.files.id}
  getBaseFilePoolList(query).then(res => {
    let list = res.data
    list.forEach(item => {
      fileLink[item.id] = item.id
      item.status = 'finished'
      item.url = baseFileUrl.replace('{fileId}', item.id)
    })
    fileList.value = res.data
    console.log(fileList.value)
  })
}

/** 文件上传检测 **/
const beforeUpload = (data) => {
  //console.log(data.file)
  if (fileTypeList.indexOf(data.file.file?.type)) {
    window.$message.error('不能上传当前格式的文件，请重新选择上传！')
    return false
  }
  if (data.file.file?.size / 1024 > fileSize) {
    window.$message.error('当前上传的文件大小超过' + fileSize + 'KB，请重新选择上传或压缩后再上传！')
    return false
  }

  if (!props.refName)
    props.refName = ''
  return true
}

/** 文件上传完成回调函数 **/
const finishUpload = ({file, event}) => {
  let res = JSON.parse((event?.target).response)
  if (!!res.data) {
    fileLink[file.id] = res.data
    console.log(fileLink)
    file.url = baseFileUrl.replace('{fileId}', res.data)
  } else {
    file.status = 'error'
    window.$message.error('附件上传失败：' + res.msg)
  }

  return file
}

/** 文件删除操作 **/
const removeFile = (file) => {
  let fileId = fileLink[file.file.id]
  deleteBaseFilePool(fileId).then(res => {
    if (res.data) {
      window.$message.success('成功删除附件' + file.file.name)
    }
  })
}
</script>

<style lang="scss">
.upload-box {
  width: 100%;
}
</style>
