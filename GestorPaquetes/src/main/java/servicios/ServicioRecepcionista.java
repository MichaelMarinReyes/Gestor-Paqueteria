package servicios;

import clases.puntosdecontrorutaydestino.Paquete;
import clases.puntosdecontrorutaydestino.PuntoDeControl;
import database.accionesrecepcionista.LocalizarPaqueteDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;
import java.util.List;

public class ServicioRecepcionista {
    private LocalizarPaqueteDao localizarPaqueteDao = new LocalizarPaqueteDao();

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
}
