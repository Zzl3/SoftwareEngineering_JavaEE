<template>
  <div style="width: 1000px; height: 600px;left:300px;margin-top:50px" id="main"></div>
</template>
  
  <script>
//通过this.$echarts来使用
export default {
  name: "page",
  mounted() {
    // 在通过mounted调用即可
    this.echartsInit();
  },
  data() {
    return {};
  },
  methods: {
    //初始化echarts
    echartsInit() {
      var _this = this;
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      //这里的所有echarts都需要改成this.$echarts
      let myChart = this.$echarts.init(document.getElementById("main"));
      var option = {
        title: {
          text: "借阅统计",
          subtext: "可以统计一下过去一年中的借阅数量",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
          },
        },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          // prettier-ignore
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value}活力",
          },
          axisPointer: {
            snap: true,
          },
        },
        visualMap: {
          show: false,
          dimension: 0,
          pieces: [
            {
              lte: 6,
              color: "green",
            },
            {
              gt: 6,
              lte: 8,
              color: "red",
            },
            {
              gt: 8,
              lte: 14,
              color: "green",
            },
            {
              gt: 14,
              lte: 17,
              color: "red",
            },
            {
              gt: 17,
              color: "green",
            },
          ],
        },
        series: [
          {
            name: "Electricity",
            type: "line",
            smooth: true,
            // prettier-ignore
            data: [300, 280, 250, 260, 270, 300, 550, 500, 400, 390, 380, 390, 400, 500, 600, 750, 800, 700, 600, 400],
            markArea: {
              itemStyle: {
                color: "rgba(255, 173, 177, 0.4)",
              },
              data: [
                [
                  {
                    name: "Morning Peak",
                    xAxis: "2月",
                  },
                  {
                    xAxis: "4月",
                  },
                ],
                [
                  {
                    name: "Evening Peak",
                    xAxis: "7月",
                  },
                  {
                    xAxis: "8月",
                  },
                ],
              ],
            },
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
};
</script>
  