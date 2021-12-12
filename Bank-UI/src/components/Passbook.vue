<template>

  <n-space vertical>
    <div>
      <n-space size="small">
        <n-auto-complete
            v-model:value="nameId"
            :options="nameOptions"
            placeholder="Select name"
            @select="selectedName"
        ></n-auto-complete>
        <n-auto-complete
            v-model:value="branchId"
            placeholder="Select Branch"
            :options="branchOptions"
            :disabled="disableBranchId"
            @select="selectedBranch"
        >
        </n-auto-complete>
        <n-auto-complete
            :options="accountOptions"
            placeholder="Select Account"
            v-model:value="actNumSelected"
            :disabled="disableActNum"
            @select="disableSubmit=false"
        >
        </n-auto-complete>
        <n-date-picker :disabled="disableSubmit" v-model:value="timestamp" type="date" clearable />
        <n-button :disabled="disableSubmit" ghost type="primary" @click="submit()">Submit</n-button>
      </n-space>
    </div>
    <div>
      <n-data-table :columns="tableColumns" :data="passbookResult"></n-data-table>
    </div>
  </n-space>
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";

const nameOptions = ref([{label: 'Krupal', value: 'krupal'}, {label: 'Pooja', value: 'pooja'}]);
const branchOptions = ref ([{label: 'Branch 1', value: 'branch1'}])
const accountOptions = ref(['act1', 'act2']);
const disableBranchId = ref(true)
const disableActNum = ref(true)
const disableSubmit = ref(true)
const passbookResult = ref([])
const apiService = ApiService.getInstance()
const actNumSelected = ''
const timestamp = ref( new Date().getTime() )

const nameId = ref('')
const branchId = ref('')

onMounted( async () => {
  nameOptions.value = await apiService.getCustomerNames()
} )


const tableColumns = [
  {title: 'Date', key: 'date'},
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

  passbookResult.value = await apiService.getPassBook(actNumSelected)
}

async function selectedName() {
  console.log('Selected Name')
  disableBranchId.value = false

  branchOptions.value = await apiService.getCustomerBranches(nameId.value)
}

const selectedBranch = async () => {
  console.log('Selected Branch')
  disableActNum.value = false

  accountOptions.value = await apiService.getCustomerAccounts(nameId.value, branchId.value)
}

</script>

<style scoped>

</style>
