package database.accionesadmin;

import clases.roles.Operador;
import clases.roles.Recepcionista;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDao {
    public List<Recepcionista> obtenerRecepcionistas() {
        List<Recepcionista> recepcionistas = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM recepcionista;");
            while (rs.next()) {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.getIdRecepcionista();
                recepcionista.getNombre();
                recepcionista.getApellido();
                recepcionista.getContraseña();
                recepcionista.isSesionActiva();
                recepcionistas.add(recepcionista);
            }
            return recepcionistas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recepcionista obtenerRecepcionistaPorId(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("SELECT * FROM recepcionista WHERE id_recepcionista = ?;");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.getIdRecepcionista();
                recepcionista.getNombre();
                recepcionista.getApellido();
                recepcionista.getContraseña();
                recepcionista.isSesionActiva();
                return recepcionista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recepcionista insertarRecepcionista(Recepcionista recepcionista) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement(
                    "INSERT INTO recepcionista (nombre, apellido, contraseña, rol, sesion_activa, id_recepcionista, id_paquete) VALUES (?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, recepcionista.getNombre());
            stmt.setString(2, recepcionista.getApellido());
            stmt.setString(3, recepcionista.getContraseña());
            stmt.setString(4, recepcionista.getRol());
            stmt.setBoolean(5, recepcionista.isSesionActiva());
            stmt.setInt(6, recepcionista.getIdRecepcionista());
            stmt.setInt(7, recepcionista.getIdPaquete());
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                recepcionista.setIdRecepcionista(generatedKeys.getInt(1));
                return recepcionista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recepcionista actualizarRecepcionista(Recepcionista recepcionista) {
        // Método no implementado
        return null;
    }

    public void eliminarRecepcionista(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("DELETE FROM recepcionista WHERE id_recepcionista = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
