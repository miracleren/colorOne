<template>
  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="用户名">
          <n-input v-model:value="searchFrom.userName" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="类型">
          <select-dict type="base_success_status"
                       v-model:value="searchFrom.status"
                       class="input-140">
          </select-dict>
        </n-form-item>
        <n-form-item label="登录日期">
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
    <template #content>
      <n-data-table
          :columns="table.columns"
          :data="tableData"
          :pagination="pagination"
          :row-key="(row)=>row.noticeId"
          :row-props="table.rowProps"
          :row-class-name="table.rowClassName"
          flex-height
          :remote="true"
      />
    </template>
  </layout-table-search>

  <n-modal v-model:show="formConfig.show" preset="card" :title="formConfig.title" class="edit-from">
    <form-notice v-model="formData" :config="formConfig"/>
  </n-modal>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import icon from '@/components/icon/index.vue'
import FormNotice from '@/views/system/manage/base-notice/form-notice.vue'
import SelectDict from '@/components/select-dict'
import {paginationOption} from '@/utils/system/plugin-config'
import {formRangeTime} from '@/utils/DateUtils'
import {getBaseLogLoginList} from '@/api/system/log'


/** 查询参数 **/
const searchFrom = ref({
  noticeTitle: null, noticeType: null
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
      title: '日志时间',
      key: 'logTime'
    },
    {
      title: '用户账号',
      key: 'userName'
    },
    {
      title: '登录IP地址',
      key: 'ipAddress',
    },
    {
      title: '浏览器',
      key: 'browser'
    },
    {
      title: '系统',
      key: 'os'
    },
    {
      title: '登录状态',
      key: 'statusName'
    },
    {
      title: '日志说明',
      key: 'msg',
      width: 300
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
  }
}
const tableData = ref([])
const pagination = paginationOption(() => getData())

/** 查询登录日志数据 **/
const getData = () => {
  searchFrom.value.params = Object.assign(formRangeTime(rangeDate.value), {
    page: pagination.value.page,
    pageSize: pagination.value.pageSize,
  })
  getBaseLogLoginList(searchFrom.value).then(res => {
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
  }
}

</script>
