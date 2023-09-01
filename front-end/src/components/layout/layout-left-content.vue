<template>
  <div class="content-search">
    <div v-if="useSlots().search" class="search-box">
      <slot name="search"></slot>
    </div>

    <div class="info-body">
      <div :style="{width:`${props.leftPercent}%`}" class="left-box">
        <div v-if="useSlots().leftBar" class="left-tool-box">
          <slot name="leftBar"></slot>
        </div>
      </div>
      <div :style="{width:`${100-props.leftPercent}%`}" class="right-box">
        <div v-if="useSlots().tool" class="tool-box">
          <slot name="tool"></slot>
        </div>
        <div v-if="useSlots().content" class="content-box">
          <slot name="content"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {useSlots} from 'vue'

/**
 * 查询顶、操作表格与右操作区三角形结构
 */

const props = defineProps({
  leftPercent: {
    type: Number,
    default: 20
  }
})

</script>

<style lang="scss">
.content-search {
  display: flex;
  flex-direction: column;
  height: inherit;

  .search-box {
    padding: 10px 16px;
    background-color: #9e9e9e26;

    .n-form--inline {
      flex-wrap: wrap;

      .n-input {
        //max-height: 32px;
      }
    }
  }

  .info-body {
    display: flex;
    height: 100%;

    .left-box {
      height: 100%;
      padding: 12px 0 12px 12px;
      box-sizing: border-box;

      .left-tool-box {
        border: 1px solid #7f7f7f3d;
        height: 100%;
        box-sizing: border-box;
        overflow: auto;
      }
    }

    .right-box {
      height: 100%;
      display: flex;
      flex-direction: column;

      .tool-box {
        padding: 12px 16px 0 16px;
        display: flex;

        .n-button {
          margin-right: 15px;
          height: 28px;
        }
      }

      .content-box {
        padding: 12px;
        flex: 1;

        .n-data-table {
          height: 100%;
        }

        .select-table-row td {
          background-color: rgba(182, 221, 184, 0.2);
          color: var(--n-th-icon-color-active);
        }
      }
    }
  }

}
</style>
