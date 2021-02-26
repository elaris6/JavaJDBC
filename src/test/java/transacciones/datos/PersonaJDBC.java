package transacciones.datos;

import transacciones.domain.PersonaDTO;

import java.sql.*;
import java.util.*;

public class PersonaJDBC {

    /* TRANSACCIONES */

    /* Se denomina transacciones SQL a un bloque de sentencias SQL ejecutadas de forma conjunta sobre
    * la misma conexión de BBDD.
    * El objetivo de las transacciones es poder controlar la grabación de los cambios para que se
    * produzca solo si no hay errores en el bloque completo. Para ello se debe hacer uso de las
    * funciones:
    *
    * - autocommit. Por defecto valor "true", deberá configurarse a "false.
    * - commit. Ejecutar cuando se confirme la correcta ejecución del bloque de sentencias.
    * - rollback. Junto con un bloque de código "tru catch", permitirá dar marcha atrás a las
    * sentencias si se produce algún error. */

    /* Siempre que se cierra una conexión JDBC se realiza un "commit" de forma automática, por lo que
    * si realizamos una conexión para cada sentencia, no habrá forma de realizar el control por
    * transacciones (bloques de sentencias). */

    /* Se crea un nuevo atributo de clase de tipo "Connection" que permitirá distinguir si se desea
    * realizar la ejecución por transacciones o de forma unitaria, conservando así ambas posibilidades. */

    private Connection conexionTransaccional;

    private static final String SQL_SELECT_ALL = "SELECT ID_PERSONA,NOMBRE,APELLIDO,EMAIL,TELEFONO FROM TEST.PERSONA";
    private static final String SQL_INSERT = "INSERT INTO TEST.PERSONA (NOMBRE, APELLIDO, EMAIL, TELEFONO) VALUES (?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM TEST.PERSONA WHERE ID_PERSONA = ?";
    private static final String SQL_UPDATE = "UPDATE TEST.PERSONA SET NOMBRE = ?, APELLIDO = ?, EMAIL = ?, TELEFONO = ? WHERE ID_PERSONA = ?";


    /* CONSTRUCTOR.
    * Se crean un constructor vacío, así como uno que permitirá instanciar la clase con nuevo atributo. */

    public PersonaJDBC(){}

    public PersonaJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    /* Para que se puedan controlar las exepciones SQL producidas en el bloque de sentencias SQL
    * de la transacción, cada método encargado de realizar las sentencias debe propagar las
    * posibles excepciones que ocurran, pues en caso contrario, se procesarán en el propio
    * método y el método invocador no podrá capturarlas para realizar el "rollback" si procede. */
    public List<PersonaDTO> seleccionar() throws SQLException{

        /* Es buena práctica crear todas las variables que serán requeridas durante
        * la instrucción. */
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();

        try {
            /* Se consulta si el atributo "conexionTransaccional" está inicializado, si lo está,
            * se toma como conexión y si no, se crea una nueva. */
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_SELECT_ALL);
            rs = pstm.executeQuery();
            /* En este caso al ser un SELECT, iteramos los resultados y los incluimos en un
            * ArrayList del tipo de la clase DAO. */
            while (rs.next()){
                persona = new PersonaDTO(rs.getInt("id_persona"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("email"),rs.getString("telefono"));
                personas.add(persona);
            };
        } finally {
                Conexion.close(rs);
                Conexion.close(pstm);
                /* Se consulta si el atributo "conexionTransaccional" está inicializado, si lo está
                * no se cierra la conexión para que no se haga un commit automático. Si no lo está,
                * se cierra pues significará que la hemos creado en el propio método. */
                if (this.conexionTransaccional == null) {Conexion.close(conn);}
        }
        return personas;
    }

    public int insertar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            /* Se consulta si el atributo "conexionTransaccional" está inicializado, si lo está,
             * se toma como conexión y si no, se crea una nueva. */
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_INSERT);
            /* En una instrucción SQL a la que sea necesario informar parámetros,
            * debemos usar los métodos "set" según el tipo de dato */
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setString(3, persona.getEmail());
            pstm.setString(4, persona.getTelefono());

            registros = pstm.executeUpdate();

        } finally {
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {Conexion.close(conn);}
        }
        return registros;
    }

    public int eliminar(PersonaDTO persona) throws SQLException{

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            /* Se consulta si el atributo "conexionTransaccional" está inicializado, si lo está,
             * se toma como conexión y si no, se crea una nueva. */
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_DELETE);
            /* En una instrucción SQL a la que sea necesario informar parámetros,
             * debemos usar los métodos "set" según el tipo de dato */
            pstm.setInt(1, persona.getIdPersona());

            registros = pstm.executeUpdate();

        } finally {
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {Conexion.close(conn);}
        }
        return registros;
    }

    public int actualizar(PersonaDTO persona) throws SQLException{

        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            /* Se consulta si el atributo "conexionTransaccional" está inicializado, si lo está,
             * se toma como conexión y si no, se crea una nueva. */
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_UPDATE);
            /* En una instrucción SQL a la que sea necesario informar parámetros,
             * debemos usar los métodos "set" según el tipo de dato */
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setString(3, persona.getEmail());
            pstm.setString(4, persona.getTelefono());
            pstm.setInt(5, persona.getIdPersona());

            registros = pstm.executeUpdate();

        } finally {
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {Conexion.close(conn);}
        }
        return registros;
    }
}
