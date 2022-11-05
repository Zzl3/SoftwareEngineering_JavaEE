<template>
  <div>
    <el-menu
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1">自习教室</el-menu-item>
      <el-submenu index="2">
        <template slot="title">组队招募</template>
        <el-menu-item index="2-1">组队项目</el-menu-item>
        <el-menu-item index="2-2">我要组队</el-menu-item>  <!--我要组队包括新建和修改-->
      </el-submenu>
      <el-menu-item index="3">资料分享</el-menu-item>
      <el-menu-item index="4">按时打卡</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  data() {
    return {};
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>
