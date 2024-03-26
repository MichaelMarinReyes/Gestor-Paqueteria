package backend.clases.rutaspuntosdecontrol;

public class Ruta {
    private int idRuta;
    private String destino;
    private PuntoControl puntoControl;
    private boolean activa;

    public Ruta(int idRuta, String destino, PuntoControl puntoControl, boolean activa) {
        this.idRuta = idRuta;
        this.destino = destino;
        this.puntoControl = puntoControl;
        this.activa = activa;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public PuntoControl getPuntoControl() {
        return puntoControl;
    }

    public void setPuntoControl(PuntoControl puntoControl) {
        this.puntoControl = puntoControl;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
