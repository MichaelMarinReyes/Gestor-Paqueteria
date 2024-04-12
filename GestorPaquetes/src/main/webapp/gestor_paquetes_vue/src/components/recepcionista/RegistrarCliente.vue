<template>
  <div class="register-container">
    <h1>Registrar Cliente</h1>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="nit">NIT:</label>
        <input type="text" id="nit" v-model="nit" required>
      </div>
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="nombre" required>
      </div>
      <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" v-model="apellido" required>
      </div>
      <div class="form-group">
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" v-model="contrasena" required>
      </div>
      <button type="submit">Registrar Cliente</button>
    </form>
    <p v-if="registered">Cliente registrado exitosamente.</p>
    <p v-else-if="registrationFailed" class="error-msg">Error al registrar cliente. Por favor, inténtalo de nuevo.</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nit: '',
      nombre: '',
      apellido: '',
      contrasena: '',
      registered: false,
      registrationFailed: false
    };
  },
  methods: {
    register() {
      const clientData = {
        nit: this.nit,
        nombre: this.nombre,
        apellido: this.apellido,
        contrasena: this.contrasena
      };

      // Realiza una solicitud POST al servidor con los datos del cliente para el registro
      axios.post('/register/client', clientData)
          .then(response => {
            // Verifica la respuesta del servidor
            if (response.data === 'success') {
              // Registro exitoso
              this.registered = true;
              this.registrationFailed = false;
            } else {
              // Error en el registro
              this.registered = false;
              this.registrationFailed = true;
            }
          })
          .catch(error => {
            console.error('Error al registrar cliente:', error);
            this.registered = false;
            this.registrationFailed = true;
          });
    }
  }
};
</script>

<style scoped>
.register-container {
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

.error-msg {
  color: red;
}
</style>
