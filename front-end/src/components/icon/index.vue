<template>
  <n-icon :size="props.size" :color="props.color" class="icon-wrapper">
    <svg aria-hidden="true">
      <use :xlink:href="name"/>
    </svg>
  </n-icon>
</template>

<script>
export default {
  name: "cIcon",
  props: {
    icon: {
      type: String,
      default: 'Accessibility'
    },
    size: {
      type: String,
      default: '18'
    },
    color: {
      type: String,
      default: 'black'
    }
  },
  setup(props) {
    const name = `#icon-${props.icon}`
    return {
      props, name
    }
  }
}

</script>

<style lang="scss" scoped>
.icon-wrapper {

}
</style>
