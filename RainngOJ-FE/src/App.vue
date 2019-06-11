<template>
  <div id="app">
    <el-container>
      <el-header>
        <nav-bar/>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar'

export default {
  components: { NavBar },
  created () {
    this.$ajax.get('/user/getLoginStatus').then(res => {
      if (res.login && res.username.length > 0) {
        this.$store.commit('login', res)
      }
    })
  }
}
</script>

<style lang="scss">
  #app {
    width: 90%;
    min-width: 850px;
    margin: auto;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  .el-main {
    padding: 5px;
  }

  .el-header {
    padding: 0 5px;
  }

  .el-menu-item {
    padding: 0 15px;
  }
</style>
