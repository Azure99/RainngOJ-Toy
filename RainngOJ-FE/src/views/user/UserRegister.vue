<template>
  <div id="user-register-form">
    <h2>用户注册</h2>
    <el-form label-position="right" label-width="auto" :model="form" :rules="rules" ref="form">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="学校" prop="school">
        <el-input v-model="form.school"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import ElementUI from 'element-ui'

export default {
  name: 'UserRegister',
  data () {
    return {
      form: {
        username: '',
        password: '',
        email: '',
        nickname: '',
        school: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '用户名长度在3到20之间', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 32, message: '密码长度在6到32之间', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { min: 7, max: 50, message: '邮箱长度在7-50之间', trigger: 'blur' }
        ],
        nickname: [
          { required: false, message: '请输入昵称', trigger: 'blur' },
          { min: 0, max: 20, message: '昵称长度不能超过20', trigger: 'blur' }
        ],
        school: [
          { required: false, message: '请输入学校名称', trigger: 'blur' },
          { min: 0, max: 20, message: '学校长度不能超过20', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    register () {
      this.$refs['form'].validate(v => {
        if (v) {
          this.$ajax.post('/user/register', this.form).then(res => {
            ElementUI.Message({ showClose: true, duration: 2000, message: '注册成功', type: 'success' })
            this.$router.push({ name: 'Home' })
          })
        }
      })
    }
  }
}
</script>

<style scoped>
  #user-register-form {
    width: 40%;
    margin: auto;
    text-align: center;
  }
</style>
