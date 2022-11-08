<template>
  <div id="body">
    <div class="shell">
      <input type="text" placeholder="Search" />
      <a href="#">
        <i class="fa fa-hand-o-right"></i>
        <i class="fa fa-search"></i>
      </a>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
@import url("https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css");
* {
  outline: none;
  padding: 0;
  margin: 0;
  text-decoration: none;
}

#body {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffdcdc;
  height: 0px;
}

.shell {
  margin-top: 60px;
  position: relative;
  padding: 0px;
  background-color:  #939e96;
  border-radius: 5px;
  box-shadow: 0 0 0 5px #fff;
}

.shell input {
  width: 80%;
  color: #fff;
  font: 100 20px "优设标题黑";
  border: 0;
  background-color: transparent;
}

.shell input::placeholder {
  color: #f8f2f2fb;
}

.shell a {
  display: flex;
  font-size: 20px;
  position: absolute;
  right: -90%;
  top: -15%;
  color: #fff;
  width: 250px;
  height: 150px;
  z-index:999;
}

.shell a .fa {
  margin: 10px 28px;
  transition: 0.3s;
}

.shell a .fa-search {
  transform: translateX(-80px);
  opacity: 1;
}

.shell a .fa-hand-o-right {
  transform: translateX(-20px);
  opacity: 0;
}

.shell a:hover .fa-search {
  transform: translateX(0);
  opacity: 0;
}

.shell a:hover .fa-hand-o-right {
  transform: translateX(10px);
  opacity: 1;
}

.shell a::before {
  content: "GO!";
  position: absolute;
  display: block;
  font-size: 10px;
  background-color: #ff7575;
  padding: 5px 30px;
  top: 50px;
  right: -25px;
  border-radius: 5px;
  transition: 0.3s;
  opacity: 0;
  animation: box 4s infinite ease;
}

.shell a:hover::before {
  top: -115px;
  opacity: 0;
}

@keyframes box {
  0% {
    transform: rotate(0deg);
  }
  33% {
    transform: rotate(8deg);
  }
  66% {
    transform: rotate(-8deg);
  }
  100% {
    transform: rotate(0deg);
  }
}
</style>