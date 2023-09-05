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
      <n-button secondary type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增分组
      </n-button>
      <n-button v-permit="['dict:add']" :disabled="!(ObjectIsNotEmpty(checkRow) && !checkRow.parentId)" secondary
                type="success" @click="handle('addChildren')">
        <icon icon="Tree"/>
        新增字典数据
      </n-button>
      <n-button v-permit="['dict:edit']" :disabled="ObjectIsEmpty(checkRow)" secondary type="success"
                @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button v-permit="['dict:delete:dictId']" :disabled="ObjectIsEmpty(checkRow)" secondary type="warning"
                @click="handle('delete')">
        <icon icon="Delete"/>
        删除
      </n-button>
    </template>
    <template #content>
      <n-data-table
          :columns="table.columns"
          :data="tableData"
          :pagination="pagination"
          :row-key="(row)=>row.dictId"
          :row-props="table.rowProps"
          :row-class-name="table.rowClassName"
          default-expand-all
          :remote="true"
          flex-height
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
import {deleteBaseDict, getBaseDictTreeList} from '@/api/system/dict'
import FormDict from '@/views/system/manage/base-dict/form-dict.vue'
import {ObjectIsEmpty, ObjectIsNotEmpty} from '@/utils/ObjectUtils'
import {formRangeTime} from '@/utils/DateUtils'
import {paginationOption} from '@/utils/system/plugin-config'


/** 查询参数 **/
const searchFrom = ref({
  dictLabel: null, dictType: null, parentId: null
})
const rangeDate = ref(null)

/** 初始化相关数据 **/
onMounted(() => {

  getData()
})

/** 表格数据、事件、配置 **/
let checkRow = ref({})
const table = {
  columns: [
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
  rowClassName(row) {
    return row === checkRow.value ? 'select-table-row' : ''
  },
  rowProps: (row) => {
    return {
      onClick: (e) => {
        if (e.target.localName === 'td')
          row === checkRow.value ? checkRow.value = {} : checkRow.value = row
      }
    }
  },
  pagination: {
    pageSize: 15
  }
}
const tableData = ref([])
const pagination = paginationOption(() => getData())

/** 查询字典数据 **/
const getData = () => {
  searchFrom.value.params = Object.assign(formRangeTime(rangeDate.value), {
    page: pagination.value.page,
    pageSize: pagination.value.pageSize,
  })
  getBaseDictTreeList(searchFrom.value).then(res => {
    tableData.value = res.data.rows
    pagination.value.itemCount = 0 || res.data.total
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
      window.$dialog.warning(
          {
            title: '警告',
            content: '你确定删除该角色吗？',
            positiveText: '确定',
            negativeText: '关闭',
            onPositiveClick: () => {
              deleteBaseDict(checkRow.value.dictId).then(res => {
                if (res.data) {
                  checkRow.value = {}
                  window.$message.success('成功删除数据')
                  getData()
                }
              })
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
