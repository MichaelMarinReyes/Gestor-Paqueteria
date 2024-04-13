package database.accionesrecepcionista;

import clases.puntosdecontrorutaydestino.Paquete;
import database.ConexionDB;

import java.sql.*;
import java.time.LocalTime;

public class BodegaDao {

    public Paquete crearPaquete(Paquete paqueteEntidad) {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(
                    "insert into bodega (id_transaccion, id_paquete, id_ruta) values (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setInt(1, paqueteEntidad.getIdPaquete());
            preparedStatement.setString(2, paqueteEntidad.getNit());
            preparedStatement.setString(3, paqueteEntidad.getEstadoPaquete());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                paqueteEntidad.setIdPaquete(generatedKeys.getInt(1));
                return paqueteEntidad;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
