<template>

  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="字典名称">
          <n-input v-model:value="searchFrom.dictLabel" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="字典类型">
          <n-input v-model:value="searchFrom.dictType" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="创建日期">
          <n-date-picker v-model:value="rangeDate"
                         update-value-on-close type="daterange"
                         clearable
                         class="input-360"/>
        </n-form-item>
        <n-button type="primary" @click="handle('search')">
          <template #icon>
            <icon icon="Search"/>
          </template>
          搜索
        </n-button>
      </n-form>
    </template>
    <template #tool>
      <n-button dashed type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增分组
      </n-button>
      <n-button :disabled="!!checkRow.parentId || !ObjectIsEmpty(checkRow)" dashed type="success"
                @click="handle('addChildren')">
        <icon icon="Tree"/>
        新增字典数据
      </n-button>
      <n-button :disabled="!ObjectIsEmpty(checkRow)" dashed type="success" @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button :disabled="!ObjectIsEmpty(checkRow)" dashed type="warning" @click="handle('delete')">
        <icon icon="Delete"/>
        删除
      </n-button>
    </template>
    <template #content>
      <n-data-table
          :columns="table.columns"
          :data="tableData"
          :pagination="table.pagination"
          style="height: 100%"
          :row-key="(row)=>row.dictId"
          v-model:checked-row-keys="(key,rows)=>{checkRow=rows[0]}"
          allow-checking-not-loaded
          @load="onLoadChildren"
      />
    </template>
  </layout-table-search>

  <n-modal v-model:show="formConfig.show" preset="card" :title="formConfig.title" class="edit-from">
    <form-dict v-model="formData" :config="formConfig"/>
  </n-modal>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import icon from '@/components/icon/index.vue'
import {deleteBaseDict, getBaseDictChildren, getBaseDictList} from '@/api/system/dict'
import FormDict from '@/views/system/manage/base-dict/form-dict.vue'
import {ObjectIsEmpty} from '@/utils/ObjectUtils'
import {formRangeTime} from '@/utils/DateUtils'

/** 查询参数 **/
const searchFrom = ref({
  dictLabel: null, dictType: null, parentId: null
})
const rangeDate = ref(null)

/** 初始化相关数据 **/
onMounted(() => {

  getData()
})

/** 表格数据、事件 **/
let checkRow = ref({})
const table = {
  checkKey: null,
  columns: [
    {
      type: 'selection',
      multiple: false
    },
    {
      title: '字典主键',
      key: 'dictId'
    },
    {
      title: '字典标签',
      key: 'dictLabel'
    },
    {
      title: '字典类型',
      key: 'dictType'
    },
    {
      title: '字典键值',
      key: 'dictValue'
    },
    {
      title: '字典排序',
      key: 'dictSort'
    },
    {
      title: '状态',
      key: 'statusName'
    },
    {
      title: '创建时间',
      key: 'createTime'
    },
    {
      title: '备注',
      key: 'remark'
    }
  ],
  pagination: {
    pageSize: 15
  }
}
const tableData = ref([])
const onLoadChildren = (row) => {
  console.log('onLoadChildren')
  return new Promise((resolve) => {
    getBaseDictChildren(row.dictId).then(res => {
      row.children = res.data
      if (res.data.length === 0)
        window.$message.success('当前字典分组没有数据')
      resolve()
    })

  })
}


/** 查询字典数据 **/
const getData = () => {
  searchFrom.value.params = formRangeTime(rangeDate.value)
  getBaseDictList(searchFrom.value).then(res => {
    res.data.forEach(row => {
      row.isLeaf = false
    })
    tableData.value = res.data
  })
}

/**数据操作事件**/
const formData = ref({})
const formConfig = ref({
  show: false,
  title: '',
  type: '',
  success: (data) => {
    getData()
  }
})
const handle = (key) => {
  formConfig.value.type = key
  switch (key) {
    case 'search': {
      getData()
      break
    }
    case 'add': {
      formConfig.value.show = true
      formConfig.value.title = '新增字典组'
      formData.value = {}
      break
    }
    case 'edit': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.dictLabel + '-修改字典组'
      formData.value = checkRow.value
      console.log('formData.value', formData.value)
      break
    }
    case 'delete': {
      deleteBaseDict(checkRow.value.dictId).then(res => {
        if (res.data) {
          checkRow.value = {}
          window.$message.success('成功删除数据')
          getData()
        }
      })
      break
    }
    case 'addChildren': {
      formConfig.value.show = true
      formConfig.value.title = '新增字典数据'
      console.log(checkRow.value)
      formData.value = {parentId: checkRow.value.dictId, dictType: checkRow.value.dictType}
      break
    }
  }
}

</script>
