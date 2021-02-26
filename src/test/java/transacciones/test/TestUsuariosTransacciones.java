package transacciones.test;

import transacciones.datos.Conexion;
import transacciones.datos.UsuarioJDBC;
import transacciones.domain.UsuarioDTO;

import java.sql.*;
import java.util.*;


public class TestUsuariosTransacciones {

    public static void main(String[] args) {

        Connection conexion = null;


        try{
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()) conexion.setAutoCommit(false);
            System.out.println("AutoCommit = " + conexion.getAutoCommit());

            UsuarioJDBC usuarioJDBC = new UsuarioJDBC(conexion);

            // Insertamos varios registros
            UsuarioDTO uIns = new UsuarioDTO("user7","pwd111");
            usuarioJDBC.insertar(uIns);
            uIns = new UsuarioDTO("user8","pwd222");
            usuarioJDBC.insertar(uIns);
            uIns = new UsuarioDTO("user9","pwd333");
            usuarioJDBC.insertar(uIns);

            // Actualizamos un registro
            UsuarioDTO uUpd = new UsuarioDTO(1,"elaris666","0987654321");
            usuarioJDBC.actualizar(uUpd);

            // Borramos un registro
            UsuarioDTO uDel = new UsuarioDTO(2);
            usuarioJDBC.eliminar(uDel);

            conexion.commit();
            System.out.println("Se realiza commit del bloque transación");
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
            try {
                conexion.rollback();
                System.out.println("Se realiza rollback del bloque transación");
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        } finally {
            try {
                conexion.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        imprimirUsuarios();
    }

    public static void imprimirUsuarios(){
        List<UsuarioDTO> usuarios = new ArrayList<>();
        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
        try{
            usuarios = usuarioJDBC.seleccionar();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Imprimiendo listado usuarios:");
        usuarios.forEach(usuario -> {
            System.out.println(usuario);
        });
    }
}
