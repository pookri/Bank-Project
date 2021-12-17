<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup

import Transactions from "./components/Transactions.vue";
import Passbook from "./components/Passbook.vue";
import CustomerCRUD from "./components/CustomerCRUD.vue";
import Branch from "./components/Branch.vue";
import Employee from "./components/Employee.vue";
import Account from "./components/Account.vue";


</script>

<template>

  <n-layout>
    <n-layout-header>
      <h2>Bank Project</h2>
    </n-layout-header>
  </n-layout>

  <n-tabs type="segment">
    <n-tab-pane name="Branch" tab="Branch"> <n-dialog-provider><Branch></Branch></n-dialog-provider> </n-tab-pane>
    <n-tab-pane name="Employee" tab="Employee"> <Employee></Employee> </n-tab-pane>
    <n-tab-pane name="Accounts" tab="Accounts"> <Account></Account> </n-tab-pane>
    <n-tab-pane name="Transaction" tab="Transaction"> <n-message-provider> <transactions></transactions> </n-message-provider> </n-tab-pane>
    <n-tab-pane name="Passbook" tab="Passbook"> <passbook></passbook> </n-tab-pane>
    <n-tab-pane name="Customer" tab="Customer"> <n-dialog-provider> <n-message-provider><CustomerCRUD></CustomerCRUD> </n-message-provider> </n-dialog-provider></n-tab-pane>
  </n-tabs>

<!--  <img alt="Vue logo" src="./assets/logo.png" />-->
<!--  <HelloWorld msg="Hello Vue 3 + Vite" />-->
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
