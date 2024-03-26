package backend.database;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class ConeccionMysql {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/gestor_paquetes?serverTimezon=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private Connection connection;

    public ConeccionMysql() {
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema desde connection: " + connection.getSchema());
            System.out.println("Esquema desde connection: " + connection.isClosed());

            Statement insertStatement = connection.createStatement();
            int result = insertStatement.executeUpdate(
              "create table usuario (" +
                      "id_usuario integer not null," +
                      "nombre varchar(50) not null," +
                      "nit varchar(15) not null," +
                      "constraint  PK_usuario primary key (id_usuario))"
            );

            Statement query = connection.createStatement();
            ResultSet resultQuery = query.executeQuery("SELECT * from administrador");
/*
            while (resultQuery.next()) {
                System.out.println("imprimir los datos de la tabla");
                System.out.println(resultQuery.getString("nombre columna"));
            }*/
            insertStatement.close();
            resultQuery.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException ex) {

                }
            }
        }
    }
}
