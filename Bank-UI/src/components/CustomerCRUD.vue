<template>
  <n-space vertical>
    <n-space vertical>
      <n-input-group>
        <n-input v-model:value="custSSN" placeholder="Customer SSN"> </n-input>
        <n-input v-model:value="firstName" placeholder="First Name.." />
        <n-input v-model:value="lastName" placeholder="Last Name.."></n-input>
        <n-input v-model:value="aptNum" placeholder="Apt#" />
        <n-input v-model:value="streetName" placeholder="Street Name"></n-input>
        <n-input v-model:value="city" placeholder="City"></n-input>
        <n-input v-model:value="state" placeholder="State"></n-input>
        <n-input v-model:value="zipcode" placeholder="Zipcode"></n-input>
      </n-input-group>
      <n-input-group>
        <n-select v-model:value="accountType" placeholder="Account Type"></n-select>
        <n-input v-model:value="overdraftLimit" placeholder="Overdraft limit: "></n-input>
        <n-input v-model:value="initialDeposit" placeholder="Initial Deposit"></n-input>
        <n-select v-model:value="employeeAssist" placeholder="Employee helping"></n-select>
        <n-button @click="submit" ghost> Submit </n-button>
      </n-input-group>
    </n-space>

    <n-data-table :columns="tableColumn" :data="customerData"></n-data-table>
  </n-space>
</template>

<script lang="ts" setup>

  import {ref} from "vue";
  import {Customer} from "../models/Customer";
  import {ApiService} from "../api/ApiService";


  const custSSN = ref('')
  const firstName = ref('')
  const lastName = ref('')
  const aptNum = ref('')
  const streetName = ref('')
  const city = ref('')
  const state = ref('')
  const zipcode = ref('')
  const accountType = ref('')
  const overdraftLimit = ref(0)
  const initialDeposit = ref(500)
  const employeeAssist = ref('')

  const apiService = ApiService.getInstance()

  const tableColumn = [
    {title: 'Customer SSN', key: 'ssn'},
    {title: 'First Name', key: 'name'},
    {title: 'Last Name', key: 'ssn'},
    {title: '', key: 'ssn'},
    {title: 'Customer SSN', key: 'ssn'},

  ]

  const customerData = ref([])

  const submit = async () =>{
    const customer: Customer = {
      ssn: custSSN.value,
      firstName: firstName.value,
      lastName: lastName.value,
      aptNum: aptNum.value,
      streetName: streetName.value,
      city: city.value,
      state: state.value,
      zipcode: zipcode.value,
      accountType: accountType.value,
      overdraftLimit: overdraftLimit.value,
      initialDeposit: initialDeposit.value,
    }
    const isCreated =  await apiService.createCustomer(customer)
  }



</script>

<style scoped>

</style>