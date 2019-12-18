<template>
  <div id="contest-detail" v-loading="loading">
    <t-zoom-in-top>
      <h2 id="contest-detail-title">{{contest.title}}</h2>

      <el-card shadow="always">
        <div v-html="contest.description">{{contest.description}}</div>
      </el-card>

      <el-table :data="contest.problems" id="contest-problem-list" stripe>
        <el-table-column label="ID" prop="id" width="75px"/>

        <el-table-column label="标题" prop="title">
          <template slot-scope="scope">
            <span @click="showProblem(scope.row.id)" class="cursor-pointer">{{scope.row.title}}</span>
          </template>
        </el-table-column>

        <el-table-column label="正确" prop="solvedCount" width="75px"/>
        <el-table-column label="提交" prop="submitCount" width="75px"/>
        <el-table-column label="正确率" prop="ratio" width="75px"/>

      </el-table>
    </t-zoom-in-top>
  </div>
</template>

<script>
export default {
  name: 'ContestDetail',
  data () {
    return {
      loading: true,
      contest: {
        id: 1,
        title: '',
        description: '',
        startTime: '',
        endTime: '',
        problems: []
      }
    }
  },
  methods: {
    showProblem (id) {
      this.$router.push({ name: 'ProblemDetail', params: { id: id } })
    }
  },
  created () {
    this.$ajax.get('/contest/getContest', {
      id: this.$route.params.id
    }).then(res => {
      this.contest = res

      let problems = res.problems
      for (let i = 0; i < problems.length; i++) {
        if (problems[i].submitCount === 0) {
          problems[i].ratio = '0%'
        } else {
          problems[i].ratio = Math.round((problems[i].solvedCount / problems[i].submitCount) * 100 * 100) / 100 + '%'
        }
      }

      this.loading = false
    })
  }
}
</script>

<style scoped>
  #contest-detail-title {
    text-align: center;
  }

  #contest-detail {
    width: 90%;
    margin: auto;
  }

  #contest-problem-list {
    margin-top: 20px;
  }

  .cursor-pointer {
    cursor: pointer;
  }
</style>
