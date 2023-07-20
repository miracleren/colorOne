<template>
  <n-config-provider :theme="curTheme" class="app-provider">
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
        <div class="header-tags">
          <n-tabs default-value="home" closable>
            <n-tab-pane name="home" tab="首页">
            </n-tab-pane>
            <n-tab-pane name="oasis" tab="Oasis">
            </n-tab-pane>
            <n-tab-pane name="the beatles" tab="the Beatles">
            </n-tab-pane>
            <n-tab-pane name="jay chou" tab="周杰伦">
            </n-tab-pane>
            <n-tab-pane name="the beatles1" tab="the beatles1">
            </n-tab-pane>
            <n-tab-pane name="the beatles2" tab="the beatles2">
            </n-tab-pane>
            <n-tab-pane name="the beatles3" tab="the beatles3">
            </n-tab-pane>
          </n-tabs>
        </div>
        <div class="header-options">
          <user-options @changeTheme="changeTheme"></user-options>
        </div>
      </n-layout-header>

      <!--中部-->
      <n-layout class="body-center" has-sider>
        <sidebar-menu></sidebar-menu>
        <n-layout class="section-body">
          <router-view/>
        </n-layout>
      </n-layout>

      <!--底部-->
      <!--    <n-layout-footer  bordered>-->
      <!--      颜一简易系统-->
      <!--    </n-layout-footer>-->
    </n-layout>
  </n-config-provider>
</template>

<script setup>
import {ref} from "vue"
import {darkTheme} from 'naive-ui'
import UserOptions from "@/views/system/home/user-options.vue"
import SidebarMenu from "@/views/system/home/sidebar-menu.vue"

//更改主题颜色
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

        .n-tabs {
          margin-top: 30px;
        }
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
      .section-body{
        padding: 16px;
      }
    }

    //.n-layout-footer{
    //  height: 30px;
    //}
  }
}
</style>
