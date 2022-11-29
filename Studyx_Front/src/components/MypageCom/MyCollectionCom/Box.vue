<template>
  <div>
    <!-- box-1 -->
    <div class="box">
      <!-- top-bar -->
      <div class="top-bar"></div>
      <!-- view-btns  -->
      <div class="btn">
        <a href="#" v-on:click="viewdirdetail()"><i class="fas fa-eys"></i>查看详情</a>
        <a href="#" v-on:click="deletecollectdir()"
          ><i class="fas fa-eys"></i>删除收藏夹</a
        >
      </div>
      <!-- img and details  -->
      <div class="details">
        <img src="@/assets/1.png" />
        <strong>{{ Title }}</strong>
        <p>{{ Description }}</p>
      </div>
    </div>

    <el-dialog :title="Title" :visible.sync="dialogTableVisible">
      <el-table :data="gridData">
        <el-table-column property="isbn" label="ISBN" width="200"></el-table-column>
        <el-table-column
          property="collectiontime"
          label="收藏时间"
          width="200"
        ></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="deletecollection(scope.$index, scope.row)"
              >取消收藏</el-button
            >
            <el-popover
              placement="top-start"
              title="书本信息"
              width="300"
              height="1000"
              trigger="click"
              :content="catecontent"
              style="white-space: pre-wrap"
            >
              <Categorycard
                :isbn="isbn"
                :author="author"
                :bookabstract="bookabstract"
                :bookname="bookname"
                :label="label"
                :mark="mark"
                :price="price"
                :publishdate="publishdate"
                :type="type"
              ></Categorycard>
              <el-button
                slot="reference"
                size="mini"
                type="danger"
                @click="viewisbndetail(scope.$index, scope.row)"
                >查看详情</el-button
              >
            </el-popover>
            <!--             
            <el-button
              size="mini"
              type="danger"
              @click="viewisbndetail(scope.$index, scope.row)"
              >查看详情</el-button
            > -->
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import Categorycard from "./Categorycard.vue";
export default {
  components: { Categorycard },
  props: {
    Picsrc: String,
    Description: String,
    Title: String,
  },
  data() {
    return {
      isbn: "--",
      bookname: "--",
      author: "--",
      publishdate: "--",
      type: "--",
      bookabstract: "--",
      label: "--",
      price: "--",
      mark: "--",
      catecontent: "暂无信息～",
      gridData: [],
      dialogTableVisible: false,
    };
  },
  methods: {
    deletecollectdir() {
      let vm = this;
      this.$axios
        .post("/user/deletecollectiondir", {
          userid: vm.$myglobal.nowuserid,
          dirname: vm.Title,
        })
        .then((res) => {
          vm.$message({
            showClose: true,
            message: "删除收藏夹成功",
            type: "success",
          });
        });
      vm.$router.go(0);
    },
    viewdirdetail() {
      let vm = this;
      vm.gridData = undefined;
      vm.gridData = new Array(); //先清空再进行筛选

      this.dialogTableVisible = true;
      this.$axios
        .post("/user/getcollection", {
          userid: vm.$myglobal.nowuserid,
          dirname: vm.Title,
        })
        .then((res) => {
          for (let item of res.data) {
            // console.log(item);
            vm.gridData.push(item);
          }
        });
    },
    deletecollection(index, row) {
      let vm = this;
      this.$axios
        .post("/user/deletecollection", {
          userid: vm.$myglobal.nowuserid,
          isbn: row.isbn,
        })
        .then((res) => {
          vm.$message({
            showClose: true,
            message: "取消收藏成功",
            type: "success",
          });
        });
      this.dialogTableVisible = false;
    },
    viewisbndetail(index, row) {
      let _this = this;
      this.$axios({
        url: "/user/getcategoryinfo",
        method: "post",
        data: row.isbn,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        console.log(res);
        _this.isbn = row.isbn;
        _this.bookname = res.data.bookname;
        _this.author = res.data.author;
        _this.publishdate = res.data.publishdate;
        _this.type = res.data.type;
        _this.bookabstract = res.data.bookabstract;
        _this.label = res.data.label;
        _this.price = res.data.price;
        _this.mark= res.data.mark;
      });
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
}
.box {
  width: 700px;
  height: 200px;
  background-color: #fff;
  box-shadow: 2px 2px 30px rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: px;
  border-radius: 10px;
  margin: 20px;
  position: relative;
}

.box .nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
.box .nav .verify {
  color: #17b667;
}
.box .nav .heart {
  color: rgba(155, 155, 155);
}
.box .nav .heart:before {
  content: "\f004";
  font-family: fontAwesome;
  line-height: 30px;
  z-index: 1;
}
.box .nav .heart-btn:checked ~ .heart:before {
  color: #e41934;
}
.box .nav .heart-btn {
  display: none;
}
.box .details {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.box .details img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden;
  object-fit: cover;
  object-position: center;
}
.box .details strong {
  font-weight: 500;
  color: #141414;
  letter-spacing: 1px;
}
.box .details p {
  font-size: 0.8rem;
  color: #7a7a7a;
  margin: 5px 0px;
}
.box .btn {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box .btn a {
  color: #8b8b8b;
  padding: 8px 22px;
  border-radius: 20px;
  font-size: 0.9rem;
}
.box .btn a i {
  margin-right: 10px;
}
.box .btn a:hover {
  color: #fff;
  background-color: #507bfc;
  box-shadow: 2px 5px 15px rgba(80, 123, 252, 0.5);
  transition: all ease 0.3s;
}
.box:hover {
  box-shadow: 2px 2px 30px rgba(4, 15, 49, 0.1);
  transform: scale(1.01);
  transition: all ease 0.1s;
}
</style>
