package transacciones.test;

import transacciones.datos.*;
import transacciones.domain.PersonaDTO;

import java.sql.*;
import java.util.List;

public class TestPersonasTransacciones {

    public static void main(String[] args) {

        /* Se crea un objeto "Connection", pues la conexión para transacciones se manejará
         * desde fuera del ámbito de datos, para que sea global.
         * Se debe crear el objeto fuera del bloque "try catch" */
        Connection conexion = null;
        try {
            /* Se crea un objeto "Connection", pues la conexión para transacciones se manejará
            * desde fuera del ámbito de datos, para que sea global. */
            conexion = Conexion.getConnection();

            /* Si consulta si la propiedad "autocommit" está activada y en tal caso se desactiva.
            * Si la propiedad "autocommit" está activada, se ejecuta un "commit" automático tras
            * cada sentencia SQL. */
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            /* Se instancia la clase de la entidad de datos, pero con el constructor que admite
            * un objeto de tipo "Connection", para que las sentencias se gestionen en modelo
            * TRANSACCION. */
            PersonaJDBC personaJDBC = new PersonaJDBC(conexion);

            /* Se ejecutan todas las transacciones de cambio de datos que sean requeridas dentro
            * del bloque "transacción". */
            PersonaDTO pIns = new PersonaDTO("Vicenta","Jaurena","mamita@gmail.com","666888999");
            personaJDBC.insertar(pIns);

            PersonaDTO pUpd = new PersonaDTO(2,"ISRA","Balboa","i.baljau@gmail.com","666303530");
            personaJDBC.actualizar(pUpd);

            /* En caso de ejecutarse sin problemas las sentencias de la transacción, se podrá
            * realizar un "commit" sobre la conexión, para consolidar los cambios en BBDD. */
            conexion.commit();
            System.out.println("Se ha ejecutado el commit del bloque transacción.");

        } catch (SQLException throwables) {
            /* Cada método de modificación debe propagar la excepción "SQLException" para que pueda
            * ser capturada por "catch" del bloque de transacción, pues en caso contrario, se
            * procesará la excepción dentro del método y no se podría ejecutar el rollback en
            * caso de fallo. */
            throwables.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            /* En caso de capturarse una excepción en alguna de las sentencias de la transacción,
            * se realiza un rollback de las sentencias realizadas hasta ese momento. */
            try {
                conexion.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        listarPersonas();

    }

    public static void listarPersonas(){
        // Listado personas de la BBDD
        PersonaJDBC personaJDBC = new PersonaJDBC();
        List<PersonaDTO> personas = null;
        try {
            personas = personaJDBC.seleccionar();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        personas.forEach(persona -> {
            System.out.println(persona);
        });
    }
}
