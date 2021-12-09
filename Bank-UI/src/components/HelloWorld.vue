<template>
  <h1>{{ msg }}</h1>
  <button @click="count++">count is: {{ count }}</button>
  <p>Edit <code>components/HelloWorld.vue</code> to test hot module replacement.</p>
</template>

<script lang="ts" setup>

  import {onMounted} from 'vue';
  name: 'HelloWorld'
  defineProps
  onMounted( () => {

  } )

// export default {
//   name: 'HelloWorld',
//   props: {
//     msg: String
//   },
//   data() {
//     return {
//       count: 0
//     }
//   }
// }
</script>
