<template>
  <div>
    <Navigation></Navigation>
    <Echart1></Echart1>
    <Echart2></Echart2>
  </div>
</template>

<script>
import Navigation from "@/components/NavigaCom/Navigation";
import Echart1 from "@/components/AllbookCom/Echart1";
import Echart2 from "@/components/AllbookCom/Echart2";
export default {
  components: { Echart1, Echart2, Navigation },
};
</script>

<style></style>
