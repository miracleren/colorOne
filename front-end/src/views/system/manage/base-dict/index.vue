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
        新增
      </n-button>
      <n-button :disabled="!ObjectIsEmpty(checkRow)" dashed type="success" @click="handle('edit')">
        修改
      </n-button>
      <n-button :disabled="!ObjectIsEmpty(checkRow)" dashed type="warning" @click="handle('delete')">
        删除
      </n-button>
    </template>
    <template #content>
      <n-data-table
          :columns="table.columns"
          :data="tableData"
          :pagination="table.pagination"
          style="height: 100%"
          flex-height
          :row-key="(row)=>row.dictId"
          @update:checked-row-keys="(key,rows)=>{checkRow=rows[0]}"
      />
    </template>
  </layout-table-search>

  <n-modal v-model:show="showForm" preset="dialog" :title="formTitle">
    <form-dict v-model:value="formData"></form-dict>
  </n-modal>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import icon from '@/components/icon/index.vue'
import {getDictList} from '@/api/system/dict'
import FormDict from '@/views/system/manage/base-dict/form-dict.vue'
import {ObjectIsEmpty} from '@/utils/ObjectUtils'
import {formRangeTime} from '@/utils/DateUtils'

/** 查询参数 **/
const searchFrom = ref({
  dictLabel: null, dictType: null, parentId: null, params: {}
})
const rangeDate = ref(null)

/** 初始化相关数据 **/
onMounted(() => {
  getData()
})

/** 表格数据 **/
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
      title: '字典排序',
      key: 'dictSort'
    },
    {
      title: '状态',
      key: 'statusName'
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

/** 查询字典数据 **/
const getData = () => {
  console.log('rangeDate', rangeDate.value)
  console.log('formRangeTime(rangeDate)', formRangeTime(rangeDate.value))
  searchFrom.value.params = formRangeTime(rangeDate.value)
  // if (rangeDate.value != null) {
  //   searchFrom.value.params['beginTime'] = rangeDate.value[0]
  //   searchFrom.value.params['endTime'] = rangeDate.value[1]
  // }
  console.log('searchFrom.value', searchFrom.value)
  getDictList(searchFrom.value).then(res => {
    tableData.value = res.data
  })
}

/**数据操作事件**/
const showForm = ref(false)
const formData = ref({})
const formTitle = ref('')
const handle = (key) => {
  switch (key) {
    case 'search': {
      getData()
      break
    }
    case 'add': {
      formTitle.value = '新增字典分组'
      formData.value = {}
      showForm.value = true
      break
    }
    case 'edit': {
      console.log('edit formData', checkRow)
      formTitle.value = '修改字典分组-' + checkRow.value.dictLabel
      formData.value = checkRow.value
      showForm.value = true
      break
    }
    case 'delete': {
      console.log('delete')
      break
    }
  }
}

</script>
