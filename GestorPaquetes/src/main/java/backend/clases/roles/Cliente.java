package backend.clases.roles;

public class Cliente extends Usuario{
    private String nit;

    public Cliente(int idUsuario, String nit) {
        super(idUsuario);
        this.nit = nit;
    }
}
