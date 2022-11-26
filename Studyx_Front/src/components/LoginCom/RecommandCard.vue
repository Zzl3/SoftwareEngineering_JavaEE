<template>
  <div class="app">
    <div>
      <!--板块-->
      <h2 style="text-align: left; padding-left: 8vw; margin-bottom: 0">{{ CardTitle }}</h2>
      <h4
        style="
          padding-left: 8vw;
          text-align: left;
          color: #bebaba;
          font-weight: normal;
          margin-top: 0;
        "
      >{{ CardDescription }}</h4>
      <el-divider style="width: 80% !important"></el-divider>
      <div style="width: 85%; margin: 0 auto" class="containerFlex">
        <!--使用flex布局-->
        <div class="CardContainer" v-for="(item, index) in stayInfoList" :key="index">
          <!--卡片-->
          <div id="cards">
            <link
              rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
            <link
              href="https://fonts.googleapis.com/css?family=Alfa+Slab+One|Arima+Madurai|Atomic+Age|Bahiana|Baloo|Bangers|Barrio|Bevan|Bungee|Bungee+Inline|Chonburi|Coiny|Comfortaa|Cousine|Life+Savers|Lobster|Milonga|New+Rocker|Racing+Sans+One|Ranchers|Sigmar+One|Source+Code+Pro"
              rel="stylesheet"
            />
            <div id="obsu">
              <div id="sekil">
                <img
                  src="https://s-media-cache-ak0.pinimg.com/236x/2f/11/69/2f1169d967bd0a2505d8088011ff1a2e.jpg"
                  alt
                />
              </div>
              <div id="kitabadi">
                <h1>这是书名</h1>
              </div>
              <div id="kimindi">
                <p>这是作者</p>
              </div>
              <div id="reytinq">
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star-o" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
              </div>
              <div id="haqqinda">
                <h3>这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介</h3>
              </div>
            </div>
          </div>
        </div>
        <div class="clear"></div>
      </div>
    </div>
  </div>
</template>
  
<script>
export default {
  name: "HomeStayCard",
  components: {},
  data() {
    return {
      stayInfoList: [
        {
          StayType: "房源类型",
          StayName: "房源名称加载中...",
          UserAvatar:
            "https://joes-bucket.oss-cn-shanghai.aliyuncs.com/img/用户白名单.png",
          StayPrice: 399,
          UserId: 11111,
          StayCommentRate: 4.9,
          StayCommentNum: 0,
          StayPic: [
            "https://joes-bucket.oss-cn-shanghai.aliyuncs.com/img/房屋 (2).png",
          ],
        },
        {
          StayType: "房源类型",
          StayName: "房源名称加载中...",
          UserAvatar:
            "https://joes-bucket.oss-cn-shanghai.aliyuncs.com/img/用户白名单.png",
          StayPrice: 399,
          UserId: 11111,
          StayCommentRate: 4.9,
          StayCommentNum: 0,
          StayPic: [
            "https://joes-bucket.oss-cn-shanghai.aliyuncs.com/img/房屋 (2).png",
          ],
        },
        {
          StayType: "房源类型",
          StayName: "房源名称加载中...",
          UserAvatar:
            "https://joes-bucket.oss-cn-shanghai.aliyuncs.com/img/用户白名单.png",
          StayPrice: 399,
          UserId: 11111,
          StayCommentRate: 4.9,
          StayCommentNum: 0,
          StayPic: [
            "https://joes-bucket.oss-cn-shanghai.aliyuncs.com/img/房屋 (2).png",
          ],
        },
      ],
      stayIdList: [10059, 10200, 10062],
    };
  },
  props: {
    CardTitle: String,
    CardDescription: String,
    cardType: Number,
  },
  methods: {
  },
  filters: {
    ellipsis(value) {
      if (!value) return "";
      if (value.length > 16) {
        return value.slice(0, 16) + "...";
      }
      return value;
    },
  },
};
</script>

<style scoped>
.containerFlex {
  display: flex;
  flex-direction: row; /*容器内成员的排列方式为从左到右*/
  flex-wrap: wrap; /*换行方式，放不下就换行*/
  justify-content: flex-start; /*项目在主轴上的对齐方式*/
  align-content: flex-start;
}

.CardContainer {
  width: 400px;
  height: 310px;
  margin-bottom: 10px;
  margin-left: 25px;
}

#sekil img {
  margin-left: -300px;
  margin-top: -30px;
  width: 130px;
  box-shadow: 2px 2px 2px #9e9e9e;
  -webkit-box-shadow: 5px 5px 3px #9e9e9e;
  -moz-box-shadow: 3px 3px 1px #9e9e9e;
}
#obsu {
  margin-top: 10%;
  width: 300px;
  height: 230px;
  background-color: rgb(238, 238, 238);
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 20px 25px -5px;
  cursor: pointer;
}

#obsu:hover {
  margin-top: 10%;
  width: 300px;
  height: 230px;
  background-color: rgb(245, 245, 245);
  border-radius: 10px;
  transform: translateY(-4px);
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px !important;
  cursor: pointer;
}

#kitabadi {
  margin-top: -150px;
  font-size: 11px;
  margin-left: 55px;
  color: #464647;
}
#kimindi {
  padding-left: 55px;
  margin-top: -10px;
  color: #555659;
}
#reytinq {
  padding-left: 55px;
  margin-top: -5px;
}
#haqqinda {
  color: #555659;
  width: 200px;
  padding-left: 80px;
  font-size: 13px;
}
</style>