package backend.clases.rutaspuntosdecontrol;

public class PuntoControl {
    private int id;
    private Destino destino;
    private double tarifaOperacion;
    private int capacidadCola;
    private Ruta ruta;

    public PuntoControl(int id, Destino destino, double tarifaOperacion, int capacidadCola, Ruta ruta) {
        this.id = id;
        this.destino = destino;
        this.tarifaOperacion = tarifaOperacion;
        this.capacidadCola = capacidadCola;
        this.ruta = ruta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public double getTarifaOperacion() {
        return tarifaOperacion;
    }

    public void setTarifaOperacion(double tarifaOperacion) {
        this.tarifaOperacion = tarifaOperacion;
    }

    public int getCapacidadCola() {
        return capacidadCola;
    }

    public void setCapacidadCola(int capacidadCola) {
        this.capacidadCola = capacidadCola;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
