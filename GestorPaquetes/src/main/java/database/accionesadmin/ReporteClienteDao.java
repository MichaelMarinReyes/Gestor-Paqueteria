package database.accionesadmin;

import clases.reportes.ReporteCliente;
import database.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReporteClienteDao {

    public List<ReporteCliente> obtenerReporteClientes() {
        List<ReporteCliente> reporteClientes = new ArrayList<>();
        String query = "select p.nit, u.nombre, u.apellido, count(*) as 'paquetes_entregados' from paquete p join usuario u on p.nit = u.nit where p.estado_paquete = 'Entregado' group by p.nit, u.nombre, u.apellido;";
        try {
            PreparedStatement preparedStatement = ConexionDB.getInstancia().conectar().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReporteCliente reporteCliente = new ReporteCliente();
                reporteCliente.setNit(resultSet.getString("nit"));
                reporteCliente.setNombre(resultSet.getString("nombre"));
                reporteCliente.setApellido(resultSet.getString("apellido"));
                reporteCliente.setPaquetesEntregados(resultSet.getInt("paquetes_entregados"));
                reporteClientes.add(reporteCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reporteClientes;
    }

}
