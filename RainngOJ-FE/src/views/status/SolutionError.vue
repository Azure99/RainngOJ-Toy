<template>
  <div id="solution-error" v-loading="loading">
    <t-zoom-in-top>
      <h2 id="error-title">错误查看 - {{error.id}}</h2>
      <el-card shadow="always">
        <pre id="error-info" v-html="error.info">{{error.info}}</pre>
      </el-card>
    </t-zoom-in-top>
  </div>
</template>

<script>
export default {
  name: 'SolutionError',
  data () {
    return {
      loading: true,
      error: {
        id: null,
        info: ''
      }
    }
  },
  created () {
    this.$ajax.get('/solution/getError', {
      id: this.$route.params.id
    }).then(res => {
      this.error = res
      this.loading = false
    })
  }
}
</script>

<style scoped>
  #error-title {
    text-align: center;
  }

  #error-info {
    font-size: 14px;
  }
</style>
