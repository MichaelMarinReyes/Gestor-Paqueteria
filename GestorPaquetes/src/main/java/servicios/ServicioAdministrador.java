package servicios;

import clases.puntosdecontrorutaydestino.*;
import database.accionesadmin.PuntoDeControlDao;
import database.accionesadmin.RutaDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.util.List;

public class ServicioAdministrador {
    private PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();
    private RutaDao rutaDao = new RutaDao();

    //CRUD PARA PUNTOS DE CONTROL
    public List<PuntoDeControl> obtenerPuntosDeControl() {
        return puntoDeControlDao.obtenerPuntosDeControl();
    }

    public PuntoDeControl crearPuntoControl(PuntoDeControl puntoDeControlEntidad) throws ExcepcionApi {
        if (puntoDeControlEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
        return puntoDeControlDao.crearPuntoControl(puntoDeControlEntidad);
    }

    public void eliminarPuntoControl(int idPuntoControl) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(idPuntoControl);

        if (puntoDeControl.getPaquetesEnCola() > 0) {
            puntoDeControlDao.eliminarPuntoControl(idPuntoControl);
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control tiene paquetes en cola").build();
        }
    }

    public void editarPuntoControl(PuntoDeControl puntoDeControlEntidad) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(puntoDeControlEntidad.getIdPuntoControl());
        if (puntoDeControl != null) {
            puntoDeControl.setNombre(puntoDeControlEntidad.getNombre());
            puntoDeControl.setIdOperador(puntoDeControlEntidad.getIdOperador());
            puntoDeControl.setIdPaquete(puntoDeControlEntidad.getIdPaquete());
            puntoDeControl.setPaquetesEnCola(puntoDeControlEntidad.getPaquetesEnCola());
            puntoDeControl.setTarifaOperacion(puntoDeControlEntidad.getTarifaOperacion());
            puntoDeControlDao.actualizarPuntoControl(puntoDeControl);
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
    }


    public PuntoDeControl obtenerPuntoControl(int id) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(id);
        if (puntoDeControl == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
        return puntoDeControl;
    }

    //CRUD PARA RUTAS
    public Ruta crearRuta(Ruta rutaEntidad) throws ExcepcionApi{
        if (rutaEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No se creó la ruta\n Datos faltantes").build();
        }
        return rutaDao.crearRuta(rutaEntidad);
    }

    public List<Ruta> obtenerRutas() {
        return rutaDao.obtenerRutas();
    }

    public void editarRuta(Ruta rutaEntidad) {
        Ruta ruta = rutaDao.obtenerRuta(rutaEntidad.getIdRuta());
        if (ruta != null) {
            ruta.setNombreRuta(rutaEntidad.getNombreRuta());
            ruta.setIdPuntoControl(rutaEntidad.getIdPuntoControl());
            ruta.setIdDestino(rutaEntidad.getIdDestino());
            rutaDao.actualizarRuta(ruta);
        }
    }

    public void eliminarRuta(int idRuta) {
        Ruta ruta = rutaDao.obtenerRuta(idRuta);
        rutaDao.eliminarRuta(idRuta);
    }
}
