<template>
  <div class="login-container">
    <h2>Crear Nuevo Operador</h2>

    <form @submit.prevent="crearOperador" id="crearOperadorForm">
      <div class="form-group">
        <label for="idOperador">ID Operador:</label>
        <input type="text" id="idOperador" v-model="idOperador" required>
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
        <label for="contraseña">Contraseña:</label>
        <input type="password" id="contraseña" v-model="contraseña" required>
      </div>

      <div class="form-group">
        <label for="idPuntoControl">ID Punto de Control:</label>
        <input type="text" id="idPuntoControl" v-model="idPuntoControl">
      </div>

      <button type="submit">Crear Operador</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'CrearOperador',
  data() {
    return {
      idOperador: '',
      nombre: '',
      apellido: '',
      contraseña: '',
      idPuntoControl: '',
      sesionActiva: 'Activa',
      rol: 'OPERADOR'
    };
  },
  methods: {
    async crearOperador() {
      if (!this.idOperador || !this.nombre || !this.apellido || !this.contraseña) {
        alert('Por favor completa todos los campos obligatorios.');
        return;
      }

      try {
        const datosOperador = {
          idOperador: parseInt(this.idOperador),
          nombre: this.nombre,
          apellido: this.apellido,
          contraseña: this.contraseña,
          idPuntoControl: parseInt(this.idPuntoControl),
          sesionActiva: this.sesionActiva,
          rol: this.rol
        };

        const response = await axios.post('http://localhost:8090/gestionar-operadores', datosOperador);

        if (response.status === 201) {
          alert('Operador creado correctamente');
          this.resetearFormulario();
        } else if (response.status === 409) {
          alert('Ya existe un operador con el ID: ' + this.idOperador);
        }
      } catch (error) {
        console.error('Error al crear operador', error);
        alert('Error al crear el operador. Por favor, intenta nuevamente.');
      }
    },
    resetearFormulario() {
      this.idOperador = '';
      this.nombre = '';
      this.apellido = '';
      this.contraseña = '';
      this.idPuntoControl = '';
    },
    regresar() {
      this.$router.push('/gestionar-operadores');
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

.crear-usuario {
  margin-top: 10px;
}

.error-msg {
  color: red;
  margin-bottom: 10px;
}
</style>
