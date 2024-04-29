package database.accionesadmin;

import clases.puntosdecontrorutaydestino.RutaPuntoControl;
import database.ConexionDB;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutaPuntoControlDao {

    public List<RutaPuntoControl> obtenerPuntosControlRuta(int idRuta) {
        String query = "select rpc.id_ruta, r.nombre_ruta, rpc.id_punto_de_control, pc.nombre AS nombre_punto_control from ruta_punto_de_control as rpc join punto_de_control as pc on rpc.id_punto_de_control = pc.id_punto_control join ruta as r on rpc.id_ruta = r.id_ruta where rpc.id_ruta = ?;";
        List<RutaPuntoControl> puntosControl = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setInt(1, idRuta);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        return puntosControl;
    }

    public RutaPuntoControl obtenerPuntoControlRuta(int idRuta, int idPuntoControl) {
        String query = "select * from ruta_punto_de_control where id_ruta = ? and id_punto_de_control = ?";
        RutaPuntoControl rutaPuntoControl = null;

        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setInt(1, idRuta);
            preparedStatement.setInt(2, idPuntoControl);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rutaPuntoControl = new RutaPuntoControl();
                rutaPuntoControl.setIdRuta(resultSet.getInt("id_ruta"));
                rutaPuntoControl.setIdPuntoControl(resultSet.getInt("id_punto_de_control"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutaPuntoControl;
    }


    public RutaPuntoControl añadirPuntoControl(RutaPuntoControl rutaPuntoControl) {
        String query = "insert into ruta_punto_de_control (id_ruta, id_punto_de_control) values (?,?);";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setInt(1, rutaPuntoControl.getIdRuta());
            preparedStatement.setInt(2, rutaPuntoControl.getIdPuntoControl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutaPuntoControl;
    }
}
