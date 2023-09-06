<template>
  <layout-left-content>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="用户ID或名称">
          <n-input v-model:value="searchFrom.userName" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="手机号码">
          <n-input v-model:value="searchFrom.phone" class="input-220" clearable/>
        </n-form-item>
        <n-form-item label="创建日期">
          <n-date-picker v-model:value="rangeDate" update-value-on-close type="daterange" clearable
                         class="input-360"/>
        </n-form-item>
        <n-form-item label="状态">
          <select-dict type="base_user_status" v-model:value="searchFrom.status" class="input-140"
                       clearable></select-dict>
        </n-form-item>

        <n-button type="primary" @click="handle('search')">
          <template #icon>
            <icon icon="Search"/>
          </template>
          搜索
        </n-button>
      </n-form>
    </template>
    <template #leftBar>
      <n-card title="📖部门结构" :bordered="false">
        <n-tree
            block-line
            :data="deptOptions"
            :pagination="pagination"
            key-field="deptId"
            label-field="deptName"
            children-field="children"
            selectable
            default-expand-all
            flex-height
            :remote="true"
            @update:selected-keys="checkDept"
        />
      </n-card>
    </template>
    <template #tool>
      <n-button v-permit="['user:add']" secondary type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['user:edit']" secondary type="success"
                @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['user:reset:password']" secondary type="info"
                @click="handle('reset')">
        <icon icon="Key"/>
        重置密码
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['user:delete:userId']" secondary type="warning"
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
          :row-key="(row)=>row.userId"
          :row-props="table.rowProps"
          :row-class-name="table.rowClassName"
          flex-height
          :remote="true"
      />
    </template>
  </layout-left-content>

  <n-modal v-model:show="formConfig.show" preset="card" :title="formConfig.title" class="edit-from">
    <form-user v-if="formConfig.type !== 'reset'" v-model="formData" :config="formConfig"/>
    <form-password v-if="formConfig.type === 'reset'" v-model="formData" :config="formConfig"/>
  </n-modal>
</template>

<script setup>
import {h, onMounted, ref} from 'vue'
import LayoutLeftContent from '@/components/layout/layout-left-content.vue'
import icon from '@/components/icon/index.vue'
import {ObjectIsEmpty} from '@/utils/ObjectUtils'
import SelectDict from '@/components/select-dict'
import {formRangeTime} from '@/utils/DateUtils'
import {deleteBaseUser, getBaseUserList, switchBaseUserStatus} from '@/api/system/user'
import {NSwitch} from 'naive-ui'
import FormUser from '@/views/system/manage/base-user/form-user.vue'
import FormPassword from '@/views/system/manage/base-user/form-password.vue'
import {getBaseDeptTreeList} from '@/api/system/dept'
import {traverseTree} from '@/utils/TreeUtils'
import {paginationOption} from '@/utils/system/plugin-config'


/** 查询参数 **/
const searchFrom = ref({
  userName: null, phone: null, status: null, deptId: null
})
const rangeDate = ref(null)
const deptOptions = ref([])

/** 初始化相关数据 **/
onMounted(() => {
  //构建左侧选择树
  getBaseDeptTreeList().then(d => {
    traverseTree(d.data, (row) => {
      if (row.status === 1)
        row.deptName = row.deptName + ' (停用)'
    })
    deptOptions.value = d.data
  })

  getData()
})

/** 表格数据、事件、配置 **/
let checkRow = ref({})
const table = {
  columns: [
    {
      title: '用户编号',
      key: 'userId'
    },
    {
      title: '用户账号',
      key: 'userName',
    },
    {
      title: '用户昵称',
      key: 'nickName'
    },
    {
      title: '部门名称',
      key: 'deptId',
    },
    {
      title: '手机号码',
      key: 'phone'
    },
    {
      title: '状态',
      key: 'status',
      render(row) {
        return h(NSwitch, {
          defaultValue: row.status === 0,
          onUpdateValue(value) {
            console.log('handleChange')
            row.status = value ? 0 : 1
            switchBaseUserStatus(row).then(res => {
              window.$message.success(`成功切换${row.nickName}的用户状态！`)
            })
          }
        })
      }
    },
    {
      title: '创建时间',
      key: 'createTime',
      width:180
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

/** 查询用户数据 **/
const getData = () => {
  searchFrom.value.params = Object.assign(formRangeTime(rangeDate.value), {
    page: pagination.value.page,
    pageSize: pagination.value.pageSize,
  })
  getBaseUserList(searchFrom.value).then(res => {
    tableData.value = res.data.rows
    pagination.value.itemCount = 0 || res.data.total
    console.log('tableData.value ', tableData.value)
  })

}

/** 部门树触发事件 **/
const checkDept = (v) => {
  searchFrom.value.deptId = v.length > 0 ? v[0] : null
  getData()
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
      formConfig.value.title = '新增用户'
      formData.value = {deptId: searchFrom.value.deptId}
      break
    }
    case 'edit': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.userName + '-修改用户'
      formData.value = checkRow.value
      console.log('formData.value', formData.value)
      break
    }
    case 'reset': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.userName + '-重置用户密码'
      formData.value = checkRow.value
      console.log('formData.value', formData.value)
      break
    }
    case 'delete': {
      window.$dialog.warning(
          {
            title: '警告',
            content: '你确定删除该用户吗？',
            positiveText: '确定',
            negativeText: '关闭',
            onPositiveClick: () => {
              deleteBaseUser(checkRow.value.userId).then(res => {
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
