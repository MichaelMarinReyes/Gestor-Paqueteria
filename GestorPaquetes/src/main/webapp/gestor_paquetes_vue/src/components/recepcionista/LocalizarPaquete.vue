<template>
  <div class="receptionist-view">
    <h1>Panel del Recepcionista</h1>

    <!-- Cuadro de búsqueda -->
    <div class="search-box">
      <label for="search">Buscar por ID de Paquete:</label>
      <input type="text" id="search" v-model="searchQuery" @input="searchPackages">
    </div>

    <!-- Tabla de datos de paquetes -->
    <h2>Datos de Paquetes</h2>
    <table>
      <thead>
      <tr>
        <th>ID Paquete</th>
        <th>ID Cliente</th>
        <th>Punto de Control</th>
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
.receptionist-view {
  max-width: 800px;
  margin: auto;
  padding: 20px;
}

.search-box {
  margin-bottom: 20px;
}

.search-box label {
  display: block;
  margin-bottom: 5px;
}

.search-box input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
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
