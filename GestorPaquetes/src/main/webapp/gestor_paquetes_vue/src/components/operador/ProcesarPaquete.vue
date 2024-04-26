<template>
  <div class="login-container">
    <h2>Procesar Paquete</h2>

    <form @submit.prevent="procesarPaquete" method="post" id="procesarPaqueteForm">
      <div class="form-group">
        <label for="idPaquete">ID de Paquete:</label>
        <input type="text" id="idPaquete" name="idPaquete" v-model="idPaquete" required>
      </div>

      <div class="form-group">
        <label for="idOperador">ID de Operador:</label>
        <input type="text" id="idOperador" name="idOperador" v-model="idOperador" disabled>
      </div>

      <div class="form-group">
        <label for="tiempoEntrada">Hora de Entrada:</label>
        <input type="time" id="tiempoEntrada" name="tiempoEntrada" v-model="horaEntrada" required>
      </div>

      <div class="form-group">
        <label for="tiempoSalida">Hora de Salida:</label>
        <input type="time" id="tiempoSalida" name="tiempoSalida" v-model="horaSalida" required>
      </div>

      <!--
      <div class="form-group">
        <label for="puntosControl">Puntos de Control:</label>
        <select id="puntosControl" name="puntosControl[]" multiple required>
          <option value="Punto 1">Punto 1</option>
          <option value="Punto 2">Punto 2</option>
          <option value="Punto 3">Punto 3</option>
        </select>
      </div> -->
      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destino" v-model="selectedDestino" required>
          <option value="" disabled selected>Seleccione un destino</option>
          <option v-for="destino in destinos" :key="destino.idDestino" :value="destino.nombre">
            {{ destino.nombre }}
          </option>
        </select>
      </div>
      <button type="submit">Procesar Paquete</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'ProcesarPaquete',
  data() {
    return {
      idPaquete: '',
      idOperador: '',
      horaEntrada: '',
      horaSalida: '',
      selectedDestino: '',
      destinos: []
    };
  },
  async mounted() {
    this.idOperador = this.$route.params.idOperador;
    this.llenarDestinos()
  },
  methods: {
    async llenarDestinos() {
      try {
        const response = await axios.get('http://localhost:8090/destinos');
        this.destinos = response.data;
      } catch (error) {
        alert('Error al cargar destinos', error);
      }
    },
    async procesarPaquete() {
      // Aquí puedes implementar la lógica para procesar el paquete con los datos del formulario
      console.log('ID de Paquete:', this.idPaquete);
      console.log('ID de Operador:', this.idOperador);
      console.log('Hora de Entrada:', this.tiempoEntrada);
      console.log('Hora de Salida:', this.tiempoSalida);
      console.log('Destino Seleccionado:', this.selectedDestino);

    },
    async regresar() {
      this.$router.push({path: '/operador/' + this.idOperador});
    },
  }
}
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
input[type="datetime-local"] {
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

.actualizar-usuario {
  margin-top: 10px;
}

.error-msg {
  color: red;
  margin-bottom: 10px;
}
</style>