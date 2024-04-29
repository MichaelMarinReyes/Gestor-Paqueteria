package database;

import java.sql.*;

public final class ConexionDB {
    private static ConexionDB instancia;
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/gestor_paquetes?serverTimezon=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private Connection conexion;

    private ConexionDB() {
    }

    public static ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    /**
     * Permite crear la conexión a la base de datos.
     *
     * @return la conexión a la base de datos.
     */
    public Connection conectar() {
        try {
            if (conexion == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
                /*
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conexion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);*/
            }
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error en Claas.forName: " + e.getMessage());
        }
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        } finally {
            conexion.close();
        }
    }
}
