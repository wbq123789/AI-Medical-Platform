<script setup>
import {Check, Document, Plus} from "@element-plus/icons-vue";

const props = defineProps({
  show:Boolean
})

const emit=defineEmits(['close','success'])
function initEditor() {

}
function submitTrain() {
  emit('success')
}
</script>

<template>
  <div>
    <el-drawer :model-value="show"
               direction="btt"
               :close-on-click-modal="false"
               :size="650"
               @open="initEditor"
               @close="emit('close')"
    >
      <template #header>
        <div>
          <div style="font-weight: bold">发起新训练</div>
          <div style="font-size: 13px">在这里你可以调整模型训练方式和模型参数</div>
        </div>
      </template>

      <div style="flex: 1;">
        <el-row>
          <el-col>
            <el-input placeholder="请输入模型训练最大轮次" :prefix-icon="Document"
            style="height: 100%" maxlength="40" />
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-input placeholder="请输入模型训练最大轮次" :prefix-icon="Document"
                      style="height: 100%" maxlength="40" />
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-input placeholder="请输入模型训练最大轮次" :prefix-icon="Document"
                      style="height: 100%" maxlength="40" />
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-input placeholder="请输入模型训练最大轮次" :prefix-icon="Document"
                      style="height: 100%" maxlength="40" />
          </el-col>
        </el-row>
      </div>
      <div class="AdminButton">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-button type="success" :icon="Check" @click="submitTrain" plain round>发起个性化训练</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" :icon="Plus" @click="submitTrain" plain round>发起全局训练</el-button>
          </el-col>
        </el-row>
      </div>
    </el-drawer>
  </div>
</template>

<style scoped>
.AdminButton{
  padding-top: 280px;
}
:deep(.el-drawer){
  width: 900px;
  margin: auto;
  border-radius: 10px 10px 0 0;
}
:deep(.el-drawer__header){
  margin: 0;
}
.hint{
  font-size: 13px;
  margin-top: 10px;
  color: var(--el-text-color-placeholder);
}
:deep(.el-row) {
  margin-bottom: 20px;
}
</style>