package servicios;

import clases.puntosdecontrorutaydestino.Paquete;
import clases.puntosdecontrorutaydestino.PuntoDeControl;
import database.accionesadmin.ClienteDao;
import database.accionesadmin.PuntoDeControlDao;
import database.accionesrecepcionista.DestinoPaqueteDao;
import database.accionesrecepcionista.LocalizarPaqueteDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;
import java.util.List;

public class ServicioRecepcionista {
    private LocalizarPaqueteDao localizarPaqueteDao = new LocalizarPaqueteDao();
    private ClienteDao clienteDao = new ClienteDao();
    private DestinoPaqueteDao destinoPaqueteDao = new DestinoPaqueteDao();

    public List<Paquete> obtenerPaquetes() {
        return localizarPaqueteDao.obtenerPaquetes();
    }

    public Paquete insertarPaquete(Paquete paqueteEntidad) throws ExcepcionApi {
        int idPaquete = paqueteEntidad.getIdPaquete();
        if (paqueteEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("No se proporcionó paquete").build();
        }

        if (localizarPaqueteDao.obtenerPaquete(idPaquete) != null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("Ya existe un paquete con ese id").build();
        }

        if (clienteDao.obtenerCliente(paqueteEntidad.getNit()) == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No existe un cliente con ese nit, debe registrarlo primero").build();
        }

        PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(paqueteEntidad.getIdPuntoControl());

        if (puntoDeControl != null) {
            int paquetesEnCola = puntoDeControl.getPaquetesEnCola();
            int maximaEnCola = puntoDeControl.getMaximaEnCola();

            if (paquetesEnCola >= maximaEnCola) {
                throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("Este punto de control ya no tiene más espacio de almacenaje").build();
            }
        }

        return localizarPaqueteDao.crearPaquete(paqueteEntidad);
    }

    public void actualizarPaquete(Paquete paqueteEntidad, int idPaquete) throws ExcepcionApi {
        Paquete paquete = localizarPaqueteDao.obtenerPaquete(paqueteEntidad.getIdPaquete());
        if (paquete != null) {
            paquete.setIdPaquete(paqueteEntidad.getIdPaquete());
            paquete.setNit(paqueteEntidad.getNit());
            paquete.setEstadoPaquete(paqueteEntidad.getEstadoPaquete());
            paquete.setIdDestino(paqueteEntidad.getIdDestino());
            paquete.setIdPuntoControl(paqueteEntidad.getIdPuntoControl());
            paquete.setHoraEntrada(paqueteEntidad.getHoraEntrada());
            paquete.setHoraSalida(paqueteEntidad.getHoraSalida());
            localizarPaqueteDao.actualizarPaquete(paquete, idPaquete);
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No existe un paquete con ese id").build();
        }
    }

    public void eliminarPaquete(int idPaquete) {
        Paquete paquete = localizarPaqueteDao.obtenerPaquete(idPaquete);
        if (paquete != null) {
            localizarPaqueteDao.eliminarPaquete(paquete.getIdPaquete());
        }
    }

    //LISTADO DE PAQUETES EN EL DESTINO
    public List<Paquete> obtenerPaquetesEntregados() {
        return destinoPaqueteDao.obtenerPaquetesEntregados();
    }
}
