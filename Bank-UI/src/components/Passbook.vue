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
      List of Owner(s): {{listOfOwners}}
    </div>
    <div>
      <n-data-table remote :row-key="rowKey" :columns="tableColumns" :data="passbookResult"></n-data-table>
    </div>
  </n-space>
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";

const nameOptions = ref([{label: 'Krupal', value: 'krupal'}, {label: 'Pooja', value: 'pooja'}]);
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

const tableColumns = [
  {title: 'Date', key: 'date',
    render(row) {
    return new Date(row.date).toDateString()
    }
  },
  {title: 'Transaction Code', key: 'transactionCode'},
  {title: 'Transaction Name', key: 'transactionName'},
  {title: 'Debits', key: 'debits'},
  {title: 'Credits', key: 'credits'},
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
