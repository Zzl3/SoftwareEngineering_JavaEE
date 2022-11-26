<template>
  <el-card :body-style="{ padding: '0px' }">
    <img :src="books[0].cover" alt="封面">
    <div style="padding: 14px;">
      <h2 class="title">{{ books[0].title }}</h2>
      <p>{{books[0].author}}</p>
      <p>{{books[0].date}}</p>
      <p>{{books[0].press}}</p>
      <p>{{books[0].abs}}</p>

      <div class="bottom clearfix">
        <el-button @click="goBack" type="text" class="button">返回</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "BookDetails",
  data() {
    return {
      books: [{
        cover: 'https://i.loli.net/2019/04/10/5cada7e73d601.jpg',
        title: '三体',
        author: '刘慈欣',
        date: '2019-05-05',
        press: '重庆出版社',
        abs: '文化大革命如火如荼进行的同时。军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……'
      }],
      currentPage: 1,
      pagesize: 18
    };
  },
  methods: {
    goBack(){
      this.$router.replace('/book')
    },
  },
  book () {
    this.$axios
      .post('/book', {
        username: this.loginForm.username,
        password: this.loginForm.password
      })
      .then(successResponse => {
        if (successResponse.data.code === 200) {
          this.$router.replace({path: '/index'})
        }
      })
      .catch(failResponse => {
      })
  },
}
</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

</style>
