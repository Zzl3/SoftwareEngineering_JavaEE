<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
      <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/admin/adminborrow">借阅管理</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div class="rwd-table">
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'date', order: 'descending' }"
      >
        <el-table-column prop="starttime" label="借阅日期" sortable width="150">
        </el-table-column>
        <el-table-column prop="returntime" label="归还日期" sortable width="150">
        </el-table-column>
        <el-table-column prop="bookid" label="书籍ID" sortable width="90">
        </el-table-column>
        <el-table-column prop="userid" label="用户ID" width="90"> </el-table-column>
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
            <el-button size="mini" @click="borrowcon(scope.$index, scope.row)"
              >确认借阅</el-button
            >
            <el-button
              size="mini"
              type="warning"
              @click="returncon(scope.$index, scope.row)"
              >确认归还</el-button
            >
          </template>
        </el-table-column>
        <el-table-column label="查看详情" width="100px">
          <template slot-scope="scope">
            <el-button
              type="info"
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              >查看详情</el-button
            >
          </template>
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
    borrowcon(index, row) {
      var _this = this;
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
        });
      }
    },
    returncon(index, row) {
      var _this = this;
      console.log(index, row);
      if (row.status != "归还中") {
        _this.$message({
          showClose: true,
          message: "该借阅并未在归还中",
          type: "warning",
        });
      } else {
        this.$axios.post("/changeborrow", {
          userid: row.userid,
          bookid: row.bookid,
          status: "已结束",
        });
        _this.$message({
          showClose: true,
          message: "归还成功",
          type: "success",
        });
      }
    },
    filterTag(value, row) {
      return row.status === value;
    },
  },
};
</script>

<style scoped>
.rwd-table {
  position: absolute;
  top: 150px;
  left: 300px;
}
</style>
