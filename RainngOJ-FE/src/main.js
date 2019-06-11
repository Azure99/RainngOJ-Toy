import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueClipboard from 'vue-clipboard2'
import App from './App.vue'
import router from './router'
import store from './store'
import ajax from './common/ajax'
import TZoomInTop from '@/components/transition/TZoomInTop'
import TZoomInBottom from '@/components/transition/TZoomInBottom'

Vue.config.productionTip = false
Vue.prototype.$ajax = ajax

Vue.use(ElementUI)
Vue.use(VueClipboard)

Vue.component('TZoomInTop', TZoomInTop)
Vue.component('TZoomInBottom', TZoomInBottom)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
