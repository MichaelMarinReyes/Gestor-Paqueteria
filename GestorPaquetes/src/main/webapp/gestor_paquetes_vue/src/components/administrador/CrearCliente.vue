<template>
  <div class="login-container">
    <h2>Crear Nuevo Usuario</h2>

    <form @submit.prevent="crearCliente" method="post" id="crearUsuarioForm">
      <label for="nit">NIT:</label>
      <input type="text" id="nit" name="nit" v-model="nit" required>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" v-model="nombre" required>

      <label for="apellido">Apellido:</label>
      <input type="text" id="apellido" name="apellido" v-model="apellido" required>

      <label for="contraseña">Contraseña:</label>
      <input type="password" id="contraseña" name="contraseña" v-model="contraseña" required>

      <button type="submit" class="crear-usuario">Crear Usuario</button>
    </form>

    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'CrearCliente',
  data() {
    return {
      nit: '',
      nombre: '',
      apellido: '',
      contraseña: '',
      rol: 'CLIENTE',
      estadoCuenta: 'Activa'
    };
  },
  methods: {
    async crearCliente() {
      if (!this.nit || !this.nombre || !this.apellido || !this.contraseña) {
        alert('Por favor completa todos los campos obligatorios.');
        return;
      }

      try {
        const datosCliente = {
          nit: this.nit,
          nombre: this.nombre,
          apellido: this.apellido,
          contraseña: this.contraseña,
          rol: this.rol,
          estadoCuenta: this.estadoCuenta
        };

        console.log('Datos a enviar ', datosCliente)

        const response = await axios.post('http://localhost:8090/gestionar-clientes', datosCliente);

        if (response.status === 201) {
          alert('Usuario creado correctamente');
          this.resetearFormulario();
        } else if (response.status === 409) {
          alert('Ya existe un cliente con el nit: ' + this.nit);
        }
      } catch (error) {
        console.log('Error al crear cliente', error);
        alert('Error al crear el usuario. Por favor, intenta nuevamente.');
      }
    },
    resetearFormulario() {
      this.nit = '';
      this.nombre = '';
      this.apellido = '';
      this.contraseña = '';
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

.crear-usuario {
  margin-top: 10px;
}

.error-msg {
  color: red;
  margin-bottom: 10px;
}
</style>
