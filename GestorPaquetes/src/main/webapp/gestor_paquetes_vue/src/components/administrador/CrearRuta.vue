<template>
  <div class="login-container">
    <h2>Crear Nueva Ruta</h2>

    <form @submit.prevent="crearDestino" method="post" id="crearRecepcionistaForm">
      <div class="form-group">
        <label for="idRuta">ID de Ruta:</label>
        <input type="text" id="idRuta" name="idRuta" required>
      </div>

      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
      </div>

      <div class="form-group">
        <label for="puntosControl">Puntos de Control:</label>
        <select id="puntosControl" name="puntosControl[]" multiple required>
          <option value="" disabled>Seleccione uno o más puntos de control</option>
          <option v-for="puntoDeControl in puntosDeControl" :key="puntoDeControl.nombre" :value="puntoDeControl.nombre">{{ puntoDeControl.nombre }}</option>
        </select>
      </div>

      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destinos[]"  required>
          <option value="" disabled>Seleccione un Destino</option>
          <option v-for="destino in destinos" :key="destino.nombre" :value="destino.nombre">{{ destino.nombre }}</option>
        </select>
      </div>

      <button type="submit">Crear Ruta</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'CrearRuta',
  data() {
    return {
      puntosDeControl: [],
      destinos: []
    };
  },
  mounted() {
    this.llenarPuntosDeContol()
  },
  methods: {
    async llenarPuntosDeContol(){
      try {
        const response = await axios.get('http://localhost:8090/gestionar-puntos-de-control');
        this.puntosDeControl = response.data;
        const resp = await axios.get('http://localhost:8090/destinos');
        this.destinos = resp.data;
      } catch (error) {
        console.log('Error al cargar puntos de control');
      }
    },
    async crearDestino(){
      alert('creando destino');
    },
    regresar() {
      this.$router.push('/gestionar-rutas');
    }
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="password"] {
  width: calc(100% - 22px);
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

select {
  width: calc(100% - 22px);
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #0056b3;
}

.boton-regresar {
  margin-top: 10px;
}

.crear-ruta {
  margin-top: 10px;
}

.error-msg {
  color: red;
  margin-bottom: 10px;
}
</style>