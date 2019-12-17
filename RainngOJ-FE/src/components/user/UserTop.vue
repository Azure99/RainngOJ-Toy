<template>
  <div id="top-users">
    <el-table :data="tableData" height="400px" id="top-users-table" size="mini" stripe
              v-loading="loading">
      <el-table-column label="#" prop="rank" width="37px">
      </el-table-column>

      <el-table-column align="center" label="用户" prop="username">
      </el-table-column>

      <el-table-column label="解决" prop="solvedCount" width="50px">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'UserTop',
  data () {
    return {
      loading: true,
      tableData: []
    }
  },
  methods: {
    getTopUsers () {
      this.$ajax.get('/user/getTopUsers').then(res => {
        this.tableData = res
        this.loading = false
      })
    }
  },
  created () {
    this.getTopUsers()
  }
}
</script>

<style scoped>
  #top-users-table {
    width: 100%;
    font-size: 13px;
  }
</style>
