<template>
  <div class="home">
    <body class="news">
      <header>
        <div class="nav">
          <ul>
            <a
              href="/index"
              style="
                display: inline-block;
                float: left;
                top: 50px;
                margin-top: 10px;
                padding: 0px;
                margin-left: 20px;
              "
            >
              <i class="el-icon-s-home" style="font-size: 30px"></i
            ></a>
            <a
              style="
                display: inline-block;
                float: left;
                top: 50px;
                margin-top: 10px;
                padding: 0px;
                margin-left: 20px;
              "
            >
              <i class="el-icon-help" style="font-size: 30px" @click="drawer = true"></i
            ></a>
            <el-drawer
              title="这里是有关积分的介绍"
              :visible.sync="drawer"
              :direction="direction"
              :before-close="handleClose"
            >
              <!-- 这里就是积分的介绍RankDetail组件 -->
              <span>
                <RankDetail> </RankDetail>
              </span>
            </el-drawer>
            <li class="classroom"><a href="#">教室板块</a></li>
            <li class="project">
              <a href="#">测试用例</a>
              <ul>
                <li><a href="/project/allproject">测试用例</a></li>
                <li><a href="/project/myproject">我的组队</a></li>
                <li><a href="/project/addproject">新建组队</a></li>
              </ul>
            </li>
            <li class="material"><a href="#">资料板块</a></li>
            <li class="punch"><a href="#">打卡板块</a></li>
            <el-dropdown
              style="
                border-bottom: none;
                top: 13px;
                display: inline-block;
                right: 40px;
                position: fixed;
              "
            >
            <el-avatar :src=url></el-avatar>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item icon="el-icon-s-fold" @click.native="logout"
                  >退出登录</el-dropdown-item
                >
                <el-dropdown-item icon="el-icon-s-home" @click.native="mypage"
                  >我的页面</el-dropdown-item
                >
              </el-dropdown-menu>
            </el-dropdown>
          </ul>
        </div>
      </header>
    </body>
  </div>
</template>

<script>
import RankDetail from "@/components/NavigaCom/RankDetail.vue";
export default {
  name: "Navigation",
  components: { RankDetail },
  data() {
    return {
      url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
      drawer: false,
      direction: "rtl",
    };
  },
  mounted() {
    //alert(this.$myglobal.imageurl);
    this.getimg();
  },
  methods: {
    getimg() {
      var _this = this;
      this.$axios({
        url: "/user/getImg",
        method: "post",
        data: _this.$myglobal.nowuserid,
        headers: {
          "Content-Type": "text/plain",
        },
      }).then((res) => {
        _this.url = res.data;
        console.log(_this.url);
      });
    },
    handleClose(done) {
      done();
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      this.$axios.post("logout");
      this.$router.push({
        path: "/login",
      });
    },
    mypage() {
      this.$router.push({
        path: "/user",
      });
    },
  },
};
</script>
<style scoped>
.avatar {
  width: 30px;
  height: 30px;
  display: block;
}
.drawer {
  background: linear-gradient(200deg, #517fa4, #243949);
}
.home {
  width: 100%;
  position: flex;
}
body {
  margin: 0;
  padding: 0;
  background: #ccc;
}

.nav ul {
  list-style: none;
  background-color: rgb(59, 62, 59);
  text-align: center;
  padding: 0;
  margin: 0;
}

.nav li {
  font-size: 1.2em;
  line-height: 40px;
  text-align: left;
  font-family: "Ek Mukta";
  font-weight: bolder;
  /*font-family: Georgia, 'Times New Roman', Times, serif*/
}

.nav a {
  text-decoration: none;
  color: #fff;
  display: block;
  padding-left: 15px;
  border-bottom: 1px solid #888;
  transition: 0.3s background-color;
}

.nav a:hover {
  background-color: #939e96;
}

.nav a.active {
  background-color: #aaa;
  color: #444;
  cursor: default;
}

/* Sub Menus */
.nav li li {
  font-size: 0.8em;
}

@media screen and (min-width: 650px) {
  .nav li {
    width: 130px;
    border-bottom: none;
    height: 50px;
    line-height: 50px;
    font-size: 1.3em;
    display: inline-block;
    margin-right: -4px;
  }

  .nav a {
    border-bottom: none;
  }

  .nav > ul > li {
    text-align: center;
  }

  .nav > ul > li > a {
    padding-left: 0;
  }

  /* Sub Menus */
  .nav li ul {
    position: absolute;
    display: none;
    width: inherit;
  }

  .nav li:hover ul {
    display: block;
  }

  .nav li ul li {
    display: block;
  }
}
</style>
