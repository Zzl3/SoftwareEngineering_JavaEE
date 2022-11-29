<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <books1 class="books-area" ref="booksArea"></books1>
    </el-main>
  </el-container>
</template>

<script>


import SideMenu from '@/components/BookCom/SideMenu'
import Books1 from '@/components/BookCom/Books1'
import ReturnButton from '@/components/BookCom/ReturnButton'


export default {
  name: 'library',
  components: {Books1, SideMenu},
  methods: {
    listByCategory () {
      var _this = this
      var cid = this.$refs.sideMenu.cid
      var url = 'categories/' + cid + '/books'
      this.$axios.get('/type?cid=' + cid, {
      }).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.$refs.booksArea.books = resp.data.result
          _this.$refs.booksArea.currentPage = 1
        }
      })
    }
  }
}
</script>

<style scoped>
.books-area {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
}
</style>
