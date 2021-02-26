package buenaspracticasjdbc.datos;

import buenaspracticasjdbc.domain.PersonaDTO;

import java.sql.SQLException;
import java.util.List;

/* Data Access Object - DAO */

/* Siguiendo el patrón de diseño DAO, se debe crear una clase de acceso a datos
* para cada entidad que maneje la aplicación.
* Esta clase será la encargada de realizar cualquier interacción con los datos
* de este tipo de dominio o entidad.
* Lo ideal es crear una interfaz con los métodos abstractos y posteriormente
* implementar las distintas clases necesarias según el tipo de gestión a
* realizar con esa entidad (BBDD, Ficheros, webservices...). */

public interface PersonaDAO {

    public List<PersonaDTO> seleccionar();

    public int insertar(PersonaDTO personaDTO);

    public int eliminar(PersonaDTO personaDTO);

    public int actualizar(PersonaDTO personaDTO);
}
