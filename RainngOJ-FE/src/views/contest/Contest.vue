<template>
  <div id="contest-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination id="contest-list-pagination" layout="prev, pager, next"
                     background element-loading-spinner="el-icon-more-outline"
                     v-show="itemCount > pageSize" :page-size="pageSize"
                     :total="itemCount" @current-change="getPage">
      </el-pagination>

      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="ID" width="75px"/>

        <el-table-column prop="title" label="标题">
          <template slot-scope="scope">
            <span class="cursor-pointer" @click="showContest(scope.row)">{{scope.row.title}}</span>
          </template>
        </el-table-column>

        <el-table-column prop="status" label="状态" width="160px"/>
        <el-table-column prop="startTime" label="开始时间" width="160px"/>
        <el-table-column prop="endTime" label="截止时间" width="160px"/>

      </el-table>
    </t-zoom-in-top>
  </div>
</template>

<script>
export default {
  name: 'Contest',
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
      this.$ajax.get('/contest/getContestPage', {
        index: index
      }).then(res => {
        let time = new Date()
        for (let i = 0; i < res.length; i++) {
          res[i].jsStartTime = this.parseTime(res[i].startTime)
          res[i].jsEndTime = this.parseTime(res[i].endTime)
          res[i].status = '未开始'
          if (time < res[i].jsStartTime) {
            res[i].status = '未开始'
          } else if (time > res[i].jsEndTime) {
            res[i].status = '已结束'
          } else {
            res[i].status = '进行中'
          }
        }

        this.tableData = res
        this.loading = false
      })
    },
    showContest (contest) {
      if (contest.status === '未开始') {
        this.$message('作业还没有开始')
      } else if (contest.status === '已结束') {
        this.$message('作业已经结束')
      } else {
        this.$router.push({ name: 'ContestDetail', params: { id: contest.id } })
      }
    },
    parseTime (time) {
      time = time.replace(/-/g, ':').replace(' ', ':')
      time = time.split(':')
      return new Date(time[0], (time[1] - 1), time[2], time[3], time[4], time[5])
    }
  },
  created () {
    this.$ajax.get('/contest/getPageInfo').then(res => {
      this.itemCount = res.itemCount
      this.pageSize = res.pageSize
      this.getPage(1)
    })
  }
}
</script>

<style scoped>
  #contest-list {
    width: 90%;
    height: 100%;
    margin: auto;
  }

  #contest-list-pagination {
    text-align: center;
  }

  .cursor-pointer {
    cursor: pointer;
  }
</style>
