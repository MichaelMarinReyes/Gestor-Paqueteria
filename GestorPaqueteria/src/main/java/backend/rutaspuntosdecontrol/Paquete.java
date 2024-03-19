package backend.rutaspuntosdecontrol;

import backend.roles.Cliente;

import java.time.LocalDate;

public class Paquete {
    private int id;
    private double peso;
    private String destino;
    private Cliente cliente;
    private LocalDate tiempoEnPuntoDecontrol;
}
