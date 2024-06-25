<script setup>

import OptionBar from "@/components/OptionBar.vue";
</script>

<template>
  <div class="content">
    <div class="main-body">
      <OptionBar/>
      <div style="margin: 0;">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content{
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #888787;
  width: calc(100vw - 232px);
}
.main-body{
  margin: 1%;
  width: 90%;
  border-radius: 10px;
  background-color: #e8e8e8;
}
.dark .main-body{
  background-color: #1c1c1c;
}
.dark .content{
  background-color: #404040;
}
</style>