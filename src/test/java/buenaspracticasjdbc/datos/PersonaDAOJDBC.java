package buenaspracticasjdbc.datos;

/* Data Access Object - DAO */

/* Siguiendo el patrón de diseño DAO, se debe crear una clase de acceso a datos
* para cada entidad que maneje la aplicación.
* Esta clase será la encargada de realizar cualquier interacción con los datos
* de este tipo de dominio o entidad.
* Lo ideal es crear una interfaz con los métodos abstractos y posteriormente
* implementar las distintas clases necesarias según el tipo de gestión a
* realizar con esa entidad (BBDD, Ficheros, webservices...). */

import buenaspracticasjdbc.domain.PersonaDTO;

import java.sql.*;;
import java.util.*;

public class PersonaDAOJDBC implements PersonaDAO{

    /* SENTENCIAS SQL */
    /* Es una buena práctica crear las posibles sentencias requeridas como constantes
    * dentro de la clase DAO. */

    private static final String SQL_SELECT_ALL = "SELECT ID_PERSONA,NOMBRE,APELLIDO,EMAIL,TELEFONO FROM TEST.PERSONA";
    private static final String SQL_INSERT = "INSERT INTO TEST.PERSONA (NOMBRE, APELLIDO, EMAIL, TELEFONO) VALUES (?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM TEST.PERSONA WHERE ID_PERSONA = ?";
    private static final String SQL_UPDATE = "UPDATE TEST.PERSONA SET NOMBRE = ?, APELLIDO = ?, EMAIL = ?, TELEFONO = ? WHERE ID_PERSONA = ?";

    /* Se deberá generar un método por cada tipo de instrucción en BBDD a realizar. En
    * este caso, creamos uno para instrucciones SELECT y lo llamamos "seleccionar".  */
    public List<PersonaDTO> seleccionar(){

        /* Es buena práctica crear todas las variables que serán requeridas durante
        * la instrucción. */
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();

        try {
            /* Se realiza la operación completa deseada generando la conexión, instrucción
            * y set de resultados. */
            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_SELECT_ALL);
            rs = pstm.executeQuery();
            /* En este caso al ser un SELECT, iteramos los resultados y los incluimos en un
            * ArrayList del tipo de la clase DAO. */
            while (rs.next()){
                persona = new PersonaDTO(rs.getInt("id_persona"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("email"),rs.getString("telefono"));
                personas.add(persona);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        } finally {
                Conexion.close(rs);
                Conexion.close(pstm);
                Conexion.close(conn);
        }
        return personas;
    }

    public int insertar(PersonaDTO persona){
        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_INSERT);
            /* En una instrucción SQL a la que sea necesario informar parámetros,
            * debemos usar los métodos "set" según el tipo de dato */
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setString(3, persona.getEmail());
            pstm.setString(4, persona.getTelefono());

            registros = pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        } finally {
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return registros;
    }

    public int eliminar(PersonaDTO persona){

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_DELETE);
            /* En una instrucción SQL a la que sea necesario informar parámetros,
             * debemos usar los métodos "set" según el tipo de dato */
            pstm.setInt(1, persona.getIdPersona());

            registros = pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        } finally {
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return registros;
    }

    public int actualizar(PersonaDTO persona){

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_UPDATE);
            /* En una instrucción SQL a la que sea necesario informar parámetros,
             * debemos usar los métodos "set" según el tipo de dato */
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setString(3, persona.getEmail());
            pstm.setString(4, persona.getTelefono());
            pstm.setInt(5, persona.getIdPersona());

            registros = pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        } finally {
            Conexion.close(pstm);
            Conexion.close(conn);
        }
        return registros;
    }
}
