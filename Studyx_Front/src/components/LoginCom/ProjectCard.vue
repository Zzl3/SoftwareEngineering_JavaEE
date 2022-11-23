<template>
  <div id="app">
    <h2 style="text-align: left; padding-left: 8vw; margin-bottom: 0">
      {{ CardTitle }}
    </h2>
    <h4
      style="
        padding-left: 8vw;
        text-align: left;
        color: #bebaba;
        font-weight: normal;
        margin-top: 0;
        margin-bottom:30px;
      "
    >
      {{ CardDescription }}
    </h4>
    <el-divider style="width: 80% !important;"> </el-divider>
    <div class="wrap">
      <div class="box-top">
        <div class="image"></div>
      </div>
      <div class="box-bottom">
        <h2>{{ projectname }}</h2>
        <span>{{ projectdetail }}</span>
      </div>
      <div class="box-bottom2">
        <ul>
          <li>{{ otherdetail }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    projectname: String,
    projectdetail: String,
    otherdetail: String,
    CardTitle: String,
    CardDescription: String,
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}

#body {
  background-color: rgb(250, 220, 245);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* -------------------------------------------- */
/* 外层容器 */
/* -------------------------------------------- */
.wrap {
  position: relative;
  width: 330px;
  height: 260px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 0 10px rgb(0, 0, 0, 0.5);
  overflow: hidden;
  border-radius: 0 0 30px 30px;
  margin:40px;
  margin-left:80px;
}
/* -------------------------------------------- */
/* 上部分的块 */
/* -------------------------------------------- */
.box-top {
  position: relative;
  width: 330px;
  height: 130px;
  background-color: rgb(180, 180, 210);
  display: flex;
  justify-content: center;
  transition: 0.5s;
}
.box-top::after {
  content: "";
  width: 40px;
  height: 40px;
  clip-path: polygon(50% 40%, 0 0, 100% 0);
  background-color: rgb(180, 180, 210);
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  bottom: -38px;
  z-index: 999;
}
.image {
  position: absolute;
  top: 20px;
  width: 90px;
  height: 90px;
  background-image: url("../../assets/1.png");
  background-size: cover;
  border: 5px solid rgb(140, 140, 170);
  transition: 0.5s;
}
/* -------------------------------------------- */
/* 下部分的两个大块 */
/* -------------------------------------------- */
.box-bottom,
.box-bottom2 {
  position: absolute;
  top: 130px;
  width: 330px;
  height: 130px;
  text-align: center;
  transition: 0.5s;
}
.box-bottom h2 {
  display: block;
  margin: 30px 0 10px 0;
  font: 800 28px "Hobo Std";
}
.box-bottom span {
  font: 400 24px "Giddyup Std";
}
.box-bottom h2::after {
  content: "";
  display: block;
  width: 200px;
  height: 2px;
  background-color: #000;
  position: absolute;
  left: 50%;
  transform: translate(-50%, 4px);
}
/* -------------------------------------------- */
/* 下部分的第二个大块 */
/* -------------------------------------------- */
.box-bottom2 {
  top: 300px;
  height: 220px;
}
.box-bottom2 li {
  margin: 25px;
  font: 800 20px "OCR A Std";
  transition: 0.2s;
}
/* -------------------------------------------- */
/* 鼠标移入的效果 */
/* -------------------------------------------- */
.wrap:hover .box-top {
  height: 50px;
}
.wrap:hover .image {
  width: 35px;
  height: 35px;
  border-width: 3px;
  transform: translate(-140px, -15px);
}
.wrap:hover .box-bottom {
  top: 300px;
}
.wrap:hover .box-bottom2 {
  top: 50px;
}
li:hover {
  color: rgb(100, 160, 160);
}

</style>