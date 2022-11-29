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
      var option = {
        color: ["#67F9D8", "#FFE434", "#56A3F1", "#FF917C"],
        title: {
          text: "类别统计，可以统计一下我们的图书类别",
        },
        legend: {},
        radar: [
          {
            indicator: [
              { text: "Indicator1" },
              { text: "Indicator2" },
              { text: "Indicator3" },
              { text: "Indicator4" },
              { text: "Indicator5" },
            ],
            center: ["25%", "50%"],
            radius: 120,
            startAngle: 90,
            splitNumber: 4,
            shape: "circle",
            axisName: {
              formatter: "【{value}】",
              color: "#428BD4",
            },
            splitArea: {
              areaStyle: {
                color: ["#77EADF", "#26C3BE", "#64AFE9", "#428BD4"],
                shadowColor: "rgba(0, 0, 0, 0.2)",
                shadowBlur: 10,
              },
            },
            axisLine: {
              lineStyle: {
                color: "rgba(211, 253, 250, 0.8)",
              },
            },
            splitLine: {
              lineStyle: {
                color: "rgba(211, 253, 250, 0.8)",
              },
            },
          },
          {
            indicator: [
              { text: "Indicator1", max: 150 },
              { text: "Indicator2", max: 150 },
              { text: "Indicator3", max: 150 },
              { text: "Indicator4", max: 120 },
              { text: "Indicator5", max: 108 },
              { text: "Indicator6", max: 72 },
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
          {
            type: "radar",
            emphasis: {
              lineStyle: {
                width: 4,
              },
            },
            data: [
              {
                value: [100, 8, 0.4, -80, 2000],
                name: "Data A",
              },
              {
                value: [60, 5, 0.3, -100, 1500],
                name: "Data B",
                areaStyle: {
                  color: "rgba(255, 228, 52, 0.6)",
                },
              },
            ],
          },
          {
            type: "radar",
            radarIndex: 1,
            data: [
              {
                value: [120, 118, 130, 100, 99, 70],
                name: "Data C",
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
              {
                value: [100, 93, 50, 90, 70, 60],
                name: "Data D",
                areaStyle: {
                  color: new _this.$echarts.graphic.RadialGradient(0.1, 0.6, 1, [
                    {
                      color: "rgba(255, 145, 124, 0.1)",
                      offset: 0,
                    },
                    {
                      color: "rgba(255, 145, 124, 0.9)",
                      offset: 1,
                    },
                  ]),
                },
              },
            ],
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
};
</script>

<style></style>
