<template>

  <n-space vertical>
    <div>
      <n-grid x-gap="12" :cols="6">
        <n-gi>
        <n-select
            v-model:value="nameId"
            :options="nameOptions"
            placeholder="Select name"
            @update-value="selectedName"
        ></n-select>
        </n-gi>
<!--        <n-auto-complete-->
<!--            v-model:value="branchId"-->
<!--            placeholder="Select Branch"-->
<!--            :options="branchOptions"-->
<!--            :disabled="disableBranchId"-->
<!--            @select="selectedBranch"-->
<!--        >-->
<!--        </n-auto-complete>-->
        <n-gi>
        <n-select
            v-model:value="actNumSelected"
            :options="accountOptions"
            placeholder="Select Account"
            @update-value="disableSubmit=false"
        >
        </n-select>
        </n-gi>
        <n-gi>
          <n-date-picker :disabled="disableSubmit" v-model:value="timestamp" type="date" clearable />
        </n-gi>
        <n-gi>
          <n-button :disabled="disableSubmit" ghost type="primary" @click="submit()">Submit</n-button>
        </n-gi>
      </n-grid>
    </div>
    <div>
      List of Owner(s):
      <n-tag v-for="owner in listOfOwners" :key="owner">{{owner}}</n-tag>
<!--      {{listOfOwners}}-->
    </div>
    <div>
      <n-data-table remote :row-key="rowKey" :columns="tableColumns" :data="passbookResult"></n-data-table>
<!--      <n-data-table remote :row-key="rowKey" :columns="tableColumns" :data="tmpResult"></n-data-table>-->
    </div>
  </n-space>
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";
import {create} from "naive-ui";
import {Passbook} from "../models/Passbook";

const nameOptions = ref([{label: 'Dhruvi', value: 'dhruvi'}, {label: 'Pooja', value: 'pooja'}]);
const branchOptions = ref ([{label: 'Branch 1', value: 'branch1'}])
const accountOptions = ref([]);
const disableBranchId = ref(true)
const disableActNum = ref(true)
const disableSubmit = ref(true)
const passbookResult = ref([])
const apiService = ApiService.getInstance()
const actNumSelected = ref(null)
const timestamp = ref( new Date().getTime() )
const listOfOwners = ref([] as string[])

const nameId = ref(null)
const branchId = ref('')

onMounted( async () => {
  nameOptions.value = await apiService.getCustomerNames()
} )

function rowKey(rowData){
  return rowData.transactionId;
}

const tmpResult: Passbook[] = [

  {date: new Date(), transactionCode: '', transactionName: 'Balance Forward', debits: 0, credits: 0, balance: 500},
  {date: new Date(), transactionCode: 'DS', transactionName: 'Customer Deposit', debits: 100, credits: 0, balance: 600},
  {date: new Date(), transactionCode: 'WD', transactionName: 'Withdrawl', debits: 0, credits: 50, balance: 550},
  {date: new Date(), transactionCode: 'DS', transactionName: 'Customer Deposit', debits: 10, credits: 0, balance: 560}
]

const tableColumns = [
  {title: 'Date', key: 'date',
    render(row) {
    return new Date(row.date).toDateString()
    }
  },
  {title: 'Transaction Code', key: 'transactionCode'},
  {title: 'Transaction Name', key: 'transactionName'},
  {title: 'Debits', key: 'debits', render(row): string {
      if (row.debits === 0){
        return '--'
      } else {
        return row.debits
      }
    } },
  {title: 'Credits', key: 'credits', render(row): string {
    if (row.credits === 0){
      return '--'
    } else {
      return row.credits
    }
    }},
  {title: 'Balance', key: 'balance'}
]

const submit = async () => {
  disableBranchId.value = true
  disableActNum.value = true
  disableSubmit.value = true

  passbookResult.value = await apiService.getPassBook(actNumSelected.value, timestamp.value)
  listOfOwners.value = await apiService.getListOfOwners(actNumSelected.value)
}

async function selectedName(value: string) {
  console.log('Selected Name')
  // disableBranchId.value = false
  // branchOptions.value = await apiService.getCustomerBranches(nameId.value)
  disableActNum.value = false
  accountOptions.value = await apiService.getCustomerAccounts(value)


}

const selectedBranch = async () => {
  console.log('Selected Branch')
  disableActNum.value = false

  // accountOptions.value = await apiService.getCustomerAccounts(nameId.value, branchId.value)
}

</script>

<style scoped>

</style>
