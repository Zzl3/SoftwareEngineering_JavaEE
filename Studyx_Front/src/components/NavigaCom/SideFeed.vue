<<template>
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
      <el-dialog :visible.sync="feedback" title="反馈" @handleChange="no_feed">
        <div>
          <el-form label-width="80px">
            <el-form-item label="反馈内容">
              <el-input
                  type="textarea"
                  v-model="feed_content"
                  placeholder="请输入您要反馈的内容"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="feed_yes" style="float: left">确认</el-button>
              <el-button @click="feed_no" style="float: right">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>
      <el-dialog :visible.sync="donationbook" title="捐书" @handleChange="no_donationb">
        <div>
          <el-form label-width="80px">
            <el-form-item label="ISBN">
              <el-input
                  type="textarea"
                  v-model="isbn"
                  placeholder="请输入要捐赠书籍的ISBN编码"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="isbn_yes" style="float: left">确认</el-button>
              <el-button @click="isbn_no" style="float: right">取消</el-button>
            </el-form-item>
          </el-form>
<!--          <div class="group">
            <el-input placeholder="请输入要捐赠书籍的ISBN编码" v-model="isbn" />
          </div>
          <div style="width: 90%; height: 60px; margin: auto">
            <button style="display: inline; float: left" @click="isbn_yes" class="buttonx">确认</button>
            &lt;!&ndash;        <div style="width:50px;"></div>&ndash;&gt;
            <button style="display: inline; float: right" @click="isbn_no" class="buttonx">取消</button>
          </div>-->
        </div>
      </el-dialog>
      <el-dialog :visible.sync="donationmoney" title="捐款">
        <div>
          <el-form label-width="80px">
            <el-form-item label="金额">
              <el-input
                  type="textarea"
                  v-model="money"
                  placeholder="请输入要捐赠的金额(只需输入数字即可，单位：元)"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="money_yes" style="float: left">确认</el-button>
              <el-button @click="money_no" style="float: right">取消</el-button>
            </el-form-item>
          </el-form>
<!--          <div class="group">
            <input placeholder="请输入要捐赠的金额(只需输入数字即可，单位：元)" class="input" v-model="money" />
          </div>
          <div style="width: 90%; height: 60px; margin: auto">
            <button style="display: inline; float: left" @click="money_yes" class="buttonx">确认</button>
            <button style="display: inline; float: right" @click="money_no" class="buttonx">取消</button>
          </div>-->
        </div>
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
      feed_content: "",
      isbn:"",
      money:"",
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
    feed_no() {
      this.feedback=false;
      this.feed_content = "";
    },
    feed_yes() {
      //alert(this.$myglobal.nowuserid)
      var _this = this;
      if (this.feed_content != "") {
        //this.$message(this.content)
        this.$axios
            .post("/addFeedback", {
              content: _this.feed_content,
              type: "反馈",
            })
            .then((res) => {
              //alert("res")
              // console.log(res.data)
              if (res.data == "yes") {
                this.$message.success("反馈成功，我们会尽快答复");
                //alert(res.data)
              } else {
                console.log(res.data);
                this.$message.error("反馈失败，请重新反馈");
              }
            });
        //alert(res.data)
        this.feed_content = "";
        this.feedback=false
      } else {
        this.$message.error("反馈内容不能为空！");
      }
    },
    isbn_no() {
      this.donationbook=false
      this.isbn = "";
    },
    isbn_yes() {
      //alert(this.$myglobal.nowuserid)
      var _this = this;
      if (this.isbn != "") {
        //this.$message(this.content)
        this.$axios
            .post("/addDonationbook", {
              content: _this.isbn,
              type: "捐书",
            })
            .then((res) => {
              //alert("res")
              // console.log(res.data)
              if (res.data == "yes") {
                this.$message.success("捐书成功，感谢您的捐赠！");
                //alert(res.data)
                _this.isbn = "";
                _this.donationbook=false
              } else {
                console.log(res.data);
                this.$message.error("不存在有该ISBN的书籍，请输入正确的ISBN编码");
              }
            });
        //alert(res.data)
      } else {
        this.$message.error("书籍ISBN不能为空！");
      }
    },
    money_no() {
      this.donationmoney=false
      this.money = "";
    },
    money_yes() {
      //alert(this.$myglobal.nowuserid)
      var _this = this;
      if (this.money != "") {
        //this.$message(this.content)
        this.$axios({
          url: "/donationmoney",
          method: "post",
          data: _this.money,
          headers: {
            "Content-Type": "text/plain",
          },
        })
            .then((res) => {
              var result=res.data.split(":")
              //alert("res")
              // console.log(res.data)
              if (result[0] == "yes") {
                this.$message.success(result[1])
                //this.pay();
                window.open("http://localhost:8443/api/pay?subject=donation"+"&traceNo="+11+"&totalAmount="+_this.money);
                /*window.open('')*/
                //this.$message.success("捐书成功，感谢您的捐赠！");
                //alert(res.data)
                _this.money = "";
                _this.donationmoney=false
              } else {
                //console.log(res.data);
                this.$message.error(result[1]);
              }
            });
        //alert(res.data)
      } else {
        this.$message.error("捐赠金额不能为空！");
      }
    },
    pay(){
      var _this=this
      /*this.request.get('/api/pay',{
        params:{
          subject:donation,
          traceNo:8,
          totalAmount:_this.money
        }
      }).then(res =>{
        window.open(res.data);
      })*/
      this.$axios.post("/pay",{
        subject:"donation",
        traceNo:"9",
        totalAmount:_this.money
      })
          .then((res) => {
            window.open("http://localhost:8443/api/pay?subject=donation"+"&traceNo="+9+"&totalAmount="+_this.money);
          });
    }
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
.group {
  display: flex;
  line-height: 28px;
  align-items: center;
  position: relative;
  max-width: 90%;
  margin: auto;
}

.icon {
  position: absolute;
  left: 1rem;
  fill: #777;
  width: 1rem;
  height: 1rem;
}
</style>

