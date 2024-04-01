package database.accionesadmin;

import clases.roles.Cliente;
import clases.roles.Operador;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperadorDao {

    public List<Operador> obtenerOperadores() {
        List<Operador> operadores = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM operador;");
            while (rs.next()) {
                Operador operador = new Operador();
                operador.getIdOperador();
                operador.getNombre();
                operador.getApellido();
                operador.getContraseña();
                operador.isSesionActiva();
                operadores.add(operador);
            }
            return operadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Operador obtenerOperadorPorId(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("SELECT * FROM operador WHERE id_operador = ?;");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Operador operador = new Operador();
                operador.getIdOperador();
                operador.getNombre();
                operador.getApellido();
                operador.getContraseña();
                operador.isSesionActiva();
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Operador insertarOperador(Operador operador) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement(
                    "INSERT INTO operador (nombre, apellido, contraseña, rol, sesion_activa, id_operador, id_punto_control) VALUES (?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, operador.getNombre());
            stmt.setString(2, operador.getApellido());
            stmt.setString(3, operador.getContraseña());
            stmt.setString(4, operador.getRol());
            stmt.setBoolean(5, operador.isSesionActiva());
            stmt.setInt(6, operador.getIdOperador());
            stmt.setInt(7, operador.getIdPuntoControl());
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                operador.setIdOperador(generatedKeys.getInt(1));
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Operador actualizarOperador(Operador operador) {
        // Método no implementado
        return null;
    }

    public void eliminarOperador(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("DELETE FROM operador WHERE id_operador = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
