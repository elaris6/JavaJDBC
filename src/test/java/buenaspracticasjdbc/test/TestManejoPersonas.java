package buenaspracticasjdbc.test;

import buenaspracticasjdbc.datos.PersonaDAO;
import buenaspracticasjdbc.datos.PersonaDAOJDBC;
import buenaspracticasjdbc.domain.PersonaDTO;

import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {

        PersonaDAO personaDAO = new PersonaDAOJDBC();

        // Insertamos un nuevo objeto
        PersonaDTO pIns = new PersonaDTO("Jose","Balboa","joseordago@gmail.com",null);
        System.out.println("Registros afectados: " + personaDAO.insertar(pIns));


        // Eliminando un objeto
        //Persona pDel = new Persona(6);
        //System.out.println("Registros eliminados: " + personaDAO.eliminar(pDel));

        // Actualizando un objeto
        //Persona pUpd = new Persona(3,"Alicia","Balboa","alicia.balcue@outlook.com","666777888");
        //System.out.println("Registros eliminados: " + personaDAO.actualizar(pUpd));

        listarPersonas();

    }

    public static void listarPersonas(){
        // Listado personas de la BBDD
        PersonaDAO personaDAO = new PersonaDAOJDBC();
        List<buenaspracticasjdbc.domain.PersonaDTO> personas = personaDAO.seleccionar();

        personas.forEach(persona -> {
            System.out.println(persona);
        });
    }
}
