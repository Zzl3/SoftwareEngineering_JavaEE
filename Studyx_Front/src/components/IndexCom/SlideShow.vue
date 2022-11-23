<template>
  <el-carousel :interval="4000" type="card" height="500px">
    <el-carousel-item v-for="item in imgList" :key="item">
      <img :src="item.img" style="height: 100%; width: 100%" />
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  data() {
    return {
      imgList: [
        { img: require("@/assets/1.png") },
        { img: require("@/assets/2.png") },
        { img: require("@/assets/3.png") },
        { img: require("@/assets/4.png") },
        { img: require("@/assets/5.png") },
        { img: require("@/assets/6.jpg") },
      ],
    };
  },
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  z-index: -1;
}
</style>