package database.accionesadmin;

import clases.roles.Operador;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperadorDao {

    public Operador crearOperador(Operador operador) {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(
                    "insert into operador (id_operador, nombre, apellido, contraseña, id_punto_control, sesion_activa, rol) values (?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setInt(1, operador.getIdOperador());
            preparedStatement.setString(2, operador.getNombre());
            preparedStatement.setString(3, operador.getApellido());
            preparedStatement.setString(4, operador.getContraseña());
            preparedStatement.setInt(5, operador.getIdPuntoControl());
            preparedStatement.setBoolean(6, operador.isSesionActiva());
            preparedStatement.setString(7, operador.getRol());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                operador.setIdOperador(generatedKeys.getInt(1));
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operador> obtenerOperadores() {
        List<Operador> operadores = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from operador;");
            while (resultSet.next()) {
                Operador operador = new Operador();
                operador.setIdOperador(resultSet.getInt("id_operador"));
                operador.setNombre(resultSet.getString("nombre"));
                operador.setApellido(resultSet.getString("apellido"));
                operador.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                operador.setContraseña(resultSet.getString("contraseña"));
                operador.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                operador.setSesionActiva(resultSet.getBoolean("sesion_activa"));
                operador.setRol(resultSet.getString("rol"));
                operadores.add(operador);
            }
            return operadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Operador obtenerOperador(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("select * from operador where id_operador = ?;");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Operador operador = new Operador();
                operador.setIdOperador(resultSet.getInt("id_operador"));
                operador.setNombre(resultSet.getString("nombre"));
                operador.setApellido(resultSet.getString("apellido"));
                operador.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                operador.setContraseña(resultSet.getString("contraseña"));
                operador.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                operador.setSesionActiva(resultSet.getBoolean("sesion_activa"));
                operador.setRol(resultSet.getString("rol"));
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarOperador(Operador operador, int idOperador) {
        String query = "update operador set id_operador = ?, nombre = ?, apellido = ?, contraseña = ?, id_punto_control = ?, sesion_activa = ?, rol = ? where id_operador = ?;";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, operador.getIdOperador());
            preparedStatement.setString(2, operador.getNombre());
            preparedStatement.setString(3, operador.getApellido());
            preparedStatement.setString(4, operador.getContraseña());
            preparedStatement.setInt(5, operador.getIdPuntoControl());
            preparedStatement.setBoolean(6, operador.isSesionActiva());
            preparedStatement.setString(7, operador.getRol());
            preparedStatement.setInt(8, idOperador);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("La actualización del operador no afectó ninguna fila");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarOperador(int id) {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("delete from operador where id_operador = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
