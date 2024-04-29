package database.accionesadmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.ConexionDB;
import clases.puntosdecontrorutaydestino.PuntoDeControl;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

public class PuntoDeControlDao {

    public List<PuntoDeControl> obtenerPuntosDeControl() {
        List<PuntoDeControl> puntosDeControl = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("select * from punto_de_control");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PuntoDeControl puntoDeControl = new PuntoDeControl();
                puntoDeControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                puntoDeControl.setNombre(resultSet.getString("nombre"));
                puntoDeControl.setIdOperador(resultSet.getInt("id_operador"));
                puntoDeControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                puntoDeControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                puntoDeControl.setMaximaEnCola(resultSet.getInt("maxima_en_cola"));
                puntoDeControl.setEstado(resultSet.getString("estado"));
                puntosDeControl.add(puntoDeControl);
            }
            return puntosDeControl;
        } catch (SQLException e) {
            e.printStackTrace();
            /// throw new RuntimeException(e.getMessage());
        }
        return puntosDeControl;
    }

    public PuntoDeControl obtenerPuntoControl(int id) {
        PuntoDeControl puntoControl = null;
        String query = "select * from punto_de_control where id_punto_control = ?;";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    puntoControl = new PuntoDeControl();
                    puntoControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                    puntoControl.setNombre(resultSet.getString("nombre"));

                    puntoControl.setIdOperador(resultSet.getInt("id_operador"));
                    puntoControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                    puntoControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                    puntoControl.setMaximaEnCola(resultSet.getInt("maxima_en_cola"));
                    puntoControl.setEstado(resultSet.getString("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puntoControl;
    }


    public PuntoDeControl crearPuntoControl(PuntoDeControl puntoControl) throws ExcepcionApi {
        String query = "insert into punto_de_control (nombre, id_operador, paquetes_en_cola, tarifa_operacion, maxima_en_cola, estado) values (?, ?, ?, ?, ?, ?)";

        try {
            if (puntoControl == null) {
                throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("El punto de control proporcionado es nulo").build();
            }

            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setString(1, puntoControl.getNombre());

            if (puntoControl.getIdOperador() == 0) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, puntoControl.getIdOperador());
            }

            preparedStatement.setInt(3, puntoControl.getPaquetesEnCola());
            preparedStatement.setDouble(4, puntoControl.getTarifaOperacion());
            preparedStatement.setInt(5, puntoControl.getMaximaEnCola());
            preparedStatement.setString(6, puntoControl.getEstado());

            boolean rowsAffected = preparedStatement.execute();
            if (rowsAffected) {
                throw new SQLException("No se pudo crear el punto de control");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el punto de control: " + e.getMessage(), e);
        }
        return puntoControl;
    }


    public void actualizarPuntoControl(PuntoDeControl puntoControl) {
        String query = "update punto_de_control set nombre = ?, id_operador = ?, paquetes_en_cola = ?, tarifa_operacion = ?, maxima_en_cola = ?, estado = ? where id_punto_control = ?";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setString(1, puntoControl.getNombre());

            if (puntoControl.getIdOperador() == 0) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, puntoControl.getIdOperador());
            }

            preparedStatement.setInt(3, puntoControl.getPaquetesEnCola());
            preparedStatement.setDouble(4, puntoControl.getTarifaOperacion());
            preparedStatement.setDouble(5, puntoControl.getMaximaEnCola());
            preparedStatement.setString(6, puntoControl.getEstado());
            preparedStatement.setInt(7, puntoControl.getIdPuntoControl());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarPuntoControl(int id) {
        String query = "delete from punto_de_control where id_punto_control = ?;";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PuntoDeControl> obtenerPuntoControlDeOperador(int idOperador) {
        List<PuntoDeControl> puntoControl = new ArrayList<>();
        String query = "select * from punto_de_control where id_operador = ?;";
        try {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idOperador);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PuntoDeControl puntoDeControl = new PuntoDeControl();
                    puntoDeControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                    puntoDeControl.setNombre(resultSet.getString("nombre"));
                    puntoDeControl.setIdOperador(resultSet.getInt("id_operador"));
                    puntoDeControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                    puntoDeControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                    puntoDeControl.setMaximaEnCola(resultSet.getInt("maxima_en_cola"));
                    puntoDeControl.setEstado(resultSet.getString("estado"));
                    puntoControl.add(puntoDeControl);
                }
                return puntoControl;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
