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
              @keyup.enter="handleLogin"
          />
        </n-form-item>

        <n-form-item v-if="!!loginInfo.code">
          <n-grid :cols="12" :x-gap="12">
            <n-form-item-gi :span="7">
              <n-input v-model:value="loginInfo.captcha" placeholder="验证码" @keyup.enter="handleLogin">
                <template #suffix>
                  <component :is="CIcon" :icon="'QrCode'" color="rgb(224,224,230)"></component>
                </template>
              </n-input>
            </n-form-item-gi>
            <n-form-item-gi :span="5">
              <img :src="codeUrl" @click="getCaptchaCode" alt="验证码一枚"/>
            </n-form-item-gi>
          </n-grid>
        </n-form-item>

        <n-form-item>
          <n-button
              :disabled="!loginInfo.userName|| !loginInfo.password || (!loginInfo.captcha&&!!loginInfo.code)"
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
import CIcon from '@/components/icon/index.vue'
import {onMounted, ref} from 'vue'
import {useRouter} from 'vue-router'
import {useStore} from 'vuex'
import {formatDate} from '@/utils/DateUtils'
import {captchaImage} from '@/api/system/login'

/** 初始化相关数据 **/
onMounted(() => {
  getCaptchaCode()
})

//region 用户登录
let loginInfo = ref({
  userName: 'OneAdmin',
  password: 'Color@123',
  captcha: '',
  code: ''
})

//登录获取token，用户息通过路由守卫获取，并校验限权
const store = useStore()
const router = useRouter()
const handleLogin = () => {
  console.log('loginInfo.userName', !loginInfo.userName || !loginInfo.password || (!loginInfo.captcha && !!loginInfo.code))
  if (!loginInfo.value.userName || !loginInfo.value.password || (!loginInfo.value.captcha && !!loginInfo.value.code))
    return

  console.log('loginInfo.userName')

  store.dispatch('userLogin', loginInfo.value).then(() => {
    console.log('handleLogin')
    window.$message.success(`欢迎${loginInfo.value.userName}，当前登录日期是 ${formatDate(new Date())}`)
    router.push('/')
  }).catch(() => {
    //登录失败处理
    getCaptchaCode()
    loginInfo.value.captcha = ''
  })
}
//endregion

/** 验证码 **/
const codeUrl = ref(null)
const getCaptchaCode = () => {
  captchaImage().then(res => {
    if (res.data.code) {
      codeUrl.value = 'data:image/gif;base64,' + res.data.image
      loginInfo.value.code = res.data.code
    }
  })

}

</script>

<style scoped lang="scss">
.login-view {
  height: 100%;
  background-image: url("../../assets/image/login-bg.jpeg");
  background-size: cover;
  position: relative;
  display: flex;

  .login-box {
    background-color: rgba(255, 255, 255, 0.6);
    width: 330px;
    padding: 38px;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;

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
