package database.accionesadmin;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import clases.puntosdecontrorutaydestino.Ruta;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutaDao {

    public List<PuntoDeControl> obtenerTodosLosPuntosControl() {
        List<PuntoDeControl> puntosControl = new ArrayList<>();
        String query = "SELECT * FROM ruta";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PuntoDeControl puntoControl = new PuntoDeControl();
                puntoControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                puntoControl.setNombre(resultSet.getString("nombre"));
                puntoControl.setIdOperador(resultSet.getInt("id_operador"));
                puntoControl.setIdPaquete(resultSet.getInt("id_paquete"));
                puntoControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                puntoControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                puntosControl.add(puntoControl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puntosControl;
    }

}
