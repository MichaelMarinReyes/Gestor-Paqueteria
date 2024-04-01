package clases.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String nombre;
    private String apellido;
    private String contraseña;
    private String rol;
    private boolean sesionActiva;

}
