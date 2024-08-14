<script setup>
const  props=defineProps({
  showing:Boolean,
  result:String
})
</script>

<template>
    <div class="analyse">
        <div class="left">
          <div v-if="showing">
            <img height="300" src="@/assets/picture.png" alt="骨折图片">
          </div>
          <el-empty v-else description="请输入病人ID进行检测"/>
        </div>
        <div class="right">
          <div>使用CNN模型预测结果为：</div>
          <div v-if="showing">{{ props.result }}</div>
        </div>
    </div>
</template>

<style scoped>
.analyse{
  display: flex;
  justify-content: center;
  gap: 3%;
  height: 100%;
}
.left{
  width: 45%;
  height: 100%;
  background-color: #bfbfbf;
  border-radius: 10px;
  align-content: center;
}
.right{
  width: 45%;
  height: 100%;
  background-color: #bfbfbf;
  font-size: 17px;
  border-radius: 10px;
  align-content: center;
}
</style>