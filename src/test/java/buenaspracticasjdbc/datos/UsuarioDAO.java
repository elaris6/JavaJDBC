package buenaspracticasjdbc.datos;

import buenaspracticasjdbc.domain.UsuarioDTO;

import java.util.List;

public interface UsuarioDAO {

    public List<UsuarioDTO> seleccionar();

    public int insertar(UsuarioDTO usuarioDTO);

    public int eliminar(UsuarioDTO usuarioDTO);

    public int actualizar(UsuarioDTO usuarioDTO);

}
