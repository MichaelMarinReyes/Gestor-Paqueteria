package backend.clases.roles;

import backend.clases.rutaspuntosdecontrol.PuntoControl;

public class Operador extends Usuario{
    private PuntoControl puntoControAsignado;

    public Operador(int idUsuario, PuntoControl puntoControAsignado) {
        super(idUsuario);
        this.puntoControAsignado = puntoControAsignado;
    }

    public void registrarTiempoPaquete() {

    }
}
