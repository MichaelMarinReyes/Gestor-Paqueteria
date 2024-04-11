package clases.puntosdecontrorutaydestino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {
    private int idPaquete;
    private String nit;
    private String estadoPaquete;
    private int idDestino;
    private int idPuntoControl;
    private String horaEntrada;
    private String horaSalida;
}
