package transacciones.domain;

/* DOMINIO O ENTIDAD */

/* Es recomendado crear una clase para cada dominio o entidad con la que se vaya
* a trabajar en la aplicación.
* En este caso, se crea una entidad que representa un registro de la tabla
* con la que se va a trabajar. */

public class PersonaDTO {

    /* Se crea un atributo de clase por cada campo que pueda tener el registro
    * de la tabla. */
    private int idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    /* Se crean tantos constructores como casos de uso se prevean necesarios. */
    public PersonaDTO() {
    }

    /* Constructor solo con id para el caso del delete (ejemplo). */
    public PersonaDTO(int idPersona) {
        this.idPersona = idPersona;
    }

    /* Constructor con todos los campos excepto id, para el caso del insert
    (ejemplo al ser el id un autoincremental). */
    public PersonaDTO(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    /* Constructor con todos los campos para update y select de cualquier tipo. */
    public PersonaDTO(int idPersona, String nombre, String apellido, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    /* Se debe generar getter y setter para acceder a cada atributo. */

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /* Se genera el método "toString()" para imprimir un registro recuperado. */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Persona{");
        sb.append("idPersona=").append(idPersona);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
