<template>
  <div>
    <div class="login-container">
      <h1>Clientes</h1>
      <div class="input-group">
        <input
            type="text"
            v-model="searchText"
            placeholder="Búsqueda Central"
            title="Búsqueda Rápida"
            class="form-control"
        />
        <button @click="crearNuevoCliente" class="btn btn-primary">Crear Cliente</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>Nit</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Rol</th>
          <th>Estado de la cuenta</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="cliente in filteredClientes" :key="cliente.nit">
          <td>{{ cliente.nit }}</td>
          <td>{{ cliente.nombre }}</td>
          <td>{{ cliente.apellido }}</td>
          <td>{{ cliente.rol }}</td>
          <td>{{ cliente.estadoCuenta }}</td>
          <td>
            <!-- Icono de editar -->
            <i class="fas fa-edit" @click="editarCliente(cliente)"></i>
            <!-- Icono de deshabilitar -->
            <i class="fas fa-ban" @click="deshabilitarCliente(cliente)"></i>
            <!-- Icono de eliminar -->
            <i class="fas fa-trash-alt" @click="eliminarCliente(cliente)"></i>
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
  name: 'TablaClientes',
  data() {
    return {
      clientes: [],
      searchText: ''
    };
  },
  mounted() {
    this.obtenerClientes();
  },
  computed: {
    filteredClientes() {
      const filterText = this.searchText.toUpperCase();
      return this.clientes.filter(cliente => {
        return (
            cliente.nit.includes(filterText) ||
            cliente.nombre.toUpperCase().includes(filterText) ||
            cliente.apellido.toUpperCase().includes(filterText) ||
            cliente.rol.toUpperCase().includes(filterText) ||
            cliente.estadoCuenta.toUpperCase().includes(filterText)
        );
      });
    }
  },
  methods: {
    async obtenerClientes() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-clientes');
        this.clientes = response.data;
      } catch (error) {
        console.error('Error al obtener clientes:', error);
      }
    },
    async editarCliente(cliente) {
      this.$router.push({path: '/editar-cliente/' + cliente.nit});
    },
    async deshabilitarCliente(cliente) {
      alert(`Deshabilitar cliente: ${cliente.nombre} ${cliente.apellido}`);
    },
    async eliminarCliente(cliente) {
      try {
        console.log(`http://localhost:8090/gestionar-clientes?nit=${cliente.nit}`)
        await axios.delete(`http://localhost:8090/gestionar-clientes?nit=${cliente.nit}`);
        window.location.reload();
        alert('Se eliminó con éxito el cliente con el nit');
      } catch (error) {
        alert('Error al eliminar cliente', cliente.nit);
      }
    },
    async crearNuevoCliente() {
      this.$router.push('/crear-cliente');
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