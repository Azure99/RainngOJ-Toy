<template>
  <div id="top-users">
    <el-table id="top-users-table" :data="tableData" size="mini" height="400px" stripe
              v-loading="loading">
      <el-table-column prop="rank" label="#" width="37px">
      </el-table-column>

      <el-table-column prop="username" label="用户" align="center">
      </el-table-column>

      <el-table-column prop="solvedCount" label="解决" width="50px">
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
