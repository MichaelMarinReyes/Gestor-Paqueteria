<template>
  <div class="login-container">
    <h2>Registrar Paquete Entregado</h2>

    <form action="#" method="post" id="registrarPaqueteForm">
      <div class="form-group">
        <label for="idPaquete">ID de Paquete:</label>
        <input type="text" id="idPaquete" name="idPaquete" required>
      </div>

      <div class="form-group">
        <label for="idCliente">ID de Cliente:</label>
        <input type="text" id="idCliente" name="idCliente" required>
      </div>

      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destino" required>
          <option value="Destino 1">Destino 1</option>
          <option value="Destino 2">Destino 2</option>
          <option value="Destino 3">Destino 3</option>
        </select>
      </div>

      <div class="form-group">
        <label for="total">Total:</label>
        <input type="number" id="total" name="total" required>
      </div>

      <button type="submit">Registrar Paquete Entregado</button>
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

select {
  width: calc(100% - 22px);
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
</style>