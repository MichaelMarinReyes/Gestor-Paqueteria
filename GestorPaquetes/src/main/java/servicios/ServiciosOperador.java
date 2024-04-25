package servicios;

import clases.puntosdecontrorutaydestino.Destino;
import clases.puntosdecontrorutaydestino.PuntoDeControl;
import database.accionesadmin.PuntoDeControlDao;
import database.accionesoperador.DestinoDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.util.List;

public class ServiciosOperador {
    private DestinoDao destinoDao = new DestinoDao();
    private PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();

    public List<Destino> obtenerDestinos() {
        return destinoDao.obtenerDestinos();
    }

    public Destino obtenerDestino(int idDestino) throws ExcepcionApi {
        Destino destino = destinoDao.obtenerDestino(idDestino);
        if (destino == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No se encontró ese destino").build();
        }
        return destino;
    }

    public List<PuntoDeControl> obtenerPuntosDeControl(int idOperador) throws ExcepcionApi {
        return puntoDeControlDao.obtenerPuntoControlDeOperador(idOperador);
    }
}
