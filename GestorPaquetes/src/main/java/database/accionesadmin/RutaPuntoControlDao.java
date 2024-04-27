package database.accionesadmin;

import clases.puntosdecontrorutaydestino.RutaPuntoControl;
import database.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutaPuntoControlDao {

    public List<RutaPuntoControl> obtenerPuntosControlRuta(int idRuta) {
        String query = "select rpc.id_ruta, r.nombre_ruta, rpc.id_punto_de_control, pc.nombre AS nombre_punto_control from ruta_punto_de_control as rpc join punto_de_control as pc on rpc.id_punto_de_control = pc.id_punto_control join ruta as r on rpc.id_ruta = r.id_ruta where rpc.id_ruta = ?;";
        List<RutaPuntoControl> puntosControl = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConexionDB.getInstancia().conectar();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idRuta);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RutaPuntoControl puntoControl = new RutaPuntoControl();
                puntoControl.setIdRuta(resultSet.getInt("id_ruta"));
                puntoControl.setNombreRuta(resultSet.getString("nombre_ruta"));
                puntoControl.setIdPuntoControl(resultSet.getInt("id_punto_de_control"));
                puntoControl.setNombrePuntoControl(resultSet.getString("nombre_punto_control"));
                puntosControl.add(puntoControl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return puntosControl;
    }
}
