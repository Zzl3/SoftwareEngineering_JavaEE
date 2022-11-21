<template>
  <div>
    <form class="basic-grey" id="myform">
      <h1>
        修改信息
        <span>请按照下方规则修改个人信息(若不填写则不会修改)</span>
      </h1>
      <label>
        <span>性别:</span>
        <select id="gender" name="gender" placeholder="请输入您的性别">
          <option value="男">男</option>
          <option value="女">女</option>
        </select>
      </label>
      <label>
        <span>年龄:</span>
        <input
          onkeyup="this.value=this.value.replace(/\D/g,'')"
          onafterpaste="this.value=this.value.replace(/\D/g,'')"
          id="age"
          type="text"
          name="age"
          placeholder="请输入您的年龄"
        />
      </label>

      <label>
        <span>邮箱:</span>
        <input
          id="email"
          type="email"
          name="mail"
          placeholder="请输入合法的邮箱地址"
          required
          pattern="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
        />
      </label>
      <label>
        <span>手机:</span>
        <input
          onkeyup="this.value=this.value.replace(/\D/g,'')"
          onafterpaste="this.value=this.value.replace(/\D/g,'')"
          id="phone"
          type="text"
          name="phone"
          placeholder="请输入合法的手机号码"
        />
      </label>

      <label>
        <span>学校:</span>
        <input
          id="school"
          type="text"
          name="school"
          placeholder="请输入您的学校"
        />
      </label>

      <label>
        <span>密码:</span>
        <input
          id="school"
          type="password"
          name="password"
          placeholder="请输入您的密码"
        />
      </label>

      <label>
        <span>重复密码:</span>
        <input
          id="school"
          type="password"
          name="re-password"
          placeholder="请再次输入您的密码"
        />
      </label>

      <label>
        <span>自我简介:</span>
        <textarea
          id="detail"
          name="detail"
          placeholder="请输入您的自我简介"
        ></textarea>
      </label>
      <label>
        <span>上传图片:</span>
        <ImgUpload  @getUrl="getURL"></ImgUpload>
      </label>
      <label>
        <!-- <span>&nbsp;</span>
        <input
          type="button"
          class="button"
          style="background: white; color: #e27575; margin-right: 20px"
          value="清空"
          @click="cleardata"
        /> -->
        <span>&nbsp;</span>
        <input
          type="button"
          class="button"
          value="确定"
          @click="submitdata"
        />
      </label>
    </form>
  </div>
</template>

<script>
import ImgUpload from "./ImgUpload";
export default {
  components: { ImgUpload },
  data() {
    return {
      url:"",
    };
  },
  methods: {
    getURL(msg) {
      this.url = msg;
    },
    submitdata() {
      var _this = this;
      var formData = new FormData(myform); // form为表单对象
      if (formData.get("password") != formData.get("re-password")) {
        _this.$message("两次密码不一致，请重新输入");
      } else {
        _this.$axios.post("/user/edituserinfo", {
          id: _this.$myglobal.nowuserid,
          password: formData.get("password"),
          phone: formData.get("phone"),
          mail: formData.get("mail"),
          gender: formData.get("gender"),
          detail: formData.get("detail"),
          school: formData.get("school"),
          age: formData.get("age"),
          url:_this.url,
        });
        this.$message({
          message: "已成功修改信息",
          type: "success",
        });
        this.$router.go(0);
      }
    },
  },
};
</script>

<style scoped>
.basic-grey {
  margin-left: auto;
  margin-right: auto;
  max-width: 500px;
  background: #f7f7f7;
  padding: 25px 15px 25px 10px;
  font: 12px Georgia, "Times New Roman", Times, serif;
  color: #888;
  text-shadow: 1px 1px 1px #fff;
  border: 1px solid #e4e4e4;
}

.basic-grey h1 {
  font-size: 25px;
  padding: 0px 0px 10px 40px;
  display: block;
  border-bottom: 1px solid #e4e4e4;
  margin: -10px -15px 30px -10px;
  color: #888;
}

.basic-grey h1 > span {
  display: block;
  font-size: 11px;
}

.basic-grey label {
  display: block;
  margin: 0px;
}

.basic-grey label > span {
  float: left;
  width: 20%;
  text-align: right;
  padding-right: 10px;
  margin-top: 10px;
  color: #888;
}

.basic-grey input[type="text"],
.basic-grey input[type="password"],
.basic-grey input[type="email"],
.basic-grey textarea,
.basic-grey select {
  border: 1px solid #dadada;
  color: #888;
  height: 30px;
  margin-bottom: 16px;
  margin-right: 6px;
  margin-top: 2px;
  outline: 0 none;
  padding: 3px 3px 3px 5px;
  width: 70%;
  font-size: 12px;
  line-height: 15px;
  box-shadow: inset 0px 1px 4px #ececec;
  -moz-box-shadow: inset 0px 1px 4px #ececec;
  -webkit-box-shadow: inset 0px 1px 4px #ececec;
}

.basic-grey textarea {
  padding: 5px 3px 3px 5px;
}

.basic-grey select {
  background: #fff;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  text-indent: 0.01px;
  text-overflow: "";
  width: 70%;
  height: 35px;
  line-height: 25px;
}

.basic-grey textarea {
  height: 100px;
}
.basic-grey .button {
  background: #e27575;
  border: none;
  padding: 10px 25px 10px 25px;
  color: #fff;
  box-shadow: 1px 1px 5px #b6b6b6;
  border-radius: 3px;
  text-shadow: 1px 1px 1px #9e3f3f;
  cursor: pointer;
}

.basic-grey .button:hover {
  background: #cf7a7a;
}
</style>