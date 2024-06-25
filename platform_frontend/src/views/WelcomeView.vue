<script setup>

</script>

<template>
  <div style="width: 100vw; height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;">
      <el-image style="height: 100%;width: 100%;" fit="cover"
          src="src/assets/picture.jpg">
      </el-image>
    </div>
    <div class="right-card">
      <router-view></router-view>
    </div>
  </div>
</template>

<style scoped>
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