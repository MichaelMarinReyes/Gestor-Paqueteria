<template>
  <div class="navbar">
    <a href="/recepcionista">Recepcionista</a>
    <a href="/administrador">Recibir paquete</a>
    <a href="/#">Registrar cliente</a>
    <a href="/#">Registrar paquete entregado</a>
    <a href="/#">Localizar paquete</a>
    <a href="../">Cerrar sesión</a>
  </div>
  <div>
    <div class="login-container">
      <h1>Estado de paquetes</h1>
      <table>
        <thead>
        <tr>
          <th>ID paquete</th>
          <th>Nit</th>
          <th>Estado de paquete</th>
          <th>ID Destino</th>
          <th>ID punto de control</th>
          <th>Hora de entrada</th>
          <th>Hora de salida</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="paquete in paquetes" :key="paquete.idPaquete">
          <td>{{ paquete.idPaquete }}</td>
          <td>{{ paquete.nit }}</td>
          <td>{{ paquete.estadoPaquete }}</td>
          <td>{{ paquete.idDestino }}</td>
          <td>{{ paquete.idPuntoControl }}</td>
          <td>{{ paquete.horaEntrada }}</td>
          <td>{{ paquete.horaSalida }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PaginaRecepcionista',
  data() {
    return {
      paquetes: [],

    };
  },
  mounted() {
    this.obtenerPaquetes();
  },
  methods: {
    async obtenerPaquetes() {
      try {
        const response = await axios.get('http://localhost:8090/gestionar-paquetes');
        this.paquetes = response.data;
      } catch (error) {
        console.error('Error al obtener paquetes:', error);
      }
    },
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