<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
      <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/admin/adminfeedback">反馈管理</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div class="rwd-table">
      <el-table
          :data="tableData"
          style="width: 100%"
          :default-sort="{ prop: 'date', order: 'ascending' }"
      >
        <el-table-column prop="id" label="序号" sortable width="150">
        </el-table-column>
        <el-table-column prop="userid" label="用户id" sortable width="150">
        </el-table-column>
        <el-table-column prop="content" label="反馈内容" sortable width="150">
        </el-table-column>
        <el-table-column prop="feedbacktime" label="反馈时间" width="150"> </el-table-column>
        <!-- <el-table-column prop="status" label="当前状态" width="150">
          </el-table-column> -->
        <el-table-column label="查看详情" width="100px" >
          <button @click="borrowcon(scope.$index, scope.row)">查看</button>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
        url: "/getAllFeedback",
        method: "post",
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
    borrowcon(index, row) {
      this.$router.push({
        path: "/admin/adminfeed?id="+row.userid,
      });
      /*var _this = this;
      if (row.status != "申请中") {
        _this.$message({
          showClose: true,
          message: "该借阅并未在申请中",
          type: "warning",
        });
      } else {
        this.$axios.post("/changeborrow", {
          userid: row.userid,
          bookid: row.bookid,
          status: "借阅中",
        });
        _this.$message({
          showClose: true,
          message: "借阅成功",
          type: "success",
        });*/
      }
    },
}

</script>

<style scoped>
.rwd-table {
  position: absolute;
  top: 150px;
  left: 300px;
}
</style>