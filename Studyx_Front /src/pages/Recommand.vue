<template>
  <!-- 这里是首页中的推荐部分 -->
  <div class="recommand">
    <!--热门自习室-->
    <RecommandCard
      CardTitle="热门自习室"
      CardDescription="热门自习室，等你来自律"
      :cardType="1"
      style="margin-top: 5%; margin-bottom: 0%"
    >
    </RecommandCard>

    <!--热门组队-->
    <RecommandCard
      CardTitle="热门组队"
      CardDescription="热门组队，就差你一个"
      :cardType="2"
      style="margin-top: 5%"
    >
    </RecommandCard>

    <!--热门资料-->
    <RecommandCard
      CardTitle="热门资料"
      CardDescription="你想要的学习资料都在这里"
      :cardType="3"
      style="margin-top: 5%"
    >
    </RecommandCard>

    <!--热门打卡-->
    <RecommandCard
      CardTitle="热门打卡"
      CardDescription="快来一起学习打卡吧"
      :cardType="4"
      style="margin-top: 5%"
    >
    </RecommandCard>
    <el-divider>
      <div style="color: #bebaba">从点击你感兴趣的一个话题开始吧！</div>
    </el-divider>
    <h4
      style="
        text-align: center;
        color: #bebaba;
        font-weight: normal;
        margin-top: 10vh;
      "
    >
      无限精彩，等着你的到来......
    </h4>
  </div>
</template>


<script>
import RecommandCard from "@/components/RecommandCard";
export default {
  name: "Recommond",
  components: { RecommandCard },
  created() {
    this.changeTags(0, 5);
  },
  data() {
    return {
    };
  },
};
</script>

<style>
.recommand{
    width: 100%;
}
</style>