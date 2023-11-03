<template>
  <div class="dash-board">
    <div class="card-box">
      <n-grid x-gap="30" :cols="4">
        <n-gi>
          <div class="card-show" style="border-color:  rgb(118,125,206);">
            <icon icon="AlarmSharp" color="rgb(118,125,206)" size="38"></icon>
            <div class="show-number">
              <n-statistic label="用户数" tabular-nums>
                <n-number-animation ref="numberAnimationInstRef" :from="0" :to="2023"/>
              </n-statistic>
            </div>
            <n-progress class="number-circle" type="circle" :percentage="83" color="rgb(118,125,206)"/>
          </div>
        </n-gi>
        <n-gi>
          <div class="card-show" style="border-color:  rgb(150,207,209);">
            <icon icon="AmericanFootballSharp" color="rgb(150,207,209)" size="38"></icon>
            <div class="show-number">
              <n-statistic label="任务数" tabular-nums>
                <n-number-animation ref="numberAnimationInstRef" :from="0" :to="1986"/>
              </n-statistic>
            </div>
            <n-progress class="number-circle" type="circle" :percentage="83" color="rgb(150,207,209)"/>
          </div>
        </n-gi>
        <n-gi>
          <div class="card-show" style="border-color:  rgb(112,158,236);">
            <icon icon="BagAdd" color="rgb(112,158,236)" size="38"></icon>
            <div class="show-number">
              <n-statistic label="文章数" tabular-nums>
                <n-number-animation ref="numberAnimationInstRef" :from="0" :to="503"/>
              </n-statistic>
            </div>
            <n-progress class="number-circle" type="circle" :percentage="83" color="rgb(112,158,236)"/>
          </div>
        </n-gi>
        <n-gi>
          <div class="card-show" style="border-color:  #FFC107;">
            <icon icon="Balloon" color="#FFC107" size="38"></icon>
            <div class="show-number">
              <n-statistic label="浏览量" tabular-nums>
                <n-number-animation ref="numberAnimationInstRef" :from="0" :to="1314"/>
              </n-statistic>
            </div>
            <n-progress class="number-circle" type="circle" :percentage="83" color="#FFC107"/>
          </div>
        </n-gi>
      </n-grid>
    </div>
    <div class="time-box">
      <n-grid x-gap="16" :cols="8">
        <n-gi :span="4">
          <div class="calendar-box">
            <n-calendar
                #="{date}"
                style="height: 500px">
              <n-gradient-text :type="types[date%3]">
                {{ calendar[date%5] }}
              </n-gradient-text>
            </n-calendar>
          </div>
        </n-gi>
        <n-gi :span="2">
          <n-timeline>
            <n-timeline-item content="啊，什么时候完成呢"/>
            <n-timeline-item
                type="success"
                title="实用模块"
                content="添加系统功能模块，哪些实用先加上去吧"
                time="Nov 1, 2023"
            />
            <n-timeline-item type="error" content="数据权限" time=" Aug 22, 2023"/>
            <n-timeline-item
                type="warning"
                title="权限"
                content="登录页面设计起来，后台权限搞起"
                time="Jul 19, 2023"
            />
            <n-timeline-item
                type="info"
                title="开工大吉"
                content="开始搭那建颜一简易操作系统，框架搭起"
                time=" Jun 10, 2022"
                line-type="dashed"
            />
            <n-timeline-item time="Aug 9, 2021" content="啊，我要不要自己搞个业务系统呢？"/>
          </n-timeline>
        </n-gi>
        <n-gi :span="2">
          <div class="tags-box">
            <n-gradient-text type="info" style="display: block;font-size: 30px;">
              ColorOne System
            </n-gradient-text>
            <n-tag v-for="item in tags" round :bordered="false" :key="item" :type="types[randomInteger(0,3)]">
              {{ item }}
              <template #icon>
                <icon icon="CheckmarkCircle"></icon>
              </template>
            </n-tag>
          </div>
        </n-gi>

      </n-grid>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import icon from '@/components/icon'
import {randomInteger} from '@/utils/RandomUtils'

const calendar = ['睡觉', '休息', '敲代码', '旅游', '$']
const types = ['success', 'warning', 'error', 'info']
const tags = ['ColorOne', 'SpringBoot3', 'java17', 'Spring Security', 'MybatisPlus', 'Vue3', 'naiveUi', 'nodejs', 'MySql', 'redis']
</script>

<style lang="scss">
.dash-board {
  background-color: rgb(228, 229, 233, 0.1);
  height: 100%;

  .card-box {
    padding: 30px;


    .card-show {
      position: relative;
      border-left: 8px solid;
      height: 100px;
      //background-color: rgba(255, 255, 255, 0.5);
      //border-radius: 15px;
      padding: 20px;
      //box-sizing: border-box;
      .show-number {
        .n-statistic-value__content {
          opacity: 0.9;
        }
      }

      .number-circle {
        position: absolute;
        top: 20px;
        left: 120px;
      }
    }
  }

  .time-box {
    .n-timeline {
      padding: 50px;
    }

    .tags-box {
      padding: 30px;

      .n-tag {
        margin-left: 20px;
        margin-top: 20px;
      }
    }

    .calendar-box {
      padding: 30px;
    }

  }


}

</style>
