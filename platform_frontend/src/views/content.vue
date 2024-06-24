<script setup>

import OptionBar from "@/components/OptionBar.vue";
</script>

<template>
  <div class="content">
    <OptionBar/>
    <router-view/>
  </div>
</template>

<style lang="less" scoped>
.content{
  background-color: #dddddd;
  width: calc(100vw - 232px);
}
.dark .content{
  background-color: #3e3e3e;
}
</style>