<template>
  <el-button @click.native="Login">返回</el-button>
</template>

<script>
export default {
  name: "ReturnButton",
  Login() {
    this.$router.push({
      path: "/Book/BookDislay",
    });
  }
}
</script>

<style scoped>

</style>
