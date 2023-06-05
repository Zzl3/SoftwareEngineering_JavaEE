<template>
  <div>
    <div class="basss">
      <div class="base">
        <!-- 注册登录界面 -->
        <div class="loginAndRegist" v-if="!isFind">
          <!--登录表单-->
          <div class="loginArea">
            <transition
              name="animate__animated animate__bounce"
              enter-active-class="animate__fadeInUp"
              leave-active-class="animate__zoomOut"
              appear
            >
              <div v-show="isShow" class="title">
                <span
                  style="
                    display: inline-block;
                    margin: 0px;
                    padding: 0px;
                    margin-top: 30px;
                  "
                  >SIGN IN</span
                >
                <br />
                <el-link
                  type="info"
                  @click="changerole()"
                  style="margin: 0px; padding: 0px"
                  >{{ nowrole }}</el-link
                >
              </div>
            </transition>
            <transition
              name="animate__animated animate__bounce"
              enter-active-class="animate__fadeInUp"
              leave-active-class="animate__zoomOut"
              appear
            >
              <!-- 密码框和用户名框 -->
              <div v-show="isShow" class="sign-in-container">
                <el-form :model="loginForm" ref="loginForm">
                  <el-form-item prop="userName">
                    <el-input
                      v-show="nowrole == 'USER NOW'"
                      v-model="loginForm.mail"
                      style="width: 230px"
                      placeholder="Mail"
                      size="meddle"
                      autocomplete="off"
                    ></el-input>
                    <el-input
                      v-show="nowrole != 'USER NOW'"
                      v-model="loginForm.mail"
                      style="width: 230px"
                      placeholder="Name"
                      size="meddle"
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>

                  <el-form-item prop="password">
                    <el-input
                      show-password
                      v-model="loginForm.password"
                      style="width: 230px"
                      placeholder="Password"
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>

                  <el-form-item required>
                    <el-col :span="18">
                      <el-form-item prop="validCode">
                        <el-input
                          v-model="loginForm.validCode"
                          style="width: 140px"
                          placeholder="Verification code"
                        ></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="5" style="margin-left: -52px">
                      <validCode
                        v-model="validCode"
                        ref="refresh"
                        @sendData="getCode"
                      ></validCode>
                    </el-col>
                  </el-form-item>
                </el-form>
              </div>
            </transition>
            <el-link
              v-show="nowrole == 'USER NOW'"
              type="info"
              @click="forgetPassword()"
              style="margin: 5px; padding: 0px"
              >FORGET PASSWORD</el-link
            >
            <transition
              name="animate__animated animate__bounce"
              enter-active-class="animate__fadeInUp"
              leave-active-class="animate__zoomOut"
              appear
            >
              <Sentbutton
                v-show="isShow"
                infor="confirm"
                @click.native="Login"
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
              <div v-show="!isShow" class="rigestTitle" style="margin-top: 58px">
                SIGN UP
              </div>
            </transition>
            <transition
              name="animate__animated animate__bounce"
              enter-active-class="animate__fadeInUp"
              leave-active-class="animate__zoomOut"
              appear
            >
              <!-- 注册表单-->
              <div v-show="!isShow" class="sign-up-container">
                <input type="text" placeholder="Mail" v-model="regUser.regPhone" />
                <div>
                  <input
                    style="width: 120px"
                    type="text"
                    placeholder="验证码"
                    v-model="regUser.regverification"
                  />
                  <button style="font-size: 12px; width: 70px" @click="getVerification">
                    GET
                  </button>
                </div>

                <input type="text" placeholder="昵称" v-model="regUser.regUsername" />
                <input type="password" placeholder="密码" v-model="regUser.regPwd" />
                <input
                  type="password"
                  placeholder="再次输入密码"
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
                style="margin-top: 50px"
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
        <div class="loginAndRegist find" v-if="isFind">
          <!-- 注册表单 -->
          <div class="registArea">
            <transition
              name="animate__animated animate__bounce"
              enter-active-class="animate__fadeInUp"
              leave-active-class="animate__zoomOut"
              appear
            >
              <!-- 注册表头-->
              <div class="passTitle">FIND PASSWORD</div>
            </transition>
            <transition
              name="animate__animated animate__bounce"
              enter-active-class="animate__fadeInUp"
              leave-active-class="animate__zoomOut"
              appear
            >
              <!-- 注册表单-->
              <div class="sign-up-container" style="margin-top: 15px">
                <input
                  type="text"
                  placeholder="Mail"
                  v-model="findUser.findPhone"
                  style="top: 0px"
                />
                <div>
                  <input
                    type="text"
                    placeholder="验证码"
                    v-model="findUser.findverification"
                    style="width: 120px"
                  />
                  <button style="font-size: 12px" @click="getVerification_f">GET</button>
                </div>
                <input
                  type="password"
                  placeholder="Password"
                  v-model="findUser.findPwd"
                />
                <input
                  type="password"
                  placeholder="Re-enter Password"
                  v-model="findUser.findRePwd"
                />
                <div style="display: flex; margin-left: 80px; margin-top: 30px">
                  <button
                    style="height: 40px; line-height: 15px; margin-bottom: 50px"
                    @click="findPassword"
                  >
                    确认
                  </button>
                  <div style="width: 40px"></div>
                  <button style="height: 40px; line-height: 15px" @click="changeToLogin">
                    取消
                  </button>
                </div>
              </div>
            </transition>
            <!-- <transition
            name="animate__animated animate__bounce"
            enter-active-class="animate__fadeInUp"
            leave-active-class="animate__zoomOut"
            appear
          >
          </transition> -->
          </div>
        </div>
      </div>
    </div>
    <Recommand></Recommand>
  </div>
</template>

<script>
import validCode from "@/components/LoginCom/VerificationCode";
import Sentbutton from "@/components/LoginCom/SentButton";
import Loadingbutton from "@/components/LoginCom/LoadingButton";
import Recommand from "./Recommand";
import "animate.css";
// eslint-disable-next-line no-unused-vars
export default {
  name: "Login",
  components: { Sentbutton, Loadingbutton, Recommand, validCode },
  data() {
    return {
      validCode: "",
      regVerification: "",
      loginForm: {
        mail: "",
        password: "",
        validCode: "",
      },
      //看看用不用转成用户对象
      nowrole: "USER NOW", //当前是用户还是管理员
      regUser: {
        regPhone: "",
        regUsername: "",
        regRePwd: "",
        regPwd: "",
        selectValue: "",
        regverification: "",
      },
      styleObj: {
        bordertoprightradius: "15px",
        borderbottomrightradius: "15px",
        bordertopleftradius: "0px",
        borderbottomleftradius: "0px",
        rightDis: "0px",
      },
      isShow: true,
      isFind: false,
      findUser: {
        findPhone: "",
        findRePwd: "",
        findPwd: "",
        findverification: "",
      },
    };
  },
  created() {
    this.loadInfoOfAdmin();
  },
  methods: {
    //从子组件获取验证码，并将验证码返回到页面
    getCode(data) {
      // console.log(data)
      this.validCode = data;
    },
    forgetPassword() {
      this.isFind = true;
    },
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
      this.isShow = true;
      this.isFind = false;
    },
    getVerification() {
      //alert("按钮点击")
      var _this = this;
      if (this.regUser.regPhone == "") {
        this.$message.error("邮箱不能为空");
        return;
      }
      this.$message("邮件正在发送，请稍等");
      //alert(this.regUser.regPhone)
      this.$axios({
        url: "/sms",
        method: "post",
        data: _this.regUser.regPhone,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        // console.log(res.data.code)
        if (res.data != null) {
          this.$message.success("验证码发送成功");
          this.regVerification = res.data;
        } else {
          console.log(res.data);
          this.$message.error("验证码发送失败");
          return;
        }
      });
    },
    getVerification_f() {
      //alert("按钮点击")
      var _this = this;
      if (this.findUser.findPhone == "") {
        this.$message.error("邮箱不能为空");
        return;
      }
      this.$message("邮件正在发送，请稍等");
      //alert(this.regUser.regPhone)
      this.$axios({
        url: "/sms",
        method: "post",
        data: _this.findUser.findPhone,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        // console.log(res.data.code)
        if (res.data != null) {
          this.$message.success("邮件发送成功");
          this.findVerification = res.data;
        } else {
          console.log(res.data);
          this.$message.error("验证码发送失败");
          return;
        }
      });
    },
    //用户登录，或者管理员登录
    Login() {
      var _this = this;
      var loginv = this.loginForm.validCode;
      var v = this.validCode;
      //alert(loginv+':'+v)

      var loginv1 = loginv.toLowerCase();
      var v1 = v.toLowerCase();

      //alert(loginv1+"::"+v1)
      if (loginv1 != v1) {
        _this.$message.error("验证码错误！");
      } else {
        if (this.nowrole == "USER NOW") {
          _this.$axios
            .post("/login", {
              mail: _this.loginForm.mail,
              password: _this.loginForm.password,
            })
            .then((res) => {
              // console.log(res.data)
              if (res.data.code == "200") {
                _this.$myglobal.setnowmail = _this.loginForm.mail;
                _this.$message.success({
                  message: "登录成功！",
                  duration: "500",
                });
                var that = _this;
                _this
                  .$axios({
                    url: "/getuserid",
                    method: "post",
                    data: that.loginForm.mail,
                    headers: {
                      "Content-Type": "text/plain",
                    },
                  })
                  .then((res) => {
                    that.$myglobal.setnowuserid(res.data); //改变全局nowusername
                    alert(res.data + ":" + that.$.nowuserid);
                    // console.log( _this.$myglobal.nowuserid)
                  });
                this.$router.push({
                  path: "/index",
                });
              } else {
                this.$message.error("用户名或密码错误！");
              }
            });
        } else if (this.nowrole == "ADMINISTRATOR NOW") {
          _this.$axios
            .post("/login/admin", {
              adminname: _this.loginForm.mail,
              password: _this.loginForm.password,
            })
            .then((res) => {
              // console.log(res.data)
              if (res.data.code == "200") {
                this.$message.success({
                  message: "登录成功！",
                  duration: "500",
                });
                this.$router.push({
                  path: "/admin",
                });
              } else {
                this.$message.error("用户名或密码错误！");
              }
            });
        }
      }
    },
    //用户注册
    userRegister() {
      var _this = this;
      if (this.regUser.regPhone === "") {
        this.$message.error("邮箱不能为空！");
        return false;
      } else if (
        this.regUser.regverification != this.regVerification ||
        this.regVerification == ""
      ) {
        _this.$message.error("验证码错误！");
      } else if (this.regUser.regUsername === "") {
        this.$message.error("用户名不能为空！");
        return false;
      } else if (this.regUser.regPwd != this.regUser.regRePwd) {
        this.$message.error("两次密码输入不同，请检查后重新注册！");
        return false;
      } else {
        let user = {};
        user.username = this.regUser.regUsername;
        user.password = this.regUser.regPwd;
        user.mail = this.regUser.regPhone;
        this.$axios
          .post("/register", {
            mail: user.mail,
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
              console.log(res.data.message);
              this.$message.error(res.data.message);
              return;
            }
          });
      }
    },
    findPassword() {
      var _this = this;
      if (this.findUser.findPhone === "") {
        this.$message.error("邮箱不能为空！");
        return false;
      } else if (
        this.findUser.findverification != this.findVerification ||
        this.findVerification == ""
      ) {
        _this.$message.error("验证码错误！");
      } else if (this.findUser.findPwd != this.findUser.findRePwd) {
        this.$message.error("两次密码输入不同，请检查后重新输入！");
        return false;
      } else {
        let user = {};
        user.password = this.findUser.findPwd;
        user.mail = this.findUser.findPhone;
        this.$axios
          .post("/findpassword", {
            mail: user.mail,
            password: user.password,
          })
          .then((res) => {
            // console.log(res.data.code)
            if (res.data.code == "200") {
              this.$message.success("找回密码成功");
              _this.findUser = {
                findPhone: "",
                findRePwd: "",
                findPwd: "",
                findverification: "",
              };
              _this.changeToLogin();
            } else if (res.data.code == "400") {
              console.log(res.data.message);
              this.$message.error(res.data.message);
              return;
            }
          });
      }
    },
    changerole() {
      if (this.nowrole == "USER NOW") {
        this.nowrole = "ADMINISTRATOR NOW";
      } else {
        this.nowrole = "USER NOW";
      }
    },
  },
};
</script>

<style scoped>
span {
  padding: 10px 0;
  transition: 1s;
}
input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 200px;
}
.sign-in-container {
  padding-top: 0px;
  margin-top: 20px;
  margin-bottom: 0px;
  left: 0;
  width: 100%;
  z-index: 2;
}
.sign-up-container {
  padding-top: 0px;
  margin-top: 20px;
  margin-bottom: -35px;
  left: 0;
  width: 100%;
  z-index: 2;
}
.basss {
  /*align-items: center;*/
  background-size: 100%;
  background-image: url("../../assets/background.png");
  background-repeat: no-repeat;
}
.base {
  top: 0;
  left: 30%;
  width: 85%;
  height: 100%;
  position: relative;
  display: flex; /*重要*/
  flex-wrap: wrap; /*重要*/ /*--让弹性盒元素在必要的时候拆行：*/
  align-content: space-between; /*重要*/
}
.loginAndRegist {
  width: 50%;
  height: 100%;
  margin-top: 220px;
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
  width: 310px;
  z-index: 2;
  top: 0;
  right: 0;
  background-image: url("../../assets/welcome.png");
  background-size: 90%;
}
.showInfo:hover {
  background-size: 100%;
  background-position: -50px -50px;
}
.title {
  width: 70%;
  /*flex: 1;*/
  border-bottom: 3px solid #6b9184;
  /*display: flex;*/
  align-items: center;
  color: #83bc93;
  font-weight: bold;
  font-size: 24px;
  justify-content: center;
}
#aaa {
  transition: 0.2s linear;
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
.passTitle {
  z-index: 9999;
  height: 30px;
  width: 70%;
  color: #83bc93;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  justify-content: center;
  margin-top: 30px;
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
