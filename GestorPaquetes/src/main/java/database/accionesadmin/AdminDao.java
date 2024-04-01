package database.accionesadmin;

import clases.roles.Cliente;
import database.ConexionDB;

import javax.sql.ConnectionPoolDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliente");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNit(resultSet.getString("nit"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setContraseña(resultSet.getString("contraseña"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente obtenerPorNit(String nit) {
        Cliente cliente = null;
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliente WHERE nit = ?");
        ) {
            preparedStatement.setString(1, nit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setNit(resultSet.getString("nit"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setApellido(resultSet.getString("apellido"));
                    cliente.setContraseña(resultSet.getString("contraseña"));
                    cliente.setEstadoCuenta(resultSet.getString("estado_cuenta"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public void insertarCliente(Cliente cliente) throws SQLException {
        Connection connection = ConexionDB.getInstancia().conectar();
        Statement statement = connection.createStatement();
        try {
            int result = statement.executeUpdate("insert into usuario (nit, nombre, apellido, contraseña, estado_cuenta) values " +
                    "('" + cliente.getNit() + "'," +
                    " '" + cliente.getNombre() + "'," +
                    " '" + cliente.getApellido() + "'," +
                    " '" + cliente.getContraseña() + "'," +
                    " '" + cliente.getEstadoCuenta() + "')");
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente " + e.getMessage());
        }
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        Connection connection = ConexionDB.getInstancia().conectar();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("update usuario set nit='" + cliente.getNit() + "' where nit='" + cliente.getNit() + "'");
        } catch (SQLException e) {
            System.out.println("Error al actualizar " + e.getMessage());
        }
    }

    public void eliminar(String nit) {
        Connection connection = ConexionDB.getInstancia().conectar();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from usuario where nit ='" + nit + "'");
            preparedStatement.setString(1, nit);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
    }
}
