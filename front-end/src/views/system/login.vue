<template>
  <div class="login-view">
    <div class="login-box">
      <n-gradient-text class="login-title" :size="26" type="success">
        颜一简易管理系统登录
      </n-gradient-text>
      <n-form
          label-placement="left">
        <n-form-item>
          <n-input v-model:value="loginInfo.userName" placeholder="用户名">
            <template #suffix>
              <component :is="CIcon" :icon="'Accessibility'" color="rgb(224,224,230)"></component>
            </template>
          </n-input>
        </n-form-item>
        <n-form-item>
          <n-input
              v-model:value="loginInfo.password"
              type="password"
              show-password-on="mousedown"
              placeholder="密码"
              :maxlength="18"
          />
        </n-form-item>
        <n-form-item>
          <n-button
              :disabled="loginInfo.userName==='' || loginInfo.password ===''"
              @click="handleLogin"
              class="login-button" type="success">
            登录
          </n-button>
        </n-form-item>
      </n-form>
    </div>
  </div>
</template>

<script setup>
import CIcon from '@/components/icon'
import {reactive} from "vue"
import {useRouter} from 'vue-router'
import {loginUser} from "@/api/system/login";
import {setToken} from "@/utils/system/token.js";
import {useStore} from 'vuex'

//region 用户登录
let loginInfo = reactive({
  userName: "OneAdmin",
  password: "Color@123"
})

const store = useStore();
const router = useRouter()
const handleLogin = () => {
  loginUser(loginInfo).then(res => {
    setToken(res.data.token)
    store.dispatch("userLogin", res.data)
    router.push("/home")
  })
}
//endregion

</script>

<style scoped lang="scss">
.login-view {
  height: 100%;
  background-image: url("../../assets/image/login-bg.jpeg");
  background-size: cover;
  position: relative;

  .login-box {
    background-color: rgba(255, 255, 255, 0.6);
    width: 330px;
    position: absolute;
    padding: 38px;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    height: 260px;

    .login-title {
      margin-bottom: 28px;
      font-weight: bold;
    }

    .login-button {
      width: 100%;
    }
  }
}
</style>
