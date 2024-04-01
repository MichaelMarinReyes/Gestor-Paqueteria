package clases.puntosdecontrorutaydestino;

public class Destino {
    private int idDestino;
    private String nombre;
    private double cuotaDestino;

    public Destino(int idDestino, String nombre, double cuotaDestino) {
        this.idDestino = idDestino;
        this.nombre = nombre;
        this.cuotaDestino = cuotaDestino;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCuotaDestino() {
        return cuotaDestino;
    }

    public void setCuotaDestino(double cuotaDestino) {
        this.cuotaDestino = cuotaDestino;
    }
}
