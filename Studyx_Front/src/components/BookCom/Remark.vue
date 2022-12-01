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
      <br>
      <br>
      <el-button class="bn-flat" type="primary" round  @click="addRemark">保存</el-button>

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

.bn-flat1{
  font: 12px Helvetica,Arial,sans-serif;
  line-height: 1.62;
  font-size: 13px;
  display: inline-block;
  border-width: 1px;
  border-style: solid;
  border-color: #111;
  color: #111;
  -webkit-border-radius: 3px;
  overflow: hidden;
  vertical-align: middle;
}
</style>
