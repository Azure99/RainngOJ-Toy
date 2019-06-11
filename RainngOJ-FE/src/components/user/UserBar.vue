<template>
  <div>
    <div id="nav-user-bar">
      <t-zoom-in-top>
        <div v-if="!this.$store.state.status.isLogin">
          <el-button type="primary" icon="el-icon-user" round @click="loginDialog">登录</el-button>
          <el-button type="success" icon="el-icon-edit" round @click="register">注册</el-button>
        </div>
        <div v-else>
          <el-button type="primary" round>{{this.$store.state.status.username}}</el-button>
          <el-button type="info" round @click="logout">注销</el-button>
        </div>
      </t-zoom-in-top>
    </div>

    <el-dialog title="登录" :visible.sync="loginDialogShow" width="30%">
      <user-login/>
    </el-dialog>
  </div>

</template>

<script>
import ElementUI from 'element-ui'
import UserLogin from '@/components/user/UserLogin'

export default {
  name: 'UserBar',
  components: { UserLogin },
  data () {
    return {
      loginDialogShow: false
    }
  },
  methods: {
    loginDialog () {
      this.loginDialogShow = true
    },
    register () {
      this.$router.push({ name: 'UserRegister' })
    },
    logout () {
      this.$ajax.get('/user/logout').then(res => {
        this.$store.commit('logout')
        ElementUI.Message({ showClose: true, duration: 2000, message: '注销成功', type: 'success' })
      })
    }
  }
}
</script>

<style scoped>
  #nav-user-bar {
    margin-top: 8px;
    text-align: right;
  }
</style>
