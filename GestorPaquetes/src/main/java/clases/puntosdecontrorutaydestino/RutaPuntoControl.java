package clases.puntosdecontrorutaydestino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutaPuntoControl {
    private int idRuta;
    private String nombreRuta;
    private int idPuntoControl;
    private String nombrePuntoControl;
}
