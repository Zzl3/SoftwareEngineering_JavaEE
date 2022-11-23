<template>
  <div>
    <!-- 到时候需要改这里的接口 -->
    <el-upload
      class="avatar-uploader"
      action="http://localhost:8443/api/user/covers"
      :show-file-list="false"
      accept=".jpg,.png"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <!-- <div style="float: left; margin-left: 20px; position: float">
      <el-avatar shape="square" v-if="url" :src="url" :size="100" :fit="fit"></el-avatar>
    </div>
    <div
      style="float: left; margin-left: 20px; margin-bottom: 20px"
      slot="tip"
      class="el-upload__tip"
    >
      只能上传jpg文件，且不超过500kb
    </div>
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
      <el-button size="small" type="primary" style="float: left; margin-bottom: 50px"
        >点击上传</el-button
      >
    </el-upload> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: "",
      // img_name: "",
      // upload_data: {
      //   path: "temporary",
      //   id: "",
      //   target: "temporary",
      // },
      // url: "https://gitee.com/zjjjjjj_----zjjj/javaee/blob/master/image/0e3d8d05-6672-43a8-a7cd-3cf00cd34d32.jpg",
      // fileList: [],
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.$myglobal.setimageurl(this.imageUrl);//设置头像文件路径
      // alert(this.$myglobal.imageurl);
      this.$emit("getUrl", res);
      this.$message.success("上传成功");
    },
    beforeAvatarUpload(file) {
      //在头像上传之前需要做的判断，如判断文件格式
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
    },
    // handleRemove(file, fileList) {
    //   console.log(file, fileList);
    // },
    // handlePreview(file) {
    //   console.log(file);
    // },
    // handleSuccess(response) {
    //   console.log(response);
    //   //这里和后端是对应的
    //   this.url = response;
    //   this.$emit("getUrl", this.url);
    //   this.$message.success("上传成功");
    // },
    // handleExceed(files, fileList) {
    //   this.$message.warning(
    //     `当前限制选择1个文件，本次选择了 ${files.length} 个文件，共选择了 ${
    //       files.length + fileList.length
    //     } 个文件`
    //   );
    // },
    // beforeRemove(file, fileList) {
    //   return this.$confirm(`确定移除 ${file.name}？`);
    // },
  },
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #ffffff;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
