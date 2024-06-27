<script setup>

import {ArrowRightBold} from "@element-plus/icons-vue";
import router from "@/router/index.js";

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-08',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-08',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

const judge = true;

</script>

<template>
  <div>
    <el-container>
      <el-header class="main-content-header">
        <el-row>
          <el-col :span="20" style="align-content: center;display: flex;justify-content: left">
            <div style="align-content: center">
              <el-image class="logo" src="src/assets/logo.svg"/>
            </div>
            <div style="width: 70%;align-content: center;margin-left: 15px;font-size: 23px">
              基于AI的医疗平台
            </div>
          </el-col>
          <el-col :span="4" style="align-content: center">
            <div  class="user-info">
              <el-button @click="router.back" divided>
                <el-icon><ArrowRightBold /></el-icon>
                返回
              </el-button>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main style="margin-top: 3%">
        <div class="main-form">
          <el-table :data="tableData" height="600" style="width: 80%" >
            <el-table-column prop="date" label="名称" align="center" style="height: 30px"/>
            <el-table-column prop="name" label="时间" align="center"/>
            <el-table-column prop="address" label="轮次" align="center"/>
            <el-table-column prop="address" label="发起者" align="center"/>
            <el-table-column fixed="right" label="操作" min-width="120" align="center">
              <template #default>
                <el-button link type="success" size="small" @click="" v-show="!judge">
                  加入个性化
                </el-button>
                <el-button link type="primary" size="small" @click="" v-show="!judge">
                  加入全局
                </el-button>
                <el-button link type="success" size="small" @click="" v-show="judge">
                  停止个性化
                </el-button>
                <el-button link type="primary" size="small" @click="" v-show="judge">
                  停止全局
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.main-content-header {
  border-bottom: 1px solid var(--el-border-color);
  height: 55px;

  .logo {
    height: 38px;
  }
  .user-info{
    height: 55px;
    text-align: right;
    font-size: 20px;
    margin-right: 20px;
    align-content: center;
  }
}
.main-form{
  text-align: center;
  display: flex;
  justify-content: center;
  align-content: center;
}
</style>