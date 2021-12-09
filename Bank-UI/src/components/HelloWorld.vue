<template>
  <h1>{{ msg }}</h1>
  <button @click="count++">count is: {{ count }}</button>
  <p>Edit <code>components/HelloWorld.vue</code> to test hot module replacement.</p>
</template>

<script lang="ts" setup>

import {defineProps, ref} from "vue";

const props = defineProps<{
  msg: string
}>()
let count = ref(0);

</script>
