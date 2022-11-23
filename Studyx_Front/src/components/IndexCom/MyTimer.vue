<template>
  <section class="timer" id="time">
    <div class="timer-wrap">
      <div class="time-header">
        <h2>当前时间</h2>
        <hr class="line" />
        <span class="intro">Current&nbsp;Time</span>
      </div>
      <div class="time-area">
        <div>{{ hour }}</div>
        <div>{{ minute }}</div>
        <div>{{ second }}</div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style scoped>
/* time */
.time-area {
  padding: 10px 200px;
  width: 610px;
  display: flex;
  justify-content: space-between;
  margin: 0px auto;
}
.time-area div {
  height: 100px;
  width: 200px;
  background-color:  #444;
  color: white;
  font-size: 33px;
  line-height: 100px;
}
</style>
