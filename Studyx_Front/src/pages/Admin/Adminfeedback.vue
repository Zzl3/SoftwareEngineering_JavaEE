<template>
  <Contentfield>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
        <el-breadcrumb-item
          ><a href="/admin/adminfeedback">反馈管理</a></el-breadcrumb-item
        >
      </el-breadcrumb>
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'order', order: 'ascending' }"
      >
        <el-table-column prop="order" label="序号" sortable width="150" :show-overflow-tooltip="true"> </el-table-column>
        <el-table-column prop="userid" label="用户id" sortable width="150" :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column prop="content" label="反馈内容" width="150" :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column prop="feedbacktime" label="反馈时间" width="150" sortable>
        </el-table-column>
        <el-table-column
            prop="state"
            label="答复状态"
            width="150"
            sortable
        >
          <template slot-scope="scope">
            <div>{{scope.row.state==0?'未答复':'已答复'}}</div>
          </template>
        </el-table-column>
        <el-table-column label="查看详情" width="350px" align="center">
          <template slot-scope="scope" style="display: flex">
            <el-button @click="feedbackdetail(scope.$index, scope.row);">查看反馈</el-button>
            <el-button @click="userdetail(scope.$index, scope.row);">查看用户</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="反馈信息" :visible.sync="dialogTableVisible_feedback" >
        <el-form ref="form" label-width="80px">
          <el-form-item label="反馈内容">
            <div>{{feedback==null?"":feedback.content}}</div>
          </el-form-item>
          <el-form-item label="答复内容">
            <el-input type="textarea" v-model="content" v-if="is_reply" placeholder="还没答复，请输入答复内容"></el-input>
            <div v-if="!is_reply">{{feedback==null?"":feedback.replycontent}}</div>
          </el-form-item>
          <el-form-item label="答复时间" v-if="!is_reply">
            <div v-if="!is_reply">{{feedback==null?"":feedback.replytime}}</div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="yes_feedback" style="float: left">确认</el-button>
            <el-button @click="no_feedback" style="float: right">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-dialog title="用户信息" :visible.sync="dialogTableVisible_user">
        <el-form ref="form" :model="user" label-width="80px">
          <el-form-item label="用户id">
            <div>{{user.id==null?"信息不详":user.id}}</div>
          </el-form-item>
          <el-form-item label="用户昵称">
            <div>{{user.username==null?"信息不详":user.username}}</div>
          </el-form-item>
          <el-form-item label="用户年龄">
            <div>{{user.age==null?"信息不详":user.age}}</div>
          </el-form-item>
          <el-form-item label="用户电话">
            <div>{{user.phone==null?"信息不详":user.phone}}</div>
          </el-form-item>
          <el-form-item label="用户邮箱">
            <div>{{user.mail==null?"信息不详":user.mail}}</div>
          </el-form-item>
          <el-form-item label="用户介绍">
            <div>{{user.detail==null?"信息不详":user.detail}}</div>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </Contentfield>
</template>

<script>
import Contentfield from "../../components/AdminCom/Contentfield.vue";
export default {
  components: { Contentfield },
  data() {
    return {
      tableData: [],
      dialogTableVisible_feedback: false,
      dialogTableVisible_user: false,
      feedbackid:null,
      userid:1,
      feedback:null,
      content:"",
      is_reply:false,
      user:{
        id:"",
        username:"",
        phone:"",
        mail:"",
        detail:'',
        age:""
      }
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    getdata() {
      let vm = this;
      this.$axios({
        url: "/getAllFeedback",
        method: "post",
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        var i=1
        for (let item of res.data) {
          if (item.type == "反馈") {
            item.order=i
            i=i+1
            console.log(item);
            if (item.replytime == null || item.replytime == '') {
              item.state = 0
            } else {
              item.state = 1
            }
            vm.tableData.push(item);
          }
        }
      });
    },
    feedbackdetail(index, row) {
      this.dialogTableVisible_feedback=true
      this.dialogTableVisible_user=false
      this.feedbackid=row.id
      this.userid=row.userid
      var _this=this
      this.$axios
          .post("/getFeedbackById", {
            id:_this.feedbackid
          })
          .then((res) => {
            console.log(res.data)
            if (res.data!=null) {
              _this.feedback=res.data
              if(res.data.replycontent==""||res.data.replycontent==null){
                _this.is_reply=true
              }else{
                _this.is_reply=false
              }
            } else {
              this.$message.error("系统繁忙，请稍后再试");
            }
          });
      //alert("diji")
      /*this.$router.push({
        path: "/admin/adminfeed?id=" + row.id,
      });*/
    },
    userdetail(index, row) {
      this.dialogTableVisible_user=true
      this.dialogTableVisible_feedback=false
      this.userid=row.userid
      var _this=this
      this.$axios
          .post("/getuserinfo", {
            id:_this.userid
          })
          .then((res) => {
            // console.log(res.data)
            if (res.data!=null) {
              _this.user.id=res.data.id
              _this.user.mail=res.data.mail
              _this.user.phone=res.data.phone
              _this.user.detail=res.data.detail
              _this.user.age=res.data.age
              _this.user.username=res.data.username
            }else{
              _this.$message("系统繁忙，请稍后再试！")
            }
          });
      //alert("diji")
      /*this.$router.push({
        path: "/admin/adminfuser?id=" + row.userid,
      });*/
    },
    yes_feedback(){
      var _this=this
      if(this.is_reply==true) {
        if (this.content != "") {
          //this.$message(this.content)
          this.$axios.post("/addReply", {
            id: _this.feedback.id,
            replycontent: _this.content
          })
              .then((res) => {
                //alert("res")
                // console.log(res.data)
                if (res.data == "yes") {
                  this.$message.success("答复成功，用户将会收到邮件");
                  location.reload()
                  //alert(res.data)

                } else {
                  console.log(res.data);
                  this.$message.error("系统繁忙，答复失败，请重新答复");
                }
              });
          //alert(res.data)
          this.content = ""
          this.dialogTableVisible_feedback = false
        } else {
          this.$message.error("答复内容不能为空！")
        }
      }else{
        this.dialogTableVisible_feedback = false
      }
    },
    no_feedback(){
      this.dialogTableVisible_feedback=false
    },
    /*yes_user(){

    },
    no_user(){
      this.dialogTableVisible_user=false
    }*/
  },
};
</script>

<style scoped>
.rwd-table {
  position: absolute;
  top: 150px;
  left: 300px;
}
.el-breadcrumb {
  height: 50px;
}
.text_class{
  /*float: left;*/
  width:90%;
  margin: auto;

}
textarea {
  width: 90%;
  margin: 0.75rem  0;
  border: gray 1px solid;
  padding-left: 1.125rem;
  height: 6.5rem ;
}
.button_x {
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

.button_x:hover {
  background-color: #23c483;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}

.button_x:active {
  transform: translateY(-1px);
}
</style>
