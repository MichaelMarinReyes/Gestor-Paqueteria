package servicios;

import clases.puntosdecontrorutaydestino.Paquete;
import clases.puntosdecontrorutaydestino.PuntoDeControl;
import clases.puntosdecontrorutaydestino.Ruta;
import database.ConexionDB;
import database.accionesrecepcionista.LocalizarPaqueteDao;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicioRecepcionista {
    private LocalizarPaqueteDao localizarPaqueteDao = new LocalizarPaqueteDao();

    public List<Paquete> obtenerPaquetes() {
        return localizarPaqueteDao.obtenerPaquetes();
    }


}
