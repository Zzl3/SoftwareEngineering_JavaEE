<template>
  <div>
    这里是测试用例，由于书籍页面没有建好，所以我就先写收藏和借阅的接口，后面直接对接就可以
    <br />
    <Collectionclick @click.native="addcollection"></Collectionclick>
    <el-button @click="addcollection">书籍收藏</el-button>
    <el-button @click="addborrow">书籍借阅</el-button>
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
  </div>
</template>

<script>
import Collectionclick from "@/components/MypageCom/MyCollectionCom/CollectionClick";
export default {
  name: "Projectall",
  components: { Collectionclick },
  data() {
    return {
      gridData: [],
      dialogTableVisible: false,
      isbn: "9787020033430",
      bookid: 1,
    };
  },
  methods: {
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

<style></style>
