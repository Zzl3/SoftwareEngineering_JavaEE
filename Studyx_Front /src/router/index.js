import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

// 导入刚才编写的组件
import AppIndex from '@/pages/home/AppIndex'
import Login from '@/pages/Login'
export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      redirect: 'login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex,
      meta: {
        requireAuth: true //需要被拦截,只有登录后才能访问
      }
    },
  ]
})
