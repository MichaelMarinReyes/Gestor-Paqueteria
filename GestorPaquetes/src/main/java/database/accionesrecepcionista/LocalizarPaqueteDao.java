package database.accionesrecepcionista;

import clases.puntosdecontrorutaydestino.Paquete;
import clases.puntosdecontrorutaydestino.PuntoDeControl;
import database.ConexionDB;
import database.accionesadmin.PuntoDeControlDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LocalizarPaqueteDao {

    public Paquete crearPaquete(Paquete paqueteEntidad) throws ExcepcionApi {
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(
                    "insert into paquete (id_paquete, nit, estado_paquete, id_destino, id_punto_control, hora_entrada, hora_salida) values (?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setInt(1, paqueteEntidad.getIdPaquete());
            preparedStatement.setString(2, paqueteEntidad.getNit());
            preparedStatement.setString(3, paqueteEntidad.getEstadoPaquete());
            preparedStatement.setInt(4, paqueteEntidad.getIdDestino());
            preparedStatement.setInt(5, paqueteEntidad.getIdPuntoControl());
            LocalTime horaEntrada = paqueteEntidad.getHoraEntrada();

            if (horaEntrada != null) {
                Time horaEntradaSql = Time.valueOf(horaEntrada);
                preparedStatement.setTime(6, horaEntradaSql);
            } else {
                preparedStatement.setNull(6, Types.TIME);
            }

            LocalTime horaSalida = paqueteEntidad.getHoraSalida();
            if (horaSalida != null) {
                Time horaSalidaSql = Time.valueOf(horaSalida);
                preparedStatement.setTime(7, horaSalidaSql);
            } else {
                preparedStatement.setNull(7, Types.TIME);
            }
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                paqueteEntidad.setIdPaquete(generatedKeys.getInt(1));
                this.aumentarNumeroPaqueteEnCola(paqueteEntidad.getIdPuntoControl());
                return paqueteEntidad;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void actualizarPaquete(Paquete paquete, int idPaquete) {
        String query = "update paquete set id_paquete = ?, nit = ?, estado_paquete = ?, id_destino = ?, id_punto_control = ?, hora_entrada = ?, hora_salida = ? where id_paquete = ?";
        try (Connection connection = ConexionDB.getInstancia().conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paquete.getIdPaquete());
            preparedStatement.setString(2, paquete.getNit());
            preparedStatement.setString(3, paquete.getEstadoPaquete());
            preparedStatement.setInt(4, paquete.getIdDestino());
            preparedStatement.setInt(5, paquete.getIdPuntoControl());
            LocalTime horaEntrada = paquete.getHoraEntrada();
            if (horaEntrada != null) {
                Time horaEntradaSql = Time.valueOf(horaEntrada);
                preparedStatement.setTime(6, horaEntradaSql);
            } else {
                preparedStatement.setNull(6, Types.TIME);
            }

            LocalTime horaSalida = paquete.getHoraSalida();
            if (horaSalida != null) {
                Time horaSalidaSql = Time.valueOf(horaSalida);
                preparedStatement.setTime(7, horaSalidaSql);
            } else {
                preparedStatement.setNull(7, Types.TIME);
            }
            preparedStatement.setInt(8, idPaquete);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("La actualización del paquete no afectó ninguna fila en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error en dao" + e.getMessage());
        }
    }

    public void eliminarPaquete(int idPaquete) {
        int idPuntoControl= this.obtenerPaquete(idPaquete).getIdPuntoControl();
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement("delete from paquete where id_paquete = ?;");
            preparedStatement.setInt(1, idPaquete);
            preparedStatement.execute();

            disminuirNumeroPaquetesEnCola(idPuntoControl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void aumentarNumeroPaqueteEnCola(int idPuntoControl) {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        try {
            conexion = ConexionDB.getInstancia().conectar();
            PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();
            int paquetesEnCola = puntoDeControlDao.obtenerPuntoControl(idPuntoControl).getPaquetesEnCola();
            paquetesEnCola++;

            String sql = "update punto_de_control set paquetes_en_cola = ? where id_punto_control = ?";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, paquetesEnCola);
            preparedStatement.setInt(2, idPuntoControl);

            int filasActualizadas = preparedStatement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Se actualizó correctamente el número de paquetes_en_cola.");
            } else {
                System.out.println("No se realizó ninguna actualización.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el número de paquetes_en_cola.", e);
        }
    }

    private void disminuirNumeroPaquetesEnCola(int idPuntoControl) {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = ConexionDB.getInstancia().conectar();
            PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();
            int paquetesEnCola = puntoDeControlDao.obtenerPuntoControl(idPuntoControl).getPaquetesEnCola();

            if (paquetesEnCola > 0) {
                paquetesEnCola--;
            }

            String sql = "update punto_de_control set paquetes_en_cola = ? where id_punto_control = ?";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, paquetesEnCola);
            preparedStatement.setInt(2, idPuntoControl);

            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Se actualizó correctamente el número de paquetes_en_cola.");
            } else {
                System.out.println("No se realizó ninguna actualización.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el número de paquetes_en_cola.", e);
        }
    }
}