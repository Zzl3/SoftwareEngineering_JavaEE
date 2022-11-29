<template>
  <section class="picture-section" id="picture">
    <div class="pic-header">
      <h2>照片墙</h2>
      <hr class="line" />
      <span class="intro">Pictures</span>
    </div>
    <div class="pic-wrap">
      <div class="row-wrap">
        <div>
          <img src="@/assets/book2.jpg" alt="" />
        </div>
        <div>
          <img src="@/assets/book6.jpg" alt="" />
        </div>
        <div>
          <img src="@/assets/book7.jpg" alt="" />
        </div>
      </div>
      <div class="row-wrap">
        <div>
          <img src="@/assets/book9.jpeg" alt="" />
        </div>
        <div>
          <img src="@/assets/book10.jpeg" alt="" />
        </div>
        <div>
          <img src="@/assets/book3.png" alt="" />
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style  scoped>
/* 照片墙 */
.picture-section {
  background-color: white;
  padding-top: 30px;
}
.pic-wrap {
  /* margin-top: 20px; */
  /* width: 1300px; */
  padding: 10px 120px 10px;
  margin: 10px auto;
}
.row-wrap {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}
.row-wrap img {
  width: 340px;
  height: 240px;
  box-sizing: border-box;
  border-radius: 6px;
}
.line {
  border: none;
  border-top: 5px solid #bbb;
  max-width: 90px;
  margin: 15px auto 15px;
}
h2 {
  font-weight: 600;
  color: #333;
  letter-spacing: 1px;
  font-family: "Ek Mukta";
  font-size: 36px;
  margin: 0;
}
a {
  text-decoration: none;
}
ul {
  /* 去除默认样式 */
  padding: 0;
  /*去除项目符号 (小点)*/
  list-style: none;
}
</style>
