package buenaspracticasjdbc.datos;

import buenaspracticasjdbc.domain.UsuarioDTO;

import java.sql.*;;
import java.util.*;

public class UsuarioDAOJDBC implements UsuarioDAO{

    /* Sentencias SQL requeridas */

    private static final String SQL_SELECT = "SELECT ID_USUARIO,USUARIO,PASSWORD FROM TEST.USUARIO";
    private static final String SQL_INSERT = "INSERT INTO TEST.USUARIO (USUARIO,PASSWORD) VALUES (?,?)";
    private static final String SQL_DELETE = "DELETE FROM TEST.USUARIO WHERE ID_USUARIO = ?";
    private static final String SQL_UPDATE = "UPDATE TEST.USUARIO SET USUARIO = ?, PASSWORD = ? WHERE ID_USUARIO = ?";

    /* Método SELECT  */
    public List<UsuarioDTO> seleccionar(){

        List<UsuarioDTO> usuarios = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        UsuarioDTO user;

        try{
            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_SELECT);
            rs = pstm.executeQuery();
            while(rs.next()){
                user = new UsuarioDTO(rs.getInt("id_usuario"),rs.getString("usuario"),rs.getString("password"));
                usuarios.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return usuarios;
    }

    /* Método INSERT  */
    public int insertar(UsuarioDTO user){

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try{

            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, user.getUsuario());
            pstm.setString(2, user.getPassword());
            registros = pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return registros;
    }

    /* Método DELETE */
    public int eliminar(UsuarioDTO user){

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try{

            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, user.getIdUsuario());
            registros = pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return registros;
    }

    /* Método UPDATE */
    public int actualizar(UsuarioDTO user){

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try{

            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, user.getUsuario());
            pstm.setString(2, user.getPassword());
            pstm.setInt(3, user.getIdUsuario());
            registros = pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return registros;
    }
}
