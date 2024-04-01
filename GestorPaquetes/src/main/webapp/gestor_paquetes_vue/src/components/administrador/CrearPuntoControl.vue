<template>
  <div class="login-container">
    <h2>Crear Nuevo Punto de Control</h2>

    <form action="#" method="post" id="crearPuntoControlForm">
      <div class="form-group">
        <label for="idOperador">ID Operador:</label>
        <input type="text" id="idOperador" name="idOperador" required>
      </div>

      <div class="form-group">
        <label for="idPaquete">ID Paquete:</label>
        <input type="text" id="idPaquete" name="idPaquete" required>
      </div>

      <div class="form-group">
        <label for="tarifaOperacion">Tarifa de Operación:</label>
        <input type="number" id="tarifaOperacion" name="tarifaOperacion" required>
      </div>

      <button type="submit">Crear Punto de Control</button>
    </form>
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

input[type="text"],
input[type="number"] {
  width: calc(100% - 22px); /* Resta 22px para dar espacio al padding y border */
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

.error-msg {
  color: red;
}
</style>