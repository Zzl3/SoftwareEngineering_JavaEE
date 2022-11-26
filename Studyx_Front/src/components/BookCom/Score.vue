<template>
  <div class="block">
    <span class="demonstration">图书评分</span>
    <p>  </p>
    <p>  </p>
    <el-rate
      v-model="value2"
      :colors="colors">
    </el-rate>
  </div>
</template>

<script>
export default {
  name: "Score",
  data() {
    return {
      value1: null,
      value2: null,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900']  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    }
  }
}
</script>

<style scoped>

</style>
