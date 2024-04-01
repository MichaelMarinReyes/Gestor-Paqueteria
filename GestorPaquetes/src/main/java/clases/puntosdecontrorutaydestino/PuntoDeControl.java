package clases.puntosdecontrorutaydestino;

public class PuntoDeControl {
    private int idPuntoControl;
    private String nombre;
    private int idOperador;
    private int idPaquete;
    private int paquetesEnCola;
    private double tarifaOperacion;

    public PuntoDeControl(int idPuntoControl, String nombre, int idOperador, int idPaquete, int paquetesEnCola, double tarifaOperacion) {
        this.idPuntoControl = idPuntoControl;
        this.nombre = nombre;
        this.idOperador = idOperador;
        this.idPaquete = idPaquete;
        this.paquetesEnCola = paquetesEnCola;
        this.tarifaOperacion = tarifaOperacion;
    }

    public int getIdPuntoControl() {
        return idPuntoControl;
    }

    public void setIdPuntoControl(int idPuntoControl) {
        this.idPuntoControl = idPuntoControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public int getPaquetesEnCola() {
        return paquetesEnCola;
    }

    public void setPaquetesEnCola(int paquetesEnCola) {
        this.paquetesEnCola = paquetesEnCola;
    }

    public double getTarifaOperacion() {
        return tarifaOperacion;
    }

    public void setTarifaOperacion(double tarifaOperacion) {
        this.tarifaOperacion = tarifaOperacion;
    }
}
