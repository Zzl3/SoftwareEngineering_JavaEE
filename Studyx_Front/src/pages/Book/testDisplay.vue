<template>
  <el-card class="box-card">
    <div class="author">{{bookList}}</div>
  </el-card>
</template>

<script>
export default {
  name: "testDisplay",
  data() {
    return {
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
    loadBooks() {
      var _this = this
      this.$axios
        .get('/books')
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            _this.bookList = successResponse.data.result
            this.$message.error(successResponse.data.message);
          }
          else
            this.$message.error("反应成功")
        })
        .catch(failResponse => {
          this.$message.error("数据发送失败");
        })

    },
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
