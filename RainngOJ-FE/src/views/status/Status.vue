<template>
  <div id="status-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination id="status-list-pagination" layout="prev, pager, next"
                     background element-loading-spinner="el-icon-more-outline"
                     v-show="itemCount > pageSize"
                     :page-size="pageSize" :total="itemCount" @current-change="getPage">
      </el-pagination>

      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="提交Id" width="85px" align="center"/>
        <el-table-column prop="problemId" label="题目Id" width="80px" align="center">
          <template slot-scope="scope">
            <span class="status-list-problemid" @click="showProblem(scope.row.problemId)">{{scope.row.problemId}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="username" label="用户名" align="center"/>

        <el-table-column prop="result" label="结果" align="center">
          <template slot-scope="scope">
            <status-result :status="scope.row"/>
          </template>
        </el-table-column>

        <el-table-column prop="timeCost" label="时间" width="90px" align="center"/>
        <el-table-column prop="memoryCost" label="内存" width="95px" align="center"/>
        <el-table-column prop="language" label="语言" width="80px" align="center">
          <template slot-scope="scope">
            <span v-if="currentUsername === scope.row.username || isAdmin"
                  class="status-list-language" @click="showSolution(scope.row.id)">
              {{scope.row.language}}
            </span>
            <span v-else>{{scope.row.language}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="codeLength" label="长度" width="70px" align="center"/>
        <el-table-column prop="submitTime" label="提交时间" width="155px" align="center"/>
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
