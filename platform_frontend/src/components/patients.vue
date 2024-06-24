<script setup>
import {Search} from "@element-plus/icons-vue";
import {ref,reactive} from "vue"
import Analyse from "@/components/analyse.vue";

const data = reactive({
  ID:1,
})

const input = ref();

function search(){
  if(input!=null){

  }
}
</script>

<template>
  <div style="height: calc(100vh - 55px - 23vh)" class="display">
    <div class="area">
      <div style="height: 20%;align-content: center;margin:1% 5%">
          <el-row :gutter="5">
            <el-col :span="20">
              <el-input v-model="input" placeholder="病人ID号" maxlength="15" type="text">
                <template #prefix>
                  <el-icon><Search /></el-icon>
                </template>
              </el-input>
            </el-col>
            <el-col :span="4" style="text-align: right">
              <el-button type="success" plain @click="">查找此病人</el-button>
            </el-col>
          </el-row>
      </div>
      <div style="height: 70%">
        <analyse/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.area{
  width: 80%;
  height: 80%;
  background-color: #a8a8a8;
  border-radius: 8px;
  text-align: center;
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