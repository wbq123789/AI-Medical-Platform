<template>
  <div style="text-align: center;margin: 0 10%">
      <div style="font-size: 35px;font-weight: bolder; margin: 3%">
        Fisco&nbsp;Bcos区块链监控平台
      </div>
    <el-tabs
      v-model="activeName"
      type="card"
      class="demo-tabs"
      @tab-click="handleClick"
    >
      <el-tab-pane label="监控群组" name="001"><One/></el-tab-pane>
      <el-tab-pane label="群组二" name="002"><Other :group="'002'"/></el-tab-pane>
      <el-tab-pane label="群组三" name="003"><Other :group="'003'"/></el-tab-pane>
      <el-tab-pane label="群组四" name="004"><Other :group="'004'"/></el-tab-pane>
      <el-tab-pane label="群组五" name="005"><Other :group="'005'"/></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { TabsPaneContext } from 'element-plus'
import One from './struct/one.vue'
import Other from './struct/other.vue'

const activeName = ref('001')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  // 当 tab 被点击时更新 activeName
  activeName.value = tab.props.name;
}
</script>


<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>