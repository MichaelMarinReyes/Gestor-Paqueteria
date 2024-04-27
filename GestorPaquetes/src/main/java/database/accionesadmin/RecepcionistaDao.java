package database.accionesadmin;

import clases.roles.Recepcionista;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDao {

    public Recepcionista crearRecepcionista(Recepcionista recepcionista) {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(
                    "insert into recepcionista (id_paquete, nombre, apellido, contraseña, sesion_activa, rol) values (?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, recepcionista.getIdPaquete());
            preparedStatement.setString(2, recepcionista.getNombre());
            preparedStatement.setString(3, recepcionista.getApellido());
            preparedStatement.setString(4, recepcionista.getContraseña());
            preparedStatement.setString(5, recepcionista.getSesionActiva());
            preparedStatement.setString(6, recepcionista.getRol());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                recepcionista.setIdRecepcionista(generatedKeys.getInt(1));
                return recepcionista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recepcionista> obtenerRecepcionistas() {
        List<Recepcionista> recepcionistas = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from recepcionista;");
            while (resultSet.next()) {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setIdRecepcionista(resultSet.getInt("id_recepcionista"));
                recepcionista.setIdPaquete(resultSet.getInt("id_paquete"));
                recepcionista.setNombre(resultSet.getString("nombre"));
                recepcionista.setApellido(resultSet.getString("apellido"));
                recepcionista.setContraseña(resultSet.getString("contraseña"));
                recepcionista.setSesionActiva(resultSet.getString("sesion_activa"));
                recepcionista.setRol(resultSet.getString("rol"));
                recepcionistas.add(recepcionista);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recepcionistas;
    }

    public Recepcionista obtenerRecepcionista(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("select * from recepcionista where id_recepcionista = ?;");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setIdRecepcionista(resultSet.getInt("id_recepcionista"));
                recepcionista.setIdPaquete(resultSet.getInt("id_paquete"));
                recepcionista.setNombre(resultSet.getString("nombre"));
                recepcionista.setApellido(resultSet.getString("apellido"));
                recepcionista.setContraseña(resultSet.getString("contraseña"));
                recepcionista.setSesionActiva(resultSet.getString("sesion_activa"));
                recepcionista.setRol(resultSet.getString("rol"));
                return recepcionista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarRecepcionista(Recepcionista recepcionista, int idRecepcionista) {
        String query = "update recepcionista set id_recepcionista = ?, id_paquete = ?, nombre = ?, apellido = ?, contraseña = ?, sesion_activa = ?, rol = ? where id_recepcionista = ?";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recepcionista.getIdRecepcionista());
            preparedStatement.setInt(2, recepcionista.getIdPaquete());
            preparedStatement.setString(3, recepcionista.getNombre());
            preparedStatement.setString(4, recepcionista.getApellido());
            preparedStatement.setString(5, recepcionista.getContraseña());
            preparedStatement.setString(6, recepcionista.getSesionActiva());
            preparedStatement.setString(7, recepcionista.getRol());
            preparedStatement.setInt(8, idRecepcionista);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("La actualización del recepcionista no afectó ninguna fila en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error en dao" + e.getMessage());
        }
    }

    public void eliminarRecepcionista(int id) {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("delete from recepcionista where id_recepcionista = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
