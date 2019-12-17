<template>
  <div id="rank-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination :page-size="pageSize" :total="itemCount" @current-change="getPage"
                     background
                     element-loading-spinner="el-icon-more-outline" id="rank-list-pagination"
                     layout="prev, pager, next" v-show="itemCount > pageSize">
      </el-pagination>

      <el-table :data="tableData" stripe>

        <el-table-column label="排名" prop="rank" width="75px"/>
        <el-table-column label="用户名" prop="username" width="150px"/>
        <el-table-column label="昵称" prop="nickname"/>
        <el-table-column label="正确" prop="solvedCount" width="75px"/>
        <el-table-column label="提交" prop="submitCount" width="75px"/>
        <el-table-column label="正确率" prop="ratio" width="75px"/>
        <el-table-column label="操作" v-if="isAdmin" width="60px">
          <template slot-scope="scope">
            <span @click="lockOrUnlock(scope.row)" class="user-lock-unlock">{{scope.row.locked ? '解封' : '封禁'}}</span>
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
