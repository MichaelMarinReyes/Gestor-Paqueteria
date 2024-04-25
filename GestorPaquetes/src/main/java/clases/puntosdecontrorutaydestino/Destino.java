package clases.puntosdecontrorutaydestino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destino {
    private int idDestino;
    private String nombre;
    private double cuotaDestino;
    private int paquetesEnDestino;
}
