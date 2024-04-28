<template>
  <div class="login-container">
    <h2>Crear Nueva Ruta</h2>

    <form @submit.prevent="crearRuta" method="post" id="crearRecepcionistaForm">
      <div class="form-group">
        <label for="idRuta">ID de Ruta:</label>
        <input type="text" id="idRuta" name="idRuta" v-model="idRuta" required>
      </div>

      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" v-model="nombreRuta" required>
      </div>

      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destinos[]" v-model="idDestino" required>
          <option value="" disabled>Seleccione un Destino</option>
          <option v-for="destino in destinos" :key="destino.idDestino" :value="destino.idDestino">{{
              destino.nombre
            }}
          </option>
        </select>
      </div>

      <button type="submit">Crear Ruta</button>
    </form>
    <button @click="regresar" class="boton-regresar">Regresar</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'CrearRuta',
  data() {
    return {
      destinos: [],
      idRuta: '',
      nombreRuta: '',
      idDestino: '',
    };
  },
  mounted() {
    this.llenarPuntosDeContol()
  },
  methods: {
    async llenarPuntosDeContol() {
      try {
        const resp = await axios.get('http://localhost:8090/destinos');
        this.destinos = resp.data;
      } catch (error) {
        console.log('Error al cargar puntos de control');
      }
    },
    async crearRuta() {
      const datosRuta = {
        idRuta: parseInt(this.idRuta),
        nombreRuta: this.nombreRuta,
        idDestino: this.idDestino
      };

      try {
        const response = await axios.post('http://localhost:8090/gestionar-rutas', datosRuta);

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
      this.$router.push('/gestionar-rutas');
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