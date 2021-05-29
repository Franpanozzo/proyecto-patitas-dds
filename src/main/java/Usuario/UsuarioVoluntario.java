package Usuario;

import Asociacion.Asociacion;
import Exceptions.UsuarioNoPerdioMascotaException;

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

  @Override
  public String getMailContacto() {
    throw new UsuarioNoPerdioMascotaException("Los usuarios voluntarios no pierden mascotas, por lo tanto no se los notifica");
  }
}




