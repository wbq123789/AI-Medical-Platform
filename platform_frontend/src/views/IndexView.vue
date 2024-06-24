<script setup>
import { logout,get} from '@/net'
import router from "@/router";
import {useStore} from "@/store";
import {reactive, ref} from "vue";
import {
  ChatDotSquare,
  HelpFilled,
  Location,
  Notification, ArrowRightBold, Aim, UserFilled, ZoomIn
} from "@element-plus/icons-vue";

const store = useStore();
const loading= ref(true);
const searchInput = reactive({
  type:'1',
  text:""
})
get('api/user/info',(data)=>{
  store.user=data
  loading.value=false
})

function userLogout() {
  logout(() => router.push("/"))
}
</script>

<template>
  <div class="main-content" v-loading="loading" element-loading-text="加载中，请稍等...">
    <el-container style="height: 100%" v-if="!loading">
      <el-header class="main-content-header">
        <el-row>
          <el-col :span="20" style="align-content: center"><el-image class="logo" src="src/assets/logo.svg"/></el-col>
          <el-col :span="4" style="align-content: center">
            <div  class="user-info">
              <el-button @click="userLogout" divided>
                <el-icon><ArrowRightBold /></el-icon>
                退出系统
              </el-button>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-container>
        <el-aside width="230px">
          <el-scrollbar style="height: calc(100vh - 55px)">
            <el-menu
                router
                :default-active="$route.path"
                style="min-height: calc(100vh - 55px)"
                :default-openeds="['1','2','3']"
            >
              <el-sub-menu index="1">
                <template #title>
                  <el-icon><UserFilled /></el-icon>
                  <span><b>疾病诊断</b></span>
                </template>
                <el-menu-item index="/index">
                  <template #title>
                    <el-icon><ZoomIn /></el-icon>
                    脑卒中
                  </template>
                </el-menu-item>
                <el-menu-item index="1-2">
                  <template #title>
                    <el-icon><ZoomIn /></el-icon>
                    帕金森
                  </template>
                </el-menu-item>
                <el-menu-item index="1-3">
                  <template #title>
                    <el-icon><ZoomIn /></el-icon>
                    白血病
                  </template>
                </el-menu-item>
              </el-sub-menu>
              <el-menu-item index="2">
                <template #title>
                  <el-icon><Aim /></el-icon>
                  <span><b>药物发现</b></span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-scrollbar>
        </el-aside>
        <el-main class="main-content-page">
          <el-scrollbar style="height: calc(100vh - 55px)">
            <router-view v-slot="{ Component}">
              <transition name="el-fade-in-linear" mode="out-in">
                <component :is="Component" style="height: 100%"></component>
              </transition>
            </router-view>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="less" scoped>
.main-content-page{
  padding: 0;
  background-color: #f7f8fa;
}
.dark .main-content-page{
  background-color: #1f2023;
}

.main-content{
  height: 100vh;
  width: 100vw;
}
.main-content-header{
  border-bottom: 1px solid var(--el-border-color);
  height: 55px;
  .logo{
    height: 38px;
  }
  .user-info{
    height: 55px;
    text-align: right;
    font-size: 20px;
    margin-right: 20px;
    align-content: center;

    .el-avatar:hover{
      cursor: pointer;
    }
  }
}
</style>
