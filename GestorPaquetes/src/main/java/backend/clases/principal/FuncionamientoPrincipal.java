package backend.clases.principal;

import backend.clases.roles.*;
import backend.database.ConeccionMysql;

import java.sql.*;

public class FuncionamientoPrincipal {
    private Administrador administrador;
    private Operador operador;
    private Recepcionista recepcionista;
    private Cliente cliente;
    private ConeccionMysql conexion = new ConeccionMysql();

    public FuncionamientoPrincipal() {

    }

    /**
     * Muestra el listado de clientes.
     */
    public void mostrarClientes() {
        Connection connection = conexion.conectar();
        PreparedStatement preparedStatement = null;
        try {
            Statement insertStatement = connection.createStatement();

            Statement query = connection.createStatement();
            ResultSet resultQuery = query.executeQuery("SELECT * from usuario;");


            System.out.println("imprimir los datos de la tabla");
            System.out.println("Nit        Nombre      Apellido        Contraseña");
            while (resultQuery.next()) {
                System.out.println(resultQuery.getString("nit") + "       " + resultQuery.getString("nombre") + "     " + resultQuery.getString("apellido") + "  " + resultQuery.getString("contraseña"));
            }
            insertStatement.close();
            resultQuery.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.out.println("ERROR al mostrar clientes " + e.getMessage());
        }
    }

    /**
     * Crea un nuevo cliente y lo guarda en la base de datos.
     *
     * @param nit        representa el nit del cliente.
     * @param nombre     representa el nombre del cliente.
     * @param apellido   representa el apellido del cliente.
     * @param contraseña representa la contraseña del cliente.
     */
    public void crearClientes(String nit, String nombre, String apellido, String contraseña) {
        Connection connection = conexion.conectar();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into usuario (nit, nombre, apellido, contraseña) values (" + nit + ", " + nombre + ", " + apellido + ", " + contraseña);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Sirve para editar los datos de un cliente.
     *
     * @param nit es el parámetro para encontrar el cliente que coincida.
     */
    public void editarCliente(String nit) {
        Connection connection = conexion.conectar();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update usuario set nombre = '" + nit + "' where nit = '" + nit + "';");
        } catch (SQLException e) {
            System.out.println("Error al editar cliente " + e.getMessage());
        }
    }

    /**
     * Sirve para crear un operador.
     */
    public void crearOperadores(String idPuntocontrol, String nombre, String apellido, String contraseña) {
        Connection connection = conexion.conectar();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into operador (id_punto_control, nombre, apellido, contraseña) values (" + idPuntocontrol + ", " + nombre + ", " + apellido + ", " + contraseña + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Inserta un nuevo operador creado por el administrador.
     *
     * @param idDestino    es el identificador del destino.
     * @param nombre       es el nombre del destino.
     * @param cuotaDestino es la cuota fijada por el administrador.
     */
    public void crearDestino(int idDestino, String nombre, double cuotaDestino) {
        Connection connection = conexion.conectar();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into destino (id_destino, nombre, cuota_destino) values (" + idDestino + ", " + nombre + ", " + cuotaDestino + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Verifica el usuario y contraseña del cliente
     *
     * @return
     */
    public boolean verificarUsuario(String nit, String contraseña) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = conexion.conectar();
            if (connection != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");

                String sql = "SELECT * FROM usuario WHERE nit = ? AND contraseña = ?";
                System.out.println("SQL generado: " + sql); // Agregar este mensaje para verificar el SQL generado

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nit);
                preparedStatement.setString(2, contraseña);
                resultSet = preparedStatement.executeQuery();

                // Si se encuentra al menos una fila, las credenciales son válidas
                if (resultSet.next()) {
                    System.out.println("Credenciales válidas para el usuario con nit: " + nit);
                    return true;
                } else {
                    System.out.println("Credenciales inválidas para el usuario con nit: " + nit);
                }
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            conexion.desconectar();
        }
        return false;
    }

}
