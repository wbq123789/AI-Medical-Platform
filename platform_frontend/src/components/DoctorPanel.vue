<script setup>
import router from "@/router/index.js";
import * as echarts from 'echarts/core';
import { GridComponent } from 'echarts/components';
import { LineChart } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';
import {ref,onMounted} from "vue"

echarts.use([GridComponent, LineChart, CanvasRenderer, UniversalTransition]);

const chartRef = ref(null);
onMounted(()=>{
  const myChart = echarts.init(chartRef.value);
  let option;

  option = {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [150, 230, 224, 218, 135, 147, 260],
        type: 'line'
      }
    ]
  };

  option && myChart.setOption(option);
})
</script>

<template>
  <div style="height: calc(100vh - 55px - 23vh)" class="display">
    <div class="area">
      <div class="writer">
        文字介绍这个模型的生成时间，参与机构，模型的历史准确度曲线，模型的评价
      </div>
      <div class="block" ref="chartRef">
      </div>
      <div style="text-align: center;margin-bottom: 3%;width: 100%">
        <el-button size="large" round type="info" style="width: 20%" @click="router.push('/patients')">去使用</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.block{
  width: 80%;
  height: 60%; /* 修改这里 */
}

.writer{
  text-align: center;
  font-size: 20px;
  font-family: 楷体;
  padding-top: 4%;
}
.area{
  display: flex;
  flex-direction: column;
  justify-content: center; /* 修改这里 */
  align-items: center; /* 添加这里 */
  text-align: center;
  width: 80%;
  height: 80%;
  background-color: #a8a8a8;
  border-radius: 8px;
}
.display{
  display: flex;
  justify-content: center;
  align-items: center;
}
.dark .area{
  background-color: #252525;
}
</style>