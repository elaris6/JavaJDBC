package buenaspracticasjdbc.test;

import buenaspracticasjdbc.datos.UsuarioJDBC;
import buenaspracticasjdbc.domain.UsuarioDTO;

import java.util.List;

public class TestManejoUsuarios {

    public static void main(String[] args) {

        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();

        // Insertar usuarios
        //Usuario uIns = new Usuario("nata","g5690jy045tik3");
        //System.out.println("Registros insertados: "+usuarioDAO.insertar(uIns));

        // Eliminar usuarios
        UsuarioDTO uDel = new UsuarioDTO(4);
        System.out.println("Registros eliminados: "+ usuarioJDBC.eliminar(uDel));

        // Actualizar usuarios
        UsuarioDTO uUpd = new UsuarioDTO(1,"elaris6","1234567890");
        System.out.println("Registros actualizados: "+ usuarioJDBC.actualizar(uUpd));

        imprimirUsuarios();
    }

    public static void imprimirUsuarios(){

        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
        List<UsuarioDTO> usuarios;
        usuarios = usuarioJDBC.seleccionar();
        usuarios.forEach(System.out::println);

    }
}
