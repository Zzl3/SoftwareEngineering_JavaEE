<template>
  <div>
    <el-dialog :before-close="handleDialogClose" title="意见反馈" :visible.sync="dialogTableVisible" showClose="false">
      <textarea placeholder="请输入您要反馈的内容" v-model="content">
      </textarea>
      <div style="width: 90%;margin:auto;height: 40px">
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
  name: "FeedbackAdd",
  components: {  },
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
        this.$axios.post("/addFeedback",{
          content:_this.content,
          type:"反馈"
        })
            .then((res) => {
              //alert("res")
              // console.log(res.data)
              if (res.data == "yes") {
                this.$message.success("反馈成功，我们会尽快答复");
                //alert(res.data)

              } else {
                console.log(res.data);
                this.$message.error("反馈失败，请重新反馈");
              }
            });
        //alert(res.data)
        this.content = ""
        this.$router.push({
          path: "/index",
        });
      }else{
        this.$message.error("反馈内容不能为空！")
      }
    },

  },
};
</script>

<style scoped>
textarea {
  width: 90%;
  margin: 0.75rem  0;
  border: gray 1px solid;
  padding-left: 1.125rem;
  height: 6.5rem ;
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