package domain.Seguridad;

public interface ValidacionContra {

    public boolean validar(String contrasenia, String nombreUsuario);

    //agegar q devuelve mensaje
    public String mensajeError();

}
