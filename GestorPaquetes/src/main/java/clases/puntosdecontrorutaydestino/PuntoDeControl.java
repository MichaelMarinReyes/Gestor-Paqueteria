package clases.puntosdecontrorutaydestino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntoDeControl {
    private int idPuntoControl;
    private String nombre;
    private int idOperador;
    private int idPaquete;
    private int paquetesEnCola;
    private double tarifaOperacion;


}
