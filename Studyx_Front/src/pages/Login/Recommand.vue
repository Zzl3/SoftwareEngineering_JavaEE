<template>
  <!-- 这里是首页中的推荐部分 -->
  <div>
    <div class="recommand">
      <!--新书推荐-->
      <RecommandCard
        CardTitle="新书推荐"
        CardDescription="新进图书，欢迎借阅"
        :cardType="1"
        style="margin-top: 5%; margin-bottom: 0%"
      ></RecommandCard>

      <!--经典好书-->
      <RecommandCard
        CardTitle="经典好书"
        CardDescription="阅读经典，品味人生"
        :cardType="3"
        style="margin-top: 5%; margin-bottom: 5%"
      ></RecommandCard>

      <el-divider>
        <div style="color: #bebaba">今天你读书了吗？</div>
      </el-divider>
      <h4
        style="
        text-align: center;
        color: #bebaba;
        font-weight: normal;
        margin-top: 10vh;
      "
      >无限精彩，等着你的到来......</h4>
    </div>
  </div>
</template>


<script>
import ProjectCard from "@/components/LoginCom/ProjectCard";
import RecommandCard from "@/components/LoginCom/RecommandCard";
export default {
  name: "Recommond",
  components: { RecommandCard, ProjectCard },
  created() {
    this.changeTags(0, 5);
  },
  data() {
    return {};
  },
};
</script>

<style>
.recommand {
  width: 100%;
}
</style>