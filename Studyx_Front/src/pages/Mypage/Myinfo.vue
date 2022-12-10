<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
      <el-breadcrumb-item :to="{ path: '/user' }">我的页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/user/myinfo">个人信息</a></el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 名片分三大块
    最外层的一块a
    文字一大块b
    平面圆柱一块c -->
    <div class="a">
      <div class="b">
        <a href="#">{{ username }}</a>
        <!-- <h2>个人信息卡片</h2> -->
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
    <div id="loaddd">
      <div class="loader">
        <svg viewBox="0 0 80 80">
          <circle id="test" cx="40" cy="40" r="32"></circle>
        </svg>
      </div>

      <div class="loader triangle">
        <svg viewBox="0 0 86 80">
          <polygon points="43 8 79 72 7 72"></polygon>
        </svg>
      </div>

      <div class="loader">
        <svg viewBox="0 0 80 80">
          <rect x="8" y="8" width="64" height="64"></rect>
        </svg>
      </div>
    </div>
    <div id="share"><Share></Share></div>
  </div>
</template>

<script>
import Card from "@/components/MypageCom/MyInfoCom/Card";
import Myfrom from "@/components/MypageCom/MyInfoCom/Form";
import TDButton from "@/components/MypageCom/MyInfoCom/TDButton";
import Share from "@/components/MypageCom/MyInfoCom/Share";
export default {
  name: "Myinfo",
  components: { TDButton, Myfrom, Card, Share },
  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: "120px",
      username: "",
      gender: "",
      mail: "",
      school: "",
      phone: "",
      detail: "",
      age: "",
      integration: "",
      status: "",
      nowuserid:"",
    };
  },
  mounted() {
    this.getlist(); //页面一进入就加载表格
  },
  methods: {
    getlist() {
      var _this = this;
      //alert(_this.$myglobal.nowuserid)
      //this.username = this.$myglobal.nowuserid; //当前用户
      this.$axios({
        url: "/user/getuserinfo",
        method: "post",
        data: _this.$myglobal.nowuserid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        this.username = res.data.username;
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
#share {
  position: absolute;
  top: 620px;
  right: 880px;
}
#loaddd {
  position: absolute;
  top: 600px;
  right: 400px;
}
.loader {
  --path: #2f3545;
  --dot: #5628ee;
  --duration: 3s;
  width: 44px;
  height: 44px;
  position: relative;
}

.loader:before {
  content: "";
  width: 6px;
  height: 6px;
  border-radius: 50%;
  position: absolute;
  display: block;
  background: var(--dot);
  top: 37px;
  left: 19px;
  transform: translate(-18px, -18px);
  animation: dotRect var(--duration) cubic-bezier(0.785, 0.135, 0.15, 0.86) infinite;
}

.loader svg {
  display: block;
  width: 100%;
  height: 100%;
}

.loader svg rect,
.loader svg polygon,
.loader svg circle {
  fill: none;
  stroke: var(--path);
  stroke-width: 10px;
  stroke-linejoin: round;
  stroke-linecap: round;
}

.loader svg polygon {
  stroke-dasharray: 145 76 145 76;
  stroke-dashoffset: 0;
  animation: pathTriangle var(--duration) cubic-bezier(0.785, 0.135, 0.15, 0.86) infinite;
}

.loader svg rect {
  stroke-dasharray: 192 64 192 64;
  stroke-dashoffset: 0;
  animation: pathRect 3s cubic-bezier(0.785, 0.135, 0.15, 0.86) infinite;
}

.loader svg circle {
  stroke-dasharray: 150 50 150 50;
  stroke-dashoffset: 75;
  animation: pathCircle var(--duration) cubic-bezier(0.785, 0.135, 0.15, 0.86) infinite;
}

.loader.triangle {
  width: 48px;
}

.loader.triangle:before {
  left: 21px;
  transform: translate(-10px, -18px);
  animation: dotTriangle var(--duration) cubic-bezier(0.785, 0.135, 0.15, 0.86) infinite;
}

@keyframes pathTriangle {
  33% {
    stroke-dashoffset: 74;
  }

  66% {
    stroke-dashoffset: 147;
  }

  100% {
    stroke-dashoffset: 221;
  }
}

@keyframes dotTriangle {
  33% {
    transform: translate(0, 0);
  }

  66% {
    transform: translate(10px, -18px);
  }

  100% {
    transform: translate(-10px, -18px);
  }
}

@keyframes pathRect {
  25% {
    stroke-dashoffset: 64;
  }

  50% {
    stroke-dashoffset: 128;
  }

  75% {
    stroke-dashoffset: 192;
  }

  100% {
    stroke-dashoffset: 256;
  }
}

@keyframes dotRect {
  25% {
    transform: translate(0, 0);
  }

  50% {
    transform: translate(18px, -18px);
  }

  75% {
    transform: translate(0, -36px);
  }

  100% {
    transform: translate(-18px, -18px);
  }
}

@keyframes pathCircle {
  25% {
    stroke-dashoffset: 125;
  }

  50% {
    stroke-dashoffset: 175;
  }

  75% {
    stroke-dashoffset: 225;
  }

  100% {
    stroke-dashoffset: 275;
  }
}

.loader {
  display: inline-block;
  margin: 0 16px;
}

.infouser {
  text-align: left;
}
.a {
  position: relative;
  width: 800px;
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
  width: 350px;
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
