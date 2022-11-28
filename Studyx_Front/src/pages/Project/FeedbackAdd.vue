<template>
  <div>
    <el-dialog :before-close="handleDialogClose" title="意见反馈" :visible.sync="dialogTableVisible" showClose="false">
<!--      <textarea placeholder="请输入您要反馈的内容" v-model="content">
      </textarea>
      <div style="width: 90%;margin:auto;height: 40px">
        <button style="display: inline;float: left"
                @click="yes"
        >确认</button>
&lt;!&ndash;        <div style="width:50px;"></div>&ndash;&gt;
        <button style="display: inline;float: right"
                @click="no"
        >取消</button>
      </div>-->
      <el-form label-width="80px">
        <el-form-item label="反馈内容">
          <el-input type="textarea" v-model="content" placeholder="请输入您要反馈的内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="yes" style="float: left">确认</el-button>
          <el-button @click="no" style="float: right">取消</el-button>
        </el-form-item>
      </el-form>
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
</style>