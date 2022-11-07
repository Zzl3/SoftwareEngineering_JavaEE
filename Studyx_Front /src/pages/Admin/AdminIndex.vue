<template>
  <div>
    这里是管理员页面
    <el-button @click="back">返回登录</el-button>
  </div>
</template>

<script>
export default {
  name: "Adminindex",
  methods: {
    back() {
      this.$router.push({
        path: "/login",
      });
    },
  },
};
</script>

<style>
</style>