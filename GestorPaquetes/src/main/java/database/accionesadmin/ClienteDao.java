package database.accionesadmin;

import clases.roles.Cliente;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClienteDao {
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cliente;");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.getNit();
                cliente.getNombre();
                cliente.getApellido();
                cliente.getContraseña();
                cliente.isSesionActiva();
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente obtenerClientePorId(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?;");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.getNit();
                cliente.getNombre();
                cliente.getApellido();
                cliente.getContraseña();
                cliente.isSesionActiva();
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente insertarCliente(Cliente cliente) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement(
                    "INSERT INTO cliente (nombre, apellido, contraseña, rol, sesion_activa, nit) VALUES (?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getContraseña());
            stmt.setString(4, cliente.getRol());
            stmt.setBoolean(5, cliente.isSesionActiva());
            stmt.setString(6, cliente.getNit());
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // No se suele asignar un ID autogenerado a un cliente
                // cliente.setIdCliente(generatedKeys.getInt(1));
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente actualizarCliente(Cliente cliente) {
        // Método no implementado
        return null;
    }

    public void eliminarCliente(int id) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("DELETE FROM cliente WHERE id_cliente = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
