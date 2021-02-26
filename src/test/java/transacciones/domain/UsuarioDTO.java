package transacciones.domain;

public class UsuarioDTO {

    private int idUsuario;
    private String usuario;
    private String password;

    public UsuarioDTO() {}

    public UsuarioDTO(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioDTO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public UsuarioDTO(int idUsuario, String usuario, String password) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + this.idUsuario +
                ", usuario='" + this.usuario + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
