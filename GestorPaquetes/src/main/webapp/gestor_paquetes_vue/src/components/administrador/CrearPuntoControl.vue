<template>
  <div class="login-container">
    <h2>Crear Nuevo Punto de Control</h2>

    <form action="#" method="post" id="crearPuntoControlForm">
      <div class="form-group">
        <label for="idOperador">ID Punto de Control:</label>
        <input type="text" id="idOperador" name="idOperador" required>
      </div>

      <div class="form-group">
        <label for="idOperador">Nombre:</label>
        <input type="text" id="idOperador" name="idOperador" required>
      </div>

      <div class="form-group">
        <label for="idOperador">ID Operador:</label>
        <input type="text" id="idOperador" name="idOperador">
      </div>

      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destinos[]" required>
          <option value="" disabled>Seleccione un Destino</option>
          <option v-for="destino in destinos" :key="destino.nombre" :value="destino.nombre">{{ destino.nombre }}</option>
        </select>
      </div>

      <div class="form-group">
        <label for="tarifaOperacion">Tarifa de Operación:</label>
        <input type="text" id="tarifaOperacion" name="tarifaOperacion" value="Q. ">
      </div>

      <button type="submit">Crear Punto de Control</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CrearPuntoDeControl",
  data() {
    return {
      destinos: []
    };
  },
  mounted() {
    this.llenarDestinos();
  },
  methods: {
    async llenarDestinos() {
      const resp = await axios.get('http://localhost:8090/destinos');
      this.destinos = resp.data;
    },
    async regresar() {
      this.$router.push('/gestionar-puntos-de-control');
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
input[type="number"] {
  width: calc(100% - 22px); /* Resta 22px para dar espacio al padding y border */
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

.crear-recepcionista {
  margin-top: 10px;
}

.error-msg {
  color: red;
}
</style>