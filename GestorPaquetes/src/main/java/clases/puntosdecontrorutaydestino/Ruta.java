package clases.puntosdecontrorutaydestino;

public class Ruta {
    private int idRuta;
    private String nombreRuta;
    private int idPuntoControl;
    private int idDestino;

    public Ruta(int idRuta, String nombreRuta, int idPuntoControl, int idDestino) {
        this.idRuta = idRuta;
        this.nombreRuta = nombreRuta;
        this.idPuntoControl = idPuntoControl;
        this.idDestino = idDestino;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public int getIdPuntoControl() {
        return idPuntoControl;
    }

    public void setIdPuntoControl(int idPuntoControl) {
        this.idPuntoControl = idPuntoControl;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
}
