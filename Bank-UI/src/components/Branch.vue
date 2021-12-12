<template>

  <n-space vertical>
    <n-space>
      <span>Create Branch</span>
      <n-input v-model:value="branchName" type="text" placeholder="Branch Name"></n-input>
      <n-input v-model:value="branchCity" type="text" placeholder="Branch City"></n-input>
      <n-button>OK</n-button>
    </n-space>
    <n-data-table :columns="tableColumn" :data="branchData"></n-data-table>
  </n-space>

</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";

const tableColumn = [
  {title: 'Name', key:'name'},
  {title: 'City', key:'city'},
  {title: 'Assets', key:'assets'},
]
const branchName = ref('')
const branchCity = ref('')

const branchData = ref([])
const apiService = ApiService.getInstance()

onMounted( async () =>{
  branchData.value = await apiService.getAllBranches()
})



</script>

<style scoped>

</style>