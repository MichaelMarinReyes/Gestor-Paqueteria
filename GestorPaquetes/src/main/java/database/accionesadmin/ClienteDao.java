package database.accionesadmin;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import clases.roles.Cliente;
import database.ConexionDB;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public Cliente crearCliente(Cliente cliente) throws ExcepcionApi {
        if (cliente == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("El cliente proporcionado es nulo").build();
        }

        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into usuario (nit, nombre, apellido, contraseña, rol, estado_cuenta) values (?, ?, ?, ?, ?, ?);",
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getContraseña());
            preparedStatement.setString(5, cliente.getRol());
            preparedStatement.setString(6, cliente.getEstadoCuenta());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo crear el cliente");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setNit(generatedKeys.getString(1));
                    return cliente;
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el cliente");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el cliente: " + e.getMessage(), e);
        }
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from usuario;");
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
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente obtenerCliente(String nit) {
        try {
            PreparedStatement stmt = ConexionDB.getInstancia().conectar().prepareStatement("select * from usuario where nit = ?;");
            stmt.setString(1, nit);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNit(resultSet.getString("nit"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setContraseña(resultSet.getString("contraseña"));
                cliente.setRol(resultSet.getString("rol"));
                cliente.setEstadoCuenta(resultSet.getString("estado_cuenta"));
                System.out.println("contenido " + cliente);
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarCliente(Cliente cliente, String nitBuscado) {
        System.out.println(cliente.toString());
        String query = "update usuario set nit = ?, nombre = ?, apellido = ?, contraseña = ?, rol = ?, estado_cuenta = ? where nit = ?";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cliente.getNit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getContraseña());
            preparedStatement.setString(5, cliente.getRol());
            preparedStatement.setString(6, cliente.getEstadoCuenta());
            preparedStatement.setString(7, nitBuscado);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
