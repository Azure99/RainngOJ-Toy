<template>
  <div id="user-login">
    <el-form :model="loginForm" :rules="rules" ref="loginForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input show-password v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="login" id="user-login-submit" type="primary">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import ElementUI from 'element-ui'

export default {
  name: 'UserLogin',
  data () {
    return {
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度为3-20', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 32, message: '长度为6-32', trigger: 'blur' }
        ]
      },
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    login () {
      this.$refs['loginForm'].validate(v => {
        if (v) {
          let username = this.loginForm.username
          let password = this.loginForm.password

          this.$ajax.post('/user/login', { username: username, password: password }).then(res => {
            ElementUI.Message({ showClose: true, duration: 2000, message: '登录成功', type: 'success' })
            if (res.admin === true) {
              const h = this.$createElement
              this.$notify({
                title: '欢迎管理员',
                message: h('i', { style: 'color: teal' }, '您的身份是管理员, 您可以额外拥有编辑题目, 封禁用户, 查看所有用户代码等权限')
              })
            }

            this.$store.commit('login', res)
          })
        }
      })
    }
  }
}
</script>

<style scoped>
  #user-login-submit {
    margin: auto;
  }
</style>
