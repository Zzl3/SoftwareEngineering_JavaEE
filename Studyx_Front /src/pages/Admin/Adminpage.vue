<template>
  <div id="admin_page">
    <div>
      <!-- 这里是头部 -->
      <Topnav></Topnav>
    </div>
    <div id="body">
      <!-- 这里是侧边栏 -->
      <Sidebar></Sidebar>
      <!-- 这里是中间部分 -->
      <div id="main">
        这里是管理员页面
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from "../../components/AdminCom/Sidebar";
import Topnav from "../../components/AdminCom/Topnav";
export default {
  name: "Adminindex",
  components: { Sidebar, Topnav },
  data() {
    return {
      isCollapse: true,
    };
  },
  methods: {
    back() {
      this.$router.push({
        path: "/login",
      });
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style>
#admin_page {
  /*background-color: #ecf0f5;*/
  background-color: white;
  background-size: 100% 100%;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>