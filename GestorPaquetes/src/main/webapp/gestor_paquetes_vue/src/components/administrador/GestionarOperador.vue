<template>
  <div>
    <div class="login-container">
      <h1>Operadores</h1>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="crearNuevoOperador" class="btn btn-primary">Crear Operador</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Rol</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="operador in filteredOperadores" :key="operador.idOperador">
          <td>{{ operador.idOperador }}</td>
          <td>{{ operador.nombre }}</td>
          <td>{{ operador.apellido }}</td>
          <td>{{ operador.rol }}</td>
          <td>
            <button @click="editarOperador(operador)" title="Editar">
              <i class="fas fa-edit"></i>
            </button>
            <button @click="deshabilitarOperador(operador)" title="Deshabilitar">
              <i class="fas fa-ban"></i>
            </button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'TablaOperadores',
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
    async editarOperador(operador) {
      // Aquí puedes implementar la lógica para editar un operador
      alert(`Editar operador: ${operador.nombre} ${operador.apellido}`);
    },
    async deshabilitarOperador(operador) {
      // Aquí puedes implementar la lógica para deshabilitar un operador
      alert(`Deshabilitar operador: ${operador.nombre} ${operador.apellido}`);
    },
    async crearNuevoOperador() {
      this.$router.push('/crear-operador');
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

button i {
  font-size: 16px;
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
</style>