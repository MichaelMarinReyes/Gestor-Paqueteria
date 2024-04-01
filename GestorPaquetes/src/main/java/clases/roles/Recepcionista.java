package clases.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepcionista extends Usuario{
    private int idRecepcionista;
    private int idPaquete;

}
