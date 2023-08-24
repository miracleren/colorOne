<template>
  <n-form
      ref="formRef"
      :model="model"
      label-placement="top"
  >
    <n-grid :cols="24" :x-gap="24">
      <n-form-item-gi :span="24" label="上级菜单">
        <n-tag type="success">
          {{ model.parentName || '无父级菜单' }}
        </n-tag>
      </n-form-item-gi>

      <n-form-item-gi :span="24" label="关联操作接口">
        <n-select
            v-model:value="selectPermit"
            multiple
            filterable
            placeholder="选择相关操作关联接口"
            :options="permitOptions">
        </n-select>
      </n-form-item-gi>

      <n-form-item-gi :span="24" label="批量备注">
        <n-input v-model:value="model.remark" placeholder="请输入批量备注"/>
      </n-form-item-gi>

    </n-grid>
  </n-form>

  <div class="form-action">
    <n-button type="primary" @click="handlePost">提交</n-button>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {addBaseMenu, addPermitUrls} from '@/api/system/menu'
import {getPermitUrl} from '@/api/system/menu'


const props = defineProps({
  modelValue: Object,
  config: Object
})
const formRef = ref(null)
const selectPermit = ref([])
const permitOptions = ref([])

/** 初始化相关数据 **/
const model = ref({})
let urls = []
onMounted(() => {
  //拷贝数据，编辑时不影响行数据
  model.value = Object.assign({}, props.modelValue)
  getPermitUrl().then(res => {
    let group = []
    let options = []
    urls = res.data
    urls.forEach(item => {
      if (!group.includes(item.controller))
        group.push(item.controller)
    })

    for (let c of group) {
      let g = {
        type: 'group',
        label: c,
        key: c,
        children: []
      }
      res.data.forEach(u => {
        if (u.controller === g.key) {
          g.children.push({
            label: u.name + ' --- ' + u.methodName + ' -> ' + u.permitCode,
            value: u.permitCode
          })
        }
      })
      options.push(g)
    }

    permitOptions.value = options
    console.log(permitOptions.value)
  })
})

/** 提交数据 **/
const handlePost = () => {
  if (selectPermit.value === null || selectPermit.value.length === 0) {
    window.$message.error('请选择相关操作关联接口')
    return
  }

  //新增数据
  if (props.config.type === 'add_api') {
    let addData = []

    selectPermit.value.forEach(row => {
      for (let p of urls) {
        if (!!p.permitCode && p.permitCode === row) {
          addData.push({
            menuName: p.name,
            parentId: model.value.parentId,
            path: p.permitCode,
            remark: model.value.remark
          })
          break
        }
      }
    })

    addPermitUrls(addData).then(res => {
      if (res.data) {
        props.config.show = false
        props.config.success(model.value)
        window.$message.success('成功新增数据')
      }
    })
  }

}
</script>
