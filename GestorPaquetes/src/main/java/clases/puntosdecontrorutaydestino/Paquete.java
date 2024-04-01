package clases.puntosdecontrorutaydestino;

public class Paquete {
    private int idPaquete;
    private String nit;
    private String estadoPaquete;
    private int puntoControlAcutal;
    private int idDestino;

    public Paquete(int idPaquete, String nit, String estadoPaquete, int puntoControlAcutal, int idDestino) {
        this.idPaquete = idPaquete;
        this.nit = nit;
        this.estadoPaquete = estadoPaquete;
        this.puntoControlAcutal = puntoControlAcutal;
        this.idDestino = idDestino;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEstadoPaquete() {
        return estadoPaquete;
    }

    public void setEstadoPaquete(String estadoPaquete) {
        this.estadoPaquete = estadoPaquete;
    }

    public int getPuntoControlAcutal() {
        return puntoControlAcutal;
    }

    public void setPuntoControlAcutal(int puntoControlAcutal) {
        this.puntoControlAcutal = puntoControlAcutal;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
}
