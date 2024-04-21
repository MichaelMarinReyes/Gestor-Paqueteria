<template>
  <div class="login-container">
    <h2>Editar Usuario</h2>

    <form @submit.prevent="actualizarCliente" method="post" id="editarUsuarioForm">
      <label for="nit">NIT:</label>
      <input type="text" id="nit" name="nit" v-model="cliente.nit" required disabled>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" v-model="cliente.nombre" required>

      <label for="apellido">Apellido:</label>
      <input type="text" id="apellido" name="apellido" v-model="cliente.apellido" required>

      <label for="contraseña">Contraseña:</label>
      <input type="password" id="contraseña" name="contraseña" v-model="cliente.contraseña" required>

      <label for="rol">Rol:</label>
      <input type="text" id="rol" name="rol" v-model="cliente.rol" required>

      <label for="estadoCuenta">Estado de Cuenta:</label>
      <input type="text" id="estadoCuenta" name="estadoCuenta" v-model="cliente.estadoCuenta" required>

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
      cliente: {
        nit: '',
        nombre: '',
        apellido: '',
        contraseña: '',
        rol: '',
        estadoCuenta: ''
      }
    };
  },
  async mounted() {
    try {
      const clienteId = this.$router.params.clienteId; // Obtener el ID del cliente desde los parámetros de ruta
      const response = await axios.get(`http://localhost:8090/obtener-cliente/${clienteId}`);
      this.cliente = response.data; // Asignar los datos del cliente al objeto cliente
    } catch (error) {
      console.log('Error al obtener datos del cliente', error);
      alert('Error al obtener datos del cliente. Por favor, intenta nuevamente.');
    }
  },
  methods: {
    async actualizarCliente() {
      try {
        const response = await axios.put(`http://localhost:8090/actualizar-cliente/${this.cliente.nit}`, this.cliente);
        if (response.status === 200) {
          alert('Usuario actualizado correctamente');
          this.regresar();
        }
      } catch (error) {
        console.log('Error al actualizar cliente', error);
        alert('Error al actualizar el usuario. Por favor, intenta nuevamente.');
      }
    },
    regresar() {
      this.$router.push('/gestionar-clientes');
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
