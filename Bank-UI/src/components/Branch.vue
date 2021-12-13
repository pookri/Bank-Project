<template>

  <n-space vertical>
    <n-space>
      <span>Create Branch</span>
      <n-input v-model:value="branchName" type="text" placeholder="Branch Name"></n-input>
      <n-input v-model:value="branchCity" type="text" placeholder="Branch City"></n-input>
      <n-button @click="createBranch">OK</n-button>
    </n-space>
    <n-data-table remote :row-key="rowKey" :columns="tableColumn" :data="branchData"></n-data-table>
  </n-space>

</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";

const tableColumn = [
  {title: 'Name', key:'name'},
  {title: 'City', key:'city'},
  {title: 'Assets', key:'assets'},
  {title: 'Manager', key: 'manager'},
  {title: 'Assistant Manager', key: 'assistantManager'},
  {title: 'Num Of Employees', key: 'numOfEmployees'},
]
const branchName = ref('')
const branchCity = ref('')

const branchData = ref([])
const apiService = ApiService.getInstance()

onMounted( async () =>{
  const result =  await apiService.getAllBranches()
  branchData.value = result
  console.log('Branch table values: ', branchData.value)
})

function rowKey (rowData) {
  return rowData.id;
}

function createBranch(){
  // apiService.createCustomer()
}


</script>

<style scoped>

</style>