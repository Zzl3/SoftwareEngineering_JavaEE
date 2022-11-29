<template>
  <el-form ref="form" :model="searchform" label-width="80px">
    <el-form-item>
      <el-input v-model="keyword" placeholder="请输入关键字"></el-input>
    </el-form-item>
    <el-form-item>
      <el-select v-model="howsearch" placeholder="请选择查找方式">
        <el-option label="按书名" value="bybookname"></el-option>
        <el-option label="按作者" value="byauthor"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="search1">立即创建</el-button>
      <el-button @click="help">取消</el-button>
    </el-form-item>
  </el-form>




</template>

<script>
export default {
  name: "test",
  data() {
    return {
      keyword:'',
      howsearch:'',
      responseResult: [],
      bookList:{
        bookname:'',
        author:'',
        isbn:'',
        type:'',
        publisher:'',
        label:'',
        summary:'',
        mark:'',
        price:'',
      },
    }
  },
  //挂载
  mounted: function () {
    this.loadBooks()
  },
  methods: {
    loadBooks () {
      var _this = this
      this.$axios
        .get('/books')
        .then(successResponse => {
        if (successResponse.data.code === 200) {
          _this.bookList = successResponse.data.result
        }
      })
    },

    search1() {
      var _this = this;
      //this.keyword=this.form.keyword;
      if (_this.howsearch == "bybookname") {
        _this.$axios
          .post('/search/byname', {
            bookname: _this.keyword,
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.error(successResponse.data.message);
              //如果查询到了内容，那么打印
            } else
              this.$message.error(successResponse.data.message);
          })
          .catch(failResponse => {
            this.$message.error("数据发送失败");
          })
      } else {
        _this.$axios
          .get('/search/byauthor?author='+_this.keyword)
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.error(successResponse.data.message);
            } else
              this.$message.error(successResponse.data.message);
          })
          .catch(failResponse => {
            this.$message.error("按作者查询数据发送失败");
          })

      }
    },
    search(){
      var _this = this
      this.$axios
        .get('/books')
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            _this.bookList = successResponse.data.result
            this.$message.error(successResponse.data.message);
            this.$router.push({
              path: "/book",
            });
          }
          else
            this.$message.error("反应成功")
        })
        .catch(failResponse => {
          this.$message.error("数据发送失败");
        })
    },
    help() {
      this.$router.replace({path: '/bookparticulars'})
    },
    resultprint(){

    },

  }
}
</script>

<style scoped>

</style>
