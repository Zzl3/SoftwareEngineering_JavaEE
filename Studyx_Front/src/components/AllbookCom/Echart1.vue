<template>
  <div
    style="width: 500px; height: 300px; left: 0px; margin-top: 50px"
    id="main"
  ></div>
</template>

<script>
import global from "../../utils/global.js";

//通过this.$echarts来使用
export default {
  name: "page",
  mounted() {
    // 在通过mounted调用即可
    this.echartsInit();
  },
  data() {
    return {
      chart_data: [],
      myChart: null,
    };
  },
  //数据自动刷新，必然需要一个监听机制告诉Echarts重新设置数据
  // watch: {
  //   //观察option的变化
  //   option: {
  //     handler(newVal, oldVal) {
  //       if (this.myChart) {
  //         if (newVal) {
  //           this.myChart.setOption(newVal);
  //         } else {
  //           this.myChart.setOption(oldVal);
  //         }
  //       } else {
  //         this.echartsInit();
  //       }
  //     },
  //     deep: true, //对象内部属性的监听，关键。
  //   },
  // },

  methods: {
    //初始化echarts
    echartsInit() {
      var _this = this;
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      //这里的所有echarts都需要改成this.$echarts
      let myChart = this.$echarts.init(document.getElementById("main"));
      //let min=300,max=660;

      this.$axios({
        url: "/user/countborrow",
        method: "get",
        params: {
          id: global.nowuserid,
        },
      }).then((res) => {
        for (let val of res.data) {
          //console.log(res.data)
          _this.chart_data.push(val * 100 + 300);
          //console.log(_this.chart_data);
        }
      });

      var option = {
        title: {
          text: "借阅统计",
          //subtext: "可以统计一下过去一年中的借阅数量",
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
            //data: _this.chart_data,
            data: [300, 280, 250, 260, 270, 300, 550, 500, 400, 390, 380, 390],
            markArea: {
              itemStyle: {
                color: "rgba(255, 173, 177, 0.4)",
              },
              data: [
                [
                  {
                   // name: "Morning Peak",
                    xAxis: "2月",
                  },
                  {
                    xAxis: "4月",
                  },
                ],
                [
                  {
                   // name: "Evening Peak",
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
      console.log("not here?");
      //console.log(_this.chart_data);
      console.log(option);
      myChart.setOption(option);
    },
  },
};
</script>
