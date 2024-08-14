<template>
  <div>
    模型运行情况
  </div>
  <div style="text-align: center;">
    <el-table :default-sort="{ prop: 'round', order: 'descending' }" :data="filteredTableData" style="width: 100%" size="large">
      <el-table-column label="机构ID" prop="agency_id" />
      <el-table-column label="模型ID" sortable show-overflow-tooltip prop="file_id" />
      <el-table-column label="模型参数" show-overflow-tooltip prop="file_param"/>
      <el-table-column label="轮次" sortable prop="round" />
      <el-table-column label="激励值" prop="reward" />
      <el-table-column label="时间" sortable prop="time" />
      <!-- 在el-table-column使用作用域插槽 -->
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="Type to search" />
        </template>
        <!-- 在default作用域插槽中使用scope变量，并将scope.row传递给open方法 -->
        <template #default="scope">
          <el-button plain size="small" type="primary" @click="open(scope.row, scope.$index)">下载模型数据</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import {computed, ref} from 'vue'
import {getList,getModel} from '@/net'



interface Transaction {
  agency_id: string,
  file_id: string,
  file_param: string,
  reward: string,
  round: string,
  time: string
}

const search = ref('')
const tableData = ref<Transaction[]>([])

const filteredTableData = computed(() => {
  return search.value.trim() === '' ? tableData.value : tableData.value.filter((item) =>
          Object.values(item).some((value) =>
              value.toLowerCase().includes(search.value.trim().toLowerCase())
          )
      );
})


const Group_id = defineProps({
  ID: {
    type: String
  },
})

function getGroupID(){
  console.log(Group_id.ID)
}

getList(Group_id.ID, (data) => {
  try {
    tableData.value = data
  } catch (e) {
    console.error('Error parsing data:', e)
  }
})
const doSomethingWithRoundData = (index) => {
  if (tableData.value.length > 0) {
    return  tableData.value[index].round;
  } else {
    return 'No data'
  }
};

const open = (row, index) => {
  let str = doSomethingWithRoundData(index);
  getModel(Group_id.ID,Group_id.ID,tableData.value[index].file_id,str);
};


</script>
<style scoped>

</style>