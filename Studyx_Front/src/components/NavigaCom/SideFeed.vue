<template>
  <div>
    <ul class="wrapper">
      <li class="icon facebook" @click="feed()">
        <span class="tooltip">FEEDBACK</span>
        <span><i class="el-icon-star-off" ></i></span>
      </li>
      <li class="icon twitter" @click="donationm()">
        <span class="tooltip">DONATE MONEY</span>
        <span><i class="el-icon-goods" ></i></span>
      </li>
      <li class="icon instagram" @click="donationb()">
        <span class="tooltip">DONATE BOOK</span>
        <span><i class="el-icon-video-pause" ></i></span>
      </li>
    </ul>
    <div id="aaaaa">
      <el-dialog :visible.sync="feedback" title="反馈">
        <FeedbackAddVue></FeedbackAddVue>
      </el-dialog>
      <el-dialog :visible.sync="donationbook" title="捐书">
        <DonationbookAddVue></DonationbookAddVue>
      </el-dialog>
      <el-dialog :visible.sync="donationmoney" title="捐款">
        <DonationmoneyAddVue></DonationmoneyAddVue>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import DonationbookAddVue from "../../pages/DonationFeedback/DonationbookAdd.vue";
import FeedbackAddVue from "../../pages/DonationFeedback/FeedbackAdd.vue";
import DonationmoneyAddVue from "../../pages/DonationFeedback/DonationmoneyAdd.vue";

export default {
  components: { DonationbookAddVue, FeedbackAddVue, DonationmoneyAddVue },
  data() {
    return {
      //注意这些是控制页面开关的
      donationbook: false,
      feedback: false,
      donationmoney: false,
    };
  },
  methods: {
    feed() {
      this.feedback = true;
    },
    donationm() {
      this.donationmoney = true;
    },
    donationb() {
      this.donationbook = true;
    },
  },
};
</script>

<style scoped>
#aaaaa {
  z-index: 99999999;
}
.wrapper {
  display: inline-flex;
  list-style: none;
  height: 120px;
  width: 100%;
  padding-top: 40px;
  font-family: "Poppins", sans-serif;
  justify-content: center;
}

.wrapper .icon {
  position: relative;
  background: #fff;
  border-radius: 50%;
  margin: 10px;
  width: 50px;
  height: 50px;
  font-size: 18px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: all 0.2s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .tooltip {
  position: absolute;
  top: 0;
  font-size: 14px;
  background: #fff;
  color: #fff;
  padding: 5px 8px;
  border-radius: 5px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .tooltip::before {
  position: absolute;
  content: "";
  height: 8px;
  width: 8px;
  background: #fff;
  bottom: -3px;
  left: 50%;
  transform: translate(-50%) rotate(45deg);
  transition: all 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .icon:hover .tooltip {
  top: -45px;
  opacity: 1;
  visibility: visible;
  pointer-events: auto;
}

.wrapper .icon:hover span,
.wrapper .icon:hover .tooltip {
  text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.1);
}

.wrapper .facebook:hover,
.wrapper .facebook:hover .tooltip,
.wrapper .facebook:hover .tooltip::before {
  background: #1877f2;
  color: #fff;
}

.wrapper .twitter:hover,
.wrapper .twitter:hover .tooltip,
.wrapper .twitter:hover .tooltip::before {
  background: #1da1f2;
  color: #fff;
}

.wrapper .instagram:hover,
.wrapper .instagram:hover .tooltip,
.wrapper .instagram:hover .tooltip::before {
  background: #e4405f;
  color: #fff;
}
</style>
