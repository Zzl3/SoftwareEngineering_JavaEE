<template>
  <div class="base">
    <!-- 注册登录界面 -->
    <div class="loginAndRegist">
      <!--登录表单-->
      <div class="loginArea">
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <!-- 标语 -->
          <div v-show="isShow" class="title">SIGN IN</div>
        </transition>
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <!-- 密码框和用户名框 -->
          <div v-show="isShow" class="sign-in-container">
            <input type="text" placeholder="Name" v-model="loginUser.username" />
            <input
              type="password"
              placeholder="Password"
              v-model="loginUser.password"
            />
          </div>
        </transition>
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <Sentbutton
            v-show="isShow"
            infor="confirm"
            @click.native="UserLogin"
          ></Sentbutton>
        </transition>
      </div>
      <!-- 注册表单 -->
      <div class="registArea">
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <!-- 注册表头-->
          <div v-show="!isShow" class="rigestTitle">SIGN UP</div>
        </transition>
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <!-- 注册表单-->
          <div v-show="!isShow" class="sign-up-container">
            <input
              type="text"
              placeholder="Name"
              v-model="regUser.regUsername"
            />
            <input
              type="password"
              placeholder="Password"
              v-model="regUser.regPwd"
            />
            <input
              type="password"
              placeholder="Re-enter Password"
              v-model="regUser.regRePwd"
            />
          </div>
        </transition>
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <Sentbutton
            v-show="!isShow"
            infor="confirm"
            @click.native="userRegister"
          ></Sentbutton>
        </transition>
      </div>
      <!-- 信息展示界面 -->
      <div
        id="aaa"
        class="showInfo"
        :style="{
          borderTopRightRadius: styleObj.bordertoprightradius,
          borderBottomRightRadius: styleObj.borderbottomrightradius,
          borderTopLeftRadius: styleObj.bordertopleftradius,
          borderBottomLeftRadius: styleObj.borderbottomleftradius,
          right: styleObj.rightDis,
        }"
        ref="showInfoView"
      >
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <!-- 没有用户输入用户名或者找不到用户名的时候 -->
          <div
            v-show="isShow"
            style="
              display: flex;
              flex-direction: column;
              align-items: center;
              justify-content: center;
              width: 100%;
              height: 100%;
            "
          >
            <div style="flex: 2">
              <Loadingbutton
                infor="to register"
                @click.native="changeToRegiest"
              ></Loadingbutton>
            </div>
          </div>
        </transition>
        <transition
          name="animate__animated animate__bounce"
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__zoomOut"
          appear
        >
          <!-- 用户注册的时候展示信息 -->
          <div
            v-show="!isShow"
            style="
              display: flex;
              flex-direction: column;
              align-items: center;
              justify-content: center;
              width: 100%;
              height: 100%;
            "
          >
            <div style="flex: 2">
              <Loadingbutton
                infor="to login"
                @click.native="changeToLogin"
              ></Loadingbutton>
            </div>
          </div>
        </transition>
      </div>
    </div>
    <Recommand></Recommand>
  </div>

</template>
  
  <script>
import Sentbutton from "@/components/SentButton";
import Loadingbutton from "@/components/LoadingButton";
import Recommand from "@/pages/Recommand";
import "animate.css";
// eslint-disable-next-line no-unused-vars
export default {
  name: "Login",
  components: {Sentbutton, Loadingbutton,Recommand },
  data() {
    return {
      //看看用不用转成用户对象
      loginUser: {
        username: "",
        password: ""
      },
      regUser: {
        regUsername: "",
        regRePwd: "",
        regPwd: "",
        selectValue: "",
      },
      styleObj: {
        bordertoprightradius: "15px",
        borderbottomrightradius: "15px",
        bordertopleftradius: "0px",
        borderbottomleftradius: "0px",
        rightDis: "0px",
      },
      isShow: true,
    };
  },
  created() {
    this.loadInfoOfAdmin();
  },
  methods: {
    changeToRegiest() {
      this.styleObj.bordertoprightradius = "0px";
      this.styleObj.borderbottomrightradius = "0px";
      this.styleObj.bordertopleftradius = "15px";
      this.styleObj.borderbottomleftradius = "15px";
      this.styleObj.rightDis = "50%";
      this.isShow = !this.isShow;
    },
    changeToLogin() {
      this.styleObj.bordertoprightradius = "15px";
      this.styleObj.borderbottomrightradius = "15px";
      this.styleObj.bordertopleftradius = "0px";
      this.styleObj.borderbottomleftradius = "0px";
      this.styleObj.rightDis = "0px";
      this.isShow = !this.isShow;
    },
    //用户登录
    UserLogin() {
      var _this = this
      this.$axios
        .post("/login", {
          username: this.loginUser.username,
          password: this.loginUser.password,
        })
        .then((res) => {
          // console.log(res.data)
          if (res.data.code == "200") {
            this.$message.success("登陆成功！");
            //传递登录信息
            _this.$store.commit('login', _this.loginUser)
            var path = this.$route.query.redirect;
            this.$router.replace({
              path: path === "/" || path === undefined ? "/index" : path,
            });
          } else {
            this.$message.error("用户名或密码错误！");
          }
        });
    },
    //用户注册
    userRegister() {
      if (this.regUser.regUsername === "") {
        this.$message.error("用户名不能为空！");
        return false;
      } else if (this.regUser.regPwd != this.regUser.regRePwd) {
        this.$message.error("两次密码输入不同，请检查后重新注册！");
        return false;
      } else {
        let user = {};
        user.username = this.regUser.regUsername;
        user.password = this.regUser.regPwd;
        this.$axios
          .post("/register", {
          username: user.username,
          password: user.password,
        })
          .then((res) => {
            // console.log(res.data.code)
            if (res.data.code == "200") {
              this.$message.success("注册成功");
              this.regUser = {
                regUsername: "",
                regRePwd: "",
                regPwd: "",
              };
              this.changeToLogin();
            } else if (res.data.code == "400") {
              console.log(res.data.message)
              this.$message.error(res.data.message);
              return;
            }
          });
      }
    },
  },
};
</script>
  
<style>
input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 200px;
}

.sign-in-container {
  padding-top: 0px;
  margin-top: 50px;
  margin-bottom: 50px;
  left: 0;
  width: 100%;
  z-index: 2;
}

.sign-up-container {
  padding-top: 0px;
  margin-top: 20px;
  margin-bottom: 30px;
  left: 0;
  width: 100%;
  z-index: 2;
}

.base {
  top: 0;
  left: 0;
  width:100%;
  height:100%;
  position: relative;  

  display: flex;/*重要*/
	flex-wrap: wrap;/*重要*/    /*--让弹性盒元素在必要的时候拆行：*/
	align-content: space-between;/*重要*/
  justify-content: center;
  /*align-items: center;*/
  background-image: url("../assets/background.png");
  background-repeat: no-repeat;
  background-size: 100%;
}
.loginAndRegist {
  margin-top:220px;
  margin-bottom: 230px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.loginArea {
  background-color: rgba(255, 255, 255, 0.8);
  border-top-left-radius: 15px;
  border-bottom-left-radius: 15px;
  height: 400px;
  width: 350px;
  z-index: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow: hidden;
}
.registArea {
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  height: 400px;
  width: 350px;
  background-color: rgba(255, 255, 255, 0.8);
  z-index: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.showInfo {
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  position: absolute;
  height: 400px;
  width: 350px;
  z-index: 2;
  top: 0;
  right: 0;
  background-image: url("../assets/welcome.png");
  background-size: 90%;
}
.showInfo:hover {
  background-size: 100%;
  background-position: -50px -50px;
}
.title {
  width: 70%;
  flex: 1;
  border-bottom: 3px solid #6b9184;
  display: flex;
  align-items: center;
  color: #83bc93;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  justify-content: center;
}
#aaa {
  transition: 0.3s linear;
}
.pwdArea {
  width: 100%;
  flex: 2;
  display: flex;
  flex-direction: column;
  display: flex;
  flex-direction: column;
}
.pwdArea input {
  outline: none;
  height: 30%;
  border-radius: 13px;
  padding-left: 10px;
  font-size: 20px;
  border: 3px solid gray;
}
.pwdArea input:focus {
  border: 3px solid #257b5e;
}
.btnArea {
  flex: 1;
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 20px;
}
.rigestTitle {
  width: 70%;
  flex: 1;
  color: #83bc93;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 3px solid #6b9184;
}
.registForm {
  flex: 2;
  display: flex;
  flex-direction: column;
  color: #41554e;
  font-size: 20px;
}

.registForm input {
  outline: none;
  height: 30%;
  border-radius: 13px;
  padding-left: 10px;
  font-size: 20px;
  border: 3px solid gray;
}
.registForm input:focus {
  border: 3px solid #257b5e;
}
#elselect:focus {
  border: 3px solid #257b5e;
}
.registBtn {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>