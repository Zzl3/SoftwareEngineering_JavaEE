<template>

  <div>
    <br />
    <Collectionclick @click.native="addcollection"></Collectionclick>
    <div>
          <div>
              <div>
                <el-row>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                  <el-col :span="12">
                      <br>
                      <br>
                      <br>
                    <h1 class="book-title">
                      <span property="v:itemreviewed">{{books[0].bookname}}</span>
                    </h1>
                  </el-col>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>

                  </el-row>
                  <el-row  >
                      <el-col :span="6"><div class="grid-content bg-purple">    </div></el-col>
                      <el-col :span="12">
                        <img class="book-cover"src="https://img9.doubanio.com/view/subject/s/public/s34327482.jpg" title="点击看大图" alt="长安的荔枝" rel="v:photo" style="max-width: 135px;max-height: 200px;">
                      </el-col>
                    <el-col :span="6" class="info">
                      <span class="pl"> 作者</span>:
                      <span class="pl">{{books[0].author}}</span>
                      <br>
                      <span class="pl">出版社:</span>
                      <span class="pl">{{books[0].publisher}}</span>
                      <br>
                      <span class="pl">出版日期:</span>
                      <span class="pl">{{books[0].publishdate}}</span><br>
                      <span class="pl">定价:</span>
                      <span class="pl">{{books[0].price}}</span><br>
                      <span class="pl">ISBN:</span>
                      <span class="pl">{{books[0].isbn}}</span><br></el-col>
                    <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                </el-row>
                <br>
                <br>
                <el-row>
                  <el-col :span="6"><div class="grid-content bg-purple">    </div></el-col>
                  <el-col :span="12">
                    <el-button type="warning" plain @click="addcollection">书籍收藏</el-button>
                    <el-button type="warning" plain @click="addborrow">书籍借阅</el-button>
                    <el-dialog title="选择收藏夹" :visible.sync="dialogTableVisible">
                      <el-table :data="gridData">
                        <el-table-column property="dirname" label="名称" width="150"></el-table-column>
                        <el-table-column property="content" label="简介" width="350"></el-table-column>
                        <el-table-column label="选择">
                          <template slot-scope="scope">
                            <el-button size="mini" @click="checkcollect(scope.$index, scope.row)"
                            >确认</el-button
                            >
                          </template>
                        </el-table-column>
                      </el-table>
                    </el-dialog>
                  </el-col>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                </el-row>

                <el-row>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                  <el-col :span="12"><p>发布评论：</p></el-col>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                </el-row>
                <el-row>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                  <el-col :span="12">

                    <Remark @onRemark="addRemark" ref="Remark"></Remark>

                    <!--评论区-->
                    <el-card v-for="item in remarks.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                             :key="item.id">
                      <div class="info">
                        <div class="title" >
                          {{item.content}}
                          {{item.isbn}}
                          {{item.remarktime}}
                          {{item.userid}}
                          {{item.remarkid}}
                        </div>
                        <i class="el-icon-delete" @click="deleteRemark(item.remarkid)">删除评论</i>
                      </div>
                    </el-card>
                    <el-row>
                      <el-pagination
                          @current-change="handleCurrentChange"
                          :current-page="currentPage"
                          :page-size="pagesize"
                          :total="remarks.length">
                      </el-pagination>
                    </el-row>
                  </el-col>
                  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
                </el-row>




              </div>

              <div style="padding: 14px;">

                <el-rate
                    v-model="books[0].mark"
                    disabled
                    max="10"
                    show-score
                    text-color="#ff9900"
                    score-template="{value}">
                </el-rate>
                <div class="bottom clearfix">
                  <el-button @click="goBack" type="text" class="button">返回</el-button>
                </div>
              </div>
          </div>
        </div>
        <el-col :span="1"><el-divider direction="vertical"></el-divider></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <p>  </p>
          <p>  </p>


          <p>  </p>
          <p>  </p>

          <p>  </p>
          <p>  </p>

        </div>
        </el-col>
    </div>

</template>

<script>
import "animate.css";
import Star from '@/components/BookCom/Star'
import Score from '@/components/BookCom/Score'
import ReturnButton from '@/components/BookCom/ReturnButton'
import Remark from '@/components/BookCom/Remark'
import Books from '@/components/BookCom/Books'
import BookDetails from '@/components/BookCom/BookDetails'
import Collectionclick from "@/components/MypageCom/MyCollectionCom/CollectionClick";
export default {
  name: "Projectall",
  components: { Collectionclick,Star,Score,ReturnButton,Remark,Books,BookDetails, },
  data() {
    return {
      gridData: [],
      dialogTableVisible: false,
      bookid: 1,
      books: [],
      isbn:'',
      remarks:[],
      currentPage: 1,
      pagesize: 5,
    };
  },
  mounted: function () {
    this.loadBooks()
  },
  methods: {
    deleteRemark(remarkid){
      this.$confirm('此操作将永久删除该评论, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
            this.$axios
                .post('/delete', {remarkid: remarkid}).then(resp => {

              if (resp && resp.status === 200) {
                this.loadRemarks()
                this.$message({
                  type: 'info',
                  message: '已删除'
                })

              }
            })
          }
      ).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    loadRemarks(){
      var _this = this
      this.$axios
          .get('/remarks?isbn='+_this.isbn,{
          })
          .then(resp => {
            if (resp && resp.data.code === 200) {
              _this.remarks = resp.data.result
            }
            else{
              this.$message.error("检索失败");
            }
          })
          .catch(failResponse => {
            this.$message.error("评论数据发送失败");
          })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    loadBooks() {
      var _this = this

      this.$axios
          .get('/search?keywords='+_this.$route.query.id,{
          })
          .then(resp => {
            if (resp && resp.data.code === 200) {
              _this.books = resp.data.result
              //_this.isbn=_this.books[0].isbn
              _this.isbn=_this.books[0].isbn
              this.loadRemarks()
              //this.$message.error("共" + _this.isbn);
            }
          })
          .catch(failResponse => {
            this.$message.error("书籍数据发送失败");
          })

    },
    goBack(){
      this.$router.replace('/library')
    },
    book () {
      this.$axios
          .post('/book', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({path: '/index'})
            }
          })
          .catch(failResponse => {
          })
    },
    addRemark(){
      var _this = this
      this.$axios
          .post('/addremarks', {
            content: this.$refs.Remark.textarea, //this.textarea,//从remark.vue传递过来
            userid: '1',//需要连上接口判断
            isbn: _this.isbn,
            remarktime:this.Date(),
            remarkid:'0',//等待自增
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.error("发布成功！");
              this.loadRemarks()//实时显示评论
            }
            else{
              this.$message.error("评论未上传");
            }
          })
          .catch(failResponse => {
            this.$message.error("发布失败！");
          })
    },
    Date(){

      const nowDate = new Date();

      const date = {

        year: nowDate.getFullYear(),

        month: nowDate.getMonth() + 1,

        date: nowDate.getDate(),

        hours: nowDate.getHours(),

        minutes: nowDate.getMinutes(),

        seconds: nowDate.getSeconds(),

      };

      const newmonth = date.month > 10 ? date.month : "0" + date.month;

      const newday = date.date > 10 ? date.date : "0" + date.date;

      const newminutes = date.minutes > 10 ? date.minutes : "0" + date.minutes;

      const newseconds = date.seconds > 10 ? date.seconds : "0" + date.seconds;

      //   const newminutes = date.minutes < 10 ? "0" + date.minutes : date.minutes;

      //   const newseconds = date.seconds < 10 ? "0" + date.seconds : date.seconds;

      //this.remarktime =

      return date.year + "-" + newmonth + "-" + newday + " " + date.hours + ":" + newminutes + ":" + newseconds;

    },

    addcollection() {
      let vm = this;
      vm.gridData = undefined;
      vm.gridData = new Array(); //先清空再进行筛选
      this.dialogTableVisible = true;
      this.$axios({
        url: "/user/getcollectiondir",
        method: "post",
        data: vm.$myglobal.nowuserid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        for (let item of res.data) {
          console.log(item);
          vm.gridData.push(item);
        }
      });
    },
    checkcollect(index, row) {
      let _this = this;
      // alert(row.dirname);
      this.$axios
        .post("/user/setcollection", {
          userid: _this.$myglobal.nowuserid,
          isbn: _this.isbn, //这个就是需要对接的接口
          dirname: row.dirname,
        })
        .then((res) => {
          if (res.data == "0") {
            _this.$message({
              showClose: true,
              message: "添加失败",
              type: "error",
            });
          } else {
            this.$message({
              showClose: true,
              message: "您已完成收藏",
              type: "success",
            });
          }
          this.dialogTableVisible = false;
        });
    },
    addborrow() {
      let _this = this;
      this.$axios
        .post("/user/setborrow", {
          userid: _this.$myglobal.nowuserid,
          bookid: _this.bookid, //这个就是需要对接的接口
          status: "申请中",
        })
        .then((res) => {
          console.log(res)
          if (res.data == '') {
            _this.$message({
              showClose: true,
              message: "您已被封禁!无法借阅书籍",
              type: "error",
            });
          } else {
            _this.$message({
              showClose: true,
              message: "您已开始申请",
              type: "success",
            });
          }
        });
    },
  },
};
</script>

<style>
.book-title{
  font: 12px Helvetica,Arial,sans-serif;
  word-wrap: break-word;
  display: block;
  font-weight: bold;
  color: #494949;
  margin: 0;
  padding: 0 0 15px 0;
  line-height: 1.1;
  font-size: 26px;
}
.book-cover{
  font: 12px Helvetica,Arial,sans-serif;
  line-height: 1.62;
  font-size: 13px;
  word-break: normal;
  word-wrap: break-word;
  text-align: center;
  cursor: pointer;
  color: #3377aa;
  border: 0;
  border-width: 0;
  vertical-align: middle;
  margin-bottom: 10px;
  max-width: 135px;
  max-height: 200px;
}
.book-title{
  font: 12px Helvetica,Arial,sans-serif;
  word-wrap: break-word;
  display: block;
  font-weight: bold;
  color: #494949;
  margin: 0;
  padding: 0 0 15px 0;
  line-height: 1.1;
  font-size: 26px;
}
.book-info{
  font: 12px Helvetica,Arial,sans-serif;
  line-height: 1.62;
  font-size: 13px;
  color: #111;
  word-break: normal;
  margin: 0;
  padding: 0;
  float: left;
  word-wrap: break-word;
  max-width: 333px;
}
.pl{
  word-break: normal;
  word-wrap: break-word;
  font: 12px Arial, Helvetica, sans-serif;
  line-height: 150%;
  color: #666666;
  font-size: 13px;
}
.info{
  font: 12px Helvetica,Arial,sans-serif;
  line-height: 1.62;
  font-size: 13px;
  color: #111;
  word-break: normal;
  margin: 0;
  padding: 0;
  float: left;
  word-wrap: break-word;
  max-width: 333px;
  Text-align:left
}
.book-body{
  color: #111;
  padding: 0;
  width: 1024px;
  margin: 0;
  font: 12px Helvetica,Arial,sans-serif;
  line-height: 1.62;
  font-size: 13px;
}
.star{
  word-break: normal;
  word-wrap: break-word;
  cursor: pointer;
  float: left;
  letter-spacing: 3px;
  overflow: hidden;
  background: transparent  no-repeat scroll right top !important;
  display: block;
  height: 24px;
  padding-right: 7px;
  font: normal 12px sans-serif;
  margin-right: 10px;
  text-decoration: none;
  color: #111;
}
</style>
