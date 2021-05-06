package SeguridadAlternativa;

public class usuarioContraDistintoNist implements ValidacionContra {

    public boolean validar(String unaContrasenia, String nombreUsuario) {
        return !(nombreUsuario.equals(unaContrasenia));
    }
}
