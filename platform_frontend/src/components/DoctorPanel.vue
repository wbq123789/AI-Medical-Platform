<script setup>
import router from "@/router/index.js";
import * as echarts from 'echarts/core';

import { GridComponent,TitleComponent } from 'echarts/components';
import { LineChart } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';
import {ref,onMounted,reactive} from "vue"

echarts.use([TitleComponent,GridComponent, LineChart, CanvasRenderer, UniversalTransition]);

const chartRef = ref(null);
const model=reactive({
  type:'CNN',
  time: new Date().toLocaleString(),
  agency:'襄阳中心医院',
  evaluate:'良好'
})

onMounted(()=>{
  const myChart = echarts.init(chartRef.value);
  let option;

  option = {
    title: {
      text: '模型历史准确度曲线'
    },
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
        <div style="display: flex;justify-content: space-between;">
          <span style="width: 30vw">当前模型类型：{{model.type}}</span>
          <span style="width: 30vw">模型生成时间：{{model.time}}</span>
        </div>
        <div style="display: flex;justify-content: space-between;">
          <span style="width: 30vw">参与机构：{{model.agency}}</span>
          <span style="width: 30vw">模型评价：{{model.evaluate}}</span>
        </div>
      </div>
      <el-divider/>
      <div style="margin-top: 10px" class="block" ref="chartRef">
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
  font-family: 楷体,serif;
  padding-top: 4%;
}
.area{
  display: flex;
  flex-direction: column;
  justify-content: center; /* 修改这里 */
  align-items: center; /* 添加这里 */
  text-align: center;
  width: 90%;
  height: 90%;
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