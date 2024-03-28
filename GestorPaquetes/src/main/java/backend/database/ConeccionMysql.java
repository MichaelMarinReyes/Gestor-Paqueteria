package backend.database;

import java.sql.*;

public class ConeccionMysql {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/gestor_paquetes?serverTimezon=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private Connection conexion;

    public ConeccionMysql() {
        try {
            conexion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permite crear la conexión a la base de datos.
     *
     * @return la conexión a la base de datos.
     */
    public Connection conectar() {
        return conexion;
    }


    /**
     * Desconecta a la base de datos.
     */
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos: " + e.getMessage());
        }
    }
}
