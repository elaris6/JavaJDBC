package transacciones.datos;

import java.sql.*;

/* CONEXIÓN */

/* Las buenas prácticas indican como recomendable crear una clase que sea la
* encarga de gestionar la conexión con la BBDD, tanto apertura como cierre.  */

public class Conexion {

    /* Es recomendable crear constantes ("public static final") para almacenar
    * los valores de la cadena de conexión, usuario y contraseña de conexión. */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "dbuser";
    private static final String JDBC_PASSWORD = "AliNata0";

    /* Método que gestiona la conexión. */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
    }

    /* Método para cerrar un objeto "ResultSet". */
    public static void close(ResultSet rs)  {
        try{
            rs.close();
        } catch (SQLException e){
            e.getErrorCode();
        }
    }

    /* Método para cerrar un objeto "Statement".
    * En estos métodos gestionamos el posible error en el propio método, en
    * lugar de indicar un "throws" en la firma del método, para que no deba
    * controlarse al llamar desde la clase DAO. Pero esto es opcional. */
    public static void close(Statement stm) {
        try{
            stm.close();
        } catch (SQLException e){
            e.getErrorCode();
        }
    }

    /* Método para cerrar un objeto "PreparedStatement". */
    public static void close(PreparedStatement pstm) {
        try{
            pstm.close();
        } catch (SQLException e){
            e.getErrorCode();
        }
    }

    /* Método para cerrar un objeto "Connection". */
    public static void close(Connection conn) {
        try{
            conn.close();
        } catch (SQLException e){
            e.getErrorCode();
        }
    }
}
