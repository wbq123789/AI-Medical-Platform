<template>
  <el-row>
    <el-col :span="8">
      <el-statistic title="节点数量" :value="4">
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <Location />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>
    <el-col :span="8">
      <el-statistic :value="outputValue" >
        <template #title>
          <div style="display: inline-flex; align-items: center;font-size: small;">
            区块数量
            <el-icon><Position /></el-icon>
          </div>
        </template>
      </el-statistic>
    </el-col>
    <el-col :span="8">
      <el-statistic title="交易数量" :value="transactionCounts">
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <ChatLineRound />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>
  </el-row>
  <el-divider />
  <Main/>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import { useTransition } from '@vueuse/core'
import { ChatLineRound, Refresh ,Location,Position} from '@element-plus/icons-vue'
import Main from "./Main.vue";
// import {getBlockAndTransactionNumber} from '@/net';

const source = ref(0)
const transactionCounts = ref(0)
const outputValue = useTransition(source, {
  duration: 1500,
})

onMounted(() => {
  // getBlockAndTransactionNumber("001",(data) => {
  //   console.log(typeof data)
  //   const number = data.split(",")
  //   source.value = parseInt(number[0])
  //   transactionCounts.value = parseInt(number[1])
  // })
})
</script>

<style scoped>
.el-col {
  text-align: center;
}
</style>
