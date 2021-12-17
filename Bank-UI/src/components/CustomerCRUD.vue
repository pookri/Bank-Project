<template>
  <n-space vertical>
    <n-space vertical>
      <div >

        <n-grid x-gap="12" :cols="3">
          <n-gi>
            <n-select placeholder="Type of Account"
                      :options="typesOfAccounts"
                      v-model:value="typeSelected"
                      @update-value="typeChange"
            ></n-select>
          </n-gi>
          <n-gi>
            <n-select v-model:value="branch" :options="branchOptions" placeholder="Branch"></n-select>
          </n-gi>
          <n-gi>
            <n-button @click="showEntries"> OK</n-button>
          </n-gi>

        </n-grid>

      </div>
      <n-input-group v-show="showCustEntry">
        <n-input :disabled="showEditFields" v-model:value="custSSN" placeholder="Customer SSN"> </n-input>
        <n-input v-model:value="firstName" placeholder="First Name.." />
        <n-input v-model:value="lastName" placeholder="Last Name.."></n-input>
        <n-input v-model:value="aptNum" placeholder="Apt#" />
        <n-input v-model:value="streetName" placeholder="Street Name"></n-input>
        <n-input v-model:value="city" placeholder="City"></n-input>
        <n-input v-model:value="state" placeholder="State"></n-input>
        <n-input v-model:value="zipcode" placeholder="Zipcode"></n-input>
      </n-input-group>
      <n-input-group v-show="showCustEntry">
        <n-select :disabled="showEditFields" :options="accountTypeInfo"
                  v-model:value="accountType"
                  placeholder="Account Type"
                  @update-value="selectedAccount"
        ></n-select>

        <n-input :disabled="showEditFields" v-show="showLoanField" v-model:value="initialDeposit" placeholder="Initial Deposit"></n-input>
        <n-input :disabled="showEditFields" v-show="!showLoanField" v-model:value="loanAmount" placeholder="Loan Amount"></n-input>
        <n-select :disabled="showEditFields" v-model:value="employeeAssist" :options="employeeOptions" placeholder="Employee helping"></n-select>
        <n-button v-show="showNext" @click="nextEntry">Next Entry</n-button>
        <n-button v-show="editPressed" @click="cancelEdit">Cancel</n-button>
        <n-button @click="submit" ghost> Submit </n-button>
      </n-input-group>
    </n-space>

    <n-data-table :columns="tableColumn" :data="customerData"></n-data-table>
  </n-space>
</template>

<script lang="ts" setup>

import {h, onMounted, ref} from "vue";
import {ApiService} from "../api/ApiService";
import {dateZhCN, NButton, useDialog, useMessage} from "naive-ui";
import {CustomerInfo, CustomerReq} from "../models/Customer";


const custSSN = ref('')
const firstName = ref('')
const lastName = ref('')
const aptNum = ref('')
const streetName = ref('')
const city = ref('')
const state = ref('')
const zipcode = ref('')
const accountType = ref(null)
const branch = ref(null)
const initialDeposit = ref(500)
const loanAmount = ref(500)
const employeeAssist = ref(null)

const typeSelected = ref(null)

const showCustEntry = ref(false)
const showEditFields = ref(false)
const editPressed = ref(false)

const showNext = ref(false)

const showLoanField = ref(false)

const branchOptions = ref([])
const employeeOptions = ref([])
let customerCreateReq: CustomerReq = {customers: []}

const dialog = useDialog()
const message = useMessage()

// const listOfCustomer: Customer[] = []

const typesOfAccounts = ref([
  {label: 'Single Account', value: 'Single' },
  {label: 'Joint Account', value: 'Join'}
])

const accountTypeInfo = ref([
  {label: 'Checking', value: 'CKNGS'},
  {label: 'Savings', value: 'SVNGS'},
  {label: 'Money Market', value: 'MM'},
  {label: 'Loan', value: 'LN'},
])


const apiService = ApiService.getInstance()

const tableColumn = [
  {title: 'Customer SSN', key: 'cssn'},
  {title: 'First Name', key: 'cFirstname'},
  {title: 'Last Name', key: 'cLastname'},
  {title: 'Number of Accounts', key: 'numOfAccounts'},
  {title: 'Number of Branches', key: 'numOfBranches'},
  {title: 'Personal Banker', key: 'personalBanker'},
  {title: 'Edit', key: 'edit',
    render(row) {
    return h(
        NButton,
        {
          size: 'small',
          type: 'info',
          onclick:() => { editCustomer(row) }
        },
        {default: ()=> 'Edit'}
    )
    }
  },
  {title: 'Delete', key: 'delete',
    render(row) {
      return h(
          NButton,
          {
            size: 'small',
            type: 'error',
            onClick: () => {deleteRow(row)}
          },
          {default: ()=> 'Delete'}
      )
    }
  }
]

onMounted( async () => {
   branchOptions.value = await apiService.getAllBranchIds();
   customerData.value = await apiService.getListOfCustomers();
} )

function cancelEdit() {
  editPressed.value = false
  showCustEntry.value = false
  showEditFields.value = false
  showNext.value = false

  resetEntries()
}


// Runs after edit is pressed on a row
function editCustomer(row){
  showEditFields.value = true
  showCustEntry.value = true
  editPressed.value = true
  showNext.value = false

  firstName.value = row.cFirstname
  lastName.value = row.cLastname
  aptNum.value = row.apartmentNumber
  streetName.value = row.streetName
  city.value = row.city
  state.value = row.state
  zipcode.value = row.zipCode
  custSSN.value = row.cssn

}

function deleteRow(row: any){
  console.log('clicked delete', row)
  dialog.warning({
    title: 'Confirm',
    content: 'Are you sure you want to delete this customer',
    positiveText: 'Yes',
    negativeText: 'No',
    onPositiveClick: async () => {
      // const isDone = true
      const isDone = await apiService.removeCustomer(row.cssn)
      if (isDone){
        message.success('Customer removed successfully')

      } else {
        message.error('Something went wrong: Customer was not removed')
      }
    },
    onNegativeClick: () => {

    }
  })
}
// const customerTmp: Customer = {
//   ssn: '112',
//   firstName: 'Krupa',
//   lastName: 'suths',
//   aptNum: 'aptNum',
//   streetName: 'street nu,',
//   city: 'city',
//   state: 'NJ',
//   zipcode: '0099',
//   accountType: 'dkn',
//   branch: 'dfd',
//   initialDeposit: 8989,
//   loanAmount: 885,
// }

const customerData = ref([])

const submit = async () =>{
  // const customer: Customer = {
  //   ssn: custSSN.value,
  //   firstName: firstName.value,
  //   lastName: lastName.value,
  //   aptNum: aptNum.value,
  //   streetName: streetName.value,
  //   city: city.value,
  //   state: state.value,
  //   zipcode: zipcode.value,
  //   accountType: accountType.value,
  //   branch: branch.value,
  //   initialDeposit: initialDeposit.value,
  // }

  const customer: CustomerInfo = {
    cssn: custSSN.value,
    cFirstname: firstName.value,
    cLastname: lastName.value,
    apartmentNumber: aptNum.value,
    streetName: streetName.value,
    city: city.value,
    state: state.value,
    zipCode: zipcode.value,
  }
  customerCreateReq.customers.push(customer)

  // if (customerCreateReq.customers.length === 1){
    customerCreateReq.initialDeposit = initialDeposit.value
    customerCreateReq.loanAmount = loanAmount.value
    customerCreateReq.branch = branch.value
    customerCreateReq.type = typeSelected.value
    customerCreateReq.accountType = accountType.value
    customerCreateReq.employeeHelping = employeeAssist.value
  // } else {

  // }

// todo: make the request
  if (editPressed.value){
    console.log('Customer req at edit', customerCreateReq)
    await apiService.editCustomer(customer)
  } else {
    console.log('Customer req at create', customerCreateReq)
    const rep = await apiService.createCustomer(customerCreateReq)
    if (rep){
      customerData.value = await apiService.getListOfCustomers();
    } else {
      message.error('We did not create a customer ');
    }
  }

  editPressed.value = false
  resetEntries()
  resetToPrevStage()

  // const isCreated =  await apiService.createCustomer(customer)
}

function nextEntry() {
  const customer: CustomerInfo = {
    cssn: custSSN.value,
    cFirstname: firstName.value,
    cLastname: lastName.value,
    apartmentNumber: aptNum.value,
    streetName: streetName.value,
    city: city.value,
    state: state.value,
    zipCode: zipcode.value,
  }

  customerCreateReq.customers.push(customer)

  if (customerCreateReq.customers.length === 1){
    showNext.value = false
    customerCreateReq.initialDeposit = initialDeposit.value
    customerCreateReq.loanAmount = loanAmount.value
    customerCreateReq.branch = branch.value
    customerCreateReq.type = typeSelected
    customerCreateReq.accountType = accountType
    customerCreateReq.employeeHelping = employeeAssist.value
    resetForNextCustomer()
  }

  // resetEntries()

}

function selectedAccount(value: string){
  showLoanField.value = value !== 'loan';
}

function resetForNextCustomer() {
  custSSN.value = ''
  firstName.value = ''
  lastName.value = ''
  aptNum.value = ''
  streetName.value = ''
  city.value = ''
  state.value = ''
  zipcode.value = ''
}

function resetToPrevStage(){
  customerCreateReq = {customers: []}
  showCustEntry.value = false
  typeSelected.value = null
  branch.value = null
}

function resetEntries(){
  custSSN.value = ''
  firstName.value = ''
  lastName.value = ''
  aptNum.value = ''
  streetName.value = ''
  city.value = ''
  state.value = ''
  zipcode.value = ''
  accountType.value = null
  branch.value = null
  initialDeposit.value = 500
  loanAmount.value = 500
  employeeAssist.value = null
}

async function showEntries(){
  showCustEntry.value = true
  console.log('branch selected ', branch.value)
  employeeOptions.value = await apiService.getEmployeeAssist(branch.value)
  console.log('type value from ref', typeSelected.value)
}

function typeChange(value: string){
  console.log('type value changed', value);

  if (value === 'Single'){
  } else {
    showNext.value = true
  }
}


</script>

<style scoped>
.limitlength{
  max-width: 50em;
}
</style>