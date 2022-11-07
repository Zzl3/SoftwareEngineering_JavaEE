import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

// 导入刚才编写的组件
import AdminIndex from '@/pages/Admin/AdminIndex'
import AppIndex from '@/pages/AppIndex'
import Login from '@/pages/Login'
import MyPage from '@/pages/Mypage'
import ProjectAll from '@/pages/Project/ProjectAll'
import ProjectMy from '@/pages/Project/ProjectMy'
import ProjectAdd from '@/pages/Project/ProjectAdd'
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
        requireAuth: true
      }
    },
    {
      path: '/admin/index',
      name: 'AdminIndex',
      component: AdminIndex,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/mypage',
      name: 'MyPage',
      component: MyPage,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/project/allproject',
      name: 'ProjectAll',
      component: ProjectAll,
      meta: {
        requireAuth: true //需要被拦截,只有登录后才能访问
      }
    },
    {
      path: '/project/myproject',
      name: 'ProjectMy',
      component: ProjectMy,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/project/addproject',
      name: 'ProjectAdd',
      component: ProjectAdd,
      meta: {
        requireAuth: true
      }
    },
  ]
})
