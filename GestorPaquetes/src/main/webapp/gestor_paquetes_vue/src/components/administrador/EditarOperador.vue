<template>
  <div class="login-container">
    <h2>Editar Operador</h2>

    <form @submit.prevent="actualizarOperador" method="put" id="editarUsuarioForm">
      <label for="nit">NIT:</label>
      <input type="text" id="nit" name="nit" v-model="operador.idOperador" required>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" v-model="operador.nombre" required>

      <label for="apellido">Apellido:</label>
      <input type="text" id="apellido" name="apellido" v-model="operador.apellido" required>

      <label for="contraseña">Contraseña:</label>
      <div class="password-input">
        <input :type="showPassword ? 'text' : 'password'" id="contraseña" name="contraseña"
               v-model="operador.contraseña" required/>
        <i class="fas fa-eye" @click="toggleShowPassword"></i>
      </div>

      <label for="rol">ID Punto de control:</label>
      <input type="text" id="rol" name="rol" v-model="operador.idPuntoControl">

      <label for="rol">Rol:</label>
      <input type="text" id="rol" name="rol" v-model="operador.rol" required disabled>

      <label for="sesionActiva">Estado de Cuenta:</label>
      <select id="sesionActiva" name="sesionActiva" v-model="operador.sesionActiva" required>
        <option value="Activa">Activa</option>
        <option value="Desactivada">Desactivada</option>
      </select>

      <button type="submit" class="actualizar-usuario">Actualizar Usuario</button>
    </form>

    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'EditarCliente',
  data() {
    return {
      operador: {
        idOperador: '',
        nombre: '',
        apellido: '',
        contraseña: '',
        idPuntoControl: '',
        sesionActiva: '',
        rol: ''
      },
      showPassword: false
    };
  },
  async mounted() {
    try {
      const idOperador = this.$route.params.idOperador;
      const response = await axios.get(`http://localhost:8090/gestionar-operadores?idOperador=${idOperador}`);
      this.operador = response.data;
    } catch (error) {
      console.log('Error al obtener datos del operador', error);
      alert('Error al obtener datos del operador. Por favor, intenta nuevamente.');
    }
  },
  methods: {
    async actualizarOperador() {
      try {
        const response = await axios.put(`http://localhost:8090/gestionar-operadores?idOperador=${this.operador.idOperador}`, this.operador);
        if (response.status === 200) {
          console.log(this.operador.sesionActiva);
          alert('Operador actualizado correctamente');
          this.regresar();
        }
      } catch (error) {
        console.log('Error al actualizar datos de operador: ', error);
        alert('Error al actualizar datos del operador. Por favor, intenta nuevamente.');
      }
    },
    async regresar() {
      this.$router.push('/gestionar-operadores');
    },
    async toggleShowPassword() {
      this.showPassword = !this.showPassword;
    }
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

label {
  display: block;
  font-weight: bold;
  margin-top: 10px;
}

input[type="text"],
input[type="password"] {
  width: calc(100% - 22px);
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}

.password-input {
  position: relative;
}

.password-input i {
  position: absolute;
  top: 50%;
  right: 10px;
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
