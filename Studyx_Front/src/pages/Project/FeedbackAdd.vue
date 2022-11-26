<template>
  <div>
    <el-dialog title="意见反馈" :visible.sync="dialogTableVisible" showClose="false">
      <textarea placeholder="请输入您要反馈的内容" v-model="content">
      </textarea>
      <div style="display: flex">
        <button style="height:30px;line-height: 15px;"
                @click="yes"
        >确认</button>
        <div style="width:50px;"></div>
        <button style="height:30px;line-height: 15px;"
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
    yes(){
      //alert(this.$myglobal.nowuserid)
      var _this=this
      if(this.content!="") {
        //this.$message(this.content)
        this.$axios({
          url: "/addFeedback",
          method: "post",
          data: _this.content,
          headers: {
            "Content-Type": "text/plain",
          },
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
</style>