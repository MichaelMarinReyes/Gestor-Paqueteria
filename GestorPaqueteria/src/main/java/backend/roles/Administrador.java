package backend.roles;

import backend.rutaspuntosdecontrol.Ruta;

public class Administrador extends Usuario {
    private Ruta ruta;
    private Operador operador;
    private double tarifaOperador;

    public Administrador(Ruta ruta, Operador operador, double tarifaOperador) {
        this.ruta = ruta;
        this.operador = operador;
        this.tarifaOperador = tarifaOperador;
    }
}
