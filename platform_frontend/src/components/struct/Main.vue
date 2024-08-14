<template>
  <div>
    模型运行情况
  </div>
  <div style="text-align: center;">
    <el-table :default-sort="{ prop: 'round', order: 'descending' }" :data="filteredTableData" style="width: 100%" size="large">
      <el-table-column label="机构ID" prop="agency_id" />
      <el-table-column label="模型ID" sortable show-overflow-tooltip prop="file_id" />
      <el-table-column label="模型参数" show-overflow-tooltip prop="file_param"/>
      <el-table-column label="时间" sortable prop="time" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="Type to search" />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import {computed, ref} from 'vue'
import { ElMessageBox } from 'element-plus'
import {getList} from '@/net'

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
  return search.value.trim() === ''
      ? tableData.value
      : tableData.value.filter((item) =>
          Object.values(item).some((value) =>
              value.toLowerCase().includes(search.value.trim().toLowerCase())
          )
      );
})

const open = (row: Transaction) => {
  ElMessageBox.alert(`模型数据: ${JSON.stringify(row, null, 2)}`, '模型详情', {
    confirmButtonText: 'OK',
  })
}

getList("001", (data) => {
  try {
    tableData.value = data
  } catch (e) {
    console.error('Error parsing data:', e)
  }
})
</script>