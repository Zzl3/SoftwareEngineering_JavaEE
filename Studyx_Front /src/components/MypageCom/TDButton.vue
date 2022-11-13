<template>
  <div id="button3D"><button class="button-3d-1">修改资料</button></div>
</template>

<script>
export default {};
</script>

<style scoped>

#button3D{
    position:absolute;
    margin-top:-900px;
    margin-left:1000px;
}
.button-3d-1{
    position: relative;
    background: rgb(135, 207, 138);
    border: none;
    color: white;
    padding: 15px 24px;
    font-size: 1.4rem;
    outline: none;
    box-shadow: -6px 6px 0 hsl(127, 58%, 81%);
  }
  .button-3d-1::before {
    content: "";
    display: block;
    width: 0;
    height: 0;
    position: absolute;
    top: 0;
    left: -6px;
    border: 6px solid transparent;
    border-right: 6px solid hsl(125, 50%, 69%);
    border-left-width: 0px;
  }
  
  .button-3d-1::after {
    content: "";
    display: block;
    width: 0;
    height: 0;
    position: absolute;
    bottom: -6px;
    right: 0;
    border: 6px solid transparent;
    border-top: 6px solid hsl(117, 29%, 62%);
    border-bottom-width: 0px;
  }
  .button-3d-1:active {
    background: hsl(117, 31%, 56%);
    top: 3px;
    left: -3px;
    box-shadow: -3px 3px 0 hsl(12, 45%, 19%);
  }
  .button-3d-1:active::before {
    border: 3px solid transparent;
    border-right: 3px solid hsl(16, 100%, 30%);
    border-left-width: 0px;
    left: -3px;
  }
  
  .button-3d-1:active::after {
    border: 3px solid transparent;
    border-top: 3px solid hsl(16, 100%, 30%);
    border-bottom-width: 0px;
    bottom: -3px;
  }
</style>