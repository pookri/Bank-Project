<template>
  <n-data-table :columns="tableColumns"></n-data-table>
</template>

<script lang="ts" setup>


import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";

const tableColumns = [
  {title: 'Account Number',  key: 'accountNum'},
  {title: 'Branch Id', key: 'branchId'},
  {title: 'Account Type', key: 'accountType'},
  {title: 'Account Balance', key: 'balance'},
  {title: 'Create Date', key: 'createDate'},
  {title: 'Last Access Date', key: 'lastAccessDate'}
]

const result = ref([])

const apiService: ApiService = ApiService.getInstance()

onMounted( async () => {
 result.value = await apiService.getAccountsInfo()
} )

</script>

<style scoped>

</style>