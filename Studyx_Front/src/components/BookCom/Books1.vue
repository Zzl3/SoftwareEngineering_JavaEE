<template>
  <div>
    <el-row style="height: 840px;">
      <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
      <view-switch class="switch"></view-switch>
      <el-tooltip effect="dark" placement="right"
                  v-for="item in books.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.bookname}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.author}}</span> /
          <span>{{item.publishdate}}</span> /
          <span>{{item.publisher}}</span>
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover">
            <img :src="temcover" alt="封面">
          </div>
          <div class="info">
            <div class="title" v-on:click="goDetail(item)">
              {{item.bookname}}
            </div>
          </div>
          <div class="author">{{item.author}}</div>
        </el-card>
      </el-tooltip>
    </el-row>
    <el-row>
      <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pagesize"
          :total="books.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import SearchBar from './SearchBar'
import ViewSwitch from './ViewSwitch'

export default {
  name: 'Books1',
  components: {SearchBar, ViewSwitch},
  data () {
    return {
      temcover:"https://zjjjjjj_----zjjj.gitee.io/javaee/image/cover.png",
      books: [],
      currentPage: 1,
      pagesize: 18
    }
  },
  mounted: function () {
    this.loadBooks()
  },
  methods: {
    loadBooks () {
      var _this = this
      this.$axios
          .get('/books')
          .then(resp => {
        if (resp && resp.data.code === 200) {
          _this.books = resp.data.result
          this.$message.error("共"+_this.books.length+"条");
        }
      })
          .catch(failResponse => {
            this.$message.error("数据发送失败");
          })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    searchResult () {
      var _this = this
      this.$axios
          .get('/search?keywords=' + this.$refs.searchBar.keywords, {
          }).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.books = resp.data.result
        }
        else{
          this.$message.error("查询不到结果");
        }
      })
          .catch(failResponse => {
            this.$message.error("数据发送失败");
          })
    },
    goDetail(item) {

      this.$router.push({path:'/project/allproject',query: {id:item.bookname}})

    }
  }
}
</script>
<style scoped>

.cover {
  width: 115px;
  height: 172px;
  margin-bottom: 7px;
  overflow: hidden;
  cursor: pointer;
}

img {
  width: 115px;
  height: 172px;
  /*margin: 0 auto;*/
}

.title {
  font-size: 14px;
  text-align: left;
  cursor: pointer;
}

.author {
  color: #333;
  width: 102px;
  font-size: 13px;
  margin-bottom: 6px;
  text-align: left;
}

.abstract {
  display: block;
  line-height: 17px;
}

.el-icon-delete {
  cursor: pointer;
  float: right;
}

.switch {
  display: flex;
  position: absolute;
  left: 780px;
  top: 25px;
}

a {
  text-decoration: none;
}

a:link, a:visited, a:focus {
  color: #3377aa;
}

</style>
