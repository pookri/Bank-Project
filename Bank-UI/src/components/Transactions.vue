<template>
  <n-space vertical>

    <n-grid x-gap="12" :cols="5">
      <n-gi>
        <n-select v-model:value="transactionType" placeholder="Transaction Type: "
                  :options="transactionsTypes"
                  @update-value="selectTransactionType"
        ></n-select>
      </n-gi>
      <n-gi>
        <n-select v-model:value="fromAct" :disabled="showFrom" placeholder="From Account"></n-select>
      </n-gi>
      <n-gi>
        <n-select v-model:value="toAct" :disabled="showTo"  placeholder="To Account" ></n-select>

      </n-gi>
      <n-gi>
        <n-select v-model:value="amount" :disabled="showAmount" placeholder="Amount" ></n-select>
      </n-gi>
      <n-gi>
        <n-button @click="submit">Submit</n-button>
      </n-gi>
    </n-grid>

    <n-data-table :columns="tableColumns" />
  </n-space>
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";
import {TransactionReq} from "../models/Transaction";

const transactionsTypes = [
  {label: 'Cash Deposit', value: 'deposit'},
  {label: 'Withdraw', value: 'withdraw'},
  {label: 'Transfer b/w Accounts', value: 'transfer'}
]

const tableColumns = [
  {title: 'Account Number', key: 'actNum'},
  {title: 'Transaction Number', key: 'transactionNum'},
  {title: 'Transaction Time', key: 'transactionTime'},
  {title: 'Transaction Type', key: 'transactionType'},
  {title: 'Transaction Amount', key: 'transactionAmount'},
]

const apiService = ApiService.getInstance()

const transactionType = ref('')

const fromAct = ref(null)
const toAct = ref(null)
const amount = ref(null)

const showFrom = ref(true)
const showTo = ref(true)
const showAmount = ref(true)
const tableResult = ref([])

function selectTransactionType(value: string){
  if (value === 'deposit'){
    showFrom.value = false
    showTo.value = true
    showAmount.value = false
  } else if (value === 'withdraw'){
    showFrom.value = true
    showTo.value = false
    showAmount.value = false
  } else if (value === 'transfer'){
    showFrom.value = false
    showTo.value = false
    showAmount.value = false
  }
}

onMounted( async () => {
  tableResult.value = await apiService.getAllTransactions()
} )

async function submit(){
  const req: TransactionReq = {
    transactionType: transactionType.value,
    fromAct: fromAct.value,
    toAct: toAct.value,
    amount: amount.value
  }
  await apiService.postTransaction(req)
}



</script>

<style scoped>

</style>