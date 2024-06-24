<script setup>

</script>

<template>
  <div class="Option">
    <div style="text-align: center;font-weight: bold;font-size:30px;align-content: center;padding-top: 2% ">脑&nbsp;卒&nbsp;中</div>
    <div style="margin-top: 3%" class="option-button">
      <el-row :gutter="5">
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
    <el-divider class="divider"/>
  </div>
</template>

<style scoped>
.divider{
  border-style: outset;
}
.button{
  width: 60%;
  font-size: 15px;

}
.Option{
  height: 23vh;
}
.grid-content{
  text-align: center;
}
</style>