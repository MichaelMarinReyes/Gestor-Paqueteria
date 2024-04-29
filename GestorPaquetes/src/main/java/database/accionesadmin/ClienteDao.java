package database.accionesadmin;

import clases.roles.Cliente;
import database.ConexionDB;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public Cliente crearCliente(Cliente cliente) throws ExcepcionApi {
        try {
            if (cliente == null) {
                throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("El cliente proporcionado es nulo").build();
            }

            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("insert into usuario (nit, nombre, apellido, contraseña, rol, estado_cuenta) values (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getContraseña());
            preparedStatement.setString(5, cliente.getRol());
            preparedStatement.setString(6, cliente.getEstadoCuenta());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                cliente.setNit(resultSet.getString(1));
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error en DAO " + e.getMessage());
        }
        return null;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("select * from usuario;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNit(resultSet.getString("nit"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setContraseña(resultSet.getString("contraseña"));
                cliente.setRol(resultSet.getString("rol"));
                cliente.setEstadoCuenta(resultSet.getString("estado_cuenta"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente obtenerCliente(String nit) {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("select * from usuario where nit = ?;");
            preparedStatement.setString(1, nit);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNit(resultSet.getString("nit"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setContraseña(resultSet.getString("contraseña"));
                cliente.setRol(resultSet.getString("rol"));
                cliente.setEstadoCuenta(resultSet.getString("estado_cuenta"));
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarCliente(Cliente cliente, String nitBuscado) {
        String query = "update usuario set nit = ?, nombre = ?, apellido = ?, contraseña = ?, rol = ?, estado_cuenta = ? where nit = ?";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getContraseña());
            preparedStatement.setString(5, cliente.getRol());
            preparedStatement.setString(6, cliente.getEstadoCuenta());
            preparedStatement.setString(7, nitBuscado);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en dao" + e.getMessage());
        }
    }

    public void eliminarCliente(String nit) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("delete from usuario where nit = ?;");
            stmt.setString(1, nit);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
