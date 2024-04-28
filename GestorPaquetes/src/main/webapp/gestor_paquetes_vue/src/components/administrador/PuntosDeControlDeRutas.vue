<template>
  <div>
    <div class="login-container">
      <h1>Puntos de Control de la Ruta: {{ ruta.nombreRuta }}</h1>
      <h2>Destino: {{ destino.nombre }}</h2>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="agregarPuntoControl" class="btn btn-primary">Añadir Punto de Control</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>ID Ruta</th>
          <th>Nombre de Ruta</th>
          <th>ID Punto de Control</th>
          <th>Nombre de Punto de Control</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="rutaPuntoControl in filteredPuntoControlRuta" :key="rutaPuntoControl.idRuta">
          <td>{{ rutaPuntoControl.idRuta }}</td>
          <td>{{ rutaPuntoControl.nombreRuta }}</td>
          <td>{{ rutaPuntoControl.idPuntoControl }}</td>
          <td>{{ rutaPuntoControl.nombrePuntoControl }}</td>
          <td>
            <i class="fas fa-info-circle" @click="verPuntosControl"></i>
            <i class="fas fa-ban" @click="deshabilitarPuntoControl(ruta)"></i>
          </td>
        </tr>
        </tbody>
      </table>
      <button @click="regresar" class="btn boton-regresar">Regresar</button>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'PuntosDeControlDeRuta',
  data() {
    return {
      rutasPuntosControl: [],
      nombreRuta: '',
      searchText: '',
      ruta: {
        idRuta: '',
        nombreRuta: '',
        idDestino: '',
      },
      destino: {
        idDestino: '',
        nombre: '',
        cuotaDestino: '',
        paquetesenDestino: ''
      }
    };
  },
  mounted() {
    this.obtenerRutasPuntoControl();
  },
  computed: {
    filteredPuntoControlRuta() {
      const filterText = this.searchText.toUpperCase();
      return this.rutasPuntosControl.filter(rutaPuntoControl => {
        return (
            rutaPuntoControl.idRuta.toString().includes(filterText) ||
            rutaPuntoControl.nombreRuta.toUpperCase().includes(filterText) ||
            rutaPuntoControl.idPuntoControl.toString().toUpperCase().includes(filterText) ||
            rutaPuntoControl.nombrePuntoControl.toUpperCase().includes(filterText)
        );
      });
    }
  },
  methods: {
    async obtenerRutasPuntoControl() {
      try {
        const idRuta = this.$route.params.idRuta;
        const resp = await axios.get(`http://localhost:8090/gestionar-rutas?idRuta=${idRuta}`);
        this.ruta = resp.data;

        const res = await axios.get(`http://localhost:8090/destinos?idDestino=${this.ruta.idDestino}`)
        this.destino = res.data;
        const response = await axios.get(`http://localhost:8090/rutas-y-puntos-de-control?idRuta=${idRuta}`);
        this.rutasPuntosControl = response.data;
      } catch (error) {
        console.error('Error al obtener rutas:', error);
      }
    },
    async verPuntosControl() {
      alert('Mostrar los puntos respecto a la ruta');
    },
    async deshabilitarPuntoControl(ruta) {
      alert(`Deshabilitar ruta: ${ruta.nombre}`);
    },
    async agregarPuntoControl() {
      alert('Agregar punto de control');
      //this.$router.push('/crear-ruta');
    },
    async regresar() {
      this.$router.push('/gestionar-rutas');
    }
  }
};
</script>

<style scoped>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

th {
  background-color: rgb(0, 98, 204);
}

input {
  padding: 8px;
  margin: 10px;
}

.form-control {
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 6px 10px;
  margin-right: 5px;
  border: none;
  cursor: pointer;
  background-color: transparent;
}

.input-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.input-group input {
  flex: 1;
  margin-right: 5px;
}

i {
  cursor: pointer;
  margin-right: 5px;
  color: #007bff;
}

button {
  width: 10%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  margin: 10px;
}

button:hover {
  background-color: #0056b3;
}

.boton-regresar {
  margin-top: 10px;
}
</style>