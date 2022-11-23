<template>
  <div id="admin_page">
    <div>
      <!-- 这里是头部 -->
      <Navigation></Navigation>
    </div>
    <div id="body">
      <!-- 这里是侧边栏 -->
      <Sidebar></Sidebar>
      <!-- 这里是中间部分 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Navigation from "@/components/NavigaCom/Navigation";
import Sidebar from "@/components/MypageCom/Sidebar";
export default {
  name: "Mypage",
  components: { Navigation, Sidebar },
};
</script>

<style>
</style>
