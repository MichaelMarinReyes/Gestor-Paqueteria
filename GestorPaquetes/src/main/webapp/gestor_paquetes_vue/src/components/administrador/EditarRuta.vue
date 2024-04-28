<template>
  <div class="login-container">
    <h2>Editar Ruta</h2>

    <form @submit.prevent="actualizarRuta" method="put" id="editarUsuarioForm">
      <label for="nit">ID Ruta:</label>
      <input type="text" id="nit" name="nit" v-model="ruta.idRuta" required>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" v-model="ruta.nombreRuta" required>

      <label for="idDestino">ID Destino:</label>
      <input type="text" id="idDestino" name="idDestino" v-model="ruta.idDestino" required>

      <button type="submit" class="actualizar-usuario">Actualizar Ruta</button>
    </form>

    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'EditarRuta',
  data() {
    return {
      ruta: {
        idRuta: '',
        nombreRuta: '',
        idDestino: ''
      },
    };
  },
  async mounted() {
    try {
      const idRuta = this.$route.params.idRuta;
      const response = await axios.get(`http://localhost:8090/gestionar-rutas?idRuta=${idRuta}`);
      this.ruta = response.data;
    } catch (error) {
      console.log('Error al obtener datos de la ruta', error);
      alert('Error al obtener datos de la ruta. Por favor, intenta nuevamente.');
    }
  },
  methods: {
    async actualizarRuta() {
      try {
        const response = await axios.put(`http://localhost:8090/gestionar-rutas?idRuta=${this.ruta.idRuta}`, this.ruta);
        if (response.status === 200) {
          alert('Ruta actualizada correctamente');
          this.regresar();
        }
      } catch (error) {
        console.error('Error al actualizar datos de la ruta:', error);
        alert('Error al actualizar datos de la ruta. Por favor, intenta nuevamente.');
      }
    },
    async regresar() {
      this.$router.push('/gestionar-rutas');
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
