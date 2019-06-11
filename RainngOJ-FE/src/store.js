import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    status: {
      isLogin: false,
      username: '',
      isAdmin: false
    }
  },
  mutations: {
    login (state, res) {
      state.status.isLogin = res.login
      state.status.username = res.username
      state.status.isAdmin = res.admin
    },
    logout (state) {
      state.status.isLogin = false
      state.status.username = ''
      state.status.isAdmin = false
    }
  },
  actions: {}
})
