<template>
  <div class="login-container">
    <h1>Iniciar sesión</h1>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Usuario:</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit">Iniciar sesión</button>
    </form>
    <p v-if="loggedIn">¡Bienvenido, !</p>
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
    async login() {
      try {
        const response = await axios.post('/login', {
          usuario: this.username,
          contraseña: this.password
        });
        if (response.data === 'success') {
          this.loggedIn = true;
        } else {
          this.loginFailed = true;
        }
      } catch (error) {
        console.error('Error al iniciar sesión:', error);
        this.loginFailed = true;
      }
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
