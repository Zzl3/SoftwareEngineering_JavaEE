<template>
  <!-- box-1 -->
  <div class="box">
    <!-- top-bar -->
    <div class="top-bar"></div>
    <!-- view-btns  -->
    <div class="btn">
      <a href="#"><i class="fas fa-eys"></i>查看详情</a>
      <a href="#" v-on:click="deletecollectdir()"><i class="fas fa-eys"></i>删除收藏夹</a>
    </div>
    <!-- img and details  -->
    <div class="details">
      <img src="@/assets/1.png" />
      <strong>{{ Title }}</strong>
      <p>{{ Description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    Picsrc: String,
    Description: String,
    Title: String,
  },
  methods: {
    deletecollectdir() {
      let vm = this;
      if (vm.Title == "默认收藏夹") {
        vm.$message({
          showClose: true,
          message: "默认收藏夹不可删除",
          type: "error",
        });
      } else {
        this.$axios
          .post("/user/deletecollectiondir", {
            userid: vm.$myglobal.nowuserid,
            dirname: vm.Title,
          })
          .then((res) => {
            vm.$router.go(0);
          });
      }
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
}
.box {
  width: 700px;
  height: 200px;
  background-color: #fff;
  box-shadow: 2px 2px 30px rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: px;
  border-radius: 10px;
  margin: 20px;
  position: relative;
}

.box .nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
.box .nav .verify {
  color: #17b667;
}
.box .nav .heart {
  color: rgba(155, 155, 155);
}
.box .nav .heart:before {
  content: "\f004";
  font-family: fontAwesome;
  line-height: 30px;
  z-index: 1;
}
.box .nav .heart-btn:checked ~ .heart:before {
  color: #e41934;
}
.box .nav .heart-btn {
  display: none;
}
.box .details {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.box .details img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden;
  object-fit: cover;
  object-position: center;
}
.box .details strong {
  font-weight: 500;
  color: #141414;
  letter-spacing: 1px;
}
.box .details p {
  font-size: 0.8rem;
  color: #7a7a7a;
  margin: 5px 0px;
}
.box .btn {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box .btn a {
  color: #8b8b8b;
  padding: 8px 22px;
  border-radius: 20px;
  font-size: 0.9rem;
}
.box .btn a i {
  margin-right: 10px;
}
.box .btn a:hover {
  color: #fff;
  background-color: #507bfc;
  box-shadow: 2px 5px 15px rgba(80, 123, 252, 0.5);
  transition: all ease 0.3s;
}
.box:hover {
  box-shadow: 2px 2px 30px rgba(4, 15, 49, 0.1);
  transform: scale(1.01);
  transition: all ease 0.1s;
}
</style>
