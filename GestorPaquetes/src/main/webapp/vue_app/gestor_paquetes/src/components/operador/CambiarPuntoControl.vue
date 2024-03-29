<template>
  <div class="login-container">
    <h2>Cambiar Punto de Control</h2>

    <form action="#" method="post" id="cambiarPuntoControlForm">
      <div class="form-group">
        <label for="puntoControl">Seleccionar Punto de Control:</label>
        <select id="puntoControl" name="puntoControl">
          <option value="1">Punto de Control 1</option>
          <option value="2">Punto de Control 2</option>
          <option value="3">Punto de Control 3</option>
        </select>
      </div>

      <button type="submit">Cambiar</button>
    </form>

    <h3>Datos del Punto de Control Asignados</h3>
    <table id="puntoControlData">
      <thead>
      <tr>
        <th>ID Punto de Control</th>
        <th>Nombre</th>
      </tr>
      </thead>
      <tbody>
      <!-- Aquí se cargarán los datos del punto de control seleccionado -->
      </tbody>
    </table>
  </div>
</template>

<script setup>

</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

select {
  width:  calc(100% - 22px); /* Resta 22px para dar espacio al padding y border */
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  width: 100%;
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
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}
</style>