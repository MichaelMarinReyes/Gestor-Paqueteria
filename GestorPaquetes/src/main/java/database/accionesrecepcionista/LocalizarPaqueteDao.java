package database.accionesrecepcionista;

import clases.puntosdecontrorutaydestino.Paquete;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalizarPaqueteDao {

    public List<Paquete> obtenerPaquetes() {
        List<Paquete> puntosDeControl = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from paquete;");
            while (resultSet.next()) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete(resultSet.getInt("id_paquete"));
                paquete.setNit(resultSet.getString("nit"));
                paquete.setEstadoPaquete(resultSet.getString("estado_paquete"));
                paquete.setIdDestino(resultSet.getInt("id_destino"));
                paquete.setIdPuntoControl(resultSet.getInt("id_punto_control"));/*
                paquete.setHoraEntrada(resultSet.getDate("hora_entrada").toLocalDate().toString());
                paquete.setHoraSalida(resultSet.getDate("hora_salida").toLocalDate().toString());*/
                puntosDeControl.add(paquete);
            }
            return puntosDeControl;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paquete obtenerPaquete(int id) {
        Paquete paquete = null;
        String query = "select * from paquete where id_paquete = ?;";
        try {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paquete = new Paquete();
                    paquete.setIdPaquete(resultSet.getInt("id_paquete"));
                    paquete.setNit(resultSet.getString("nit"));
                    paquete.setIdDestino(resultSet.getInt("id_destino"));
                    paquete.setIdPuntoControl(resultSet.getInt("id_punto_control"));
/*
                    java.sql.Timestamp fechaEntrada = resultSet.getTimestamp("hora_entrada");
                    if (fechaEntrada != null) {
                        paquete.setHoraEntrada(fechaEntrada.toLocalDateTime().toLocalDate().toString());
                    }

                    java.sql.Timestamp fechaSalida = resultSet.getTimestamp("hora_salida");
                    if (fechaSalida != null) {
                        paquete.setHoraSalida(fechaSalida.toLocalDateTime().toLocalDate().toString());
                    }*/
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return paquete;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
