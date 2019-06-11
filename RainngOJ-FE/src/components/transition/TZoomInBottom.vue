<template>
  <transition name="el-zoom-in-bottom">
    <div v-show="show">
      <slot></slot>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'TZoomInBottom',
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
