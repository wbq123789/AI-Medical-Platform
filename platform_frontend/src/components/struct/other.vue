<template>
  <el-row>
    <el-col :span="8">
      <el-statistic title="节点数量" :value="9">
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <Location />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>
    <el-col :span="8">
      <el-statistic :value="2">
        <template #title>
          <div style="display: inline-flex; align-items: center;font-size: small;">
            已部署的智能合约
            <el-icon><Switch /></el-icon>
          </div>
        </template>
      </el-statistic>
    </el-col>
    <el-col :span="8">
      <el-statistic title="总轮次" :value="source">
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <ChatLineRound />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>
  </el-row>
  <el-divider />
  <Recently :Id="props.group"></Recently>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import { ChatLineRound, Switch ,Location} from '@element-plus/icons-vue'
import Recently from "./Recently.vue";
// import {getLength} from "@/net";
const source = ref(0)

onMounted(() => {
  // getLength(props.group,(length) => {
  //   source.value = parseInt(JSON.parse(length));
  // });
});
const props = defineProps({
  group: {
    type: String
  }
})

</script>

<style scoped>
.el-col {
  text-align: center;
}
</style>
