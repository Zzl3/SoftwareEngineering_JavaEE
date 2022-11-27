<template>
  <Contentfield>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
        <el-breadcrumb-item
          ><a href="/admin/adminfeedback">反馈管理</a></el-breadcrumb-item
        >
      </el-breadcrumb>
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'date', order: 'ascending' }"
      >
        <el-table-column prop="id" label="序号" sortable width="150"> </el-table-column>
        <el-table-column prop="userid" label="用户id" sortable width="150">
        </el-table-column>
        <el-table-column prop="content" label="反馈内容" width="150">
        </el-table-column>
        <el-table-column prop="feedbacktime" label="反馈时间" width="150" sortable>
        </el-table-column>
        <el-table-column
            prop="replytime"
            label="答复状态"
            width="150"
            sortable
        >
          <template slot-scope="scope">
            <div>{{scope.row.replycontent==null?'未答复':'已答复'}}</div>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="status" label="当前状态" width="150">
          </el-table-column> -->
        <el-table-column label="查看详情" width="150px">
          <template slot-scope="scope">
            <el-button @click="detail(scope.$index, scope.row);">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </Contentfield>
</template>

<script>
import Contentfield from "../../components/AdminCom/Contentfield.vue";
export default {
  components: { Contentfield },
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
    detail(index, row) {
      //alert("diji")
      this.$router.push({
        path: "/admin/adminfeed?id=" + row.userid,
      });
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
.el-breadcrumb {
  height: 50px;
}
</style>
