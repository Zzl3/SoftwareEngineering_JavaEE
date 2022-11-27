<template>
    <div>
        <canvas id="QRCode_header" style="width: 280px; height: 280px"></canvas>
    </div>
</template>

<script>
import QRCode from "qrcode"; //引入生成二维码插件
export default {
  props: {
    uuurl: String,
  },
  data() {
    return {
      qrUrl: "",
    };
  },
  watch: {},
  mounted() {
    this.getQRCode();
  },
  created() {},
  methods: {
    getQRCode() {
      let _this = this;
      //生成的二维码为URL地址js
      this.qrUrl = "https://152.136.245.230:7784/play/index.html?uid=1&zid=1&roomid=53";
      let opts = {
        errorCorrectionLevel: "H", //容错级别
        type: "image/png", //生成的二维码类型
        quality: 0.3, //二维码质量
        margin: 0, //二维码留白边距
        width: 280, //宽
        height: 280, //高
        text: "http://www.xxx.com", //二维码内容
        // text: _this.uuurl, //二维码内容
        color: {
          dark: "#333333", //前景色
          light: "#fff", //背景色
        },
      };

      let msg = document.getElementById("QRCode_header");
      // 将获取到的数据（val）画到msg（canvas）上
      QRCode.toCanvas(msg, this.qrUrl, opts, function (error) {
        if (error) {
          console.log("二维码加载失败", error);
          this.$message.error("二维码加载失败");
        }
      });
    },
  },
};
</script>
