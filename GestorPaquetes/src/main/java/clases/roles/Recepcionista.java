package clases.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepcionista {
    private int idRecepcionista;
    private int idPaquete;
    private String nombre;
    private String apellido;
    private String contraseña;
    private boolean sesionActiva;
    private String rol;

}
