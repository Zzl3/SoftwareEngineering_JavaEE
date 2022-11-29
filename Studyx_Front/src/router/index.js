import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//分别是用户开始界面和登录界面
import UserIndex from '@/pages/User/UserIndex'
import Login from '@/pages/Login/Login'

//这里是管理员模块
import AdminPage from '@/pages/Admin/Adminpage'
import AdminBorrow from '@/pages/Admin/Adminborrow'
import AdminUser from '@/pages/Admin/AdminUser'
import AdminFeedback from '@/pages/Admin/Adminfeedback'
import AdminDonationmoney from '@/pages/Admin/Admindonationmoney'
import AdminDonationbook from '@/pages/Admin/Admindonationbook'
import AdminCategory from "@/pages/Admin/AdminCategory"

//这里是用户模块
import Mypage from '@/pages/Mypage/Mypage'
import Myborrow from '@/pages/Mypage/Myborrow'
import Mydonation from '@/pages/Mypage/Mydonation'
import Mycollection from '@/pages/Mypage/Mycollection'
import Myinfo from '@/pages/Mypage/Myinfo'

//这里是项目模块
import Project from '@/pages/Project/Project'
import ProjectAll from '@/pages/Project/ProjectAll'

//这里是书籍管理模块
import BookDisplay from '@/pages/Book/BookDisplay'
import BookParticulars from '@/pages/Book/BookParticulars'
import DataTest from '@/pages/Book/DataTest'
import test from '@/pages/Book/test'
import testDisplay from '@/pages/Book/testDisplay'
import library from '@/pages/Book/library'

//这里是总体书籍模块
import BookAll from '@/pages/BookAll/bookall'

//有children的都是分成模块的
export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      redirect: 'login'
    },
    //登录界面
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    //用户进入界面
    {
      path: '/index',
      component: UserIndex,
    },
    //管理员中心界面
    {
      path: '/admin',
      component: AdminPage,
      redirect: '/admin/adminborrow', //直接重定向到借阅
      children: [{
          path: '/admin/adminborrow',
          component: AdminBorrow,
        },
        {
          path: '/admin/adminuser',
          component: AdminUser,
        },
        {
          path: '/admin/adminfeedback',
          component: AdminFeedback,
        },
        {
          path: '/admin/admindonationmoney',
          component: AdminDonationmoney,
        },
        {
          path: '/admin/admindonationbook',
          component: AdminDonationbook,
        },
        {
          path: '/admin/admincategory',
          component: AdminCategory,
        }
      ],
      props: true
    },

    //个人中心界面
    {
      path: '/user',
      component: Mypage,
      redirect: '/user/myinfo', //直接重定向到个人中心
      children: [{
          path: '/user/myinfo',
          component: Myinfo,
        },
        {
          path: '/user/myborrow',
          component: Myborrow,
        },
        {
          path: '/user/mydonation',
          component: Mydonation,
        },
        {
          path: '/user/mycollection',
          component: Mycollection,
        },
      ],
      props: true
    },

    //用户进入界面
    {
      path: '/bookall',
      component: BookAll,
    },

    //测试界面
    {
      path: '/project',
      component: Project,
      children: [{
        path: '/project/allproject',
        component: ProjectAll,
      }, ],
      props: true

    },
    //书籍界面
    //书籍分类展示
    {
      path: '/book',
      name: 'book',
      component: BookDisplay,
      children: [{
        path: '/book/particulars',
        name: 'BookParticulars',
        component: BookParticulars,
      }, ],
      props: true
    },
    //书籍详情
    {
      path: '/bookparticulars',
      name: 'BookParticulars',
      component: BookParticulars,
    },
    //书籍前后端测试
    {
      path: '/test',
      name: 'test',
      component: test,
    },
    //书籍显示测试
    {
      path: '/testdis',
      name: 'testDisplay',
      component: testDisplay,
    },
    {
      path: '/library',
      name: 'library',
      component: library,
    },
  ]
})
