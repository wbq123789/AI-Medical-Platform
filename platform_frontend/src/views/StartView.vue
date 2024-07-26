<script setup>
import { TopRight } from "@element-plus/icons-vue";
import router from "@/router/index.js";
</script>

<template>
  <div style="width: 100vw; height: 100vh; overflow: hidden; display: flex; text-align: center; position: relative;">
    <el-image style="height: 100%; width: 100%;" fit="cover" src="src/assets/picture.jpg">
    </el-image>
    <div class="introduce">
      <div class="introduce-title">链医联邦-隐私保护的多机构鲁棒医疗数据联邦系统</div>
      <div style="margin: 20px auto 0 auto; width: 200px;">
        <el-button size="large" round type="primary" :icon="TopRight"
                   style="width: 100%;" @click="router.push('/')">开始分析吧</el-button>
      </div>
      <div class="introduce-image">
        <el-image src="Designer.png" fit="cover" style="border-radius: 10px;"></el-image>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
.introduce {
  position: absolute;
  top: 55%;
  left: 50%;
  z-index: 1;
  transform: translate(-50%, -50%);
  text-align: center;
  border-radius: 10px;
  padding: 20px;

  .introduce-title {
    margin: 20px 0;
    text-align: center;
    font-size: 25px;
    font-weight: bold;
    color: #535353;
  }

  .introduce-image {
    background-color: var(--el-image-color);
    height: 550px;
    width: 800px;
    margin: 20px auto;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
  }
}
</style>
