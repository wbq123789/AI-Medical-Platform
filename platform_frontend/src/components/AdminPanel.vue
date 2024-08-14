<script setup>
import {Check, Document, Promotion} from "@element-plus/icons-vue";
import {ref} from "vue"
import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";

const props = defineProps({
  show:Boolean
})

const emit=defineEmits(['close','success'])
function initEditor() {

}
async function submitTrain() {
post("api/model/startTraining",{
  command: textarea1.value
},data=>{
  console.log(data)
  ElMessage.success("模型已开始训练，请在区块链平台中查看训练详情!")
  emit('success')
})
}
const textarea1 = ref('')
</script>

<template>
  <div>
    <el-drawer :model-value="show"
               direction="btt"
               :close-on-click-modal="false"
               :size="450"
               @open="initEditor"
               @close="emit('close')"
    >
      <template #header>
        <div style="justify-content: center">
          <div style="font-weight: bold">发起新训练</div>
          <div style="font-size: 13px">在这里你可以调整模型训练方式和模型参数</div>
        </div>
      </template>
      <div style="flex: 1;padding: 0 20px">
        <el-row :gutter="10" style="margin-top: 10px;justify-content: center">
          <el-col :span="20">
            <el-input
                v-model="textarea1"
                style="height: 100%"
                :autosize="{ minRows: 6}"
                type="textarea"
                :prefix-icon="Document"
                placeholder="请输入模型训练参数"
            />
          </el-col>
        </el-row>
      </div>
      <div class="AdminButton">
        <el-button type="success" :icon="Check" @click="submitTrain" plain round size="large">发起一个新的模型训练</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<style scoped>
.AdminButton{
  padding-top: 80px;
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