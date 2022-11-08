<template>
  <div class="left-nav">
    <!-- <button @click="isCollapse=!isCollapse">展开</button> -->
    <div class="nav-logo">
      <span>
        <!-- <i class="el-icon-platform-eleme logo"></i> -->
        <!-- <img src="../../assets/images/通讯录.png" alt /> -->
        <span v-show="!isCollapse">通讯录管理</span>
      </span>
      <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
        <i
          v-show="!isCollapse"
          class="el-icon-s-fold expandBtn"
          @click="isCollapse = true"
        ></i>
        <i
          v-show="isCollapse"
          class="el-icon-s-fold shrinkBtn"
          @click="isCollapse = false"
        ></i>
      </el-radio-group>
    </div>
    <el-menu
      class="el-menu-vertical-demo"
      :unique-opened="true"
      router
      background-color="#151d41"
      text-color="#fff"
      :collapse="isCollapse"
    >
      <el-menu-item index="/">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-submenu
        v-for="(item, index) in menuList"
        :index="'' + index"
        :key="index"
      >
        <template slot="title">
          <i :class="item.icon"></i>
          <span>{{ item.authName }}</span>
        </template>
        <el-menu-item
          v-for="(subItem, index) in item.children"
          :index="'/' + subItem.path"
          :key="index"
        >
          <span>{{ subItem.authName }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>
 
<script>
export default {
  data() {
    return {
      menuList: [
        {
          id: 0,
          authName: "通讯录",
          icon: "el-icon-s-custom",
          children: [
            {
              authName: "用户与部门管理",
              id: 1,
              path: "department",
              parentid: 0,
            },
            { authName: "通讯录设置", id: 2, path: "adrbook", parentid: 0 },
          ],
        },
        {
          id: 1,
          authName: "数据报表",
          icon: "el-icon-s-claim",
          children: [
            { authName: "数据概览", id: 1, path: "overview", parentid: 1 },
            { authName: "员工活跃数据", id: 2, path: "employees", parentid: 1 },
          ],
        },
        {
          id: 2,
          authName: "企业设置",
          icon: "el-icon-office-building",
          children: [
            {
              authName: "企业信息管理",
              id: 1,
              path: "information",
              parentid: 2,
            },
            { authName: "权限管理", id: 2, path: "authority", parentid: 2 },
            { authName: "团队邀请设置", id: 3, path: "team", parentid: 2 },
            { authName: "安全策略", id: 4, path: "safety", parentid: 2 },
            { authName: "管理员日志", id: 5, path: "journal", parentid: 2 },
          ],
        },
      ],
      isCollapse: false,
    };
  },
  methods: {},
};
</script>
 
<style scoped>
.left-nav .nav-logo {
  height: 60px;
  padding: 0 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  line-height: 60px;
  margin-top: 16px;
}
.left-nav .nav-logo .logo {
  font-size: 30px;
}
.left-nav .nav-logo img {
  width: 28px;
  margin: 5px;
}
.left-nav .nav-logo .el-radio-group {
  font-size: 20px;
  line-height: 60px;
  margin-top: 20px;
  margin-left: 10px;
}
.left-nav .nav-logo .el-radio-group .shrinkBtn {
  position: fixed;
  left: -18px;
  top: 38px;
  color: #151d41;
  margin-left: 100px;
  transform: rotate(180deg);
}
.left-nav .el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.left-nav .el-menu-vertical-demo {
  border: none;
}
.left-nav .el-menu-vertical-demo .el-menu-item span {
  color: #909399;
  font-size: 16px;
}
.left-nav .el-menu-vertical-demo .el-submenu span {
  color: #909399;
  font-size: 16px;
}
.left-nav .el-menu-vertical-demo .el-submenu .el-menu-item span {
  color: #909399;
  font-size: 14px;
}
</style>