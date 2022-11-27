<template>
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
</template>

<script>
export default {
  name: "DonationmoneyAdd"
}
</script>

<style scoped>

</style>