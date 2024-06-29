<template>
  <div>
    模型运行情况
  </div>
  <div style="text-align: center;">
    <el-table :data="filteredTableData" style="width: 100%" size="large">
      <el-table-column label="agency_id" prop="agency_id" />
      <el-table-column label="轮次" prop="round" />
      <el-table-column label="激励值" prop="reward" />
      <el-table-column label="时间" prop="time" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="Type to search" />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import {computed, onMounted, ref} from 'vue'
import { ElMessageBox } from 'element-plus'
// import {getList} from '@/net'

interface Transaction {
  agency_id: string;
  round: string;
  reward: string;
  time: string;
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

onMounted(() => {
  getList("001", (data) => {
    try {
      tableData.value = JSON.parse(data)
    } catch (e) {
      console.error('Error parsing data:', e)
    }
  })
})
</script>