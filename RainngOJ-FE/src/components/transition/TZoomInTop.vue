<template>
  <transition name="el-zoom-in-top">
    <div v-show="show">
      <slot></slot>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'TZoomInTop',
  data () {
    return {
      show: false
    }
  },
  mounted () {
    this.show = true
  }
}
</script>

<style scoped>

</style>
