<template>
  <div id="status-solution">
    <t-zoom-in-top>
      <h2 id="solution-title">代码查看 - {{solution.id}}</h2>
      <div id="problem-metadata">
        <span><i class="el-icon-time"></i>时间{{solution.timeCost}}ms</span>&nbsp;|&nbsp;
        <span><i class="el-icon-pie-chart"></i>内存{{solution.memoryCost}}KB</span>&nbsp;
        <status-result :status="solution"/>
      </div>
      <el-button size="largs" v-clipboard:copy="solution.sourceCode"
                 v-clipboard:error="onCopyError" v-clipboard:success="onCopySuccess">复制
      </el-button>
      <el-card id="solution-code" shadow="always">
        <pre>{{solution.sourceCode}}</pre>
      </el-card>
    </t-zoom-in-top>
  </div>
</template>

<script>
import StatusResult from '../../components/status/StatusResult'

export default {
  name: 'SolutionDetail',
  components: { StatusResult },
  data () {
    return {
      loading: true,
      solution: {}
    }
  },
  methods: {
    onCopySuccess () {
      this.$message('复制成功')
    },
    onCopyError () {
      this.$message('复制失败')
    }
  },
  created () {
    this.$ajax.get('/solution/getSolution', {
      id: this.$route.params.id
    }).then(res => {
      this.solution = res
    })
  }
}
</script>

<style scoped>
  #solution-title {
    text-align: center;
  }

  #problem-metadata {
    color: #303131;
    font-size: 12px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
  }

  #solution-code {
    margin-top: 20px;
  }
</style>
