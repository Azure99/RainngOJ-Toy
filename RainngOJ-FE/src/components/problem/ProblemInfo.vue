<template>
  <div id="problem-info">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1">
        <template slot="title">
          <div class="collapse-title">题目描述</div>
        </template>
        <el-card class="box-card" shadow="hover">
          <div class="collapse-content" v-html="problem.description">{{problem.description}}</div>
        </el-card>
      </el-collapse-item>
      <div v-if="!problem.dbJudge">
        <el-collapse-item name="2">
          <template slot="title">
            <div class="collapse-title">题目输入</div>
          </template>
          <el-card class="box-card" shadow="hover">
            <div class="collapse-content" v-html="problem.input">{{problem.input}}</div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item name="3">
          <template slot="title">
            <div class="collapse-title">题目输出</div>
          </template>
          <el-card class="box-card" shadow="hover">
            <div class="collapse-content" v-html="problem.output">{{problem.output}}</div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item name="4">
          <template slot="title">
            <div class="collapse-title">样例输入</div>
          </template>
          <el-button v-clipboard:copy="problem.sampleInput" v-clipboard:success="onCopySuccess"
                     v-clipboard:error="onCopyError" size="small">复制
          </el-button>
          <el-card class="box-card" shadow="hover">
            <div class="collapse-content">
              <pre>{{problem.sampleInput}}</pre>
            </div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item name="5">
          <template slot="title">
            <div class="collapse-title">样例输出</div>
          </template>
          <el-button v-clipboard:copy="problem.sampleOutput" v-clipboard:success="onCopySuccess"
                     v-clipboard:error="onCopyError" size="small">复制
          </el-button>
          <el-card class="box-card" shadow="hover">
            <div class="collapse-content">
              <pre>{{problem.sampleOutput}}</pre>
            </div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item name="6">
          <template slot="title">
            <div class="collapse-title">提示</div>
          </template>
          <el-card class="box-card" shadow="hover">
            <div class="collapse-content" v-html="problem.hint">{{problem.hint}}</div>
          </el-card>
        </el-collapse-item>
      </div>
    </el-collapse>
  </div>
</template>

<script>
export default {
  name: 'ProblemInfo',
  props: ['problem'],
  data () {
    return {
      activeNames: ['1', '2', '3', '4', '5', '6']
    }
  },
  methods: {
    onCopySuccess () {
      this.$message('复制成功')
    },
    onCopyError () {
      this.$message('复制失败')
    }
  }
}
</script>

<style scoped>
  .collapse-title {
    font-size: 20px;
    font-weight: bold;
    color: #303133;
  }

  .collapse-content {
    font-size: 14px;
  }

  .box-card {
    margin-top: 10px;
  }

  .el-card {
    border: 2px solid #EBEEF5;
  }
  pre {
    margin: 0;
    font-size: 16px;
  }
</style>
