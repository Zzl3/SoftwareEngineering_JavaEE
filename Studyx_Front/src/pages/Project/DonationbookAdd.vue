<template>
  <div>
    <el-dialog :before-close="handleDialogClose" title="捐书" :visible.sync="dialogTableVisible" showClose="false">
      <div class="group">
        <input placeholder="请输入要捐赠书籍的ISBN编码" class="input" v-model="content">
      </div>
      <div style="width: 90%;height: 60px;margin: auto">
        <button style="display: inline;float: left"
                @click="yes"
        >确认</button>
        <!--        <div style="width:50px;"></div>-->
        <button style="display: inline;float: right"
                @click="no"
        >取消</button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "DonationbookAdd",
  data() {
    return {
      dialogTableVisible: true,
      content:""
    };
  },
  methods: {
    no(){
      this.$router.push({
        path: "/index",
      });
      this.content=""
    },
    handleDialogClose(){
      this.no()
    },
    yes(){
      //alert(this.$myglobal.nowuserid)
      var _this=this
      if(this.content!="") {

        //this.$message(this.content)
        this.$axios.post("/addDonationbook",{
          content:_this.content,
          type:"捐书"
        })
            .then((res) => {
              //alert("res")
              // console.log(res.data)
              if (res.data == "yes") {
                this.$message.success("捐书成功，感谢您的捐赠！");
                //alert(res.data)
                this.content = ""
                this.$router.push({
                  path: "/index",
                });
              } else {
                console.log(res.data);
                this.$message.error("不存在有该ISBN的书籍，请输入正确的ISBN编码");
              }
            });
        //alert(res.data)

      }else{
        this.$message.error("书籍ISBN不能为空！")
      }
    },

  },
}
</script>

<style scoped>
.group {
  display: flex;
  line-height: 28px;
  align-items: center;
  position: relative;
  max-width: 90%;
  margin: auto;
}

.input {
  height: 40px;
  line-height: 28px;
  padding: 0 1rem;
  width: 100%;
  padding-left: 2.5rem;
  border: 2px solid transparent;
  border-radius: 8px;
  outline: none;
  background-color: #D9E8D8;
  color: #0d0c22;
  box-shadow: 0 0 5px #C1D9BF, 0 0 0 10px #f5f5f5eb;
  transition: .3s ease;
}

.input::placeholder {
  color: #777;
}

.icon {
  position: absolute;
  left: 1rem;
  fill: #777;
  width: 1rem;
  height: 1rem;
}
button {
  padding: 1.3em 3em;
  font-size: 12px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  margin-top: 30px;
}

button:hover {
  background-color: #23c483;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}

button:active {
  transform: translateY(-1px);
}
</style>