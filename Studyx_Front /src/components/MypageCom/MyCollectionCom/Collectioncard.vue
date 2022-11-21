<template>
  <div id="card">
    <!--container-->
    <button type="submit" class="outline" @click="dialogFormVisible = true">
      添加收藏夹
    </button>
    <div class="container" v-for="(codata, index) in codata" v-bind:key="index">
      <Box :Title="codata.dirname" :Description="codata.content" id="box"></Box>
    </div>
    <el-dialog title="添加收藏夹" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="收藏夹名称" :label-width="formLabelWidth">
          <el-input v-model="form.dirname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="收藏夹简介" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="form.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addcollectiondir">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Box from "./Box.vue";
export default {
  components: { Box },
  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: "120px",
      form: {
        dirname: "",
        content: "",
      },
      username: "",
      codata: [
      ],
    };
  },
  mounted() {
    this.getallcolletctiondir();
  },
  methods: {
    getallcolletctiondir() {
      var _this = this;
      this.username = this.$myglobal.nowuserid; //当前用户
      this.$axios({
        url: "/user/getcollectiondir",
        method: "post",
        data: _this.username,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        for (let item of res.data) {
          console.log(item);
          _this.codata.push(item);
        }
      });
    },
    addcollectiondir() {
      var _this = this;
      this.dialogFormVisible = false;
      this.$axios
        .post("/user/setcollectiondir", {
          userid: _this.$myglobal.nowuserid,
          content: _this.form.content,
          dirname: _this.form.dirname,
        })
        .then((res) => {
          if (res.data == "0") {
            _this.$message({
              showClose: true,
              message: "添加失败",
              type: "error",
            });
          }
        });
      _this.$router.go(0);
    },
  },
};
</script>

<style scoped>
body {
  background-color: #ecf1ef;
}
.container {
  width: 80%;
  margin-left: 350px;
  padding-top: 40px;
}
button {
  cursor: pointer;
  outline: none;
}
button.outline {
  position: absolute;
  right: 40px;
  z-index: 3;
  background: transparent;
  color: #1172c4;
  font-size: 14px;
  border-color: #1172c4;
  border-style: solid;
  border-width: 2px;
  border-radius: 22px;
  padding: 10px 40px;
  text-transform: uppercase;
  transition: all 0.2s linear;
}
button.outline a {
  text-decoration: none;
}
button.outline:hover {
  color: white;
  background: #507bfc;
  border-color: white;
  transition: all 0.2s linear;
}
button.outline:active {
  border-radius: 22px;
}
button.white-blue {
  font-weight: 700;
  color: #00aeef;
  border-color: white;
  background: white;
}
button.white-blue:hover {
  color: white;
  background: #00aeef;
  border-color: white;
}
</style>
