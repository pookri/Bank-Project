<template>
  <n-space vertical>
<!--    <n-space>-->
<!--      <span>Create Employee </span>-->
<!--      <n-input v-model:value="employeeSSN" type="text" placeholder="Employee SSN"></n-input>-->
<!--      <n-input v-model:value="employeeName" type="text" placeholder="Employee Name"></n-input>-->
<!--      <n-input v-model:value="phoneNumber" type="text" placeholder="Employee Phone Number"></n-input>-->
<!--&lt;!&ndash;      <n-date-picker v-model:value="startDate" placeholder="Start Date"></n-date-picker>&ndash;&gt;-->
<!--      <n-select v-model:value="branch" placeholder="Branch"></n-select>-->
<!--      <n-button>OK</n-button>-->
<!--    </n-space>-->
    <n-data-table remote :row-key="rowKey" :columns="tableColumn" :data="employees"></n-data-table>
  </n-space>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";
import {EmployeeResult} from "../models/EmployeeResult";

const tableColumn = [
  {title: 'SSN', key:'employee_ssn'},
  {title: 'Name', key:'fullName'},
  {title: 'Phone Number', key:'mobile_number'},
  {title: 'Start Date', key:'start_date'},
  {title: 'Branch', key:'branch_id'},
  {title: 'Employment Period (Days) ', key: 'employeePeriod'},
  {title: 'Customers Assisted', key: 'assistedNum'},
  // {title: 'Actions', key: 'actions'}
]
const employeeSSN = ref('')
const employeeName = ref('')
const phoneNumber = ref('')
const startDate = ref(new Date().getTime())
const branch = ref('')


const employees = ref([])
const apiService = ApiService.getInstance()

onMounted( async () =>{
  employees.value = await apiService.getAllEmployees()
})

function rowKey(rowData) {
  return rowData.ssn
}

const createEmployee = async () => {
  const employee: EmployeeResult = {
    ssn: employeeSSN.value,
    name: employeeName.value,
    phoneNumber: phoneNumber.value,
    startDate: startDate.value,
    branch: branch.value
  }

  const isCreated =  await apiService.createEmployee(employee);

}


</script>

<style scoped>

</style>