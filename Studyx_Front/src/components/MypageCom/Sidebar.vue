<template>
  <div>
    <el-col :span="4">
      <div class="shell">
        <a href="/user/myinfo" class="box">
          <span><i class="el-icon-edit"></i></span><span>个人信息</span>
        </a>
        <a href="/user/mycollection" class="box">
          <span><i class="el-icon-share"></i></span><span>我的收藏</span></a
        >
        <a href="/user/myborrow" class="box">
          <span><i class="el-icon-user"></i></span><span>我的借阅</span></a
        >
        <a href="/user/mydonation" class="box">
          <span><i class="el-icon-remove-outline"></i></span
          ><span>我的捐赠</span></a
        >
      </div>
    </el-col>
  </div>
</template>

  
  <script>
export default {
  methods: {
  },
};
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
  text-decoration: none;
}

body {
  background-color: rgb(209, 213, 219);
  display: flex;
  justify-content: space-evenly;
  height: 100vh;
  align-items: center;
}

.shell {
  width: 70px;
  height: 500px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: rgb(243, 244, 246);
  border-radius: 10px;
  transition: 0.3s;
  overflow: hidden;
}

.box {
  display: block;
  height: 15%;
  width: 85%;
  margin: 2px;
  border-radius: 5px;
  position: relative;
  transition: 0.3s;
  color: #000;
}
.box i {
  margin-right: 10px;
}

.box:nth-child(1)::before,
.box span {
  position: relative;
  top: 20px;
  left: 20px;
  font: 600 20px "";
  opacity: 0;
  transition: 0.1s;
  float: left;
}
.shell:hover {
  width: 270px;
}
.box:hover {
  background-color: rgb(209, 213, 219);
}
.shell:hover span {
  opacity: 1;
}
.box:hover {
  color: #83bc93;
}
</style>