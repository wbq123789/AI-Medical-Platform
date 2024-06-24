<script setup>

</script>

<template>
  <div style="width: 100vw; height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;">
      <el-image style="height: 100%;width: 100%;" fit="cover"
          src="https://images.hdqwalls.com/download/morning-in-mountains-lake-4k-jd-3840x2160.jpg">
      </el-image>
      <div class="welcome-title">
        <div style="font-weight:bolder;font-size:25px">欢迎来到这个论坛</div>
        <div style="margin-top: 2%">在这里我们可以一起分享新鲜事、学习相关领域知识、get生活小技巧</div>
        <div style="margin-top: 1%">来和我们共享知识和乐趣，共创文明论坛&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;遇到问题请联系管理员:QQ:2902471892</div>
      </div>
    </div>
    <div class="right-card">
      <router-view></router-view>
    </div>
  </div>
</template>

<style scoped>
.welcome-title{
  position: absolute;
  bottom: 2%;
  left:1.5%;
  color: white;
  text-shadow: 0 0 10px black;
}
.right-card {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 25%;
  z-index: 1;
  border-radius: 10px; /* 可选：圆角 */
  padding: 20px; /* 可选：内边距 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 可选：阴影 */
  background-color: var(--el-bg-color);
}
</style>