<template>
  <n-layout class="app-body">
    <n-layout-header :inverted="inverted" bordered>
      <n-space justify="end">
        <n-switch v-model:value="inverted"/>
        深色
      </n-space>
      <!--        <n-menu mode="horizontal" :inverted="inverted" :options="menuOptions"/>-->
    </n-layout-header>
    <n-layout class="body-center" has-sider>
      <n-layout-sider
          bordered
          show-trigger
          collapse-mode="width"
          :collapsed-width="64"
          :width="200"
          :native-scrollbar="false"
          :inverted="inverted"
      >
        <n-menu
            :inverted="inverted"
            :collapsed-width="64"
            :collapsed-icon-size="22"
            :options="menuOptions"
        />
      </n-layout-sider>
      <n-layout/>
    </n-layout>
    <!--    <n-layout-footer :inverted="inverted" bordered>-->
    <!--      颜一简易系统-->
    <!--    </n-layout-footer>-->
  </n-layout>
</template>

<script setup>
import {h, ref} from "vue"
import CIcon from '@/components/icon'

let inverted = ref(false)

//region 菜单相关
const renderIcon = (icon) => {
  return () => h(CIcon, {name: icon, color: 'rgb(14, 122, 13)'})
}
const menuOptions = [
  {
    label: '且听风吟',
    key: 'hear-the-wind-sing',
    icon: renderIcon('Accessibility')
  },
  {
    label: '1973年',
    key: 'pinball-1973',
    icon: renderIcon('Accessibility'),
    disabled: false,
    children: [
      {
        label: '鼠',
        key: 'rat'
      }
    ]
  },
  {
    label: '寻羊冒险记',
    key: 'a-wild-sheep-chase',
    disabled: true,
    icon: renderIcon('Accessibility')
  },
  {
    label: '舞，舞，舞',
    key: 'dance-dance-dance',
    icon: renderIcon('Accessibility'),
    children: [
      {
        type: 'group',
        label: '人物',
        key: 'people',
        children: [
          {
            label: '叙事者',
            key: 'narrator',
            icon: renderIcon('Accessibility')
          },
          {
            label: '羊男',
            key: 'sheep-man',
            icon: renderIcon('Accessibility')
          }
        ]
      },
      {
        label: '饮品',
        key: 'beverage',
        icon: renderIcon('Accessibility'),
        children: [
          {
            label: '威士忌',
            key: 'whisky'
          }
        ]
      },
      {
        label: '食物',
        key: 'food',
        children: [
          {
            label: '三明治',
            key: 'sandwich'
          }
        ]
      },
      {
        label: '过去增多，未来减少',
        key: 'the-past-increases-the-future-recedes'
      }
    ]
  }
]
//endregion
</script>

<style lang="scss" scoped>
.app-body {
  height: 100%;

  .n-layout-header {
    height: 50px;
    padding: 10px;
  }

  .body-center {
    height: calc(100% - 50px);
  }

  //.n-layout-footer{
  //  height: 30px;
  //}
}
</style>
