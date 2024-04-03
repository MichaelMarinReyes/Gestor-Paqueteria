package database.accionesadmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.ConexionDB;
import clases.puntosdecontrorutaydestino.PuntoDeControl;

public class PuntoDeControlDao {

    public List<PuntoDeControl> obtenerPuntosDeControl() {
        List<PuntoDeControl> puntosDeControl = new ArrayList<>();
        try {
            Statement statement = ConexionDB.getInstancia().conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from punto_de_control;");
            while (resultSet.next()) {
                PuntoDeControl puntoDeControl = new PuntoDeControl();
                puntoDeControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                puntoDeControl.setNombre(resultSet.getString("nombre"));
/*
                Statement statementNombre = ConexionDB.getInstancia().conectar().createStatement();
                ResultSet resultSetNombre = statementNombre.executeQuery("select nombre from operador;");
                String nombre = " ";

                if (resultSetNombre.getString("id_operador") != null || (resultSetNombre.getString("id_operador").equals("0"))) {
                    nombre = resultSet.getString("id_operador");
                }
                puntoDeControl.setNombreOperador(nombre);*/
                puntoDeControl.setIdPaquete(resultSet.getInt("id_paquete"));
                puntoDeControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                puntoDeControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                puntosDeControl.add(puntoDeControl);
            }
            return puntosDeControl;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PuntoDeControl obtenerPuntoControl(int id) {
        PuntoDeControl puntoControl = null;
        String query = "select * from punto_de_control where id_punto_control = ?;";
        try {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    puntoControl = new PuntoDeControl();
                    puntoControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                    puntoControl.setNombre(resultSet.getString("nombre"));
/*
                    Statement statementNombre = ConexionDB.getInstancia().conectar().createStatement();
                    ResultSet resultSetNombre = statementNombre.executeQuery("select nombre from punto_de_control;");
                    String nombre = " ";
                    if (resultSetNombre.getString("id_operador") != null) {
                        nombre = resultSetNombre.getString("id_operador");
                    }
                    puntoControl.setNombreOperador(nombre);
                    puntoControl.setNombreOperador(resultSetNombre.);*/
                    puntoControl.setIdPaquete(resultSet.getInt("id_paquete"));
                    puntoControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                    puntoControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puntoControl;
    }


    public PuntoDeControl crearPuntoControl(PuntoDeControl puntoControl) {
        String query = "insert into punto_de_control (nombre, id_operador, id_paquete, paquetes_en_cola, tarifa_operacion) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, puntoControl.getNombre());
            preparedStatement.setInt(2, puntoControl.getIdOperador());
            preparedStatement.setInt(3, puntoControl.getIdPaquete());
            preparedStatement.setInt(4, puntoControl.getPaquetesEnCola());
            preparedStatement.setDouble(5, puntoControl.getTarifaOperacion());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                puntoControl.setIdPuntoControl(resultSet.getInt(1));
                return puntoControl;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PuntoDeControl obtenerPuntoControlPorId(int id) {
        System.out.println(id);
        PuntoDeControl puntoControl = null;
        String query = "select * from punto_de_control where id_punto_control = = ?";
        try  {
            Connection connection = ConexionDB.getInstancia().conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    puntoControl = new PuntoDeControl();
                    System.out.println(puntoControl);
                    puntoControl.setIdPuntoControl(resultSet.getInt("id_punto_control"));
                    puntoControl.setNombre(resultSet.getString("nombre"));
                    puntoControl.setIdOperador(resultSet.getInt("id_operador"));
                    puntoControl.setIdPaquete(resultSet.getInt("id_paquete"));
                    puntoControl.setPaquetesEnCola(resultSet.getInt("paquetes_en_cola"));
                    puntoControl.setTarifaOperacion(resultSet.getDouble("tarifa_operacion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puntoControl;
    }

    public void actualizarPuntoControl(PuntoDeControl puntoControl) {
        String query = "UPDATE punto_de_control SET nombre = ?, id_operador = ?, id_paquete = ?, paquetes_en_cola = ?, tarifa_operacion = ? WHERE id_punto_control = ?";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, puntoControl.getNombre());
            preparedStatement.setInt(2, puntoControl.getIdOperador());
            preparedStatement.setInt(3, puntoControl.getIdPaquete());
            preparedStatement.setInt(4, puntoControl.getPaquetesEnCola());
            preparedStatement.setDouble(5, puntoControl.getTarifaOperacion());
            preparedStatement.setInt(6, puntoControl.getIdPuntoControl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPuntoControl(int id) {
        String query = "DELETE FROM punto_de_control WHERE id_punto_control = ?;";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
