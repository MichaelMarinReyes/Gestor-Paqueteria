<template>
  <div>
    <h2>Panel de Administrador</h2>
    <div class="login-container">
      <h2>Tabla de Datos</h2>

      <table>
        <thead>
        <tr>
          <th>Nombre</th>
          <th>Rol</th>
          <th>Correo Electrónico</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>Admin 1</td>
          <td>Administrador</td>
          <td>admin1@example.com</td>
        </tr>
        <tr>
          <td>Operador 1</td>
          <td>Operador</td>
          <td>operador1@example.com</td>
        </tr>
        <tr>
          <td>Recepcionista 1</td>
          <td>Recepcionista</td>
          <td>recepcionista1@example.com</td>
        </tr>
        <tr>
          <td>Cliente 1</td>
          <td>Cliente</td>
          <td>cliente1@example.com</td>
        </tr>
        </tbody>
      </table>

      <h2>Opciones</h2>
      <button onclick="crearRuta()">Crear Ruta</button>
      <button onclick="crearCliente()">Crear Cliente</button>
      <button onclick="crearOperador()">Crear Operador</button>
      <button onclick="crearRecepcionista()">Crear Recepcionista</button>
      <h2>Reportes</h2>
      <button onclick="generarReporte('usuarios')">Reporte de Usuarios</button>
      <button onclick="generarReporte('rutas')">Reporte de Rutas</button>
      <button onclick="generarReporte('clientes')">Reporte de Clientes</button>
      <button onclick="generarReporte('operadores')">Reporte de Operadores</button>
      <button onclick="generarReporte('recepcionistas')">Reporte de Recepcionistas</button>

    </div>
  </div>
</template>

<script>
export default {
  name: 'PaginaAdministrador',
  data() {
    return {
      subtitulo: 'subtitulo del componente'
    }
  }
}
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
  background-color: #f2f2f2;
}

button {
  margin: 10px;
}
</style>