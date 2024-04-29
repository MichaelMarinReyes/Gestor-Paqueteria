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
            preparedStatement.setString(6, operador.getSesionActiva());
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
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("select * from operador;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Operador operador = new Operador();
                operador.setIdOperador(resultSet.getInt("id_operador"));
                operador.setNombre(resultSet.getString("nombre"));
                operador.setApellido(resultSet.getString("apellido"));
                operador.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                operador.setContraseña(resultSet.getString("contraseña"));
                operador.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                operador.setSesionActiva(resultSet.getString("sesion_activa"));
                operador.setRol(resultSet.getString("rol"));
                operadores.add(operador);
            }
        } catch (SQLException e) {
            e.printStackTrace(); //AQUÍ TRUENA
        }
        return operadores;
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
                operador.setSesionActiva(resultSet.getString("sesion_activa"));
                operador.setRol(resultSet.getString("rol"));
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarOperador(Operador operadorEntidad, int idOperador) {
        String query = "update operador set id_operador = ?, nombre = ?, apellido = ?, contraseña = ?, id_punto_control = ?, sesion_activa = ?, rol = ? where id_operador = ?;";
        try  {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setInt(1, operadorEntidad.getIdOperador());
            preparedStatement.setString(2, operadorEntidad.getNombre());
            preparedStatement.setString(3, operadorEntidad.getApellido());
            preparedStatement.setString(4, operadorEntidad.getContraseña());
            preparedStatement.setInt(5, operadorEntidad.getIdPuntoControl());
            preparedStatement.setString(6, operadorEntidad.getSesionActiva());
            preparedStatement.setString(7, operadorEntidad.getRol());
            preparedStatement.setInt(8, idOperador);
            preparedStatement.executeUpdate();
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
