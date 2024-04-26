<template>
  <div>
    <div class="login-container">
      <h1>Puntos de Control</h1>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="crearPuntoControl" class="btn btn-primary">Crear Punto de Control</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>ID operador</th>
          <th>Paquetes en cola</th>
          <th>Tarifa de Operación</th>
          <th>Máximos en cola</th>
          <th>Estado de punto de control</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="puntoDeControl in filteredPuntosControl" :key="puntoDeControl.idPuntoControl">
          <td>{{ puntoDeControl.idPuntoControl }}</td>
          <td>{{ puntoDeControl.nombre }}</td>
          <td>{{ puntoDeControl.idOperador }}</td>
          <td>{{ puntoDeControl.paquetesEnCola }}</td>
          <td> Q. {{ puntoDeControl.tarifaOperacion }}</td>
          <td>{{ puntoDeControl.maximaEnCola }}</td>
          <td>{{ puntoDeControl.estado }}</td>
          <td>
            <!-- Icono de editar -->
            <i class="fas fa-edit" @click="editarRuta(puntoDeControl)"></i>
            <!-- Icono de deshabilitar -->
            <i class="fas fa-ban" @click="deshabilitarRuta(puntoDeControl)"></i>
            <!-- Icono de eliminar -->
            <i class="fas fa-trash-alt" @click="eliminarRuta(puntoDeControl)"></i>
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
  name: 'TablaPuntosControl',
  data() {
    return {
      puntosDeControl: [],
      searchText: ''
    };
  },
  mounted() {
    this.obtenerPuntosDeControl();
  },
  computed: {
    filteredPuntosControl() {
      const filterText = this.searchText.toUpperCase();
      return this.puntosDeControl.filter(puntoDeControl => {
        return (
            puntoDeControl.idPuntoControl.toString().includes(filterText) ||
            puntoDeControl.nombre.toUpperCase().includes(filterText) ||
            puntoDeControl.idOperador.toString().toUpperCase().includes(filterText) ||
            puntoDeControl.paquetesEnCola.toString().toUpperCase().includes(filterText) ||
            puntoDeControl.tarifaOperacion.toString().toUpperCase().includes(filterText) ||
            puntoDeControl.maximaEnCola.toString().toUpperCase().includes(filterText) ||
            puntoDeControl.estado.toUpperCase().includes(filterText)
        );
      });
    }
  },
  methods: {
    async obtenerPuntosDeControl() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-puntos-de-control');
        this.puntosDeControl = response.data;
      } catch (error) {
        console.error('Error al obtener puntos de control:', error);
      }
    },
    async editarRuta(puntoDeControl) {
      this.$router.push({path: '/editar-ruta/' + puntoDeControl.idPuntoControl});
    },
    async deshabilitarRuta(puntoDeControl) {
      alert(`Deshabilitar punto de control: ${puntoDeControl.nombre}`);
    },
    async eliminarRuta(puntoDeControl) {
      try {
        await axios.delete(`http://localhost:8090/gestionar-puntos-de-control?idPuntoControl=${puntoDeControl.idPuntoControl}`);
        window.location.reload();
        alert('Se eliminó con éxito el punto de control con el ID');
      } catch (error) {
        alert('Error al eliminar ruta', puntoDeControl.idPuntoControl);
      }
    },
    async crearPuntoControl() {
      this.$router.push('/crear-punto-de-control');
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