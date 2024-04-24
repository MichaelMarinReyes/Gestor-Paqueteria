<template>
  <div class="login-container">
    <h2>Crear Nuevo Recepcionista</h2>

    <form @submit.prevent="crearRecepcionista" method="post" id="crearRecepcionistaForm">
      <div class="form-group">
        <label for="idRecepcionista">ID Recepcionista:</label>
        <input type="text" id="idRecepcionista" name="idRecepcionista" v-model="idRecepcionista" required>
      </div>

      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" v-model="nombre" required>
      </div>

      <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" v-model="apellido" required>
      </div>

      <div class="form-group">
        <label for="contraseña">Contraseña:</label>
        <input type="password" id="contraseña" name="contraseña" v-model="contraseña" required>
      </div>


      <button type="submit">Crear Recepcionista</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: 'CrearRecepcionista',
  data() {
    return {
      idRecepcionista: '',
      nombre: '',
      apellido: '',
      contraseña: '',
      sesionActiva: 'Activa',
      rol: 'RECEPCIONISTA'
    };
  },
  methods: {
    async crearRecepcionista() {
      if (!this.idRecepcionista || !this.nombre || !this.apellido || !this.contraseña) {
        alert('Por favor completa todos los campos obligatorios.');
        return;
      }

      try {
        const datosRecepcionista = {
          idRecepcionista: parseInt(this.idRecepcionista),
          nombre: this.nombre,
          apellido: this.apellido,
          contraseña: this.contraseña,
          sesionActiva: this.sesionActiva,
          rol: this.rol
        };

        const response = await axios.post('http://localhost:8090/gestionar-recepcionistas', datosRecepcionista);

        if (response.status === 201) {
          alert('Recepcionista creado correctamente');
          this.resetearFormulario();
        } else if (response.status === 409) {
          alert('Ya existe un recepcionista con el ID: ' + this.idRecepcionista);
        }
      } catch (error) {
        console.error('Error al crear Recepcionista', error);
        alert('Error al crear el recepcionista. Por favor, intenta nuevamente.');
      }
    },
    resetearFormulario() {
      this.idRecepcionista = '';
      this.nombre = '';
      this.apellido = '';
      this.contraseña = '';
    },
    regresar() {
      this.$router.push('/gestionar-recepcionistas');
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

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="password"] {
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

.crear-recepcionista {
  margin-top: 10px;
}

.error-msg {
  color: red;
  margin-bottom: 10px;
}
</style>