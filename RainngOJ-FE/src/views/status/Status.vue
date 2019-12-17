<template>
  <div id="status-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination :page-size="pageSize" :total="itemCount"
                     @current-change="getPage" background
                     element-loading-spinner="el-icon-more-outline"
                     id="status-list-pagination" layout="prev, pager, next" v-show="itemCount > pageSize">
      </el-pagination>

      <el-table :data="tableData" stripe>
        <el-table-column align="center" label="提交Id" prop="id" width="85px"/>
        <el-table-column align="center" label="题目Id" prop="problemId" width="80px">
          <template slot-scope="scope">
            <span @click="showProblem(scope.row.problemId)" class="status-list-problemid">{{scope.row.problemId}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="用户名" prop="username"/>

        <el-table-column align="center" label="结果" prop="result">
          <template slot-scope="scope">
            <status-result :status="scope.row"/>
          </template>
        </el-table-column>

        <el-table-column align="center" label="时间" prop="timeCost" width="90px"/>
        <el-table-column align="center" label="内存" prop="memoryCost" width="95px"/>
        <el-table-column align="center" label="语言" prop="language" width="80px">
          <template slot-scope="scope">
            <span @click="showSolution(scope.row.id)"
                  class="status-list-language" v-if="currentUsername === scope.row.username || isAdmin">
              {{scope.row.language}}
            </span>
            <span v-else>{{scope.row.language}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="长度" prop="codeLength" width="70px"/>
        <el-table-column align="center" label="提交时间" prop="submitTime" width="155px"/>
      </el-table>

    </t-zoom-in-top>
  </div>
</template>

<script>
import config from '@/common/config'
import StatusResult from '../../components/status/StatusResult'

export default {
  name: 'Status',
  components: { StatusResult },
  data () {
    return {
      loading: true,
      itemCount: 20,
      pageSize: 20,
      tableData: []
    }
  },
  methods: {
    getPage (index) {
      this.loading = true
      this.$ajax.get('/status/getStatusPage', {
        index: index
      }).then(res => {
        for (let i = 0; i < res.length; i++) {
          this.setResultFields(res[i])
        }
        setTimeout(() => this.pollStatus(), 1000)

        this.tableData = res
        this.loading = false
      })
    },
    pollStatus () {
      let data = this.tableData
      let queue = []
      for (let i = 0; i < data.length; i++) {
        if (this.isRunning(data[i].result)) {
          queue.push(data[i].id)
        }
      }

      if (queue.length > 0 && this.$route.name === 'Status') {
        let ids = queue.join(',')
        this.$ajax.get('/status/getStatusByIds', {
          ids: ids
        }).then(res => {
          for (let i = 0; i < res.length; i++) {
            if (res[i].result !== 10) {
              this.updateStatus(res[i])
            }
          }
          setTimeout(() => this.pollStatus(), 1000)
        })
      }
    },
    updateStatus (status) {
      let data = this.tableData
      for (let i = 0; i < data.length; i++) {
        if (data[i].id === status.id) {
          data[i].result = status.result
          data[i].timeCost = status.timeCost + 'MS'
          data[i].memoryCost = status.memoryCost + 'KB'
          break
        }
      }
    },
    showProblem (id) {
      this.$router.push({ name: 'ProblemDetail', params: { id: id } })
    },
    showSolution (id) {
      this.$router.push({ name: 'SolutionDetail', params: { id: id } })
    },
    setResultFields (res) {
      res.timeCost = res.timeCost + 'MS'
      res.memoryCost = res.memoryCost + 'KB'
      res.codeLength = res.codeLength + 'B'
      res.language = config.getLanguageNameById(res.language)
      return res
    },
    isRunning (res) {
      return res === 10 || res === 11
    }
  },
  computed: {
    currentUsername () {
      return this.$store.state.status.username
    },
    isAdmin () {
      return this.$store.state.status.isAdmin
    }
  },
  created () {
    this.$ajax.get('/status/getPageInfo').then(res => {
      this.itemCount = res.itemCount
      this.pageSize = res.pageSize
      this.getPage(1)
    })
  }
}
</script>

<style scoped>
  #status-list {
    width: 90%;
    min-width: 1100px;
    height: 100%;
    margin: auto;
  }

  #status-list-pagination {
    text-align: center;
  }

  .status-list-problemid {
    display: block;
    cursor: pointer;
    min-width: 10px;
    color: #0066CC;
  }

  .status-list-language {
    display: block;
    cursor: pointer;
    min-width: 10px;
    color: #0066CC;
  }
</style>
