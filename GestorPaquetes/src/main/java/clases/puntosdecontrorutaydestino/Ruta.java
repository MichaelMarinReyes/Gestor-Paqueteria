package clases.puntosdecontrorutaydestino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {
    private int idRuta;
    private String nombreRuta;
    private int idDestino;

}
