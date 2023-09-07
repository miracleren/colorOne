<template>
  <n-button color="#8a2be2" @click="()=>{showModal = !showModal}">
    <template #icon>
      <icon v-if="resetIcon" :icon="curIcon" size="18"></icon>
    </template>
    {{ curIcon === 'Image' ? '请选择图标' : curIcon }}
  </n-button>
  <n-modal v-model:show="showModal" :mask-closable="false" preset="dialog"
           title="图标"
           style="width: 800px;"
           @negative-click="()=>{showModal = false}">
    <div class="show-icon-box">
      <div v-for="item in showIcon" class="icon-box" @click="setIcon(item)">
        <icon :icon="item" size="30"></icon>
        <span class="icon-name">{{ item.length > 10 ? item.substring(0, 10) + '..' : item }}</span>
      </div>
    </div>
  </n-modal>
</template>

<script setup>
import icon from '@/components/icon'
import {computed, onMounted, ref} from 'vue'

const props = defineProps({
  value: {type: String, default: 'Image'}
})


const iconFiles = require.context('@/assets/icons/svg/', true, /\.svg$/).keys()

onMounted(() => {
  console.log('mounted', props.value)
  curIcon.value = props.value
  resetIcon.value = true
  getGroupIcon()
})

/** 显示分组图标 **/
const curIcon = computed({
  get: () => {
    return props.value
  },
  set: (val) => {
    if (val !== curIcon.value) {
      emit('update:value', val)
    }
  }
})
const showIcon = ref([])
const resetIcon = ref(false)
const showModal = ref(false)

/** 加载图标目录下所有svg图标 **/
const getGroupIcon = () => {
  let names = [], pattern = /(?<=\/)(.+?)(?=\.svg)/g
  for (let i = 0; i < iconFiles.length; i++) {
    let name = iconFiles[i].match(pattern)[0]
    if (name)
      names.push(name)
  }
  showIcon.value = names
}


/** 选定图标事件 **/
const emit = defineEmits(['update:value'])
const setIcon = (name) => {
  resetIcon.value = false
  showModal.value = false
  curIcon.value = name
  setTimeout(function () {
    resetIcon.value = true
  }, 1000)
}
</script>

<style lang="scss">
.show-icon-box {
  width: 100%;
  height: 600px;
  overflow: auto;

  .icon-box {
    float: left;
    width: 100px;
    text-align: center;
    padding: 10px;
    cursor: pointer;

    .icon-name {
      display: block;
      font-size: 9px;
    }
  }
}
</style>
