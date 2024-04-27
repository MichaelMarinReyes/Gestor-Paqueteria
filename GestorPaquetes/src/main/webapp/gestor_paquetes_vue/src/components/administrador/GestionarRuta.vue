<template>
  <div>
    <div class="login-container">
      <h1>Rutas</h1>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="crearNuevaRuta" class="btn btn-primary">Crear Ruta</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>ID Ruta</th>
          <th>Nombre</th>
          <th>Destino</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="ruta in filteredRutas" :key="ruta.idRuta">
          <td>{{ ruta.idRuta }}</td>
          <td>{{ ruta.nombreRuta }}</td>
          <td>{{ ruta.idDestino }}</td>
          <td>
            <i class="fas fa-edit" @click="editarRuta(ruta)"></i>
            <i class="fas fa-info-circle" @click="verPuntosControl(ruta)"></i>
            <i class="fas fa-ban" @click="deshabilitarRuta(ruta)"></i>
            <i class="fas fa-trash-alt" @click="eliminarRuta(ruta)"></i>
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
  name: 'TablaRutas',
  data() {
    return {
      rutas: [],
      searchText: ''
    };
  },
  mounted() {
    this.obtenerRutas();
  },
  computed: {
    filteredRutas() {
      const filterText = this.searchText.toUpperCase();
      return this.rutas.filter(ruta => {
        return (
            ruta.idRuta.toString().includes(filterText) ||
            ruta.nombreRuta.toUpperCase().includes(filterText) ||
            ruta.idDestino.toString().toUpperCase().includes(filterText)
        );
      });
    }
  },
  methods: {
    async obtenerRutas() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-rutas');
        this.rutas = response.data;
      } catch (error) {
        console.error('Error al obtener rutas:', error);
      }
    },
    async editarRuta(ruta) {
      this.$router.push({path: '/editar-ruta/' + ruta.idRuta});
    },
    async verPuntosControl(ruta) {
      this.$router.push({path: '/puntos-de-control-de-ruta/' + ruta.idRuta});
    },
    async deshabilitarRuta(ruta) {
      alert(`Deshabilitar ruta: ${ruta.nombre}`);
    },
    async eliminarRuta(ruta) {
      try {
        await axios.delete(`http://localhost:8090/gestionar-rutas?idRuta=${ruta.idRuta}`);
        window.location.reload();
        alert('Se eliminó con éxito la ruta con el nit');
      } catch (error) {
        alert('Error al eliminar ruta', ruta.idRuta);
      }
    },
    async crearNuevaRuta() {
      this.$router.push('/crear-ruta');
    },
    async regresar() {
      this.$router.push('/administrador');
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