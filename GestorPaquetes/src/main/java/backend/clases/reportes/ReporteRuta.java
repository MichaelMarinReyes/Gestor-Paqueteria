package backend.clases.reportes;

import backend.clases.rutaspuntosdecontrol.Ruta;

public class ReporteRuta {
    private Ruta ruta;
    private int cantidadPaquetes;
    private boolean estadoRuta = false;

    public ReporteRuta(Ruta ruta, int cantidadPaquetes, boolean estadoRuta) {
        this.ruta = ruta;
        this.cantidadPaquetes = cantidadPaquetes;
        this.estadoRuta = estadoRuta;
    }

    public void mostrarPaquetesActualmenteEnRuta() {

    }

    public void mostrarPaquetesFueraDeRuta() {

    }

    public void mostrarEstadoRuta() {

    }
}
