<template>
  <section class="profile-section" id="profile">
    <!-- 简介 -->
    <div class="profile-container">
      <div class="left-area">
        <div class="bg-area">
          <!-- 头像可以链接跳转到其他地方 -->
          <a
            href=""
            target="_blank"
            ><img src="@/assets/2.png" alt="" class="img-curry c-img-left"
          /></a>
          <h3>Studyx</h3>
          <p>
            这里是介绍我们的项目简介-----------------------------------------------
          </p>
          <br />
          <a
            href=""
            target="_blank"
            class="btn-a"
            >了解更多</a
          >
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style  scoped>
.profile-section {
  padding: 50px 15px 50px;
  margin: 20px auto;
}
.profile-container {
  width: 1170px;
  padding-left: 15px;
  padding-right: 15px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 120px;
}
.img-curry {
  border-radius: 50%;
  width: 350px;
  height: 350px;
  margin-top: -120px;
  border: 10px solid rgb(215, 215, 215);
}
.img-curry:hover {
  border: 10px solid rgb(140, 140, 140);
}
.left-area p,
.bg-area {
  background-color: rgb(253, 253, 253);
  margin: 0px 20px;
  box-shadow: 0px 56px 36px -60px #121d12;
}
.btn-a {
  text-decoration: none;
  background-color: rgb(0, 161, 214);
  padding: 10px 20px;
  display: inline-block;
  border-radius: 3px;
  color: white;
  margin: 10px auto 30px;
}
.btn-a:hover {
  background-color: rgb(0, 181, 229);
}
</style>
