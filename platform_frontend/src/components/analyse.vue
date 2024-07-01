<script setup>
import {ref,reactive,onMounted} from "vue"
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
} from 'echarts/components';
import { BarChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';
import {icons} from "@element-plus/icons-vue/global";
import {Flag} from "@element-plus/icons-vue";

echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  BarChart,
  CanvasRenderer
]);
const chartRef = ref(null);
onMounted(()=>{
  const myChart = echarts.init(chartRef.value);
  let option;

  option = {
    title: {
      text: '病人数据分析表'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {},
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'value',
      boundaryGap: [0, 0.01]
    },
    yAxis: {
      type: 'category',
      data: ['数据可用性','完整度指标']
    },
    series: [
      {
        name: 'ID:1001',
        type: 'bar',
        data: ['30', '80']
      }
    ]
  };

  option && myChart.setOption(option);

})
const patient=reactive({
  id:null,
  dataUsability:0,
  dataIntegrity:0
})


const radio = ref(true)
</script>

<template>
    <div class="analyse">
        <div class="left" ref="chartRef">
        </div>
        <div class="right">
          <p>使用CNN模型预测结果为：80%高风险</p>
          <el-radio-group v-model="radio">
            <el-radio :value=true>准确·</el-radio>
            <el-radio :value=false>不准确</el-radio>
          </el-radio-group>
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