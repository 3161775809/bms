<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import "@/assets/css/style.css";
export default {
  name: 'App'
}
</script>

<style>
html,body {
  margin: 0px;
  padding: 0px;
  width: 100%;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
