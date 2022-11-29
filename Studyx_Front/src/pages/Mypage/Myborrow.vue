<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
      <el-breadcrumb-item :to="{ path: '/user' }">我的页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/user/myborrow">我的借阅</a></el-breadcrumb-item>
    </el-breadcrumb>
    <!-- <input type="text" name="text" class="input" placeholder="请输入要搜索的书籍～" /> -->
    <div class="rwd-table">
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'date', order: 'descending' }"
      >
        <el-table-column prop="starttime" label="借阅日期" sortable width="170">
        </el-table-column>
        <el-table-column prop="returntime" label="归还日期" sortable width="170">
        </el-table-column>
        <el-table-column prop="bookid" label="书籍ID" sortable width="100">
        </el-table-column>
        <el-table-column prop="during" label="剩余时间" width="100"> </el-table-column>
        <el-table-column
          prop="status"
          label="当前状态"
          width="100"
          :filters="[
            { text: '申请中', value: '申请中' },
            { text: '借阅中', value: '借阅中' },
            { text: '归还中', value: '归还中' },
            { text: '已结束', value: '已结束' },
            { text: '未借阅', value: '未借阅' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="
                scope.row.status == '申请中'
                  ? ''
                  : scope.row.status == '借阅中'
                  ? 'success'
                  : scope.row.status == '归还中'
                  ? 'danger'
                  : scope.row.status == '已结束'
                  ? 'warning'
                  : scope.row.status == '未借阅'
                  ? 'info'
                  : 'primary'
              "
              disable-transitions
              >{{ scope.row.status }}</el-tag
            >
          </template>
        </el-table-column>
        <!-- <el-table-column prop="status" label="当前状态" width="150">
        </el-table-column> -->
        <el-table-column label="操作按钮" width="200px">
          <template slot-scope="scope">
            <el-button size="mini" @click="returnreq(scope.$index, scope.row)"
              >我要归还</el-button
            >
            <el-button
              size="mini"
              type="warning"
              @click="borrowcan(scope.$index, scope.row)"
              >取消借阅</el-button
            >
          </template>
        </el-table-column>
        <el-table-column label="查看详情" width="100px">
          <template slot-scope="scope">
            <el-popover
              placement="top-start"
              title="书本详情"
              width="200"
              trigger="click"
            >
              <Bookcard
                :isbn="isbn"
                :donatetime="donatetime"
                :userid="userid"
              ></Bookcard>
              <el-button
                slot="reference"
                size="mini"
                type="danger"
                @click="viewbookdetail(scope.$index, scope.row)"
                >查看详情</el-button
              >
            </el-popover>
            <!--         
            <el-popover
              placement="top-start"
              title="书本信息"
              width="300"
              height="1000"
              trigger="click"
            >
              <Bookcard
                :isbn="isbn"
                :donatetime="donatetime"
                :useridd="useridd"
              ></Bookcard>

            </el-popover> -->
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import Bookcard from "@/components/MypageCom/MyBorrowCom/Bookcard.vue";
export default {
  components: { Bookcard },
  data() {
    return {
      userid: "",
      isbn: "",
      donatetime: "",
      tableData: [],
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    getdata() {
      let vm = this;
      this.$axios({
        url: "/user/findallborrow",
        method: "post",
        data: vm.$myglobal.nowuserid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        for (let item of res.data) {
          console.log(item);
          vm.tableData.push(item);
        }
      });
    },
    returnreq(index, row) {
      let _this = this;
      if (row.status != "借阅中") {
        _this.$message({
          showClose: true,
          message: "该书籍并未在借阅中",
          type: "warning",
        });
      } else {
        this.$axios.post("/changeborrow", {
          userid: _this.$myglobal.nowuserid,
          bookid: row.bookid,
          status: "归还中",
        });
        _this.$message({
          showClose: true,
          message: "正在申请归还",
          type: "success",
        });
      }
    },
    borrowcan(index, row) {
      let _this = this;
      if (row.status != "申请中") {
        _this.$message({
          showClose: true,
          message: "该书籍并未在申请中",
          type: "warning",
        });
      } else {
        this.$axios.post("/changeborrow", {
          userid: _this.$myglobal.nowuserid,
          bookid: row.bookid,
          status: "未借阅",
        });
        _this.$message({
          showClose: true,
          message: "取消借阅成功",
          type: "success",
        });
      }
    },
    filterTag(value, row) {
      return row.status === value;
    },
    viewbookdetail(index, row) {
      let _this = this;
      this.$axios({
        url: "/user/getbookdetail",
        method: "post",
        data: row.bookid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        console.log(res);
        _this.donatetime = res.data.donatetime;
        _this.isbn = res.data.isbn;
        _this.userid = res.data.userid;
      });
    },
  },
};
</script>

<style scoped>
.input {
  position: absolute;
  top: 100px;
  right: 25px;
  border: none;
  border-radius: 15px;
  padding: 15px;
  background-color: #e8e8e8;
  box-shadow: 6px 6px 12px #ffffff, -6px -6px 12px #c5c5c5;
  font-size: medium;
  font-weight: bold;
  max-width: 200px;
}

.input:focus {
  outline-color: white;
  place-content: "Enter your message!";
}
.rwd-table {
  position: absolute;
  top: 190px;
  left: 330px;
}
</style>
