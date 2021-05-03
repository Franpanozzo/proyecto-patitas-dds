package Usuario;

public class DatoDeContacto {
    String nombreApellido;
    Integer telefono;
    String email;

   public DatoDeContacto(String nombreApellido, Integer telefono, String email){
        this.nombreApellido = nombreApellido;
        //TODO validacion formato del telefono
        this.telefono = telefono;
        //TODO validacion formato del eamil
        this.email = email;
    }
}
