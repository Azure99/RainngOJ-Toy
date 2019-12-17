<template>
  <div id="nav-bar">
    <el-row>
      <el-col :span="2">
        <div id="nav-bar-name">
          RainngOJ
        </div>
      </el-col>

      <el-col :span="14">
        <el-menu :default-active="activeIndex" mode="horizontal" ref="rootMenu" router>
          <el-menu-item index="/"><i class="el-icon-s-home"></i>首页</el-menu-item>
          <el-menu-item index="/problem"><i class="el-icon-edit"></i>题目</el-menu-item>
          <el-menu-item index="/contest"><i class="el-icon-tickets"></i>作业</el-menu-item>
          <el-menu-item index="/rank"><i :active="true" class="el-icon-info"></i>排名</el-menu-item>
          <el-menu-item index="/status"><i class="el-icon-caret-right"></i>状态</el-menu-item>
        </el-menu>
      </el-col>

      <el-col :span="8">
        <user-bar/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import UserBar from '@/components/user/UserBar'

export default {
  name: 'NavBar',
  components: { UserBar },
  data () {
    return {
      activeIndex: this.$route.path
    }
  },
  watch: {
    $route (to, from) {
      this.$refs.rootMenu.activeIndex = to.path
    }
  }
}
</script>

<style scoped>
  #nav-bar {
    height: 100%;
    border-bottom: solid 1px #e6e6e6;
  }

  #nav-bar-name {
    text-align: left;
    color: #303133;
    font-size: 16px;
    margin-top: 21px;
  }
</style>
