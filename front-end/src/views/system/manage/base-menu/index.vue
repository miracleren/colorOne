<template>

  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="菜单名称">
          <n-input v-model:value="searchFrom.menuName" class="input-220" clearable/>
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
      <n-button v-permit="['menu:add']" secondary type="success" @click="handle('add')">
        <icon icon="Add"/>
        新增
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['menu:permit:add']" secondary type="success"
                @click="handle('add_api')">
        <icon icon="Add"/>
        新增操作权限
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['menu:edit']" secondary type="success"
                @click="handle('edit')">
        <icon icon="Edit"/>
        修改
      </n-button>
      <n-button :disabled="ObjectIsEmpty(checkRow)" v-permit="['menu:delete:menuId']" secondary type="warning"
                @click="handle('delete')">
        <icon icon="Delete"/>
        删除
      </n-button>
    </template>
    <template #content>
      <n-data-table
          :columns="table.columns"
          :data="tableData"
          :row-key="(row)=>row.menuId"
          :row-props="table.rowProps"
          :row-class-name="table.rowClassName"
          flex-height
      />
    </template>
  </layout-table-search>

  <n-modal v-model:show="formConfig.show" preset="card" :title="formConfig.title" class="edit-from">
    <form-menu v-if="formConfig.type !== 'add_api'" v-model="formData" :config="formConfig"/>
    <form-permit v-if="formConfig.type === 'add_api'" v-model="formData" :config="formConfig"/>
  </n-modal>
</template>

<script setup>
import {h, onMounted, ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import icon from '@/components/icon/index.vue'
import FormMenu from '@/views/system/manage/base-menu/form-menu.vue'
import {ObjectIsEmpty, ObjectIsNotEmpty} from '@/utils/ObjectUtils'
import SelectDict from '@/components/select-dict'
import {deleteBaseMenu, getBaseMenuTreeList} from '@/api/system/menu'
import CIcon from '@/components/icon/index.vue'
import FormPermit from '@/views/system/manage/base-menu/form-permit.vue'


/** 查询参数 **/
const searchFrom = ref({
  menuName: null, status: null, parentId: null
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
      title: '菜单名称',
      key: 'menuName',
      width: 250
    },
    {
      title: '图标',
      key: 'icon',
      render(row) {
        if (row.menuType === 'b')
          return h(CIcon, {icon: 'Construct', color: '#f0a020'})
        else
          return h(CIcon, {icon: row.icon, color: 'rgb(14, 122, 13)'})
      }
    },
    {
      title: '类型',
      key: 'menuTypeName'
    },
    {
      title: '排序',
      key: 'orderNum'
    },
    {
      title: '组件路径',
      key: 'component',
      width: 250
    },
    {
      title: '地址',
      key: 'path'
    },
    {
      title: '状态',
      key: 'statusName'
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

/** 查询菜单数据 **/
const getData = () => {
  getBaseMenuTreeList(searchFrom.value).then(res => {
    tableData.value = res.data
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
      formConfig.value.title = '新增菜单'
      //新增子菜单
      console.log(checkRow)
      if (ObjectIsNotEmpty(checkRow.value))
        formData.value = {parentName: checkRow.value.menuName, parentId: checkRow.value.menuId}
      else
        formData.value = {}
      break
    }
    case 'edit': {
      formConfig.value.show = true
      formConfig.value.title = checkRow.value.menuName + '-修改菜单'
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
              deleteBaseMenu(checkRow.value.menuId).then(res => {
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
    case 'add_api': {
      formConfig.value.show = true
      formConfig.value.title = '新增操作'
      //新增菜单关联操作
      if (ObjectIsNotEmpty(checkRow.value))
        formData.value = {parentName: checkRow.value.menuName, parentId: checkRow.value.menuId}
      else
        formData.value = {}
      break
    }
  }
}

</script>
