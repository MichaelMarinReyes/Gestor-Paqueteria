<template>
  <div class="receive-package-container">
    <h1>Recibir Paquete</h1>
    <form @submit.prevent="receivePackage">
      <div class="form-group">
        <label for="nit">NIT del Cliente:</label>
        <input type="text" id="nit" v-model="nit" required>
      </div>
      <button type="submit">Recibir Paquete</button>
    </form>
    <div v-if="userInfo">
      <h2>Datos del Cliente</h2>
      <p><strong>NIT:</strong> {{ userInfo.nit }}</p>
      <p><strong>Nombre:</strong> {{ userInfo.nombre }}</p>
      <p><strong>Apellido:</strong> {{ userInfo.apellido }}</p>
    </div>
    <p v-if="!userInfo && receivedPackage">El cliente no existe. Por favor, regístrese.</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nit: '',
      userInfo: null,
      receivedPackage: false
    };
  },
  methods: {
    receivePackage() {
      // Realiza una solicitud GET al servidor para obtener la información del cliente
      axios.get(`/client/${this.nit}`)
          .then(response => {
            if (response.data) {
              // Cliente encontrado, muestra sus datos
              this.userInfo = response.data;
              this.receivedPackage = true;
            } else {
              // Cliente no encontrado
              this.userInfo = null;
              this.receivedPackage = true;
            }
          })
          .catch(error => {
            console.error('Error al buscar cliente:', error);
            this.userInfo = null;
            this.receivedPackage = false;
          });
    }
  }
};
</script>

<style scoped>
.receive-package-container {
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

input[type="text"] {
  width: 100%;
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
}

button:hover {
  background-color: #0056b3;
}
</style>
