<template>
  <div id="problem-edit" v-loading="loading">
    <h2 id="problem-edit-title">题目编辑 - {{problem.id}}</h2>

    <el-form :model="problem" label-position="right" label-width="auto" ref="form">
      <el-form-item label="标题" prop="title">
        <el-input v-model="problem.title"></el-input>
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="7">
          <el-form-item label="时间限制(MS)" prop="timeLimit">
            <el-input v-model="problem.timeLimit"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="内存限制(KB)" prop="memoryLimit">
            <el-input v-model="problem.memoryLimit"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="类型" prop="dbJudge">
            <el-switch active-text="数据库题目" inactive-text="程序题目" v-model="problem.dbJudge"></el-switch>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="题目描述" prop="description">
        <el-input :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" v-model="problem.description"></el-input>
      </el-form-item>
      <el-form-item label="输入描述" prop="input">
        <el-input :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" v-model="problem.input"></el-input>
      </el-form-item>
      <el-form-item label="输出描述" prop="output">
        <el-input :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" v-model="problem.output"></el-input>
      </el-form-item>
      <el-form-item label="样例输入" prop="description">
        <el-input :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" v-model="problem.sampleInput"></el-input>
      </el-form-item>
      <el-form-item label="样例输出" prop="description">
        <el-input :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" v-model="problem.sampleOutput"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="update" type="primary">更新题目</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'ProblemEdit',
  data () {
    return {
      loading: true,
      problem: {}
    }
  },
  methods: {
    update () {
      this.$refs['form'].validate(v => {
        if (v) {
          this.$ajax.post('/problem/updateProblem', this.problem).then(res => {
            this.$message({ showClose: true, duration: 2000, message: '更新成功', type: 'success' })
            this.$router.push({ name: 'ProblemDetail', params: { id: this.problem.id } })
          })
        }
      })
    }
  },
  created () {
    this.$ajax.get('/problem/getProblem', {
      id: this.$route.params.id
    }).then(res => {
      this.problem = res
      this.loading = false
    })
  }
}
</script>

<style scoped>
  #problem-edit {
    width: 80%;
    margin: auto;
  }

  #problem-edit-title {
    text-align: center;
  }
</style>
