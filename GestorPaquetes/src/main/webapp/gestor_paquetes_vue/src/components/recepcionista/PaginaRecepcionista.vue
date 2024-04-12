<template>
  <div class="vista-recepcionista">
    <h1>Panel del Recepcionista</h1>

    <!-- Menú de opciones -->
    <div class="menu-opciones">
      <button @click="mostrarFormularioRecibirPaquete">Recibir Paquete</button>
      <button @click="mostrarFormularioRegistrarCliente">Registrar Cliente</button>
      <button @click="mostrarFormularioRegistrarPaqueteEntregado">Registrar Paquete Entregado</button>
      <button @click="mostrarFormularioLocalizarPaquete">Localizar Paquete</button>
    </div>

    <!-- Tabla de datos de paquetes -->
    <h2>Estado de Paquetes</h2>
    <table>
      <thead>
      <tr>
        <th>ID Paquete</th>
        <th>ID Cliente</th>
        <th>Destino</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>col1</td>
        <td>col2</td>
        <td>col3</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

</script>

<style scoped>
.vista-recepcionista {
  max-width: 800px;
  margin: auto;
  padding: 20px;
}

.menu-opciones {
  margin-bottom: 20px;
}

.menu-opciones button {
  margin-right: 10px;
}

.grupo-formulario {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th, table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

table th {
  background-color: #f2f2f2;
}
</style>
