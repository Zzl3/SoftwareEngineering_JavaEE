<template>
  <div style="margin-bottom: 30px;display: flex;justify-content: center;align-items: center">
    <el-form>
      <el-form-item>
        <el-input
          @keyup.enter.native="searchClick"
          placeholder="请输入关键词..."
          prefix-icon="el-icon-search"
          size="small"
          style="width: 400px;margin-right: 10px"
          v-model="keyword">
        </el-input>
        <el-form-item>
          <el-select v-model="howsearch" placeholder="请选择查找方式">
            <el-option label="按书名" value="bybookname"></el-option>
            <el-option label="按作者" value="byauthor"></el-option>
          </el-select>
        </el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="searchClick">搜索</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data () {
    return {
      keyword: '',
      howsearch:"bybookname"
      //books: [],
      //cardLoading: []
    }
  },
  methods: {
    searchClick () {
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
            this.$message.error("数据发送失败");
          })
      }
    },
  }
}
</script>

<style scoped>

</style>
