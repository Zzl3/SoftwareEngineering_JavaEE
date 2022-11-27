<template>
  <div>
    <!-- 页面结构的编写 -->
    <div class="mainbox">
      <input type="checkbox" id="check" />
      <label for="check">Share</label>
      <div class="media-icons">
        <!-- 为了页面结构的简化，可以去掉li -->
        <a href="#"><i class="fab fa-qq" @click="shareToQQRom()"></i></a>
        <a href="#"><i class="fab fa-weixin" @click="shareToWeChat()"></i></a>
        <a href="#"><i class="fab fa-weibo" @click="shareToMicroblog()"></i></a>
      </div>
    </div>
    <el-dialog title="微信分享" :visible.sync="dialogFormVisible">
      <QRCode :uuurl="shareUrl"></QRCode>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import QRCode from "./QRcode";
export default {
  //props: ["sysInfo"], //父组件传过来的文章数据
  components: { QRCode },
  data() {
    return {
      dialogFormVisible: false,
      shareUrl: location.href,
      sysInfo: "我的主页",
    };
  },
  methods: {
    /**
     * 分享到微博
     */
    shareToMicroblog() {
      let _this = this;
      //跳转地址,目前不能用，部署到服务器上就能用了吧
      window.open(
        "https://service.weibo.com/share/share.php?url=" +
          encodeURIComponent(this.shareUrl) +
          "&title=" +
          _this.sysInfo
      );
    },
    /**
     * 分享到qq空间
     */
    shareToQQRom() {
      let _this = this;
      //跳转地址
      window.open(
        "https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" +
          encodeURIComponent(_this.shareUrl) +
          "&title=" +
          _this.sysInfo +
          "&summary=" +
          _this.sysInfo
      );
    },
    /**
     * 分享到微信：需要单独画页面
     */
    shareToWeChat() {
      this.dialogFormVisible = true;
      //localStorage.setItem("shareUrl", this.shareUrl); // 存储当前页面地址，前端页面直接根据URL链接生成二维码
      //let url = location.href.split("#")[0] + "#/Share/toWechat"; // 注意：#/Share/toWechat: 自己定义的路由
      //window.open(url);
    },
  },
};
</script>

<style scoped>
@import "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css";
/* 页面背景颜色 */
body {
  background: radial-gradient(#333, #000);
  height: 100vh;
}

/* mainbox盒子的样式 */
.mainbox {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

/* label文字框的样式书写 */
label {
  position: relative;
  background: #fff;
  width: 150px;
  height: 50px;
  border-radius: 35px;
  line-height: 50px;
  text-align: center;
  font-size: 22px;
  font-weight: 500;
  display: block;
  color: #333;
  cursor: pointer;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
  transition: all 0.3s ease;
}

/* 给文字加点hover效果 */
label:hover {
  letter-spacing: 2px;
}

/* 制作取消文字框，用before伪元素 */
label::before {
  content: "Cancel";
  position: absolute;
  height: 100%;
  width: 100%;
  left: 0;
  top: 0;
  background: #fff;
  border-radius: 35px;
  /* 透明度为0，先不展示 */
  opacity: 0;
}

/* 处理input选框 */
#check {
  display: none;
}

/* 通过input选框状态，切换取消文字的透明度为1 */
#check:checked ~ label::before {
  opacity: 1;
}

/* 修改图标样式 */

.media-icons {
  position: absolute;
  left: 50%;
  top: -120px;
  transform: translateX(-50%);
  background: #fff;
  width: 140%;
  height: 110%;
  /* 首先加上text-align试试 */
  text-align: center;
  /* 没有用继续向下找问题 */
  display: flex;
  /* 发现问题，align-items写错了  ok 大功告成*/
  align-items: center;
  justify-content: space-evenly;
  border-radius: 35px;
  padding: 4px;
  z-index: 1;
  opacity: 0;
  pointer-events: none;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
  transition: all 0.3s ease;
}

/* 通过input选框状态，切换图标框 */
#check:checked ~ .media-icons {
  opacity: 1;
  pointer-events: auto;
  top: -84px;
}

/* 制作小三角（用一个正方形旋转45度得来） */
.media-icons::before {
  content: "";
  width: 18px;
  height: 18px;
  position: absolute;
  left: 50%;
  background-color: #fff;
  bottom: -9px;
  transform: translateX(-50%) rotate(45deg);
  z-index: -1;
}

/* 修改a链接样式 */
.media-icons a {
  font-size: 22px;
  color: #333;
  transition: all 0.3s ease;
}

.media-icons a:hover {
  transform: translateY(-2px);
}

/* 为每一个icon修改颜色 */
.media-icons a:nth-child(1):hover {
  color: #000000;
}
.media-icons a:nth-child(2):hover {
  color: #7bb32e;
}
.media-icons a:nth-child(3):hover {
  color: #df2029;
}
.media-icons a:nth-child(4):hover {
  color: #000000;
}
</style>
