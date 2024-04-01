package clases.puntosdecontrorutaydestino;

public class Bodega {
    private int idTransaccion;
    private int idPaquete;
    private int idRuta;

    public Bodega(int idTransaccion, int idPaquete, int idRuta) {
        this.idTransaccion = idTransaccion;
        this.idPaquete = idPaquete;
        this.idRuta = idRuta;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }
}
