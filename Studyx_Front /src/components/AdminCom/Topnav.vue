<template>
  <div class="home">
    <body class="news">
      <header>
        <div class="nav">
          <Search></Search>
          <ul>
            <li class="classroom">
              <el-dropdown
                style="
                  border-bottom: none;
                  top: 13px;
                  display: inline-block;
                  right: 40px;
                  position: fixed;
                "
              >
                <el-avatar> admin </el-avatar>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-user-solid" @click.native="logout"
                    >用户管理</el-dropdown-item
                  >
                  <el-dropdown-item icon="el-icon-s-fold" @click.native="logout"
                    >退出登录</el-dropdown-item
                  >
                </el-dropdown-menu>
              </el-dropdown>
            </li>
          </ul>
        </div>
      </header>
    </body>
  </div>
</template>
  
  <script>
import Search from "./Search.vue";
export default {
  name: "Navigation",
  components: { Search },
  data() {
    return {};
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      // user = {
      //   username: "",
      //   password: "",
      // };
      // this.$store.commit("login", user);
      this.$router.push({
        path: "/login",
      });
    },
    mypage() {
      this.$router.push({
        path: "/mypage",
      });
    },
  },
};
</script>
  <style scoped>
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
  background-color: #444;
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
  background-color: #638d8d;
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
  