<template>
  <section class="honor-section" id="honor">
    <div class="honor-wrap">
      <div class="honor-header">
        <h2>用户如何操作</h2>
        <hr class="line" />
        <span class="intro">贴心指引</span>
      </div>
      <ul class="timeline">
        <li>
          <div class="timeline-image">
            <i class="fa fa-heart-o"></i>
          </div>
          <div class="timeline-panel">
            <h2>如何使用教室</h2>
            <p>
              如何使用教室说明...
            </p>
          </div>
        </li>
        <li>
          <div class="timeline-image">
            <i class="fa fa-heart-o"></i>
          </div>
          <div class="timeline-panel-right">
            <h2>如何进行组队</h2>
            <p>
              如何组队说明...
            </p>
          </div>
        </li>
        <li>
          <div class="timeline-image">
            <i class="fa fa-heart-o"></i>
          </div>
          <div class="timeline-panel">
            <h2>如何搜索图书</h2>
            <p>
              如何搜索图书说明...
            </p>
          </div>
        </li>
        <li>
          <div class="timeline-image">
            <i class="fa fa-heart-o"></i>
          </div>
          <div class="timeline-panel-right">
            <h2>如何打卡</h2>
            <p>
              如何打卡说明...
            </p>
          </div>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
h2 {
  font-weight: 600;
  color: #333;
  letter-spacing: 1px;
  font-family: "Ek Mukta";
  font-size: 36px;
  margin: 0;
}
a {
  text-decoration: none;
}
ul {
  /* 去除默认样式 */
  padding: 0;
  /*去除项目符号 (小点)*/
  list-style: none;
}
.honor-section {
  background-color: white;
  padding-top: 80px;
}
.honor-wrap {
  width: 1170px;
  padding: 0px 15px;
  margin: 0px auto;
  position: relative;
}
.timeline::before {
  content: "";
  position: absolute;
  top: 150px;
  bottom: 0;
  /* 注意 bottom是必须的 */
  left: 50%;
  width: 2px;
  margin-left: -1.5px;
  background-color: #555;
}
.timeline li {
  position: relative;
  /* display: block; */
  padding-bottom: 70px;
  min-height: 200px;
}
.timeline-image {
  width: 60px;
  height: 60px;
  position: absolute;
  left: 50%;
  margin-left: -30px;
  background-color: rgb(128,160,175);
  text-align: center;
  border-radius: 50%;
  color: white;
  padding-top: 15px;
  box-sizing: border-box;
}
.timeline-image i {
  font-size: 33px;
}
.timeline-panel {
  position: absolute;
  left: 0px;
  padding: 0px 10px 20px 100px;
  width: 35%;
}
.timeline-panel-right {
  position: absolute;
  width: 35%;
  right: 0px;
  padding: 0px 100px 20px 10px;
}
.timeline-panel h2 {
  text-align: right;
  font-size: 28px;
  letter-spacing: 1px;
  font-family: "Ek Mukta";
  color: rgb(211,137,66);
}
.timeline-panel-right h2 {
  text-align: left;
  font-size: 28px;
  letter-spacing: 1px;
  font-family: "Ek Mukta";
  color: rgb(211,137,66);
}
.timeline-panel p {
  color: #c17886;
  /* font-weight: bold; */
  line-height: 1.5;
  text-align: right;
}
.timeline-panel-right p {
  color: #c17886;
  line-height: 1.5;
  /* font-weight: bold; */
  text-align: left;
}
.honor-header h2 span {
  color: rgb(140, 140, 140);
  margin: 0px 8px;
}
.line {
  border: none;
  border-top: 5px solid #bbb;
  max-width: 90px;
  margin: 15px auto 15px;
}
.intro {
  color: rgb(111, 111, 111);
  font-size: 18px;
  /* font-family: Georgia, 'Times New Roman', Times, serif; */
}
</style>
