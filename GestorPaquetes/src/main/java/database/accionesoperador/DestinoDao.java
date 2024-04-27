package database.accionesoperador;

import clases.puntosdecontrorutaydestino.Destino;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinoDao {
    public List<Destino> obtenerDestinos() {
        List<Destino> destinos = new ArrayList<>();

        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from destino");
            while (resultSet.next()) {
                Destino destino = new Destino();
                destino.setIdDestino(resultSet.getInt("id_destino"));
                destino.setNombre(resultSet.getString("nombre"));
                destino.setCuotaDestino(resultSet.getInt("cuota_destino"));
                destino.setPaquetesEnDestino(resultSet.getInt("paquetes_en_destino"));
                destinos.add(destino);
            }
            return destinos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Destino obtenerDestino(int idDestino) {
        Destino destino = null;
        String query = "select * from destino where id_destino = ?";
        try {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idDestino);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    destino = new Destino();
                    destino.setIdDestino(resultSet.getInt("id_destino"));
                    destino.setNombre(resultSet.getString("nombre"));
                    destino.setCuotaDestino(resultSet.getInt("cuota_destino"));
                    destino.setPaquetesEnDestino(resultSet.getInt("paquetes_en_destino"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return destino;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
