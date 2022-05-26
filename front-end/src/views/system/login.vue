<template>
  <div class="login-view">
    <div class="login-box">
      <n-card title="颜一系统登录">
        <n-form
        >
          <n-form-item>
            <n-input placeholder="用户名">
              <template #suffix>
                <c-icon color="rgb(224,224,230)"></c-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item>
            <n-input
                type="password"
                show-password-on="mousedown"
                placeholder="密码"
                :maxlength="16"
            />
          </n-form-item>
        </n-form>
      </n-card>
    </div>
  </div>
</template>

<script>
import CIcon from "@/components/icon";

export default {
  name: "login-view",
  components: {CIcon},
  created() {
    console.log()
  }
}
</script>

<style scoped lang="scss">
.login-view {
  height: 100%;

  .login-box {
    background-color: rgba(255, 255, 255, 0.6);
    width: 380px;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    height: 380px;

    .n-card {

    }
  }
}
</style>
