package database.accionesadmin;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import clases.puntosdecontrorutaydestino.Ruta;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutaDao {

    public List<Ruta> obtenerRutas() {
        List<Ruta> rutas = new ArrayList<>();
        String query = "select * from ruta";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setIdRuta(resultSet.getInt("id_ruta"));
                ruta.setNombreRuta(resultSet.getString("nombre_ruta"));
                ruta.setIdPuntoControl(resultSet.getInt("id_punto_de_control"));
                ruta.setIdDestino(resultSet.getInt("id_destino"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutas;
    }

    public Ruta obtenerRuta(int id) {
        Ruta ruta = null;
        String query = "select * from ruta where id_ruta = ?;";
        try {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ruta = new Ruta();
                    ruta.setIdRuta(resultSet.getInt("id_ruta"));
                    ruta.setNombreRuta(resultSet.getString("nombre_ruta"));
/*
                    Statement statementNombre = ConexionDB.getInstancia().conectar().createStatement();
                    ResultSet resultSetNombre = statementNombre.executeQuery("select nombre from punto_de_control;");
                    String nombre = " ";
                    if (resultSetNombre.getString("id_operador") != null) {
                        nombre = resultSetNombre.getString("id_operador");
                    }
                    ruta.setNombreOperador(nombre);
                    ruta.setNombreOperador(resultSetNombre.);*/
                    ruta.setIdPuntoControl(resultSet.getInt("id_punto_de_control"));
                    ruta.setIdDestino(resultSet.getInt("id_destino"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruta;
    }


    public Ruta crearRuta(Ruta rutaEntidad) {
        String query = "insert into ruta (nombre_ruta, id_punto_de_control, id_destino) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, rutaEntidad.getNombreRuta());
            preparedStatement.setInt(2, rutaEntidad.getIdPuntoControl());
            preparedStatement.setInt(3, rutaEntidad.getIdDestino());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                rutaEntidad.setIdPuntoControl(resultSet.getInt(1));
                return rutaEntidad;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void actualizarRuta(Ruta rutaEntidad) {
        String query = "update ruta set nombre = ?, id_punto_de_control = ?, id_destino = ? where id_ruta = ?;";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rutaEntidad.getNombreRuta());
            preparedStatement.setInt(2, rutaEntidad.getIdPuntoControl());
            preparedStatement.setInt(3, rutaEntidad.getIdDestino());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRuta(int id) {
        String query = "delete from ruta where id_punto_control = ?;";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
