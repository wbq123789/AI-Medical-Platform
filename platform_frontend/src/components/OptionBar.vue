<script setup>

</script>

<template>
  <div class="Option">
    <div style="text-align: center;font-weight: bold;font-size:30px;align-content: center;padding-top: 2% ">脑&nbsp;卒&nbsp;中</div>
    <div class="option-button">
      <el-row :gutter="5" style="padding: 3% 0">
        <el-col :span="3">

        </el-col>
        <el-col :span="6" class="grid-content ep-bg-purple">
          <el-button class="button" type="primary">CNN</el-button>
        </el-col>
        <el-col :span="6" class="grid-content ep-bg-purple">
          <el-button class="button" type="primary">DNN</el-button>
        </el-col>
        <el-col :span="6" class="grid-content ep-bg-purple">
          <el-button class="button" type="primary"></el-button>
        </el-col>
        <el-col :span="3">

        </el-col>
      </el-row>
    </div>
  </div>
</template>

<style scoped>
.button{
  width: 60%;
  font-size: 15px;

}
.Option{
  border-radius: 10px 10px 0 0;
  background-color: #ffffff;
}
.dark .Option{
  background-color: rgb(51, 51, 57);
}
.grid-content{
  text-align: center;
}
</style>