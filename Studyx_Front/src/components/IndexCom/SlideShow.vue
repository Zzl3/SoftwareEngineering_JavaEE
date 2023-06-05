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
        { img: require("@/assets/book7.jpg") },
        { img: require("@/assets/book2.jpg") },
        { img: require("@/assets/book3.png") },
        { img: require("@/assets/book4.jpeg") },
        { img: require("@/assets/book5.jpeg") },
        { img: require("@/assets/book10.jpeg") },
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