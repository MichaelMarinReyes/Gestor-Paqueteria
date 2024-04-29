package clases.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteCliente {
    private String nit;
    private String nombre;
    private String apellido;
    private int paquetesEntregados;
}
