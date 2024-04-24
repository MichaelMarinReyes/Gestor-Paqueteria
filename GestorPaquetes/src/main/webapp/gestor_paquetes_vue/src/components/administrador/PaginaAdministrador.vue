<template>
  <div class="navbar">
    <a href="/administrador">Administrador</a>
    <a href="/administrador">Usuarios</a>
    <a href="/gestionar-clientes">Clientes</a>
    <a href="/gestionar-operadores">Operadores</a>
    <a href="/gestionar-recepcionistas">Recepcionistas</a>
    <div class="dropdown">
      <button class="dropbtn">Reportes
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <a href="#">Reporte de usuarios</a>
        <a href="#">Reporte de rutas</a>
        <a href="#">Reporte de clientes</a>
        <a href="#">Reporte de operadores</a>
        <a href="#">Reporte de recepcionistas</a>
      </div>
    </div>
    <a href="../">Cerrar sesión</a>
  </div>
  <div>
    <div class="login-container">
      <h1>Usuarios</h1>
      <h2>Clientes</h2>
      <table>
        <thead>
        <tr>
          <th>Nit</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Rol</th>
          <th>Estado de Cuenta</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="cliente in clientes" :key="cliente.nit">
          <td>{{ cliente.nit }}</td>
          <td>{{ cliente.nombre }}</td>
          <td>{{ cliente.apellido }}</td>
          <td>{{ cliente.rol }}</td>
          <td>{{ cliente.estadoCuenta }}</td>
        </tr>
        </tbody>
      </table>
      <h2>Operadores</h2>
      <table>
        <thead>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Rol</th>
          <th>Estado de cuenta</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="operador in operadores" :key="operador.idOperador">
          <td>{{ operador.idOperador }}</td>
          <td>{{ operador.nombre }}</td>
          <td>{{ operador.apellido }}</td>
          <td>{{ operador.rol }}</td>
          <td>{{operador.sesionActiva}}</td>
        </tr>
        </tbody>
      </table>
      <h2>Recepcionistas</h2>
      <table>
        <thead>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Rol</th>
          <th>Estado de cuenta</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="recepcionista in recepcionistas" :key="recepcionista.idRecepcionista">
          <td>{{ recepcionista.idRecepcionista }}</td>
          <td>{{recepcionista.nombre}}</td>
          <td>{{recepcionista.apellido}}</td>
          <td>{{recepcionista.rol}}</td>
          <td>{{recepcionista.sesionActiva}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PaginaAdministrador',
  data() {
    return {
      operadores: [],
      clientes: [],
      recepcionistas: []
    };
  },
  mounted() {
    this.obtenerOperadores();
    this.obtenerClientes();
    this.obtenerRecepcionistas();
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
    async obtenerOperadores() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-operadores');
        this.operadores = response.data;
      } catch (error) {
        console.error('Error al obtener operadores:', error);
      }
    },
    async obtenerRecepcionistas() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-recepcionistas');
        this.recepcionistas = response.data;
      } catch (error) {
        console.log('Error al obtener recepcionistas:', error)
      }
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

button {
  margin: 10px;
}

.navbar {
  overflow: hidden;
  background-color: #333;
  font-family: Arial;
  margin: 0px;
}

.navbar a {
  float: left;
  font-size: 16px;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: rgb(0, 98, 204);
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}

</style>