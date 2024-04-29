<template>
  <div class="login-container">
    <h2>Añadir punto de control a: {{ this.ruta.nombreRuta }}</h2>

    <form @submit.prevent="crearRuta" method="post" id="crearRecepcionistaForm">
      <div class="form-group">
        <label for="idRuta">ID de Ruta:</label>
        <input type="text" id="idRuta" name="idRuta" v-model="this.ruta.idRuta" disabled>
      </div>

      <div class="form-group">
        <label for="puntoControl">Puntos de control:</label>
        <select id="puntoControl" name="puntoControl" v-model="idPuntoControl" required>
          <option value="" disabled>Seleccione un Punto de Control</option>
          <option v-for="puntoControl in puntosDeControl" :key="puntoControl.idPuntoControl"
                  :value="puntoControl.idPuntoControl">{{ puntoControl.nombre }}
          </option>
        </select>
      </div>

      <button type="submit">Añadir punto de control a ruta</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'AgregarPuntoControlARuta',
  data() {
    return {
      puntosDeControl: [],
      rutaID: '',
      ruta: {
        idRuta: '',
        nombreRuta: '',
        idDestino: ''
      },
      idPuntoControl: ''
    };
  },
  mounted() {
    this.llenarPuntosDeContol()
  },
  methods: {
    async llenarPuntosDeContol() {
      try {
        this.rutaID = this.$route.params.idRuta;
        const response = await axios.get(`http://localhost:8090/gestionar-rutas?idRuta=${this.rutaID}`);
        this.ruta = response.data;

        const resp = await axios.get('http://localhost:8090/gestionar-puntos-de-control');
        this.puntosDeControl = resp.data;
      } catch (error) {
        console.log('Error al cargar puntos de control');
      }
    },
    async crearRuta() {
      const datos = {
        idRuta: parseInt(this.rutaID),
        nombreRuta: ' ',
        idPuntoControl: this.idPuntoControl,
        nombrePuntoControl: ' '
      };

      try {
        const response = await axios.post('http://localhost:8090/rutas-y-puntos-de-control', datos);

        if (response.status === 200) {
          alert('Ruta creada correctamente');
          this.resetearFormulario();
        } else if (response.status === 409) {
          alert('La ruta ya existe con ese ID');
        }
      } catch (error) {
        if (error.response) {
          if (error.response.status === 409) {
            alert('La ruta ya existe con ese ID');
          } else if (error.response.status === 500) {
            alert('Error interno del servidor');
          } else {
            alert('Error desconocido: ' + error.response.status);
          }
        } else {
          console.error('Error:', error.message);
        }
      }
    },
    async resetearFormulario() {
      this.idRuta = '';
      this.nombreRuta = '';
    },
    regresar() {
      this.$router.push('/puntos-de-control-de-ruta/' + this.rutaID);
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
  width: calc(100% - 22px);
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

select {
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

.crear-ruta {
  margin-top: 10px;
}

.error-msg {
  color: red;
  margin-bottom: 10px;
}
</style>