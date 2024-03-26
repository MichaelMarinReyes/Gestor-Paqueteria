package backend.clases.roles;

import backend.clases.rutaspuntosdecontrol.Ruta;

public class Administrador extends Usuario {
    private Cliente cliente;
    private Ruta ruta;
    private Operador operador;
    private double tarifaOperacionGlobal = 0.0;

    public Administrador(int idUsuario) {
        super(idUsuario);
    }

    public Administrador(int idUsuario, Cliente cliente, Ruta ruta, Operador operador, double tarifaOperacionGlobal) {
        super(idUsuario);
        this.cliente = cliente;
        this.ruta = ruta;
        this.operador = operador;
        this.tarifaOperacionGlobal = tarifaOperacionGlobal;
    }

    public void crearCliente() {

    }

    public void desactivarCliente() {

    }

    public void crearRuta() {

    }

    public void desactivarRuta() {

    }

    public void modificarTarifaGlobal(double tarifaGlobal) {

    }

    public void crearPuntoControl() {

    }

    public void desactivarPuntoControl() {

    }

    public void modificarDatosPuntoContro() {

    }

    public void modificarCantidadPaquetesCola() {

    }
}
