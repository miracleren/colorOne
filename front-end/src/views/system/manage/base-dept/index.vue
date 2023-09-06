<template>

  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="部门名称">
          <n-input v-model:value="searchFrom.deptName" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="状态">
          <select-dict type="base_status" v-model:value="searchFrom.status" class="input-140">
          </select-dict>
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
      <n-button secondary v-permit="['dept:add']" type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增
      </n-button>
      <n-button v-permit="['dept:edit']" :disabled="ObjectIsEmpty(checkRow)" secondary type="success"
                @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['dept:set:status']" secondary
                :type="checkRow.status === 0 ? 'error' : 'info'"
                @click="handle('status')">
        <icon icon="Stop"/>
        {{ checkRow.status === 0 ? '停用' : '启用' }}
      </n-button>
      <n-button v-permit="['dept:delete:deptId']" :disabled="ObjectIsEmpty(checkRow)" secondary type="warning"
                @click="handle('delete')">
        <icon icon="Delete"/>
        删除
      </n-button>
    </template>
    <template #content>
      <n-data-table
          :columns="table.columns"
          :data="tableData"
          :row-key="(row)=>row.deptId"
          :row-props="table.rowProps"
          :row-class-name="table.rowClassName"
          default-expand-all
          flex-height
      />
    </template>
  </layout-table-search>

  <n-modal v-model:show="formConfig.show" preset="card" :title="formConfig.title" class="edit-from">
    <form-dept v-model="formData" :config="formConfig"/>
  </n-modal>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import icon from '@/components/icon/index.vue'
import {deleteBaseDept, getBaseDeptTreeList, setBaseDeptStatus} from '@/api/system/dept'
import FormDept from '@/views/system/manage/base-dept/form-dept.vue'
import {ObjectIsEmpty, ObjectIsNotEmpty} from '@/utils/ObjectUtils'
import SelectDict from '@/components/select-dict'

/** 查询参数 **/
const searchFrom = ref({
  deptName: null, status: null
})
const setStatus = ref(1)

/** 初始化相关数据 **/
onMounted(() => {
  getData()
})

/** 表格数据、事件、配置 **/
let checkRow = ref({})
const table = {
  columns: [
    {
      title: '部门编号',
      key: 'deptId',
    },
    {
      title: '部门名称',
      key: 'deptName'
    },
    {
      title: '状态',
      key: 'statusName'
    },

    {
      title: '排序',
      key: 'deptSort'
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
  }
}
const tableData = ref([])

/** 查询部门数据 **/
const getData = () => {
  getBaseDeptTreeList(searchFrom.value).then(res => {
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
      formConfig.value.title = '新增部门'
      //新增子部门
      console.log(formData.value)
      if (ObjectIsNotEmpty(checkRow.value))
        formData.value = {parentName: checkRow.value.deptName, parentId: checkRow.value.deptId}
      else
        formData.value = {}
      break
    }
    case 'edit': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.deptName + '-修改部门'
      formData.value = checkRow.value
      console.log('formData.value', formData.value)
      break
    }
    case 'delete': {
      window.$dialog.warning(
          {
            title: '警告',
            content: '你确定删除该部门吗？',
            positiveText: '确定',
            negativeText: '关闭',
            onPositiveClick: () => {
              deleteBaseDept(checkRow.value.deptId).then(res => {
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
    case 'status': {
      let msg = checkRow.value.status === 0 ? '停用' : '启用'
      window.$dialog.warning(
          {
            title: '警告',
            content: `${msg}当前部门后，会同步${msg}部门下的所有用户，你确定进行该操作吗？`,
            positiveText: '确定',
            negativeText: '关闭',
            onPositiveClick: () => {
              checkRow.value.status = checkRow.value.status === 0 ? 1 : 0
              setBaseDeptStatus(checkRow.value).then(res => {
                if (res.data) {
                  checkRow.value = {}
                  window.$message.success('成功变更部门的状态')
                }
              })
            }
          })
    }
  }
}
</script>
