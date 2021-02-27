package poolconexiones.datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "dbuser";
    private static final String JDBC_PASS = "AliNata0";

    /* POOL DE CONEXIONES */

    /* La apertura de una nueva conexión consume bastantes recursos, por lo que si una aplicación necesita
    * mucho acceso a BBDD lo recomendable es que la gestión de estas conexiones se delegue en un sistema
    * que se dedique a gestionarlas reaprovechando los recursos siempre que sea posible. */

    /* Para realizar las conexiones mediante un pool de coenxiones es posible implementar dicho sistema
    * por uno mismo, pero existen ya implementaciones en librerías estándar. En este caso para el ejemplo
    * se usará la "commons-dbcp2" de Apache. */

    /* Se crea un atributo de tipo "BasicDataSource" */
    private static BasicDataSource dataSource;

    /* Se crea un método que inicialice el atributo "BasicDataSource".  */
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASS);
            /* Es importante no sobredimensionar el tamaño inicial del pool. Se debe adaptar a las
            * necesidades de la aplicación, aumentándolo si se detecta que es necesario, pues en caso
            * de sobredimensión se estarán desperdiciando recursos. */
            dataSource.setInitialSize(5);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        /* En lugar de crear directamente la conexión con "DriverManager", se obtiene del objeto
        * "BasicDataSource" que se inicializa en el método previo. */
        // return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
