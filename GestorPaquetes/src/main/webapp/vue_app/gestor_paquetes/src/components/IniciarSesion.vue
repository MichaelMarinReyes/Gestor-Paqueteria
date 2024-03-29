<template>
  <div class="login-container">
    <h1>Iniciar sesión</h1>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Nit:</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit">Iniciar sesión</button>
    </form>
    <p v-if="loggedIn">¡Bienvenido, {{ username }}!</p>
    <p v-else-if="loginFailed" class="error-msg">Credenciales inválidas. Por favor, inténtalo de nuevo.</p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      username: '',
      password: '',
      loggedIn: false,
      loginFailed: false
    };
  },
  methods: {
    login() {
      const userData = {
        usuario: this.username,
        contraseña: this.password
      };

      // Realiza una solicitud POST al servlet Login con los datos de inicio de sesión
      axios.post('/login', userData)
          .then(response => {
            // Si la solicitud es exitosa, verifica la respuesta del servidor
            if (response.data === 'success') {
              // Inicio de sesión exitoso
              this.loggedIn = true;
              this.loginFailed = false;
            } else {
              // Inicio de sesión fallido
              this.loggedIn = false;
              this.loginFailed = true;
            }
          })
          .catch(error => {
            console.error('Error al iniciar sesión:', error);
            this.loggedIn = false;
            this.loginFailed = true;
          });
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
