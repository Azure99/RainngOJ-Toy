<template>
  <div id="problem-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination :page-size="pageSize" :total="itemCount"
                     @current-change="getPage" background
                     element-loading-spinner="el-icon-more-outline" id="problem-list-pagination"
                     layout="prev, pager, next" v-show="itemCount > pageSize">
      </el-pagination>

      <el-table :data="tableData" stripe>
        <el-table-column label="ID" prop="id" width="75px"/>

        <el-table-column label="标题" prop="title">
          <template slot-scope="scope">
            <span @click="showProblem(scope.row.id)" class="cursor-pointer">{{scope.row.title}}</span>
          </template>
        </el-table-column>

        <el-table-column label="正确" prop="solvedCount" width="75px"/>
        <el-table-column label="提交" prop="submitCount" width="75px"/>
        <el-table-column label="正确率" prop="ratio" width="75px"/>

        <el-table-column label="操作" v-if="isAdmin" width="60px">
          <template slot-scope="scope">
            <span @click="editProblem(scope.row.id)" class="problem-edit">编辑</span>
          </template>
        </el-table-column>

      </el-table>
    </t-zoom-in-top>
  </div>
</template>

<script>
export default {
  name: 'Problem',
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
      this.$ajax.get('/problem/getProblemPage', {
        index: index
      }).then(res => {
        for (let i = 0; i < res.length; i++) {
          if (res[i].submitCount === 0) {
            res[i].ratio = 0 + '%'
          } else {
            res[i].ratio = Math.round((res[i].solvedCount / res[i].submitCount) * 100 * 100) / 100 + '%'
          }
        }

        this.tableData = res
        this.loading = false
      })
    },
    showProblem (id) {
      this.$router.push({ name: 'ProblemDetail', params: { id: id } })
    },
    editProblem (id) {
      this.$router.push({ name: 'ProblemEdit', params: { id: id } })
    }
  },
  computed: {
    isAdmin () {
      return this.$store.state.status.isAdmin
    }
  },
  created () {
    this.$ajax.get('/problem/getPageInfo').then(res => {
      this.itemCount = res.itemCount
      this.pageSize = res.pageSize
      this.getPage(1)
    })
  }
}
</script>

<style scoped>
  #problem-list {
    width: 90%;
    height: 100%;
    margin: auto;
  }

  #problem-list-pagination {
    text-align: center;
  }

  .cursor-pointer {
    cursor: pointer;
  }

  .problem-edit {
    cursor: pointer;
    color: #0066CC;
  }
</style>
