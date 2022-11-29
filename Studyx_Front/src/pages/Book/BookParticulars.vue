<template>
  <div>
  <el-row type="flex" class="row-bg" justify="center">
    <el-col :span="6"><div class="grid-content bg-purple">
      <h2>图书详情：</h2>

      <div>
        <el-card :body-style="{ padding: '0px' }">
          <img :src="books[0].cover" alt="封面">
          <div style="padding: 14px;">
            <h2 class="title">{{ books[0].title }}</h2>
            <p>{{books[0].author}}</p>
            <p>{{books[0].publishdate}}</p>
            <p>{{books[0].publisher}}</p>
            <p>{{books[0].bookabstract}}</p>

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
        </el-card>
      </div>

    </div></el-col>
    <el-col :span="1"><el-divider direction="vertical"></el-divider></el-col>
    <el-col :span="6"><div class="grid-content bg-purple">
      <p>  </p>
      <p>  </p>

      <p>馆藏副本：</p>
      <p>可借副本：</p>
      <p>  </p>
      <p>  </p>
      <el-button type="success" plain>借阅</el-button>
      <el-button type="warning" plain>收藏</el-button>
      <p>  </p>
      <p>  </p>
      <p>发布评论：</p>

      <Remark @onRemark="addRemark" ref="Remark"></Remark>
      <p>  </p>

      <p>  </p>
      <p>  </p>
      <p>  </p>
      <p>  </p>
      <p>  </p>
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

    </div></el-col>

  </el-row>
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

// eslint-disable-next-line no-unused-vars
export default {
  name: "BookParticulars",
  components: {Star,Score,ReturnButton,Remark,Books,BookDetails,},
  data() {
    return {
      //value:'4.5',
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
            this.$message.error("数据发送失败");
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
            this.$message.error("数据发送失败");
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

  },

};
</script>

<style>
.el-row {
  margin-bottom: 20px;
   margin-bottom: 0;

}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-divider{
  height:400px;
}
.details-area {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
}
</style>

