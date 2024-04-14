package database;

import java.sql.*;

public class LoginDao {

    public boolean verificarCredenciales(String usuario, String contraseña, String tipoUsuario) {
        String queryAdmin = "SELECT * FROM administrador WHERE id_administrador = ? AND contraseña = ?";
        String queryOperador = "SELECT * FROM operador WHERE id_operador = ? AND contraseña = ?";
        String queryRecepcionista = "SELECT * FROM recepcionista WHERE id_recepcionista = ? AND contraseña = ?";

        try {
            Connection connection = ConexionDB.getInstancia().conectar();

            if (tipoUsuario.equalsIgnoreCase("admin")) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryAdmin);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            } else if (tipoUsuario.equalsIgnoreCase("operador")) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryOperador);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            } else if (tipoUsuario.equalsIgnoreCase("recepcionista")) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryRecepcionista);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
