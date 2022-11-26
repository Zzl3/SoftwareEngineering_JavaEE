<template>
  <div>
    <el-col :span="4">
      <div class="shell">
        <a href="/admin/adminborrow" class="box">
          <span><i class="el-icon-edit"></i></span>
          <span>借阅管理</span>
        </a>
        <a href="/admin/adminuser" class="box">
          <span><i class="el-icon-user-solid"></i></span>
          <span>用户管理</span>
        </a>
        <a href="/admin/adminfeedback" class="box">
          <span><i class="el-icon-user-solid"></i></span>
          <span>反馈管理</span>
        <a href="/admin/admincategory" class="box">
          <span><i class="el-icon-notebook-1"></i></span>
          <span>书籍管理</span>
        </a>
        <a href="#" class="box">
          <span><i class="el-icon-s-marketing"></i></span
          ><span>authentication</span></a
        >
        <a href="#" class="box">
          <span><i class="el-icon-d-caret"></i></span
          ><span>cancellation</span></a
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