package database.accionesadmin;

import clases.puntosdecontrorutaydestino.Ruta;
import database.ConexionDB;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutaDao {

    public List<Ruta> obtenerRutas() {
        List<Ruta> rutas = new ArrayList<>();
        String query = "select * from ruta";
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setIdRuta(resultSet.getInt("id_ruta"));
                ruta.setNombreRuta(resultSet.getString("nombre_ruta"));
                ruta.setIdDestino(resultSet.getInt("id_destino"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rutas;
    }

    public Ruta obtenerRuta(int id) {
        Ruta ruta = null;
        String query = "select * from ruta where id_ruta = ?;";
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                ruta = new Ruta();
                ruta.setIdRuta(resultSet.getInt("id_ruta"));
                ruta.setNombreRuta(resultSet.getString("nombre_ruta"));
                ruta.setIdDestino(resultSet.getInt("id_destino"));
                return ruta;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ruta crearRuta(Ruta rutaEntidad) throws ExcepcionApi {
        if (rutaEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("La ruta proporcionada es nula").build();
        }
        String query = "insert into ruta (nombre_ruta, id_destino) values (?, ?)";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, rutaEntidad.getNombreRuta());
            preparedStatement.setInt(2, rutaEntidad.getIdDestino());
            preparedStatement.execute();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo crear la ruta");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rutaEntidad;
    }

    public void actualizarRuta(Ruta rutaEntidad) {
        String query = "update ruta set id_ruta= ?, nombre_ruta = ?, id_destino = ? where id_ruta = ?;";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, rutaEntidad.getIdRuta());
            preparedStatement.setString(2, rutaEntidad.getNombreRuta());
            preparedStatement.setInt(3, rutaEntidad.getIdDestino());
            preparedStatement.setInt(4, rutaEntidad.getIdRuta());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("La actualización del punto de control no afectó ninguna fila en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRuta(int id) {
        String query = "delete from ruta where id_ruta = ?;";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
