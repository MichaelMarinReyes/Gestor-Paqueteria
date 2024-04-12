package clases.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operador {
    private int idOperador;
    private String nombre;
    private String apellido;
    private String contraseña;
    private int idPuntoControl;
    private boolean sesionActiva;
    private String rol;
}
