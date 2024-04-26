<template>
  <div>
    <div class="login-container">
      <h1> Reporte de Operadores</h1>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="regresar" class="btn btn-primary">Regresar</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Contraseña</th>
          <th>ID punto de control</th>
          <th>Estado de cuenta</th>
          <th>Rol</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="operador in filteredOperadores" :key="operador.idOperador">
          <td>{{ operador.idOperador }}</td>
          <td>{{ operador.nombre }}</td>
          <td>{{ operador.apellido }}</td>
          <td>{{ operador.contraseña }}</td>
          <td>{{ operador.idPuntoControl }}</td>
          <td>{{ operador.sesionActiva }}</td>
          <td>{{ operador.rol }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'ReporteOperadores',
  data() {
    return {
      operadores: [],
      searchText: ''
    };
  },
  mounted() {
    this.obtenerOperadores();
  },
  computed: {
    filteredOperadores() {
      const filterText = this.searchText.toUpperCase();
      return this.operadores.filter(operador => {
        return (
            operador.idOperador.toString().includes(filterText) ||
            operador.nombre.toUpperCase().includes(filterText) ||
            operador.apellido.toUpperCase().includes(filterText) ||
            operador.contraseña.toUpperCase().includes(filterText) ||
            operador.idPuntoControl.toString().includes(filterText) ||
            operador.sesionActiva.toUpperCase().includes(filterText) ||
            operador.rol.toUpperCase().includes(filterText)
        );
      });
    }
  },
  methods: {
    async obtenerOperadores() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-operadores');
        this.operadores = response.data;
      } catch (error) {
        console.error('Error al obtener operadores:', error);
      }
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