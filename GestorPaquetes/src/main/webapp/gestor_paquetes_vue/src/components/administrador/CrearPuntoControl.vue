<template>
  <div class="login-container">
    <h2>Crear Nuevo Punto de Control</h2>

    <form @submit.prevent="crearPuntoControl" method="post" id="crearPuntoControlForm">
      <div class="form-group">
        <label for="idOperador">ID Punto de Control:</label>
        <input type="text" id="idOperador" name="idOperador" v-model="idPuntoControl" required>
      </div>

      <div class="form-group">
        <label for="idOperador">Nombre:</label>
        <input type="text" id="idOperador" name="idOperador" v-model="nombre" required>
      </div>

      <div class="form-group">
        <label for="idOperador">ID Operador:</label>
        <input type="text" id="idOperador" name="idOperador" v-model="idOperador">
      </div>


      <div class="form-group">
        <label for="cola">Paquetes en cola:</label>
        <input type="number" id="cola" name="cola" v-model="paquetesEnCola" required>
      </div>

      <div class="form-group">
        <label for="colaMaxima">Máximo en cola:</label>
        <input type="number" id="colaMaxima" name="colaMaxima" v-model="maximaEnCola" required>
      </div>


      <div class="form-group">
        <label for="tarifaOperacion">Tarifa de Operación:</label>
        <input type="text" id="tarifaOperacion" name="tarifaOperacion" v-model="tarifaOperacion" value="Q. ">
      </div>

      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destinos[]" required>
          <option value="" disabled>Seleccione un Destino</option>
          <option v-for="destino in destinos" :key="destino.nombre" :value="destino.nombre">{{
              destino.nombre
            }}
          </option>
        </select>
      </div>
      <button type="submit">Crear Punto de Control</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CrearPuntoDeControl",
  data() {
    return {
      destinos: [],
      idPuntoControl: '',
      nombre: '',
      idOperador: '',
      paquetesEnCola: '',
      tarifaOperacion: '',
      estado: 'Activo',
      maximaEnCola: ''
    };
  },
  mounted() {
    this.llenarDestinos();
  },
  methods: {
    async llenarDestinos() {
      const response = await axios.get('http://localhost:8090/destinos');
      this.destinos = response.data;
    },
    async crearPuntoControl() {
      if (!this.idPuntoControl || !this.nombre || !this.paquetesEnCola || !this.maximaEnCola) {
        alert('Por favor completa todos los campos obligatorios.');
        return;
      }

      const idOperador = this.idOperador ? this.idOperador : 0;
      const tarifaOperacion = this.tarifaOperacion.trim() !== '' ? this.tarifaOperacion : '0';

      try {
        const datosPuntoControl = {
          idPuntoControl: this.idPuntoControl,
          nombre: this.nombre,
          idOperador: idOperador,
          paquetesEnCola: this.paquetesEnCola,
          tarifaOperacion: tarifaOperacion,
          estado: this.estado,
          maximaEnCola: this.maximaEnCola
        };

        console.log('Datos a enviar ', datosPuntoControl);

        const response = await axios.post('http://localhost:8090/gestionar-puntos-de-control', datosPuntoControl);
        if (response.status === 200) {
          alert('Punto de control creado correctamente');
          this.limpiarCampos()
        } else if (response.status === 409) {
          alert('Ya existe un punto de control con ese ID: ' + this.idPuntoControl);
        }
      } catch (error) {
        console.log('Error al crear punto de control', error);
        alert('Error al crear punto de control. Por favor, intenta nuevamente.')
      }
    },
    async limpiarCampos() {
      this.idPuntoControl = '';
      this.nombre = '';
      this.idOperador = '';
      this.paquetesEnCola = '';
      this.maximaEnCola = '';
      this.tarifaOperacion = '';

    },
    async regresar() {
      this.$router.push('/gestionar-puntos-de-control');
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
input[type="number"] {
  width: calc(100% - 22px); /* Resta 22px para dar espacio al padding y border */
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
}
</style>