package domain.Seguridad;

public class usuarioContraDistintoNist implements ValidacionContra {

    public boolean validar(String unaContrasenia, String nombreUsuario) {
        return !(nombreUsuario.equals(unaContrasenia));
    }

    public String mensajeError(){
      return "La contrasenia debe ser distinta al nombre de usuario. ";
    }
}

