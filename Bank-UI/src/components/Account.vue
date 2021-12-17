<template>
  <n-data-table remote :row-key="rowKey" :data="result" :columns="tableColumns"></n-data-table>
</template>

<script lang="ts" setup>


import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";

const tableColumns = [
  {title: 'Account Number',  key: 'account_number'},
  {title: 'Full Name', key: 'fullName'},
  {title: 'Branch Id', key: 'branch_id'},
  {title: 'Account Type', key: 'acc_type'},
  {title: 'Account Balance', key: 'balance'},
  {title: 'Create Date', key: 'create_date', render(row) {
      return new Date(row.create_date).toDateString()
    }},
  {title: 'Last Access Date', key: 'recentAccessDate', render(row) {
      return new Date(row.recentAccessDate).toDateString()
    }}
]

const result = ref([])

const apiService: ApiService = ApiService.getInstance()

onMounted( async () => {
 result.value = await apiService.getAccountsInfo()
} )

function rowKey(rowData){
  return rowData.accountNum + ' ' + rowData.branchId;
}

</script>

<style scoped>

</style>