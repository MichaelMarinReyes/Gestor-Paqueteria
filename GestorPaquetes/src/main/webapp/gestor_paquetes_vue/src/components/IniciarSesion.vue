<template>
  <img alt="Vue logo" src="../assets/logo.png">
  <div class="contenedor-login">
    <h1>Iniciar sesión</h1>
    <form @submit.prevent="iniciarSesion">
      <div class="grupo-formulario">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" v-model="usuario" required>
      </div>

      <label for="contraseña">Contraseña:</label>
      <div class="password-input">
        <input :type="showPassword ? 'text' : 'password'" id="contraseña" name="contraseña"
               v-model="contraseña" required/>
        <i class="fas fa-eye" @click="toggleShowPassword"></i>
      </div>
      <div class="grupo-formulario">
        <label for="rol-usuario">Rol de usuario:</label>
        <select id="rol-usuario" v-model="rol">
          <option value="admin">Administrador</option>
          <option value="operador">Operador</option>
          <option value="recepcionista">Recepcionista</option>
        </select>
      </div>
      <button type="submit">Iniciar sesión</button>
    </form>
    <p v-if="sesionValida">¡Bienvenido!</p>
    <p v-else-if="sesionInvalida" class="mensaje-error">Credenciales inválidas. Por favor, inténtalo de nuevo.</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'IniciarSesion',
  data() {
    return {
      usuario: '',
      contraseña: '',
      rol: 'admin',
      sesionValida: false,
      sesionInvalida: false,
      showPassword: false

    };
  },
  methods: {
    async iniciarSesion() {
      try {
        console.log('Datos de inicio de sesión:', {
          usuario: this.usuario,
          contraseña: this.contraseña,
          rol: this.rol
        });
        const respuesta = await axios.post('http://localhost:8090/login', {
          usuario: this.usuario,
          contraseña: this.contraseña,
          rol: this.rol
        });
        if (respuesta.status === 200) {
          this.sesionValida = true;
          if (this.rol === 'admin') {
            this.$router.push('/administrador');
          } else if(this.rol === 'operador') {
            this.$router.push({path: '/operador/' + this.usuario});
          } else if(this.rol === 'recepcionista') {
            this.$router.push('/recepcionista');
          }
        } else {
          this.sesionInvalida = true;
        }
      } catch (error) {
        this.sesionInvalida = true;
      }
    },
    async toggleShowPassword() {
      this.showPassword = !this.showPassword;
    }
  }
};
</script>

<style scoped>
.contenedor-login {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.grupo-formulario {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="password"] {
  width: 90%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.password-input {
  position: relative;
}

.password-input i {
  position: absolute;
  top: 50%;
  right: 20px;
  transform: translateY(-50%);
  cursor: pointer;
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

.mensaje-error {
  color: red;
}
</style>
