<template>
  <div class="login-container">
    <h2>Editar Punto de control</h2>

    <form @submit.prevent="actualizarPuntoControl" method="put" id="editarUsuarioForm">
      <label for="nit">ID Punto de Control:</label>
      <input type="text" id="nit" name="nit" v-model="puntoDeControl.idPuntoControl" required>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" v-model="puntoDeControl.nombre" required>

      <label for="idOperador">ID de Operador:</label>
      <input type="text" id="idOperador" name="idOperador" v-model="puntoDeControl.idOperador" required>

      <label for="paqueteEnCola">Paquetes en cola:</label>
      <input type="text" id="paqueteEnCola" name="paqueteEnCola" v-model="puntoDeControl.paquetesEnCola" required>

      <label for="maximoEnCola">Máximo en cola:</label>
      <input type="text" id="maximoEnCola" name="maximoEnCola" v-model="puntoDeControl.maximaEnCola">

      <label for="tarifaOperacion">Tarifa de operación:</label>
      <input type="text" id="tarifaOperacion" name="tarifaOperacion" v-model="puntoDeControl.tarifaOperacion" required>

      <label for="estado">Estado de Punto de Control:</label>
      <select id="estado" name="estado" v-model="puntoDeControl.estado" required>
        <option value="Activo">Activa</option>
        <option value="Desactivado">Desactivada</option>
      </select>

      <button type="submit" class="actualizar-usuario">Actualizar Punto de Control</button>
    </form>

    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'EditarPuntoControl',
  data() {
    return {
      puntoDeControl: {
        idPuntoControl: '',
        nombre: '',
        idOperador: '',
        paquetesEnCola: '',
        tarifaOperacion: '',
        maximaEnCola: '',
        estado: ''
      },
    };
  },
  async mounted() {
    try {
      const idPuntoControl = this.$route.params.idPuntoControl;
      const response = await axios.get(`http://localhost:8090/gestionar-puntos-de-control?idPuntoControl=${idPuntoControl}`);
      this.puntoDeControl = response.data;
    } catch (error) {
      console.log('Error al obtener datos del punto de control', error);
      alert('Error al obtener datos del punto de control. Por favor, intenta nuevamente.');
    }
  },
methods: {
async actualizarPuntoControl() {
  try {
        const response = await axios.put(`http://localhost:8090/gestionar-puntos-de-control?idPuntoControl=${this.puntoDeControl.idPuntoControl}`, this.puntoDeControl);
        if (response.status === 200) {
          alert('Punto de control actualizado correctamente');
          this.regresar();
        }
      } catch (error) {
        console.log('Error al actualizar datos del punto de control: ', error);
        alert('Error al actualizar datos del punto de control. Por favor, intenta nuevamente.');
      }
    },
    async regresar() {
      this.$router.push('/gestionar-puntos-de-control');
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
