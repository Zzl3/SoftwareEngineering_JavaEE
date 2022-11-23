<template>
  <div>
    <Navigation></Navigation>
    <router-view></router-view>
  </div>
</template>

<script>
import Navigation from "@/components/NavigaCom/Navigation";
export default {
  components: { Navigation },
};
</script>

<style>
</style>