<template>
  <div>
    <Navigation></Navigation>
    <el-container>
      <el-aside style="width: 200px; margin-top: 20px">
        <switch></switch>
        <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
      </el-aside>
      <el-main>
        <books class="books-area" ref="booksArea"></books>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Navigation from "@/components/NavigaCom/Navigation";
import SideMenu from "@/components/BookCom/SideMenu";
import Books from "@/components/BookCom/Books";
import ReturnButton from "@/components/BookCom/ReturnButton";
import SearchBar from "@/components/BookCom/SearchBar";
import ViewSwitch from "@/components/BookCom/ViewSwitch";

export default {
  name: "BookDisplay",
  components: { Books, SideMenu, SearchBar, ViewSwitch, Navigation },
  methods: {
    listByCategory() {
      var _this = this;
      var cid = this.$refs.sideMenu.cid;
      var url = "categories/" + cid + "/books";
      this.$axios.get(url).then((resp) => {
        if (resp && resp.data.code === 200) {
          _this.$refs.booksArea.books = resp.data.result;
          _this.$refs.booksArea.currentPage = 1;
        }
      })
    },

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
