<template>
  <div id="problem-detail" v-loading="loading" v-show="show">
    <t-zoom-in-top>
      <h2 id="problem-detail-title">{{problem.title}}</h2>
      <div id="problem-metadata">
        <span><i class="el-icon-time"></i>时限{{Math.round(problem.timeLimit / 1000 * 10) / 10}}s</span>&nbsp;|&nbsp;
        <span><i class="el-icon-pie-chart"></i>内存{{Math.round(problem.memoryLimit / 1024)}}MB</span>&nbsp;|&nbsp;
        <span><i class="el-icon-document"></i>提交{{problem.submitCount}}</span>&nbsp;|&nbsp;
        <span><i class="el-icon-check"></i>解决{{problem.solvedCount}}</span>
      </div>

      <el-tabs id="problem-detail-main" type="border-card" v-if="!problem.dbJudge">
        <el-tab-pane label="题目">
          <problem-info :problem="problem"/>
        </el-tab-pane>
        <el-tab-pane label="提交">
          <problem-submit :dbJudge="problem.dbJudge" :langOptions="langOptions" :language="language" :pid="problem.id"/>
        </el-tab-pane>
      </el-tabs>
      <el-row :gutter="20" v-else>
        <el-col :span="16">
          <problem-info :problem="problem"/>
        </el-col>
        <el-col :span="8">
          <problem-submit :dbJudge="problem.dbJudge" :langOptions="langOptions" :language="language" :pid="problem.id"
                          id="db-submit"/>
        </el-col>
      </el-row>
    </t-zoom-in-top>
  </div>
</template>

<script>
import config from '@/common/config'
import ProblemInfo from '@/components/problem/ProblemInfo'
import ProblemSubmit from '@/components/problem/ProblemSubmit'

export default {
  name: 'ProblemDetail',
  components: { ProblemSubmit, ProblemInfo },
  data () {
    return {
      show: false,
      loading: true,
      problem: {
        id: 1,
        title: ' ',
        description: ' ',
        input: ' ',
        output: ' ',
        sampleInput: ' ',
        sampleOutput: ' ',
        hint: ' ',
        timeLimit: 1000,
        memoryLimit: 262144,
        submitCount: 0,
        solvedCount: 0,
        dbJudge: false
      },
      langOptions: [],
      language: 0
    }
  },
  methods: {
    updateLangOptions () {
      this.langOptions = []

      let languages = config.languages
      if (this.problem.dbJudge) {
        languages = config.dbLanguages
      }

      for (let i = 0; i < languages.length; i++) {
        this.langOptions.push({
          value: languages[i].id,
          label: languages[i].name
        })
      }

      this.language = this.langOptions[0].value
    }
  },
  created () {
    this.$ajax.get('/problem/getProblem', {
      id: this.$route.params.id
    }).then(res => {
      this.problem = res
      this.updateLangOptions()
      this.loading = false
    })
  },
  mounted () {
    this.show = true
  }
}
</script>

<style scoped>
  #problem-detail {
    width: 100%;
  }

  #problem-detail-title {
    text-align: center;
    margin-top: 0px;
  }

  #problem-metadata {
    margin-top: -10px;
    color: #303131;
    font-size: 12px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
  }

  #problem-detail-main {
    margin-top: 10px;
    width: 95%;
    margin-left: auto;
    margin-right: auto;
    min-width: 850px;
  }

  #db-submit {
    margin-top: 5px;
  }
</style>
