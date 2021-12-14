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
        <n-select v-model:value="fromAct" :disabled="showFrom" :options="listOfActs" placeholder="From Account"></n-select>
      </n-gi>
      <n-gi>
        <n-select v-model:value="toAct" :disabled="showTo" :options="listOfActs" placeholder="To Account" ></n-select>

      </n-gi>
      <n-gi>
        <n-input-number v-model:value="amount" :disabled="showAmount" placeholder="Amount" ></n-input-number>
      </n-gi>
      <n-gi>
        <n-button @click="submit">Submit</n-button>
      </n-gi>
    </n-grid>

    <n-data-table remote :row-key="rowKey" :columns="tableColumns" :data="tableResult" ></n-data-table>
  </n-space>
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";
import {TransactionReq} from "../models/Transaction";
import {useMessage} from "naive-ui";

const transactionsTypes = [
  {label: 'Cash Deposit', value: 'deposit'},
  {label: 'Withdraw', value: 'withdraw'},
  {label: 'Check', value: 'transfer'}
]

const tableColumns = [
  {title: 'Transaction Number', key: 'transactionId'},
  {title: 'Account Number', key: 'transactionAccountNumber'},
  {title: 'Transaction Date', key: 'transactionDate', render(row) {
      return new Date(row.transactionDate).toDateString()
    }},
  {title: 'Transaction Time', key: 'transactionTime', render(row) {
      return new Date(row.transactionTime).toTimeString()
    }},
  {title: 'Transaction Type', key: 'transactionType'},
  {title: 'Transaction Amount', key: 'transactionAmount'},
]

const apiService = ApiService.getInstance()

const transactionType = ref(null)

const fromAct = ref(null)
const toAct = ref(null)
const amount = ref(null)

const showFrom = ref(true)
const showTo = ref(true)
const showAmount = ref(true)
const tableResult = ref([])
const listOfActs = ref([])
const message = useMessage()

function selectTransactionType(value: string){
  if (value === 'deposit'){
    showFrom.value = true
    showTo.value = false
    showAmount.value = false
  } else if (value === 'withdraw'){
    showFrom.value = false
    showTo.value = true
    showAmount.value = false
  } else if (value === 'transfer'){
    showFrom.value = false
    showTo.value = false
    showAmount.value = false
  }
}

onMounted( async () => {
  tableResult.value = await apiService.getAllTransactions()
  listOfActs.value = await apiService.getCheckingSavingsActs()
  // fromAct.value = acts
  // toAct.value = acts
} )

function rowKey(rowData){
  return rowData.transactionId;
}

async function submit(){
  const req: TransactionReq = {
    transactionType: transactionType.value,
    fromAct: fromAct.value,
    toAct: toAct.value,
    amount: amount.value
  }
  const isCreated = await apiService.postTransaction(req)
  if (isCreated){
    message.success('Successfully created Transaction')
    tableResult.value = await apiService.getAllTransactions()
  } else {
    message.error('Something went wrong')
  }
}



</script>

<style scoped>

</style>