
<template>
  <div class="login-container">
    <h2>Crear Nueva Ruta</h2>

    <form action="#" method="post" id="crearRecepcionistaForm">
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
      </div>

      <div class="form-group">
        <label for="idPuntoControl">ID Punto de Control:</label>
        <input type="text" id="idPuntoControl" name="idPuntoControl" required>
      </div>

      <div class="form-group">
        <label for="idDestino">ID Destino:</label>
        <input type="text" id="idDestino" name="idDestino" required>
      </div>

      <button type="submit">Crear Recepcionista</button>
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
input[type="password"] {
  width:  calc(100% - 22px);
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