<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup
import HelloWorld from './components/HelloWorld.vue'
import { darkTheme } from 'naive-ui'
import { NConfigProvider } from "naive-ui";
import Transactions from "./components/Transactions.vue";
import Passbook from "./components/Passbook.vue";


</script>

<template>

  <n-tabs type="segment">
    <n-tab-pane name="Transaction" tab="Transaction"><transactions></transactions></n-tab-pane>
    <n-tab-pane name="Passbook" tab="Passbook"> <passbook></passbook> </n-tab-pane>
    <n-tab-pane name="Customer" tab="Customer">Qilixiang</n-tab-pane>
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
  margin-top: 60px;
}
</style>
