// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import store from './store'
import axios from "axios";
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
//设置端口
Vue.prototype.$axios = axios
//axios.defaults.baseURL ='http://1.15.177.18:8443/api'
axios.defaults.baseURL ='http://localhost:8443/api'

//这些是路由拦截， 有后端就不用前端了
// router.beforeEach((to, from, next) => {
//   if (to.meta.requireAuth) {
//     if (store.state.user.username) {
//       next()
//     } else {
//       next({
//         path: 'login',
//         query: {
//           redirect: to.fullPath
//         }
//       })
//     }
//   } else {
//     next()
//   }
// })

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
