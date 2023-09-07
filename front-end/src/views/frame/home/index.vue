<template>
  <n-config-provider :theme="curTheme" class="app-provider" :locale="zhCN" :date-locale="dateZhCN">
    <n-layout class="app-body">

      <!--顶部-->
      <n-layout-header bordered>
        <div class="header-logo n-text">
          <n-image
              width="32" height="32"
              object-fit="fill"
              :src="require('@/assets/logo.png')"
          />
          <n-gradient-text class="logo-text">颜一简易系统</n-gradient-text>
        </div>
        <div class="header-tags" :class="defaultSetting.useTags ? 'flex-end' : 'flex-center'">
          <tags-view v-if="defaultSetting.useTags"></tags-view>
          <breadcrumb-menu v-if="!defaultSetting.useTags"></breadcrumb-menu>
        </div>
        <div class="header-options">
          <user-options @changeTheme="changeTheme"></user-options>
        </div>
      </n-layout-header>

      <!--中部-->
      <n-layout class="body-center" has-sider>
        <sidebar-menu></sidebar-menu>
        <transition :name="defaultSetting.routerTransition" mode="out-in">
          <n-layout class="section-body" :key="$route.path">
            <router-view v-slot="{ Component ,route }">
              <keep-alive>
                <component :is="Component" v-if="defaultSetting.keepAlive" :key="route.path"/>
              </keep-alive>
              <component :is="Component" v-if="!defaultSetting.keepAlive" :key="route.path"/>
            </router-view>
          </n-layout>
        </transition>
      </n-layout>

      <!--底部-->
      <!--    <n-layout-footer  bordered>-->
      <!--      颜一简易系统-->
      <!--    </n-layout-footer>-->
    </n-layout>
  </n-config-provider>
</template>

<script setup>
import {ref} from 'vue'
import {darkTheme} from 'naive-ui'
import UserOptions from '@/views/frame/home/user-options.vue'
import SidebarMenu from '@/views/frame/home/sidebar-menu.vue'
import TagsView from '@/views/frame/home/tags-view.vue'
import defaultSetting from '@/frame/settings'
import BreadcrumbMenu from '@/views/frame/home/breadcrumb-menu.vue'
import {zhCN, dateZhCN} from 'naive-ui'

/*更改主题颜色*/
const curTheme = ref(null)
const changeTheme = () => {
  console.log(curTheme.value)
  curTheme.value = curTheme.value === null ? darkTheme : null
}
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
      display: flex;
      grid-template-columns: 200px 1fr auto;

      .header-logo {
        padding: 0 10px 0 10px;
        font-size: 18px;
        display: flex;
        min-width: 200px;
        box-sizing: border-box;

        .logo-text {
          font-weight: bold;
        }

      }

      .header-tags {
        padding: 0 10px 0 10px;
        display: flex;
        flex: 1;
        width: 0;
        height: 50px;
      }

      .flex-end {
        align-items: flex-end;
      }

      .flex-center {
        align-items: center;
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

      .section-body {
        //padding: 16px;
      }
    }

    //.n-layout-footer{
    //  height: 30px;
    //}
  }
}
</style>
