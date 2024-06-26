<script setup>

import {reactive} from "vue";
import {Lock, User} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import {login} from "@/net/index.js";

const form = reactive({
  username:"",
  password:"",
  remember: false,
})


function userLogin(){
  login(form.username,form.password,form.remember,()=>{
    router.push("/index")
  })
}
</script>

<template>
  <div style="text-align: center;margin: 0 20px;height: 400px" class="login">
    <div style="margin-top: 20%;font-weight:bolder;font-size: 25px">
      账&nbsp;号&nbsp;登&nbsp;录
    </div>
    <div style="font-size: 15px;margin-top:3%">在进入系统之前，请先输入用户名和密码进行登录</div>
    <div style="margin-top: 10%" >
      <el-form v-model="form">
        <el-form-item>
          <el-input v-model="form.username" maxlength="255" type="text" placeholder="用户名或邮箱">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input show-password v-model="form.password" maxlength="20" type="password" placeholder="密码">
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12" style="text-align: left">
            <el-form-item>
              <el-checkbox label="记住我" v-model="form.remember"></el-checkbox>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div style="margin-top: 5%">
        <el-button style="width: 50%;" type="primary" plain @click="userLogin">登&nbsp;&nbsp;录</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>