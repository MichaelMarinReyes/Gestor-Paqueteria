package database.accionesrecepcionista;

import clases.puntosdecontrorutaydestino.Paquete;
import database.ConexionDB;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DestinoPaqueteDao {

    public List<Paquete> obtenerPaquetesEntregados(){
        List<Paquete> puntosDeControl = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from paquete where estado_paquete = 'Entregado';");
            while (resultSet.next()) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete(resultSet.getInt("id_paquete"));
                paquete.setNit(resultSet.getString("nit"));
                paquete.setEstadoPaquete(resultSet.getString("estado_paquete"));
                paquete.setIdDestino(resultSet.getInt("id_destino"));
                paquete.setIdPuntoControl(resultSet.getInt("id_punto_control"));

                Time horaEntradaSql = resultSet.getTime("hora_entrada");
                if (horaEntradaSql != null) {
                    LocalTime horaEntrada = horaEntradaSql.toLocalTime();
                    paquete.setHoraEntrada(horaEntrada);
                } else {
                    paquete.setHoraEntrada(null);
                }

                Time horaSalidaSql = resultSet.getTime("hora_salida");
                if (horaSalidaSql != null) {
                    LocalTime horaSalida = horaSalidaSql.toLocalTime();
                    paquete.setHoraSalida(horaSalida);
                } else {
                    paquete.setHoraSalida(null);
                }

                puntosDeControl.add(paquete);
            }
            return puntosDeControl;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paquete paqueteEntregado(int id) {
        Paquete paquete = null;
        String query = "select * from paquete where id_paquete = ? and estado_paquete = 'Entregado';";
        try {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paquete = new Paquete();
                    paquete.setIdPaquete(resultSet.getInt("id_paquete"));
                    paquete.setNit(resultSet.getString("nit"));
                    paquete.setEstadoPaquete(resultSet.getString("estado_paquete"));
                    paquete.setIdDestino(resultSet.getInt("id_destino"));
                    paquete.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                    Time horaEntradaSql = resultSet.getTime("hora_entrada");
                    if (horaEntradaSql != null) {
                        LocalTime horaEntrada = horaEntradaSql.toLocalTime();
                        paquete.setHoraEntrada(horaEntrada);
                    } else {
                        paquete.setHoraEntrada(null);
                    }

                    Time horaSalidaSql = resultSet.getTime("hora_salida");
                    if (horaSalidaSql != null) {
                        LocalTime horaSalida = horaSalidaSql.toLocalTime();
                        paquete.setHoraSalida(horaSalida);
                    } else {
                        paquete.setHoraSalida(null);
                    }
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
