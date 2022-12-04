<template>
  <Contentfield>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
        <el-breadcrumb-item style="display: flex">
          <a href="/admin/admindonationbook" style="color: black">捐书管理</a>
        </el-breadcrumb-item>
        <el-breadcrumb-item style="display: flex">
          <a href="/admin/admindonationmoney">捐款管理</a>
        </el-breadcrumb-item>
      </el-breadcrumb>
      <el-table
          :data="tableData"
          style="width: 100%"
          :default-sort="{ prop: 'order', order: 'ascending' }"
      >
        <el-table-column prop="order" label="序号" sortable width="150" :show-overflow-tooltip="true"> </el-table-column>
        <el-table-column prop="userid" label="用户id" sortable width="150" :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column prop="content" label="ISBN"  sortable width="150" :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column prop="feedbacktime" label="提出时间" width="150" sortable>
        </el-table-column>
        <el-table-column
            prop="state"
            label="处理状态"
            width="150"
            sortable
        >
          <template slot-scope="scope">
            <div v-if="scope.row.state==0">未处理</div>
            <div v-if="scope.row.state==1" style="color:red">已拒绝</div>
            <div v-if="scope.row.state==2" style="color:green">已同意</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="350px" align="center">
          <template slot-scope="scope" style="display: flex">
            <el-button @click="bookdetail(scope.$index, scope.row);">查看书籍</el-button>
            <el-button @click="userdetail(scope.$index, scope.row);">查看用户</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="书籍信息" :visible.sync="dialogTableVisible_book" >
        <div v-if="book!=null">
          <el-form label-width="80px">
            <el-form-item label="isbn">
              <div>{{book.isbn}}</div>
            </el-form-item>
            <el-form-item label="书名">
              <div>{{book.bookname}}</div>
            </el-form-item>
            <el-form-item label="作者">
              <div>{{book.author}}</div>
            </el-form-item>
            <el-form-item label="类型">
              <div>{{book.type}}</div>
            </el-form-item>
            <el-form-item label="出版社">
              <div>{{book.publisher}}</div>
            </el-form-item>
            <el-form-item label="出版日期">
              <div>{{book.publishdate}}</div>
            </el-form-item>
            <el-form-item label="内容介绍">
              <div>{{book.bookabstract}}</div>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addbook" style="float: left" v-if="state==0">同意</el-button>
              <el-button @click="refuse" style="float: right" type="danger" v-if="state==0">拒绝</el-button>
            </el-form-item>
          </el-form>
        </div>
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
      dialogTableVisible_book: false,
      dialogTableVisible_user: false,
      state:0,
      isbn:"",
      book:null,
      feedbackid:null,
      userid:null,
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
          if (item.type == "捐书") {
            item.order=i
            i=i+1
            console.log(item);
            if (item.replytime == null || item.replytime == '') {
              item.state = 0
            } else {
              if(item.replycontent == null || item.replycontent == ''){
                item.state=2
              }else {
                item.state = 1
              }
            }
            vm.tableData.push(item);
          }
        }
      });
    },
    bookdetail(index, row) {
      this.dialogTableVisible_book=true
      this.dialogTableVisible_user=false
      this.isbn=row.content
      this.state=row.state
      console.log(index)
      console.log(this.tableData[index])
      console.log(this.tableData[index].id)
      this.feedbackid=this.tableData[index].id
      console.log(this.feedbackid)
      //this.feedbackid=row.id
      //this.$message(row.id)
      //console.log(this.tableData[index])
      this.userid=row.userid
      var _this=this
      this.$axios
          .post("/getbookinfo", {
            isbn:_this.isbn
          })
          .then((res) => {
            // console.log(res.data)
            if (res.data!=null) {
              _this.book=res.data
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
    addbook(){
      var _this=this
      //this.$message("dianji")
      this.$axios
          .post("/Adminf_book", {
            id:_this.feedbackid
          })
          .then((res) => {
            // console.log(res.data)
            if (res.data!=null) {
              _this.book=res.data
              _this.$message.success("入库成功")
            } else {
              _this.$message.error("系统繁忙，请稍后再试");
            }
            location.reload()
          });
    },
    refuse(){
      var _this=this
      //this.$message("this.feedbackid")
      //this.$message(this.feedbackid+'')
      this.$message("正在处理请稍等")
      this.$axios
          .post("/refuse", {
            id:_this.feedbackid
          })
          .then((res) => {
            // console.log(res.data)
            location.reload()
          });
    }
  },
}
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