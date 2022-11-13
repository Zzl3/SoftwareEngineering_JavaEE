<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
        <el-breadcrumb-item :to="{ path: '/user' }">我的页面</el-breadcrumb-item>
        <el-breadcrumb-item
          ><a href="/user/mycollection">我的收藏</a></el-breadcrumb-item
        >
      </el-breadcrumb>
    <Collectioncard></Collectioncard>
  </div>
</template>

<script>
import Collectioncard from "@/components/MypageCom/Collectioncard";
export default {
  components: { Collectioncard },
};
</script>

<style scoped>
</style>