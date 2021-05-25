package Usuario;

import Asociacion.Asociacion;

public class UsuarioVoluntario extends Usuario {

  public UsuarioVoluntario(String nombreUsuario, String contrasenia, Asociacion asociacion) {
    super(nombreUsuario, contrasenia, asociacion);
  }

  public boolean mismoCodigoQR(String codigoQR) {
    return false;
  }

  public void aprobarPublicaciones() {
    asociacion.aprobarPublicaciones();
  }

}