<template>
  <div class="login-container">
    <h2>Procesar Paquete</h2>

    <form action="#" method="post" id="procesarPaqueteForm">
      <div class="form-group">
        <label for="idPaquete">ID de Paquete:</label>
        <input type="text" id="idPaquete" name="idPaquete" required>
      </div>

      <div class="form-group">
        <label for="idOperador">ID de Operador:</label>
        <input type="text" id="idOperador" name="idOperador" required>
      </div>

      <div class="form-group">
        <label for="tiempoEntrada">Tiempo de Entrada:</label>
        <input type="datetime-local" id="tiempoEntrada" name="tiempoEntrada" required>
      </div>

      <div class="form-group">
        <label for="tiempoSalida">Tiempo de Salida:</label>
        <input type="datetime-local" id="tiempoSalida" name="tiempoSalida" required>
      </div>

      <div class="form-group">
        <label for="puntosControl">Puntos de Control:</label>
        <select id="puntosControl" name="puntosControl[]" multiple required>
          <option value="Punto 1">Punto 1</option>
          <option value="Punto 2">Punto 2</option>
          <option value="Punto 3">Punto 3</option>
        </select>
      </div>
      <div class="form-group">
        <label for="destino">Destino:</label>
        <select id="destino" name="destino" required>
          <option value="Destino 1">Destino 1</option>
          <option value="Destino 2">Destino 2</option>
          <option value="Destino 3">Destino 3</option>
        </select>
      </div>
      <button type="submit">Procesar Paquete</button>
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
input[type="datetime-local"] {
  width:  calc(100% - 22px); /* Resta 22px para dar espacio al padding y border */
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

.error-msg {
  color: red;
}
</style>