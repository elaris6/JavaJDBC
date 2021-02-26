package buenaspracticasjdbc.test;

import java.sql.*;

public class TestMysqlJDBC {

    public static void main(String[] args) {

        /* CONEXION TEST A MYSQL */

        /* Lo primero que se debe realizar es crear la cadena de conexión.
        * Esta cadena será diferente para cada gestor de BBDD e incluso puede
        * diferir entre versiones del mismo gestor de BBDD. */

        /* Dadas las mejoras de seguridad introducidas en las últimas versiones
        * de MySQL, es recomendable añadir parámetros adicionales en la cadena
        * de conexión:
        * ?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true */

        String url = "jdbc:mysql://localhost:3306/buenaspracticasjdbc.test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        /* Este bloque de código en principio ya no es requerido en las últimas versiones
        * de MySQL, pero algunas aplicaciones WEB pueden presentar problemas de no tenerlo.
        * En este caso no es necesario pues es una aplicación local. */
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


        try {
            /* Para crear la conexión es necesario crear un objeto de la clase "Connection", al
             * que habrá que pasar como parámetros la cadena de conexión, el usuario y la
             * contraseña a emplear. */
            Connection conexion = DriverManager.getConnection(url,"dbuser","AliNata0");

            /* Se debe crear un objeto de tipo "Statement" con la que se gestionará la
            * instrucción de SQL. */
            Statement instruccion = conexion.createStatement();

            /* Es una buena práctica crear la instrucción SQL en una variable específica. */
            var sql = "SELECT * FROM buenaspracticasjdbc.test.persona";

            /* Se almacenan los resultados devueltos por la ejecución de la instrucción
            * SQL en un objeto de tipo "ResultSet". */
            ResultSet resultado = instruccion.executeQuery(sql);

            /* Se puede recorrer el iterable "ResultSet" haciendo uso del método "next()",
            * que devolverá true, siempre que haya un siguiente resultado, y se posicionará
            * en éste. */
            while (resultado.next()){

                /* En caso de una instrucción SQL de tipo SELECT, se puede acceder a cada
                * columna recuperada haciendo uso de su índice (empezando por 1), o
                * directamente con el nombre de la columna. */
                System.out.print("Id_persona: " + resultado.getInt("id_persona"));
                System.out.print("\tNombre: " + resultado.getString("nombre"));
                System.out.print("\tApellido: " + resultado.getString("apellido"));
                System.out.print("\tEmail: " + resultado.getString("email"));
                System.out.print("\tTeléfono: " + resultado.getString("telefono"));
                System.out.println();
            }

            /* Es importante cerrar todos los objetos abiertos, debiendo hacerlo en orden
            * inverso a su apertura. */
            resultado.close();
            instruccion.close();
            conexion.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }

    }
}
