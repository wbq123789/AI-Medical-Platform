<script setup>
import {Check, Document, Promotion} from "@element-plus/icons-vue";
import {ref} from "vue"
import axios from "axios";
import {accessHeader} from "@/net/index.js";

const props = defineProps({
  show:Boolean
})

const emit=defineEmits(['close','success'])
function initEditor() {

}
function submitTrain() {
  emit('success')
}
function beforeAvatarUpload(rawFile) {
  if(rawFile.type!=="image/jpeg"&&rawFile.type!=="image/png"){
    ElMessage.error("头像格式只能为JPG/PNG")
    return false
  }else if(rawFile.size/1024>100){
    ElMessage.error("头像不能大于 100KB")
    return false
  }
  return true
}
function uploadSuccess(response) {
  ElMessage.success("头像上传成功！")
  store.user.avatar=response.data
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
        <el-row :gutter="50" style="align-items: center;text-align: center;justify-content: center">
          <el-col :span="10">
            <el-input placeholder="请输入模型ID" :prefix-icon="Document"
            style="height: 100%" maxlength="40" />
          </el-col>
          <el-col :span="10">
            <el-input placeholder="请输入模型训练最大轮次" :prefix-icon="Promotion"
                      style="height: 100%" maxlength="40" />
          </el-col>
        </el-row>
        <el-row :gutter="10" style="margin-top: 10px;justify-content: center">
          <el-col :span="20">
            <el-input
                v-model="textarea1"
                style="height: 100%"
                :autosize="{ minRows: 2}"
                type="textarea"
                :prefix-icon="Document"
                placeholder="请输入模型训练参数"
            />
          </el-col>
        </el-row>
        <el-row style="justify-content: center">
          <el-col :span="10">
            <div style="margin:5px 0">
              <el-Upload
                  :show-file-list="false"
                  :before-upload="beforeAvatarUpload"
                  :on-success="uploadSuccess"
                  :headers="accessHeader()"
              >
                <el-button style="width: 200px" size="large" round plain type="primary">上传模型</el-button>
              </el-Upload>
            </div>
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