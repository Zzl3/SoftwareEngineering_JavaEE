<template>
  <el-card :body-style="{ padding: '0px' }">
    <img :src="books[0].cover" alt="封面">
    <div style="padding: 14px;">
      <h2 class="title">{{ books[0].title }}</h2>
      <p>{{books[0].author}}</p>
      <p>{{books[0].publishdate}}</p>
      <p>{{books[0].publisher}}</p>
      <p>{{books[0].bookabstract}}</p>

      <div class="bottom clearfix">
        <el-button @click="goBack" type="text" class="button">返回</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "BookDetails",
  data() {
    return {
      books: [],
      currentPage: 1,
      pagesize: 18
    };
  },
  mounted: function () {
    this.loadBooks()
  },
  methods: {
    loadBooks() {
      var _this = this

      this.$axios
          .get('/search?keywords='+_this.$route.query.id,{
          })
          .then(resp => {
            if (resp && resp.data.code === 200) {
              _this.books = resp.data.result
              this.$message.error("共" + _this.books.length + "条");
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
  },
}
</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

</style>

