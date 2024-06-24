<script setup>
const patient = [
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
  {
    id: 1,
    name: '张三',
    availability: '95%',
    completeness: '90%'
  },
]
</script>

<template>
  <div class="analyse">
    <div class="left" >
      <el-table :data="patient" style="width: 100%" max-height="290">
        <el-table-column prop="id" label="病人ID"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="availability" label="数据可用性"/>
        <el-table-column prop="completeness" label="数据完整度"/>
      </el-table>
    </div>
    <div class="right">
      使用CNN模型预测结果为：80%高风险
    </div>
  </div>
</template>

<style scoped>
.analyse{
  display: flex;
  justify-content: center;
  gap: 3%;
  height: 100%;
}
.left{
  width: 45%;
  height: 100%;
  background-color: wheat;
  border-radius: 10px;
}
.right{
  width: 45%;
  height: 100%;
  background-color: #b3dcff;
  border-radius: 10px;
  text-align: center;
  align-content: center;
  font-size: 20px;
  font-weight: bolder;
}
</style>