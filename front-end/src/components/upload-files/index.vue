<template>
  <n-card :title="props.title" class="upload-box" size="small">
    <n-upload
        action="/api/pool/file/upload"
        v-model:file-list="fileList"
        @before-upload="beforeUpload"
        :headers="{'Authorization': 'Bearer '+ getToken()}"
        :data="uploadData"
        @finish="finishUpload"
        :on-remove="removeFile"
        :disabled="props.readonly"
    >
      <n-button v-if="!props.files.classDict" secondary type="primary" size="small" :disabled="!props.files.name">
        上传文件
      </n-button>

      <n-button v-if="!!props.files.classDict" secondary
                style="margin-right: 10px;"
                v-for="item in classDictList" :key="item.value" type="info"
                size="small" :disabled="!props.files.name"
                @click="setFileType(item)"
      >
        {{ item.label }}
      </n-button>
    </n-upload>
  </n-card>
</template>

<script setup>
/**
 * 附件上传组件
 *
 * 附加加载：该附件上传附件统一记录在pool记录表，通过关联名称、初始化记录编号，自动加载附件列表到业务模块。
 * 附件上传逻辑：附件在新增模式时，没有初始化记录编号，会生成一个临时唯一编号，文件是实时上传到服务器，并关联临时编号。
 *    待数据保存后，组件会返回临时编号到后去吧，记录入库后再更新记录的临时编号为记录号，即绑定到记录上。
 *    如附件在编辑模式，此时记录有编号，附件即时上传就会自动绑定到记录上。
 *
 */
import {getToken} from '@/utils/system/token'
import {onMounted, ref, watch} from 'vue'
import {deleteBaseFilePool, getBaseFilePoolList} from '@/api/system/file-pool'
import {randomGuid} from '@/utils/RandomUtils'
import {getSelectDictList} from '@/api/system/dict'

/** 配置属性 **/
const props = defineProps({
  title: {type: String, default: '附件管理'},           //附件管理框标题
  readonly: {type: Boolean, default: false},            //是否只读
  //配置
  files: {
    type: Object, default: () => ({
      name: '',           //关联名称，对应pool表ref_name，一般对应业务数据表表名
      id: '',           //关联id，对应pool表ref_id，一般自动关联对应数据业务表的id
      classDict: ''         //附件类别，关联字典类型标识，默认留空即只显示上传，附件无类别
    })
  }
})

/** 上传文件格式控制 **/
const baseFileUrl = '/api/pool/file/download/{fileId}?t=' + getToken()
const fileTypeList = ['image/png']
const fileSize = 3072      //大小单位KB
const fileLink = {}       //组件文件id无法修改，通过对象关联
const fileList = ref([])
const classDictList = ref([])

/** 监听绑定的ID值变化 **/
watch(() => props.files.id, (newVal, oldVal) => {
  if (!newVal) {
    //无关联id为新增附件
    props.files.id = randomGuid('files-', 4)
  } else {
    //有关联id为修改，加载已有附件
    console.log(newVal)
    if (newVal.toString().indexOf('files-') !== 0)
      getData()
  }
})

/** 初始化相关数据 **/
onMounted(() => {
  if (!!props.files.classDict) {
    getSelectDictList(props.files.classDict).then(res => {
      console.log(res.data)
      classDictList.value = res.data
    })
  }
})

/** 选定上传文件的型别 **/
const curFileClass = ref({})
const setFileType = (item) => {
  curFileClass.value = item
}

/** 加载关联的附件 **/
const getData = () => {
  let query = {refName: props.files.name, refId: props.files.id}
  getBaseFilePoolList(query).then(res => {
    let list = res.data
    list.forEach(item => {
      if (!!item.className)
        item.name = `(${item.className}) ` + item.name
      fileLink[item.id] = {key: item.id, classValue: item.classValue, className: item.className}
      item.status = 'finished'
      item.url = baseFileUrl.replace('{fileId}', item.id)
    })
    fileList.value = list
    console.log(fileList.value)
  })
}

/** 文件上传检测 **/
const uploadData = ref({})
const beforeUpload = (data) => {
  console.log(curFileClass.value.label)
  uploadData.value = {
    'refName': props.files.name,
    'refId': props.files.id,
    'className': curFileClass.value.label,
    'classValue': curFileClass.value.value
  }

  if (fileTypeList.indexOf(data.file.file?.type)) {
    window.$message.error('不能上传当前格式的文件，请重新选择上传！')
    return false
  }
  if (data.file.file?.size / 1024 > fileSize) {
    window.$message.error('当前上传的文件大小超过' + fileSize + 'KB，请重新选择上传或压缩后再上传！')
    return false
  }

  return true
}

/** 文件上传完成回调函数 **/
const finishUpload = ({file, event}) => {
  //有文件类别
  if (!!props.files.classDict) {
    file.name = `(${curFileClass.value.label}) ` + file.name
  }

  let res = JSON.parse((event?.target).response)
  if (!!res.data) {
    fileLink[file.id] = {key: res.data, classValue: curFileClass.value.value, className: curFileClass.value.label}
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
  let fileId = fileLink[file.file.id].key
  deleteBaseFilePool(fileId).then(res => {
    if (res.data) {
      window.$message.success('成功删除附件' + file.file.name)
    }
  })
}

/** 获取上传文件列表 **/
const getFilesList = () => {
  return fileList.value
}

/** 获取文件类型列表 **/
const getFilesClassList = () => {
  return classDictList.value
}

/** 返回文件数量 **/
const getFilesCount = () => {
  return fileList.value.length
}

/** 检查是否存在某类别文件 **/
const checkFileClassExists = (classValue) => {
  for (let i = 0; i < fileList.value.length; i++) {
    if (fileList.value[i].classValue.toString() === classValue.toString()) {
      return true
    }
  }
  return false
}

//暴露方法，方便父组件使用
defineExpose({
  getFilesList, getFilesClassList, getFilesCount, checkFileClassExists
})
</script>

<style lang="scss">
.upload-box {
  width: 100%;

  .n-card-header {
    font-size: var(--n-feedback-font-size);
  }

  .n-upload {
    .n-upload-file-list {
      max-height: 300px;
      overflow: auto;

      .n-upload-file-info {
        a {
          text-decoration: none !important;
        }
      }
    }
  }
}
</style>
