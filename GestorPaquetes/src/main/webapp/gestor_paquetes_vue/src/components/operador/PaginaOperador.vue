<template>
  <div class="navbar">
    <a href="#">Operador</a>
    <a @click="procesarPaquete">Procesar paquete</a>
    <div class="dropdown">
      <button class="dropbtn" @click="cambiarPuntoControl">Cambiar punto de control
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <a v-if="puntosDeControl.length === 0" @click="asignarPuntoControl">Asignar Punto de Control</a>
        <a v-for="puntoControl in puntosDeControl" :key="puntoControl.idPuntoControl"
           @click="cambiarPuntoControl(puntoControl)">
          {{ puntoControl.nombre }}
        </a>
      </div>
    </div>
    <a href="../">Cerrar sesión</a>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'PaginaOperador',
  props: {
    idOperador: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      puntosDeControl: []
    };
  },
  mounted() {
    this.obtenerPuntosControl()
  },
  methods: {
    async obtenerPuntosControl() {
      try {
        const idOperador = this.$route.params.idOperador;
        const response = await axios.get(`http://localhost:8090/puntos-de-control-asignados?idOperador=${idOperador}`);
        this.puntosDeControl = response.data;
      } catch (error) {
        console.log('Error al obtener puntos de control', error);
        alert('Error al obtener puntos de control. Por favor, inténtalo nuevamente.');
      }
    },
    async cambiarPuntoControl(puntoControl) {
      console.log('Cambiando a punto de control:', puntoControl.nombre);
    },
    async procesarPaquete() {
      const idOperador = this.$route.params.idOperador
      this.$router.push({path: '/procesar-paquete/' + idOperador});
    },
    async asignarPuntoControl() {
      console.log('Asignando nuevo punto de control...');
      // Aquí puedes implementar la lógica para asignar un nuevo punto de control
    }
  },

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