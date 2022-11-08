import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//分别是用户开始界面和登录界面
import UserIndex from '@/pages/User/UserIndex'
import Login from '@/pages/Login/Login'

//这里是管理员模块
import AdminPage from '@/pages/Admin/Adminpage'

//这里是用户模块
import MyPage from '@/pages/Mypage/Mypage'

//这里是项目模块
import Project from '@/pages/Project/Project'
import ProjectAll from '@/pages/Project/ProjectAll'
import ProjectMy from '@/pages/Project/ProjectMy'
import ProjectAdd from '@/pages/Project/ProjectAdd'

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
      chileren: [

      ],
      props: true
    },

    //个人中心界面
    {
      path: '/user',
      component: MyPage,
      chileren: [

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
        }
      ],
      props: true
    }
  ]
})
