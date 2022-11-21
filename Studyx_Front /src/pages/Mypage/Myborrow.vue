<template>
  <div>
    <el-breadcrumb separator="/" style="margin-top: 40px; margin-left: 350px">
      <el-breadcrumb-item :to="{ path: '/user' }">我的页面</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/user/myborrow">我的借阅</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div class="rwd-table">
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort="{ prop: 'date', order: 'descending' }"
      >
        <el-table-column prop="date" label="借阅日期" sortable width="150">
        </el-table-column>
        <el-table-column prop="name" label="书籍名称" sortable width="150">
        </el-table-column>
        <el-table-column prop="time" label="剩余时间" width="100"> </el-table-column>
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
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >我要归还</el-button
            >
            <el-button
              size="mini"
              type="warning"
              @click="handleDelete(scope.$index, scope.row)"
              >取消借阅</el-button
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
      tableData: [
        {
          date: "2022-11-13",
          name: "一本书",
          time: "28天",
          status: "申请中",
        },
      ],
    };
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
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
  left: 350px;
}
</style>
