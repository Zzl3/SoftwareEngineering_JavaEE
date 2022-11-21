<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
      <el-breadcrumb-item :to="{ path: '/user' }">我的页面</el-breadcrumb-item>
      <el-breadcrumb-item
        ><a href="/user/myinfo">个人信息</a></el-breadcrumb-item
      >
    </el-breadcrumb>
    <!-- 名片分三大块
    最外层的一块a
    文字一大块b
    平面圆柱一块c -->
    <div class="a">
      <div class="b">
        <a href="#">{{ username }}</a>
        <h2>个人信息卡片</h2>
        <div class="infouser">性别：{{ gender }}</div>
        <div class="infouser">年龄：{{ age }}</div>
        <div class="infouser">邮箱：{{ mail }}</div>
        <div class="infouser">手机：{{ phone }}</div>
        <div class="infouser">学校：{{ school }}</div>
        <div class="infouser">个人积分：{{ integration }}</div>
        <div class="infouser">用户状态：{{ status }}</div>
        <div class="infouser">个人介绍：{{ detail }}</div>
      </div>
      <div class="c">
        <!-- --i是用来计算平面圆柱的动效延迟和距离的
            --w则是用来计算平面圆柱的宽度 -->
        <div class="d" style="--i: 1; --w: 1.5"></div>
        <div class="d" style="--i: 2; --w: 1.6"></div>
        <div class="d" style="--i: 3; --w: 1.4"></div>
        <div class="d" style="--i: 4; --w: 1.7"></div>
        <div class="e" style="--i: 1"></div>
      </div>
      <!-- 设置二维码 -->
      <div class="f"></div>
    </div>
    <!-- 修改个人信息按钮 -->
    <TDButton @click.native="dialogFormVisible = true"></TDButton>
    <el-dialog :visible.sync="dialogFormVisible">
      <Myfrom></Myfrom>
    </el-dialog>
  </div>
</template>

<script>
import Myfrom from "@/components/MypageCom/MyInfoCom/Form";
import TDButton from "@/components/MypageCom/MyInfoCom/TDButton";
export default {
  name: "Myinfo",
  components: { TDButton, Myfrom },
  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: "120px",
      username: "user",
      gender: "",
      mail: "",
      school: "",
      phone: "",
      detail: "",
      age: "",
      integration: "",
      status:"",
    };
  },
  mounted() {
    this.getlist(); //页面一进入就加载表格
  },
  methods: {
    getlist() {
      var _this = this;
      //this.username = this.$myglobal.nowuserid; //当前用户
      this.$axios({
        url: "/user/getuserinfo",
        method: "post",
        data: _this.$myglobal.nowuserid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        this.username=res.data.username
        this.gender = res.data.gender;
        this.detail = res.data.detail;
        this.mail = res.data.mail;
        this.phone = res.data.phone;
        this.school = res.data.school;
        this.age = res.data.age;
        this.integration = res.data.integration;
        this.status = res.data.status;
      });
    },
  },
};
</script>

<style scoped>
.infouser {
  text-align: left;
}
.a {
  position: relative;
  width: 700px;
  height: 400px;
  border: #fff 10px solid;
  background-color: rgb(120, 140, 200);
  top: -450px;
  border-radius: 20px;
  overflow: hidden;
  left: 400px;
}
.b {
  position: absolute;
  width: 200px;
  height: 300px;
  left: 0;
  margin: 75px 50px;
  transition: 1s;
}
.b a {
  text-decoration: none;
  color: #fff;
  font: 900 28px "";
}
.b h2 {
  /* 鼠标放开时的动画，第一个值是动画的过渡时间
    第二个值是延迟一秒后执行动画 */
  transition: 0.5s 1s;
  opacity: 0;
  color: rgb(30, 210, 200);
}
.b span {
  transition: 0.5s 1s;
  color: #fff;
  font: 500 15px "";
  position: absolute;
  top: 70px;
}
.c {
  position: absolute;
  top: -130px;
  right: -240px;
}
.d,
.e {
  position: absolute;
  right: calc(var(--i) * 100px);
  width: calc(var(--w) * 40px);
  height: 500px;
  overflow: hidden;
  border-radius: 100px;
  transform: rotateZ(220deg) translate(0, 0);
  background: rgb(240, 220, 150);
  transition: 0.5s 0.5s;
}
.d:nth-child(2) {
  background: rgb(240, 190, 230);
}
.e {
  left: -470px;
  top: -140px;
  width: 70px;
  background-color: rgb(90, 220, 150);
}
.a:hover .c div {
  /* 设置延迟动画 */
  transition: 0.5s calc(var(--i) * 0.1s);
  /* 移动的轨迹 */
  transform: rotateZ(220deg) translate(-200px, 400px);
}
.a:hover .b {
  transition: 1s 0.5s;
  left: 370px;
}
.a:hover .b span {
  transition: 1s 0.5s;
  top: 105px;
}
.a:hover .b h2 {
  transition: 1s 0.5s;
  opacity: 1;
}
.f {
  width: 250px;
  height: 250px;
  position: absolute;
  background-image: url("../../assets/1.png");
  background-size: cover;
  margin: 70px;
  opacity: 0;
  transition: 0.5s;
}
.a:hover .f {
  transition: 1s 1.3s;
  opacity: 1;
}
</style>