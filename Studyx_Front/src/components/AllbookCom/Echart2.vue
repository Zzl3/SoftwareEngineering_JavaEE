<template>
  <div
    style="width: 1000px; height: 600px; margin-left: 300px; margin-top: 100px"
    id="main2"
  ></div>
</template>

<script>
export default {
  name: "page",
  mounted() {
    // 在通过mounted调用即可
    this.echartsInit();
  },
  methods: {
    //初始化echarts
    echartsInit() {
      var _this = this;
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      //这里的所有echarts都需要改成this.$echarts
      let myChart = this.$echarts.init(document.getElementById("main2"));
      var num=[]; //按照后端返回的 诗歌、心理学"、"童话"、"社会学"、"小说"、"传记 的顺序
      this.$axios({
        url:"/user/getcategorynum",
        methods:"get"
      }).then((res)=>{
        // console.log(res.data);
        for(let p of res.data){
          num.push(p);
        }
        //console.log(num);
      })

      var option = {
        color: ["#67F9D8", "#FFE434", "#56A3F1", "#FF917C"],
        title: {
          text: "类别统计，可以统计一下我们的图书类别",
        },
        legend: {},
        radar: [
          {
          //   // indicator: [
          //   //   { text: "Indicator1" },
          //   //   { text: "Indicator2" },
          //   //   { text: "Indicator3" },
          //   //   { text: "Indicator4" },
          //   //   { text: "Indicator5" },
          //   // ],
          //   // center: ["25%", "50%"],
          //   // radius: 120,
          //   // startAngle: 90,
          //   // splitNumber: 4,
          //   // shape: "circle",
          //   // axisName: {
          //   //   formatter: "【{value}】",
          //   //   color: "#428BD4",
          //   // },
          //   // splitArea: {
          //   //   areaStyle: {
          //   //     color: ["#77EADF", "#26C3BE", "#64AFE9", "#428BD4"],
          //   //     shadowColor: "rgba(0, 0, 0, 0.2)",
          //   //     shadowBlur: 10,
          //   //   },
          //   // },
          //   // axisLine: {
          //   //   lineStyle: {
          //   //     color: "rgba(211, 253, 250, 0.8)",
          //   //   },
          //   // },
          //   // splitLine: {
          //   //   lineStyle: {
          //   //     color: "rgba(211, 253, 250, 0.8)",
          //   //   },
          //   // },
          },
          {
            indicator: [
              { text: "诗歌", max: 600 },
              { text: "心理学", max: 600 },
              { text: "童话", max: 600 },
              { text: "社会学", max: 600 },
              { text: "小说", max: 600 },
              { text: "传记", max: 600 },
            ],
            center: ["75%", "50%"],
            radius: 120,
            axisName: {
              color: "#fff",
              backgroundColor: "#666",
              borderRadius: 3,
              padding: [3, 5],
            },
          },
        ],
        series: [
          // {
          //   type: "radar",
          //   emphasis: {
          //     lineStyle: {
          //       width: 4,
          //     },
          //   },
          //   data: [
          //     {
          //       value: [100, 8, 0.4, -80, 2000],
          //       name: "Data A",
          //     },
          //     {
          //       value: [60, 5, 0.3, -100, 1500],
          //       name: "Data B",
          //       areaStyle: {
          //         color: "rgba(255, 228, 52, 0.6)",
          //       },
          //     },
          //   ],
          // },
          {
            type: "radar",
            radarIndex: 1,
            data: [
              {
                value: num,
                name: "图书类型",
                symbol: "rect",
                symbolSize: 12,
                lineStyle: {
                  type: "dashed",
                },
                label: {
                  show: true,
                  formatter: function (params) {
                    return params.value;
                  },
                },
              },
              // {
              //   value: [100, 93, 50, 90, 70, 60],
              //   name: "Data D",
              //   areaStyle: {
              //     color: new _this.$echarts.graphic.RadialGradient(0.1, 0.6, 1, [
              //       {
              //         color: "rgba(255, 145, 124, 0.1)",
              //         offset: 0,
              //       },
              //       {
              //         color: "rgba(255, 145, 124, 0.9)",
              //         offset: 1,
              //       },
              //     ]),
              //   },
              // },
            ],
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      console.log(num);
      myChart.setOption(option);
    },
  },
};
</script>

<style></style>
