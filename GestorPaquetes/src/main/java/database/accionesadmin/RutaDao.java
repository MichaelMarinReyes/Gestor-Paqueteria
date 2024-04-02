package database.accionesadmin;

import clases.puntosdecontrorutaydestino.Ruta;
import database.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RutaDao {

    public Ruta(Ruta ruta) {
        String query = "select * from ruta";

        try {
            try {
                PreparedStatement preparedStatement =  ConexionDB.getInstancia().conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                /*preparedStatement.setString(1, ruta.getNombreRuta());
                preparedStatement.setInt(2, ruta.getIdOperador());
                preparedStatement.setInt(3, ruta.getIdPaquete());
                preparedStatement.setInt(4, ruta.getPaquetesEnCola());
                preparedStatement.setDouble(5, ruta.getTarifaOperacion());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    puntoControl.setIdPuntoControl(resultSet.getInt(1));
                    return puntoControl;*/
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
