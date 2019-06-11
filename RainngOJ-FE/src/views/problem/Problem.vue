<template>
  <div id="problem-list" v-loading="loading">
    <t-zoom-in-top>
      <el-pagination id="problem-list-pagination" layout="prev, pager, next"
                     background element-loading-spinner="el-icon-more-outline"
                     v-show="itemCount > pageSize" :page-size="pageSize"
                     :total="itemCount" @current-change="getPage">
      </el-pagination>

      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="ID" width="75px"/>

        <el-table-column prop="title" label="标题">
          <template slot-scope="scope">
            <span class="cursor-pointer" @click="showProblem(scope.row.id)">{{scope.row.title}}</span>
          </template>
        </el-table-column>

        <el-table-column prop="solvedCount" label="正确" width="75px"/>
        <el-table-column prop="submitCount" label="提交" width="75px"/>
        <el-table-column prop="ratio" label="正确率" width="75px"/>

        <el-table-column v-if="isAdmin" label="操作" width="60px">
          <template slot-scope="scope">
            <span class="problem-edit" @click="editProblem(scope.row.id)">编辑</span>
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
