<template>
  <el-row type="flex" class="row-bg" justify="center">
    <el-col :span="6"><div class="grid-content bg-purple">
      <h2>图书详情：</h2>
      <book-details></book-details>

    </div></el-col>
    <el-col :span="1"><el-divider direction="vertical"></el-divider></el-col>
    <el-col :span="6"><div class="grid-content bg-purple">
      <p>  </p>
      <p>  </p>
      <p>馆藏副本：</p>
      <p>可借副本：</p>
      <Score></Score>
      <p>  </p>
      <p>  </p>
      <el-button type="success" plain>借阅</el-button>
      <el-button type="warning" plain>收藏</el-button>
      <p>  </p>
      <p>  </p>
      <p>发布评论：</p>

      <Remark></Remark>
      <p>  </p>
      <el-button type="primary" round>发送</el-button>
      <p>  </p>
      <p>  </p>
      <p>  </p>
      <p>  </p>
      <p>  </p>
      <!--评论区-->
      <div class="block">

        <el-pagination
          layout="prev, pager, next"
          :total="50">
        </el-pagination>
      </div>
    </div></el-col>

  </el-row>

</template>

<script>
import "animate.css";
import Star from '@/components/BookCom/Star'
import Score from '@/components/BookCom/Score'
import ReturnButton from '@/components/BookCom/ReturnButton'
import Remark from '@/components/BookCom/Remark'
import Books from '@/components/BookCom/Books'
import BookDetails from '@/components/BookCom/BookDetails'

// eslint-disable-next-line no-unused-vars
export default {
  name: "BookParticulars",
  components: {Star,Score,ReturnButton,Remark,Books,BookDetails,},

};
</script>

<style>
.el-row {
  margin-bottom: 20px;
   margin-bottom: 0;

}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-divider{
  height:400px;
}
.details-area {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
}
</style>

