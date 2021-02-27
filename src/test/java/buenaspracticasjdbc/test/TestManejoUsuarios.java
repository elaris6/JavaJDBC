package buenaspracticasjdbc.test;

import buenaspracticasjdbc.datos.UsuarioDAO;
import buenaspracticasjdbc.datos.UsuarioDAOJDBC;
import buenaspracticasjdbc.domain.UsuarioDTO;

import java.util.List;

public class TestManejoUsuarios {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAOJDBC();

        // Insertar usuarios
        UsuarioDTO uIns = new UsuarioDTO("nata","g5690jy045tik3");
        System.out.println("Registros insertados: "+usuarioDAO.insertar(uIns));

        // Eliminar usuarios
        //UsuarioDTO uDel = new UsuarioDTO(4);
        //System.out.println("Registros eliminados: "+ usuarioDAO.eliminar(uDel));

        // Actualizar usuarios
        //UsuarioDTO uUpd = new UsuarioDTO(1,"elaris6","1234567890");
        //System.out.println("Registros actualizados: "+ usuarioDAO.actualizar(uUpd));

        imprimirUsuarios();
    }

    public static void imprimirUsuarios(){

        UsuarioDAO usuarioDAO = new UsuarioDAOJDBC();
        List<UsuarioDTO> usuarios;
        usuarios = usuarioDAO.seleccionar();
        usuarios.forEach(System.out::println);

    }
}
