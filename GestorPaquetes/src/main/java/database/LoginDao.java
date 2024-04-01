package database;

import java.sql.*;

public class LoginDao {

    public boolean verificarCredenciales(String usuario, String contraseña, String tipoUsuario) {
        String queryAdmin = "SELECT * FROM administrador WHERE id_administrador = ? AND contraseña = ?";
        String queryOperador = "SELECT * FROM operador WHERE id_operador = ? AND contraseña = ?";
        String queryRecepcionista = "SELECT * FROM recepcionista WHERE id_recepcionista = ? AND contraseña = ?";

        try {
            Connection connection = ConexionDB.getInstancia().conectar();

            if (tipoUsuario.equalsIgnoreCase("administrador")) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryAdmin);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            } else if (tipoUsuario.equalsIgnoreCase("Operador")) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryOperador);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();

                }
            } else if (tipoUsuario.equalsIgnoreCase("Recepcionista")) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryRecepcionista);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("entro recepcionista");
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
