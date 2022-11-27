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
import AdminFeed from '@/pages/Admin/Adminfeed'
import AdminDonation from '@/pages/Admin/Admindonation'
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
import ProjectMy from '@/pages/Project/ProjectMy'
import ProjectAdd from '@/pages/Project/ProjectAdd'
import FeedbackAdd from '@/pages/Project/FeedbackAdd'
import DonationmoneyAdd from '@/pages/Project/DonationmoneyAdd'
import DonationbookAdd from '@/pages/Project/DonationbookAdd'

//这里是书籍管理模块
import BookDisplay from '@/pages/Book/BookDisplay'
import BookParticulars from '@/pages/Book/BookParticulars'

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
        path: '/admin/adminfeed',
        component: AdminFeed,
      },
      {
        path: '/admin/admindonation',
        component: AdminDonation,
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
  //下面开始分成4个模块
  //项目界面
  {
    path: '/project',
    component: Project,
    children: [{
      path: '/project/allproject',
      component: ProjectAll,
    },
    {
      path: '/project/myproject',
      component: ProjectMy,
    },
    {
      path: '/project/addproject',
      component: ProjectAdd,
    },
      {
        path: '/project/addfeedback',
        component: FeedbackAdd,
      },
      {
        path: '/project/adddonationmoney',
        component: DonationmoneyAdd,
      },
      {
        path: '/project/adddonationbook',
        component: DonationbookAdd,
      }
    ],
    props: true
  },
            //书籍
    //书籍分类展示
    {
      path: '/book',
      name: 'book',
      component: BookDisplay,
      children: [{
        path: '/book/particulars',
        name: 'BookParticulars',
        component: BookParticulars,
      },
      ],
      props: true
    },
    //书籍详情
    {
      path: '/bookparticulars',
      name: 'BookParticulars',
      component: BookParticulars,
    },
  ]
})
