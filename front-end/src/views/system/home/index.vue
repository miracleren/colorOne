<template>
  <n-config-provider :theme="curTheme" class="app-provider">

    <n-layout class="app-body">
      <n-layout-header bordered>
        <div class="header-logo n-text">
          <n-image
              width="32" height="32"
              object-fit="fill"
              :src="require('@/assets/logo.png')"
          />
          <n-gradient-text class="logo-text">颜一简易系统</n-gradient-text>
        </div>
        <div class="header-tags">标签栏</div>
        <div class="header-options">
          <user-options @changeTheme="changeTheme"></user-options>
        </div>

      </n-layout-header>
      <n-layout class="body-center" has-sider>
        <n-layout-sider
            bordered
            show-trigger
            collapse-mode="width"
            :collapsed-width="64"
            :width="200"
            :native-scrollbar="false"
        >
          <n-menu
              :collapsed-width="64"
              :collapsed-icon-size="22"
              :options="menuOptions"
          />
        </n-layout-sider>
        <n-layout/>
      </n-layout>
      <!--    <n-layout-footer  bordered>-->
      <!--      颜一简易系统-->
      <!--    </n-layout-footer>-->
    </n-layout>
  </n-config-provider>
</template>

<script setup>
import {h, ref, watch} from "vue"
import CIcon from '@/components/icon/index.vue'
import {darkTheme} from 'naive-ui'
import UserOptions from "@/views/system/home/user-options.vue"

//更改主题颜色
const curTheme = ref(null)
const changeTheme = (value) => {
  curTheme.value = curTheme.value === null ? darkTheme : null
}

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
.app-provider {
  display: flex;
  height: 100%;

  .app-body {
    height: 100%;

    .n-layout-header {
      height: 50px;
      align-items: center;
      display: grid;
      grid-template-columns: 200px 1fr auto;

      .header-logo {
        padding: 0 10px 0 10px;
        font-size: 18px;
        display: flex;

        .logo-text {
          font-weight: bold ;
        }

      }

      .header-tags {
        padding: 0 10px 0 10px;
        display: flex;
      }

      .header-options {
        padding: 0 10px 0 10px;
        display: flex;
        align-items: center;
        align-content: space-between;
      }
    }

    .body-center {
      height: calc(100% - 50px);
    }

    //.n-layout-footer{
    //  height: 30px;
    //}
  }
}
</style>
