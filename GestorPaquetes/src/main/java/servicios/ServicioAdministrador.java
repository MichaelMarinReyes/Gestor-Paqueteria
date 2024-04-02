package servicios;

import clases.puntosdecontrorutaydestino.*;
import clases.roles.*;
import database.accionesadmin.AdminDao;
import database.accionesadmin.PuntoDeControlDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.util.List;

public class ServicioAdministrador {
    private PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();

    //CRUD PARA PUNTOS DE CONTROL
    public PuntoDeControl crearPuntoControl(PuntoDeControl puntoDeControlEntidad) throws ExcepcionApi {
        if (puntoDeControlEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
        return puntoDeControlDao.crearPuntoControl(puntoDeControlEntidad);
    }

    public void eliminarPuntoControl(int idPuntoControl) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControlPorId(idPuntoControl);

        if (puntoDeControl.getPaquetesEnCola() > 0) {
            puntoDeControlDao.eliminarPuntoControl(idPuntoControl);
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control tiene paquetes en cola").build();
        }
    }

    public void editarPuntoControl(int id) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControlPorId(id);
        if (puntoDeControl != null) {
            puntoDeControlDao.eliminarPuntoControl(puntoDeControl.getIdPuntoControl());
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
    }

    public PuntoDeControl obtenerPuntoControl(int id) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControlPorId(id);
        if (puntoDeControl == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
        return puntoDeControl;
    }

    //CRUD PARA RUTAS

}
