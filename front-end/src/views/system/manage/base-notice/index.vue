<template>
  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="公告标题">
          <n-input v-model:value="searchFrom.noticeTitle" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="类型">
          <select-dict type="base_notice_type"
                       v-model:value="searchFrom.noticeType"
                       class="input-140">
          </select-dict>
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
      <n-button :disabled="ObjectIsEmpty(checkRow)" secondary type="success" @click="handle('view')">
        <icon icon="View"/>
        查看
      </n-button>
      <n-button v-permit="['notice:add']" secondary type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['notice:edit']" secondary type="success"
                @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['notice:delete:noticeId']" secondary type="warning"
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
import {ObjectIsEmpty} from '@/utils/ObjectUtils'
import SelectDict from '@/components/select-dict'
import {paginationOption} from '@/utils/system/plugin-config'
import {formRangeTime} from '@/utils/DateUtils'
import {deleteBaseNotice, getBaseNoticeList} from '@/api/system/notice'


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
      title: '公告编号',
      key: 'noticeId'
    },
    {
      title: '公告标题',
      key: 'noticeTitle',
    },
    {
      title: '公告类型',
      key: 'noticeTypeName'
    },
    {
      title: '开始时间',
      key: 'startDate'
    },
    {
      title: '结束时间',
      key: 'endDate'
    },
    {
      title: '创建时间',
      key: 'createTime'
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

/** 查询公告数据 **/
const getData = () => {
  searchFrom.value.params = Object.assign(formRangeTime(rangeDate.value), {
    page: pagination.value.page,
    pageSize: pagination.value.pageSize,
  })
  getBaseNoticeList(searchFrom.value).then(res => {
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
    case 'view': {
      formConfig.value.show = true
      formConfig.value.title = '查看公告'
      formData.value = checkRow.value
      break
    }
    case 'add': {
      formConfig.value.show = true
      formConfig.value.title = '新增公告'
      formData.value = {}
      break
    }
    case 'edit': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.noticeTitle + '-修改公告'
      formData.value = checkRow.value
      console.log('formData.value', formData.value)
      break
    }
    case 'delete': {
      window.$dialog.warning(
          {
            title: '警告',
            content: '你确定删除该公告吗？',
            positiveText: '确定',
            negativeText: '关闭',
            onPositiveClick: () => {
              deleteBaseNotice(checkRow.value.noticeId).then(res => {
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
  }
}

</script>
