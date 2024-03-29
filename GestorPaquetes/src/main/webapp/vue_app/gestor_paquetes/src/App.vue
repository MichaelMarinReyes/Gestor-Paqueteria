<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <IniciarSesion msg="Bienvenido a envíos de paquete"/>
</template>

<script>

import IniciarSesion from './components/IniciarSesion.vue'

export default {
  name: 'App',
  components: {
    IniciarSesion,
  }
}
</script>

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
