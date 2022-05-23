<template>
  <div class="login-view">
    <div class="login-box"></div>
  </div>
</template>

<script>
export default {
  name: "login-view",
  created() {

  }
}
</script>

<style scoped lang="scss">
.login-view {
  height: 100%;

  .login-box {
    background-color: black;
    width: 570px;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    height: 350px;
    border: red solid;
  }
}
</style>
