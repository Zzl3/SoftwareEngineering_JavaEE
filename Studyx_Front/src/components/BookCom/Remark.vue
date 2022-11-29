<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-input
          type="textarea"
          :rows="2"
          clearable placeholder="请输入内容"
          v-model="textarea"
          maxlength="150"
          show-word-limit
          @clear="handleEmpty">
      </el-input>

      <el-form-item label="评分：">
        <el-rate
            v-model="value2"
        @change="changeValue"
            :colors="colors">
        >
        </el-rate>

        <el-button type="primary" round  @click="addRemark">发送</el-button>
      </el-form-item>
    </el-form>

  </div>


</template>

<script>
import Star from '@/components/BookCom/Star'
export default {
  name: "Remark",
  components: {Star},
  data() {
    return {
      textarea: '',
      userid: '1',
      isbn:'2',
      publishtime:'3',
      remarkid:'4',
      value2: null,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900']  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    }
  },
  methods: {
    addRemark(){
      this.$emit('onRemark')
    },
    changeValue(value2){
      console.log(value2);
      //this.$message.error("分数"+value2);
    },
    loadByScore(){
      this.value2 = null;
      //通过调用接口
      GetEvaluationScoringDetailResultForPanel()
          .then(data=>{
            if(data && data.Id){
            //将调用接口获取的数据赋值给双向绑定的data里的rateValue，就能实现rate的动态绑定了
            this.rateValue = data.EvaluatingScore
        }
      }).catch(err=>{
        return;
      });
    },

  }

}
</script>

<style scoped>

</style>
