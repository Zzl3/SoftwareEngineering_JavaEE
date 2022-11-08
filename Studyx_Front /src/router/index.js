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
    },
    {
      path: '/admin/index',
      name: 'AdminIndex',
      component: AdminIndex,
    },
    {
      path: '/mypage',
      name: 'MyPage',
      component: MyPage,
    },
    {
      path: '/project/allproject',
      name: 'ProjectAll',
      component: ProjectAll,
    },
    {
      path: '/project/myproject',
      name: 'ProjectMy',
      component: ProjectMy,
    },
    {
      path: '/project/addproject',
      name: 'ProjectAdd',
      component: ProjectAdd,
      // meta: {
      //   requireAuth: true
      // }
    },
  ]
})
