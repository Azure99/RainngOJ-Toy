<template>
  <div id="problem-submit">
    <div id="problem-submit-head">
      编程语言:
      <el-select v-model="language" placeholder="请选择您的语言">
        <el-option
          v-for="item in langOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button @click="submitCode">提交代码</el-button>
    </div>
    <div id="problem-submit-code">
      <el-input
        type="textarea"
        :rows="25"
        placeholder="请粘贴代码"
        v-model="sourceCode">
      </el-input>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProblemSubmit',
  props: ['pid', 'dbJudge', 'langOptions', 'language'],
  data () {
    return {
      sourceCode: ''
    }
  },
  methods: {
    submitCode () {
      this.$ajax.post('/solution/submitSolution', {
        problemId: this.pid,
        sourceCode: this.sourceCode,
        language: this.language
      }).then(res => {
        this.$message(res)
        this.$router.push({ name: 'Status' })
      })
    }
  }
}
</script>

<style scoped>
  #problem-submit-head {
    text-align: center;
  }

  #problem-submit-code {
    margin-top: 15px;
  }
</style>
