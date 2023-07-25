<template>
  <div class="no-found-body">
    <div class="head-title">
      401 UNAUTHORIZED
      <div class="head-title-shadow">
        401 UNAUTHORIZED
      </div>
    </div>

    <div class="sub-title">
      <span>抱歉，你没有权限访问当前页面！</span>
      <span>你没有权限访问当前页面！</span>
      <span>访问当登录页面？</span>
      <router-link to="/login"><span @click="">返回登录？</span></router-link>
    </div>
  </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.no-found-body {
  color: white;
  height: 100%;
  text-align: center;
  background-color: darkslategray;
  padding-top: 200px;

  .head-title {
    font-size: 6em;
    font-weight: bold;
    position: relative;

    .head-title-shadow {
      width: 100%;
      color: #efefef;
      top: 5px;
      position: absolute;
    }
  }


  .sub-title {
    width: 600px;
    margin-left: auto;
    margin-right: auto;

    span {
      display: block;
      text-align: right;
    }
    a{
      color: #FFC107;
      text-decoration:none;
      font-weight: bold;
      font-size: 1.5em;
    }
  }
}
</style>
