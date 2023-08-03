<template>

  <layout-table-search>
    <template #search>
      <n-form
          label-placement="left"
          inline
          :show-feedback="false"
      >
        <n-form-item label="用户ID或名称">
          <n-input v-model:value="searchFrom.userName" class="input-220"/>
        </n-form-item>
        <n-form-item label="手机号码">
          <n-input v-model:value="searchFrom.phone" class="input-220"/>
        </n-form-item>
        <n-form-item label="创建日期">
          <n-date-picker v-model:value="searchFrom.range" update-value-on-close type="daterange" clearable
                         class="input-360"/>
        </n-form-item>
        <n-form-item label="状态">
          <select-dict type="base_user_status" v-model:value="searchFrom.phone" class="input-140"></select-dict>
        </n-form-item>
      </n-form>
    </template>
    <template #tool>
      <n-button dashed type="success">
        新增
      </n-button>
      <n-button dashed type="success">
        修改
      </n-button>
      <n-button dashed type="warning">
        删除
      </n-button>
    </template>
    <template #content>
<!--      <n-data-table-->
<!--          :columns="columns"-->
<!--          :data="data"-->
<!--          :pagination="pagination"-->
<!--          :max-height="250"-->
<!--      />-->
    </template>
  </layout-table-search>
</template>

<script setup>
import {ref} from 'vue'
import LayoutTableSearch from '@/components/layout/layout-content-search.vue'
import selectDict from '@/components/select-dict'

const searchFrom = ref({
  userName: null, phone: null, range: null
})

/** 表格数据 **/
</script>
