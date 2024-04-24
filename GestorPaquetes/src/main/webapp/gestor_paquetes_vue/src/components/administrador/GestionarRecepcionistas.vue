<template>
  <div>
    <div class="login-container">
      <h1>Recepcionistas</h1>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="crearNuevoRecepcionsta" class="btn btn-primary">Crear Recepcionista</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Contraseña</th>
          <th>Estado de cuenta</th>
          <th>Rol</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="recepcionista in filteredRecepcionistas" :key="recepcionista.idRecepcionista">
          <td>{{ recepcionista.idRecepcionista }}</td>
          <td>{{ recepcionista.nombre }}</td>
          <td>{{ recepcionista.apellido }}</td>
          <td>{{ recepcionista.contraseña }}</td>
          <td>{{ recepcionista.sesionActiva }}</td>
          <td>{{ recepcionista.rol }}</td>
          <td>
            <!-- Icono de editar -->
            <i class="fas fa-edit" @click="editarRecepcionista(recepcionista)"></i>
            <!-- Icono de deshabilitar -->
            <i class="fas fa-ban" @click="deshabilitarRecepcionista(recepcionista)"></i>
            <!-- Icono de eliminar -->
            <i class="fas fa-trash-alt" @click="eliminarRecepcionista(recepcionista)"></i>
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
  name: 'TablaRecepcionistas',
  data() {
    return {
      recepcionistas: [],
      searchText: ''
    };
  },
  mounted() {
    this.obtenerRececpcionistas();
  },
  computed: {
    filteredRecepcionistas() {
      const filterText = this.searchText.toUpperCase();
      return this.recepcionistas.filter(recepcionista => {
        return (
            recepcionista.idRecepcionista.toString().includes(filterText) ||
            recepcionista.nombre.toUpperCase().includes(filterText) ||
            recepcionista.apellido.toUpperCase().includes(filterText) ||
            recepcionista.apellido.toUpperCase().includes(filterText) ||
            recepcionista.contraseña.toUpperCase().includes(filterText) ||
            recepcionista.sesionActiva.toUpperCase().includes(filterText) ||
            recepcionista.rol.toUpperCase().includes(filterText)
        );
      });
    }
  },
  methods: {
    async obtenerRececpcionistas() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-recepcionistas');
        this.recepcionistas = response.data;
      } catch (error) {
        console.error('Error al obtener operadores:', error);
      }
    },
    async editarRecepcionista(recepcionista) {
      this.$router.push({path: '/editar-recepcionista/' + recepcionista.idRecepcionista});
    },
    async deshabilitarRecepcionista(recepcionista) {
      alert(`Deshabilitar recepcionista: ${recepcionista.nombre} ${recepcionista.apellido}`);
    },
    async eliminarRecepcionista(recepcionista) {
      try {
        await axios.delete(`http://localhost:8090/gestionar-recepcionistas?idRecepcionista=${recepcionista.idRecepcionista}`);
        alert('Se eliminó con éxito el recepcionista con el ID');
        window.location.reload();
      } catch (error) {
        alert('Error al eliminar recepcionista', recepcionista.idRecepcionista);
      }
    },
    async crearNuevoRecepcionsta() {
      this.$router.push('/crear-recepcionista');
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