<template>
  <div class="dash-board">
    <div class="card-box">
      <n-grid x-gap="30" :cols="4">
        <n-gi>
          <div class="card-show">
            <icon icon="AlarmSharp"  color="rgb(118,125,206)" size="60"></icon>
          </div>
        </n-gi>
        <n-gi>
          <div class="card-show">
            <icon icon="AmericanFootballSharp" color="rgb(150,207,209)" size="60"></icon>
          </div>
        </n-gi>
        <n-gi>
          <div class="card-show">
            <icon icon="BagAdd" color="rgb(112,158,236)" size="60"></icon>
          </div>
        </n-gi>
        <n-gi>
          <div class="card-show">
            <icon icon="Balloon" color="rgb(246,235,224)" size="60"></icon>
          </div>
        </n-gi>
      </n-grid>
    </div>

  </div>
</template>

<script setup>
import {ref} from 'vue'
import icon from '@/components/icon'
</script>

<style lang="scss">
.dash-board {
  background-color: rgba(177, 177, 177, 0.1);
  height: 100%;

  .card-box {
    padding: 30px;

    .card-show {
      height: 200px;
      background-color: rgba(255, 255, 255, 0.5);
      border-radius: 15px;
      padding: 20px;
      box-sizing: border-box;

    }
  }
}

</style>
