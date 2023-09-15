<template>
  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="角色名称">
          <n-input v-model:value="searchFrom.roleName" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="状态">
          <select-dict type="base_status"
                       v-model:value="searchFrom.status"
                       class="input-140">
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
      <n-button v-permit="['role:add']" secondary type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['role:edit']" secondary type="success"
                @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['role:delete:roleId']" secondary type="warning"
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
          :row-key="(row)=>row.roleId"
          :row-props="table.rowProps"
          :row-class-name="table.rowClassName"
          flex-height
          :remote="true"
      />
    </template>
  </layout-table-search>

  <n-modal v-model:show="formConfig.show" preset="card" :title="formConfig.title" class="edit-from" style="width: 900px">
    <form-role v-model="formData" :config="formConfig"/>
  </n-modal>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import icon from '@/components/icon/index.vue'
import FormRole from '@/views/system/manage/base-role/form-role.vue'
import {ObjectIsEmpty} from '@/utils/ObjectUtils'
import SelectDict from '@/components/select-dict'
import {deleteBaseRole, getBaseRoleList} from '@/api/system/role'
import {paginationOption} from '@/utils/system/plugin-config'


/** 查询参数 **/
const searchFrom = ref({
  roleName: null, roleStatus: null
})

/** 初始化相关数据 **/
onMounted(() => {
  getData()
})

/** 表格数据、事件、配置 **/
let checkRow = ref({})
const table = {
  columns: [
    {
      title: '角色编号',
      key: 'roleId'
    },
    {
      title: '角色名称',
      key: 'roleName',
    },
    {
      title: '角色标识',
      key: 'roleKey'
    },
    {
      title: '排序',
      key: 'roleSort'
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

/** 查询角色数据 **/
const getData = () => {
  searchFrom.value.params = {
    page: pagination.value.page,
    pageSize: pagination.value.pageSize,
  }
  getBaseRoleList(searchFrom.value).then(res => {
    tableData.value = res.data.rows
    pagination.value.itemCount = 0 || res.data.total
    console.log('tableData.value ', tableData.value)
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
      formConfig.value.title = '新增角色'
      formData.value = {}
      break
    }
    case 'edit': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.roleName + '-修改角色'
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
              deleteBaseRole(checkRow.value.roleId).then(res => {
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
