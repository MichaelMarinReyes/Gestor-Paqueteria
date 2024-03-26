package backend.clases.rutaspuntosdecontrol;

import backend.clases.roles.Cliente;

import java.util.Date;

public class Paquete {
    private int id;
    private double peso;
    private String destino;
    private Cliente cliente;
    private Date fechaEnPuntoControl;

    public Paquete(int id, double peso, String destino, Cliente cliente, Date fechaEnPuntoControl) {
        this.id = id;
        this.peso = peso;
        this.destino = destino;
        this.cliente = cliente;
        this.fechaEnPuntoControl = fechaEnPuntoControl;
    }

    public void calcularPrecio() {

    }
}
