<template>
  <div id="rank-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination id="rank-list-pagination" layout="prev, pager, next" background
                     element-loading-spinner="el-icon-more-outline"
                     v-show="itemCount > pageSize" :page-size="pageSize"
                     :total="itemCount" @current-change="getPage">
      </el-pagination>

      <el-table stripe :data="tableData">

        <el-table-column prop="rank" label="排名" width="75px"/>
        <el-table-column prop="username" label="用户名" width="150px"/>
        <el-table-column prop="nickname" label="昵称"/>
        <el-table-column prop="solvedCount" label="正确" width="75px"/>
        <el-table-column prop="submitCount" label="提交" width="75px"/>
        <el-table-column prop="ratio" label="正确率" width="75px"/>
        <el-table-column v-if="isAdmin" label="操作" width="60px">
          <template slot-scope="scope">
            <span class="user-lock-unlock" @click="lockOrUnlock(scope.row)">{{scope.row.locked ? '解封' : '封禁'}}</span>
          </template>
        </el-table-column>

      </el-table>
    </t-zoom-in-top>
  </div>
</template>

<script>
export default {
  name: 'Rank',
  data () {
    return {
      show: false,
      loading: true,
      itemCount: 20,
      pageSize: 20,
      pageIndex: 1,
      tableData: []
    }
  },
  methods: {
    getPage (index) {
      this.loading = true
      this.pageIndex = index
      this.$ajax.get('/rank/getRankPage', {
        index: index
      }).then(res => {
        for (let i = 0; i < res.length; i++) {
          res[i].rank = this.pageSize * (this.pageIndex - 1) + i + 1
          if (res[i].submitCount === 0) {
            res[i].ratio = 0 + '%'
            continue
          }
          res[i].ratio = Math.round((res[i].solvedCount / res[i].submitCount) * 100 * 100) / 100 + '%'
        }

        this.tableData = res
        this.loading = false
      })
    },
    lockOrUnlock (user) {
      this.$ajax.get('/user/lockOrUnlock', {
        id: user.id
      }).then(res => {
        user.locked = res === 'locked'
        if (res === 'locked') {
          this.$message('封禁成功')
        } else {
          this.$message('解封成功')
        }
      })
    }
  },
  created () {
    this.$ajax.get('/rank/getPageInfo').then(res => {
      this.itemCount = res.itemCount
      this.pageSize = res.pageSize
      this.getPage(1)
      this.show = true
    })
  },
  computed: {
    isAdmin () {
      return this.$store.state.status.isAdmin
    }
  },
  mounted () {
    this.show = true
  }
}
</script>

<style scoped>
  #rank-list {
    width: 90%;
    height: 100%;
    margin: auto;
  }

  #rank-list-pagination {
    text-align: center;
  }

  .user-lock-unlock {
    cursor: pointer;
    color: #0066CC;
  }
</style>
