// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import store from './store'
import axios from "axios";
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import global from './utils/global';
import * as echarts from 'echarts';
//需要挂载到Vue原型上
Vue.prototype.$echarts = echarts; //echarts
Vue.prototype.$myglobal = global; //global

Vue.config.productionTip = false
Vue.use(ElementUI)
//设置端口
Vue.prototype.$axios = axios
//axios.defaults.baseURL ='http://1.15.177.18:8443/api'
axios.defaults.baseURL = 'http://localhost:8443/api'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  // store,
  components: {
    App
  },
  template: '<App/>'
})