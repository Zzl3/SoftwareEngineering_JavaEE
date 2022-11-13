<template>
  <div>
    <div style="float: left; margin-left: 20px; position: float">
      <!-- <img :src="base64" alt="" class="imgs" style="width=40px;height:40px"/> -->
      <el-avatar
        shape="square"
        :src="
          require('/Users/zhouzilei/Documents/GitHub/SoftwareEngineering-JavaEE/StudyX/img/avator/' +
            url +
            '.jpg')
        "
        :size="100"
        :fit="fit"
      ></el-avatar>
    </div>
    <div
      style="float: left; margin-left: 20px; margin-bottom: 20px"
      slot="tip"
      class="el-upload__tip"
    >
      只能上传jpg文件，且不超过500kb
    </div>
    <!-- 这里之后需要更改一下 -->
    <el-upload
      class="upload-demo"
      action="http://localhost:8443/api/user/covers"
      :data="upload_data"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :before-remove="beforeRemove"
      multiple
      :limit="1"
      :on-exceed="handleExceed"
      :file-list="fileList"
    >
      <el-button
        size="small"
        type="primary"
        style="float: left; margin-bottom: 50px"
        >点击上传</el-button
      >
    </el-upload>
  </div>
</template>

<script>
export default {
  data() {
    return {
      img_name: "",
      upload_data: {
        path: "temporary",
        id: "",
        target: "temporary",
      },
      url: "ghu7dl",
      fileList: [],
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleSuccess(response) {
      console.log(response);
      //这里和后端是对应的
      this.url = response;
      this.$emit('getUrl',this.url);
      this.$message.success("上传成功");
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择1个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    // beforeRemove(file, fileList) {
    //   return this.$confirm(`确定移除 ${file.name}？`);
    // },
  },
};
</script>

<style scoped></style>
